import os
import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
import warnings
warnings.simplefilter('ignore')

def read_net(filename: str) -> nx.Graph:
    cwd = os.getcwd()
    file_path = os.path.join(cwd, 'data', filename, filename+'.gml')
    G = nx.read_gml(file_path)
    return G

def plot_net(G: nx.Graph, comunities: list):
    np.random.seed(123)
    pos = nx.spring_layout(G)
    plt.figure(figsize=(9, 4))
    nx.draw_networkx_nodes(G, pos, node_size=50, cmap=plt.cm.RdYlBu, node_color=comunities)
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()

def toNCom(init_comm: dict):
    drez = {}
    for k, v in init_comm.items():
        if v in drez:
            drez[v].append(k)
        else: drez[v] = [k]
    return drez.values()


def greedyCommunitiesDetection(G: nx.Graph):
    # Input: a graph
    # Output: list of comunity index (for every node)

    communities = {node: i for i, node in enumerate(G.nodes())}
    improved = True

    while improved:
        improved = False

        mod_before = nx.algorithms.community.quality.modularity(G, toNCom(communities))

        for u, v in G.edges():
            if communities[u] != communities[v]:

                new_communities = communities.copy()
                for node in G.nodes():
                    if communities[node] == communities[v]:
                        new_communities[node] = communities[u]
                mod_after = nx.algorithms.community.modularity(G, toNCom(new_communities))

                if mod_after - mod_before > 0:
                    communities = new_communities
                    improved = True
                    break

    return list(communities.values())

def greedyCommunitiesDetectionByTool(G: nx.Graph):
    # Input: a graph
    # Output: list of comunity index (for every node)

    from networkx.algorithms import community

    communities_generator = community.girvan_newman(G)
    top_level_communities = next(communities_generator)
    sorted_communities = sorted(map(sorted, top_level_communities))
    communities = []
    for node in G.nodes:
        for i, community in enumerate(sorted_communities):
            if node in community:
                communities.append(i)
                break
    return communities

G = read_net("krebs")
comms = greedyCommunitiesDetection(G)
plot_net(G, comms)
quit()
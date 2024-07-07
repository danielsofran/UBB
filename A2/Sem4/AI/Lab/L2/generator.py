import os

from network import Network
import networkx as nx

def save(filename: str, network: Network):
    cwd = os.getcwd()
    filedir = os.path.join(cwd, 'data', filename)
    if not os.path.exists(filedir):
        os.makedirs(filedir)
    nx.write_gml(network.G, os.path.join(filedir, filename+'.gml'),
                 stringizer=lambda x: '')

    labels_file = os.path.join(filedir, 'classLabel'+filename+'.txt')
    real_file = os.path.join(filedir, 'real.dat')
    network.greedyCommunitiesDetectionNewmanGirvan()

    with open(labels_file, 'w') as f:
        for node in network.nodes:
            f.write(f"{node} {network.communities[node]}\n")

    communities = {}
    for node in network.nodes:
        if network.communities[node] in communities:
            communities[network.communities[node]].append(node)
        else:
            communities[network.communities[node]] = [node]
    with open(real_file, 'w') as f:
        for community in communities.values():
            for node in community:
                f.write(f"{node} ")
            f.write('\n')

def test1():
    G = nx.windmill_graph(3, 5)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 4
    save('windmill0', network)
    network.plot()

def test2():
    G = nx.windmill_graph(10, 20)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 11
    save('windmill1', network)
    network.plot()

def test3():
    G = nx.caveman_graph(7, 16)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 7
    save('caveman', network)
    network.plot()

def test4():
    G = nx.connected_caveman_graph(4, 18)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 4
    save('caveman_connected', network)
    network.plot()

def test5():
    G = nx.random_partition_graph([15, 13], 0.9, 0.05)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 2
    #network.greedyCommunitiesDetectionNewmanGirvan()
    save('prt0', network)
    network.plot()

def test6():
    G = nx.random_partition_graph([15, 13, 14], 0.87, 0.1)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 3
    #network.greedyCommunitiesDetectionNewmanGirvan()
    save('prt1', network)
    network.plot()

def test7():
    G = nx.random_partition_graph([15, 13, 14, 16], 0.99, 0.01)
    network = Network(G)
    network.title = 'generated'
    network.expected_communities_number = 4
    #network.greedyCommunitiesDetectionNewmanGirvan()
    save('prt2', network)
    network.plot()

test7()
quit()
import os
from functools import cached_property

import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
import warnings
from fractions import Fraction

warnings.simplefilter('ignore')


class Network:
    def __init__(self, G: nx.Graph = None):
        self.communities = None
        self.title = None
        self.G = G
        self.expected_communities_number = 0
        self.expected_communities = []
        self.__adjust_data()

    # constructor
    def __adjust_data(self):
        if self.G is not None:
            self.mat = nx.adjacency_matrix(self.G)
            self.E = nx.adjacency_matrix(self.G, dtype=int).todense()
            self.communities = [i for i in self.nodes]
            self.community_set = [{node} for node in self.G.nodes]
            self.nr_communities = self.nr_nodes
            self.A = list(dict(self.G.degree).values())
            self.edges_between_communities = []

            noduri = list(self.G.nodes)
            for u, v in self.G.edges:
                i = noduri.index(u)
                j = noduri.index(v)
                assert self.mat[i, j] == 1
                self.edges_between_communities.append({i, j})

    @cached_property
    def nr_nodes(self):
        return len(self.G.nodes)

    @property
    def nodes(self):
        return range(self.nr_nodes)

    @cached_property
    def nr_edges(self):
        return len(self.G.edges)

    # read the files of the graph from its folder
    def read(self, filename: str):
        cwd = os.getcwd()
        file_path = os.path.join(cwd, 'data', filename, filename + '.gml')
        self.G = nx.read_gml(file_path)
        self.__adjust_data()
        self.title = filename

        ans_path = os.path.join(cwd, 'data', filename, 'real.dat')
        with open(ans_path, 'r') as f:
            lines = f.readlines()
            if '' in lines:
                lines.remove('')
            self.expected_communities_number = len(lines)
            # index = 0
            # for line in lines:
            #     tokens = line.split(' ')
            #     try:
            #         tokens.remove('')
            #     except:
            #         pass
            #     for token in tokens:
            #         node = int(token)
            #         self.expected_communities[node] = index
            #     index += 1
        ans_path = os.path.join(cwd, 'data', filename, 'classLabel'+filename+'.txt')
        with open(ans_path, 'r') as f:
            for line in f.readlines():
                tokens = line.split(' ')
                if '' in tokens: tokens.remove('')
                node, comm = int(tokens[0]), int(tokens[1])
                self.expected_communities.append(comm-1)

    # plot the graph for the given communities
    def plot(self):
        np.random.seed(323)
        pos = nx.spring_layout(self.G)
        plt.figure(figsize=(9, 4))
        nx.draw_networkx_nodes(self.G, pos, node_size=50, cmap=plt.cm.Spectral, node_color=self.communities)
        nx.draw_networkx_edges(self.G, pos, alpha=0.3)
        plt.title(self.title)
        plt.show()

    # returns de DQ_max and its coresponding edge
    def __get_DQ_max(self) -> tuple:
        # get maxim DQ
        DQ_max = -1000000000  # delta Q
        edge_max = {-1, -2}  # muchia cu delta Q maxim

        # com_set_max = None
        # modularity = nx.algorithms.community.quality.modularity(self.G, self.community_set)

        for edge in self.edges_between_communities:
            i, j = tuple(edge)
            ci, cj = self.communities[i], self.communities[j]

            if ci != cj:
                eij = Fraction(self.E[ci, cj], 2 * self.nr_edges)
                ai = Fraction(self.A[ci], 2 * self.nr_edges)
                aj = Fraction(self.A[cj], 2 * self.nr_edges)
                DQ = 2 * (eij - ai * aj)

                # com_set = self.community_set.copy()
                # com_set[ci] = com_set[ci].union(com_set[cj])
                # com_set.pop(cj)
                # modularity_after = nx.algorithms.community.quality.modularity(self.G, com_set)
                # DQ = modularity_after - modularity
                if DQ > DQ_max:
                    DQ_max = DQ
                    edge_max = {i, j}
                    # com_set_max = com_set

        # self.community_set = com_set_max
        i, j = edge_max
        ci, cj = self.communities[i], self.communities[j]
        if ci < cj: return DQ_max, i, j
        return DQ_max, j, i

    # updates the auxiliary data for the communities in the graph
    def __update_data(self, edge: tuple):
        i, j = edge
        ci, cj = self.communities[i], self.communities[j]

        # Update E matrix: concatenate i row and col with j and delete j
        eij = self.E[ci, cj]
        for k in range(self.nr_communities):
            self.E[ci, k] += self.E[cj, k]
            self.E[k, ci] += self.E[k, cj]
        # self.E[ci, ci] -= eij - self.E[cj, cj]  # elimin dublurile
        # self.E[ci, ci] += 2 * self.E[cj, cj]

        self.E = np.delete(self.E, cj, 0)  # stergem linia componentei j
        self.E = np.delete(self.E, cj, 1)  # stergem coloana componentei j

        # Update A
        self.A[ci] = 0
        for k in range(self.nr_communities-1):
            self.A[ci] += self.E[ci, k]
        self.A.pop(cj)

    # replace the community  ci with cj and find auxiliary data
    def __update_communities_vector(self, ci: int, cj: int) -> tuple:
        predecesor_ci = False  # exista(ci - 1)
        succesor_ci = False  # exista(ci + 1)
        predecesor_cj = False  # exista(cj - 1) != ci, ci+1, ci-1
        succesor_cj = False  # exista(cj - 1) != ci, ci+1, ci-1

        for k in self.nodes:
            if self.communities[k] == cj:  # comunitatea j devine comunitatea i
                self.communities[k] = ci
            if self.communities[k] == ci - 1:
                predecesor_ci = True
            elif self.communities[k] == ci + 1:
                succesor_ci = True
            if self.communities[k] == cj - 1:
                predecesor_cj = True
            elif self.communities[k] == cj + 1:
                succesor_cj = True
        return predecesor_ci, succesor_ci, predecesor_cj, succesor_cj

    # shift the communities numbers so the numbers starts from 0 and are consecutive
    def __shift_communities_vector(self, ci: int, cj: int, predecesor_ci: bool, succesor_ci: bool, predecesor_cj: bool, succesor_cj: bool):
        if not predecesor_ci and ci != 0:  # comunitatea nu are predecesor si nici nu e prima comunitate
            for k in self.nodes:
                if self.communities[k] >= ci:
                    self.communities[k] -= 1  # decrementam comunitatile care nu respecta indicii matricei E
        elif min(self.communities) != 0:
            for k in self.nodes:
                self.communities[k] -= 1
        elif not succesor_ci and ci < self.nr_communities - 2:
            for k in self.nodes:
                if self.communities[k] > ci:
                    self.communities[k] -= 1  # decrementam comunitatile care nu respecta indicii matricei E
        elif (predecesor_cj and succesor_cj) or \
                (not predecesor_cj and cj == 0 and succesor_cj):  # in cazul in care cj nu are predecesor, ci nu are succesor sau invers
            #or (not succesor_cj and cj == self.nr_communities - 2 and predecesor_cj)\
            # j are si predecesor si succesor, dar j a disparut => de la succesor, decrementam
            # sau are doar succesor si e fix prima comunitate
            # daca nu are succesor dar are predecesor, nu ar trebui sa afecteze
            for k in self.nodes:
                if self.communities[k] > cj:
                    self.communities[k] -= 1

    # update the data of the communities
    def __update_communities(self, edge: tuple):
        i, j = edge
        ci, cj = self.communities[i], self.communities[j]

        predecesor_ci, succesor_ci, predecesor_cj, succesor_cj = self.__update_communities_vector(ci, cj)
        self.__shift_communities_vector(ci, cj, predecesor_ci, succesor_ci, predecesor_cj, succesor_cj)

        # self.communities[j] = self.communities[i]
        self.nr_communities -= 1
        self.edges_between_communities.remove({i, j})

    # check if the community vector is updated corectly
    def __asserts(self, edge: tuple):
        assert min(self.communities) == 0
        assert max(self.communities) == self.nr_communities - 1
        for k in range(self.nr_communities):
            assert k in self.communities
        for k in range(self.nr_communities, self.nr_nodes):
            assert k not in self.communities
        # assert np.sum(self.E) == self.nr_edges * 2

    # runs the NewmanNewman algorithm, given the number of the final communities
    def greedyCommunitiesDetectionNewmanNewmanWithNrCom(self, expected_comms: int = None):
        if expected_comms is not None:
            self.expected_communities_number = expected_comms

        k = self.nr_communities
        while self.nr_communities > self.expected_communities_number:
            # lastbug: (3,14), (25, 3)
            _, i, j = self.__get_DQ_max()
            edge = (i, j)
            # ci, cj = self.communities[i], self.communities[j]
            # if abs(ci - cj) > 1 and self.nr_communities < 10:
            #     print()
            # if edge == (24, 25):
            #     print()
            self.__update_data(edge)

            # if self.nr_communities == 5:
            #     print()
            self.__update_communities(edge)
            # self.__asserts(edge)
            # k -= 1
            # assert k == self.nr_communities

    # runs the NewmanNewman algorithm, finding the number of the final communities
    def greedyCommunitiesDetectionNewmanNewmanV2(self):
        # Q_array = [nx.algorithms.community.modularity(self.G, [{node for node in self.G.nodes}])]
        # Qmax = -1000000000
        # comm_Q_max = self.communities.copy()
        while self.nr_communities > 1:
            DQ, i, j = self.__get_DQ_max()
            # Q_array.append(Q_array[-1] + DQ)
            # if Q_array[-1] > Qmax:
            #     Qmax = Q_array[-1]
            #     comm_Q_max = self.communities.copy()
            if DQ <= 0:
                break
            edge = (i, j)
            if i<0 or j<0:
                continue
            # ci, cj = self.communities[i], self.communities[j]
            # if abs(ci - cj) > 1 and self.nr_communities < 10:
            #     print()
            # if edge == (24, 25):
            #     print()
            self.__update_data(edge)

            # if self.nr_communities == 5:
            #     print()
            self.__update_communities(edge)
            # self.__asserts(edge)
            # k -= 1
            # assert k == self.nr_communities
        # self.communities = comm_Q_max
        # self.nr_communities = len(set(self.communities))

    def greedyCommunitiesDetectionNewmanNewman(self):
        Q_array = [nx.algorithms.community.modularity(self.G, [{node for node in self.G.nodes}])]
        Qmax = -1000000000
        comm_Q_max = self.communities.copy()
        while self.nr_communities > 1:
            DQ, i, j = self.__get_DQ_max()
            Q_array.append(Q_array[-1] + DQ)
            if Q_array[-1] > Qmax:
                Qmax = Q_array[-1]
                comm_Q_max = self.communities.copy()
            edge = (i, j)
            if i<0 or j<0:
                continue
            self.__update_data(edge)
            self.__update_communities(edge)
        self.communities = comm_Q_max
        self.nr_communities = len(set(self.communities))

    # runs the NewmanGirvan algorithm from networkx
    def greedyCommunitiesDetectionNewmanGirvan(self):
        from networkx.algorithms import community
        communities_generator = community.girvan_newman(self.G)
        generated_community = None
        for i in range(self.expected_communities_number - 1):
            generated_community = next(communities_generator)
        # print(len(generated_community))

        self.communities = [0] * self.nr_nodes
        for comm_index, comm in enumerate(generated_community):
            for nod in comm:
                nod_index = list(self.G.nodes).index(nod)
                self.communities[nod_index] = comm_index
        # sorted_communities = sorted(map(sorted, generated_community))
        # self.communities = []
        # for node in self.G.nodes:
        #     for i, community in enumerate(sorted_communities):
        #         if node in community:
        #             self.communities.append(i)
        #             break

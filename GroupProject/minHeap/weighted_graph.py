import random
import min_heap
import timeit

#Undirected graph using an adjacency list
class WeightedGraph:

    def __init__(self, n):
        self.adj = {}
        for i in range(n):
            self.adj[i] = []

    def are_connected(self, node1, node2):
        for edge in self.adj[node1]:
            if edge[0] == node2:
                return True
        return False

    def adjacent_nodes(self, node):
        return self.adj[node]

    def add_node(self):
        self.adj[len(self.adj)] = []

    def add_edge(self, node1, node2, weight):
        for info in self.adj[node2]:
            if node1 == info[0]:
                return
        self.adj[node1].append((node2, weight))
        self.adj[node2].append((node1, weight))

    def w(self, node1, node2):
        for edge_info in self.adj[node1]:
            if node2 == edge_info[0]:
                return edge_info[1]

    def number_of_nodes(self):
        return len(self.adj)

def create_random_complete_graph(n,upper):
    G = WeightedGraph(n)
    for i in range(n):
        for j in range(n):
            if i != j:
                G.add_edge(i,j,random.randint(1,upper))
    return G

def graph_weight(G):
    total = 0
    for node in G.adj:
        for info in G.adj[node]:
            total += G.w(node, info[0])
    return total/2

def prim1(G):
    MST = WeightedGraph(G.number_of_nodes())
    marked = {}
    for i in range(G.number_of_nodes()):
        marked[i] = False
    marked[0] = True
    for _ in range(G.number_of_nodes() - 1):
        current_weight = 99999
        for node in G.adj:
            if not marked[node]:
                for info in G.adj[node]:
                    neighbour = info[0]
                    weight = info[1]
                    if weight < current_weight and marked[neighbour]:
                        current_weight = weight
                        current_source, current_destination = node, neighbour
        MST.add_edge(current_source, current_destination, current_weight)
        marked[current_source] = True
    return MST

def time_test(n, runs, prim):
    total = 0
    for _ in range(runs):
        G = create_random_complete_graph(n, 100)
        start = timeit.default_timer()
        prim(G)
        total += timeit.default_timer() - start
    return total/runs

def prim2(G):
    MST = WeightedGraph(G.number_of_nodes())
    marked = {}
    pred = {}
    for i in range(G.number_of_nodes()):
        marked[i] = False
    Q = min_heap.MinHeap([])
    for i in range(1, G.number_of_nodes()):
        Q.insert(min_heap.Element(i, 9999))
    Q.insert(min_heap.Element(0,0))
    while not Q.is_empty():
        v = Q.extract_min().value
        if v != 0:
            MST.add_edge(v, pred[v], G.w(v,pred[v]))
        marked[v] = True
        for edge_info in G.adj[v]:
            if edge_info[0] in Q.map:
                if edge_info[1] < Q.get_element_from_value(edge_info[0]).key: 
                    Q.decrease_key(edge_info[0], edge_info[1])
                    pred[edge_info[0]] = v
    return MST

G = WeightedGraph(8)
G.add_edge(3,5,0.18)
G.add_edge(1,7,0.21)
G.add_edge(6,7,0.25)
G.add_edge(0,2,0.29)
G.add_edge(0,7,0.31)
G.add_edge(0,1,0.32)
G.add_edge(3,4,0.34)
G.add_edge(4,5,0.40)
G.add_edge(4,7,0.46)
G.add_edge(0,6,0.51)
G.add_edge(4,6,0.51)
G.add_edge(0,5,0.60)
M = prim2(G)
print(M.adj)
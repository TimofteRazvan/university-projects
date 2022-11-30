from random import *
import math
import copy


class Graph:
    def __init__(self, n):
        self._in_vertices = {}
        self._out_vertices = {}
        self._cost = {}
        for i in range(n):
            self._in_vertices[i] = []
            self._out_vertices[i] = []

    def write_to_file(self):
        f = open("results.txt", 'a')
        costs = self.parse_costs()
        edges = self.parse_edges_redo()
        for ed in edges:
            line = str(ed[0]) + ' ' + str(ed[1]) + ' ' + str(costs[ed])
            f.write(line)
            f.write('\n')
        f.write('\n')
        f.close()

    def parse_vertices(self):
        for vertex in self._in_vertices:
            yield vertex

    # Parses the edges of the graph
    def parse_edges(self):
        for key in self._cost:
            yield key[0], key[1], self._cost[key]

    # Returns the number of vertices
    def get_vertices(self):
        return len(self._out_vertices)

    # Returns the number of edges
    def get_edges(self):
        return len(self._cost)

    def parse_vertices_redo(self):
        return list(self._out_vertices.keys())

    def parse_edges_redo(self):
        return list(self._cost.keys())

    def parse_costs(self):
        return self._cost

    def parse_in(self):
        return self._in_vertices

    def parse_out(self):
        return self._out_vertices

    def in_vertices(self, v):
        return self._in_vertices[v]

    def out_vertices(self, v):
        return self._out_vertices[v]

    def get_cost_redo(self, x, y):
        return self._cost[(x, y)]

    def is_vertex(self, v):
        print(type(v in self._out_vertices))
        return v in self._out_vertices

    def is_edge(self, x, y):
        return y in self._out_vertices[x]

    def add_edge(self, x, y, c):
        if not self.is_edge(x, y):
            self._in_vertices[y].append(x)
            self._out_vertices[x].append(y)
            self._cost[(x, y)] = c

    def add_vertex(self, v):
        if not self.is_vertex(v):
            if v == 0:
                self._in_vertices[0] = []
                self._out_vertices[0] = []
            else:
                keys = self.parse_vertices_redo()
                last = keys[len(keys) - 1]
                while last < v:
                    self._in_vertices[last + 1] = []
                    self._out_vertices[last + 1] = []
                    last += 1
                self._in_vertices[v] = []
                self._out_vertices[v] = []

    def delete_edge(self, x, y):
        if self.is_edge(x, y):
            del self._cost[(x, y)]
            self._out_vertices[x].remove(y)
            self._in_vertices[y].remove(x)

    def delete_vertex(self, v):
        if self.is_vertex(v):
            for p in self.out_vertices(v):
                del self._cost[(v, p)]
                self._in_vertices[p].remove(v)

            for p in self.in_vertices(v):
                del self._cost[(p, v)]
                self._out_vertices[p].remove(v)
            del self._out_vertices[v]
            del self._in_vertices[v]

    def get_cost(self, x, y):
        if self.is_edge(x, y):
            return self._cost[(x, y)]

    def set_cost(self, x, y, c):
        if self.is_edge(x, y):
            self._cost[(x, y)] = c

    def restore_graph(self, in_ver, out_ver, costs):
        self._in_vertices = copy.deepcopy(in_ver)
        self._out_vertices = copy.deepcopy(out_ver)
        self._cost = copy.deepcopy(costs)

    def number_edges(self):
        return len(self._cost)

    def number_vertices(self):
        return len(self._in_vertices)

    """
    Creates a MST with Prim's algorithm, then doubles the edges according to the given problem so we Eulerify it
    """

    def prim(self, v):
        tree = Graph(0)
        visited = [v]
        tree.add_vertex(v)
        print("Before add edge")
        for k in range(self.number_vertices() - 1):
            mincost = math.inf
            for i in visited:
                for j in self.out_vertices(i):
                    if j not in visited and self.get_cost_redo(i, j) < mincost:
                        mincost = self.get_cost_redo(i, j)
                        x = i
                        y = j
            # print(x)
            print(y)
            visited.append(y)
            try:
                tree.add_vertex(y)
            except:
                pass

            tree.add_edge(x, y, self.get_cost_redo(x, y))
            tree.add_edge(y, x, self.get_cost_redo(x, y))
        tree.print_euler_tour()
        return tree

    """
    def preorder_tree(self, start, tree):
        cycle=[start]
        # add start vertex to stack
        stack = [start]
        while stack:
            # the next vertex we visit will be the top of the stack
            v = stack[-1]
            neigh = tree.out_vertices(v)
            done = True
            for p in neigh:
                if p not in cycle:
                    # add to stack and visit neighbour
                    stack.append(p)
                    cycle.append(p)
                    done = False
                    break
            # all neighbours have been visited
            if done:
                del stack[-1]
        cycle.append(start)
        return cycle
    """

    """
    Algorithm using using nearest neighbour algorithm for finding the MST
    Here, visited keeps track of both the visited vertices and creates the cycle
    """

    def shortest_algorithm(self, start, visited):
        visited.append(start)
        v = start
        mincost = 0
        # We check if there are still vertices left unvisited
        while len(visited) != self.number_vertices():
            mini = math.inf
            x = -1
            # Then parse through the nearest vertex' neighbours
            for i in self.out_vertices(v):
                # Here, the vertex has not been visited and the cost is minimum, so minimum gets new value
                if i not in visited and self.get_cost_redo(v, i) < mini:
                    mini = self.get_cost_redo(v, i)
                    x = i
            # This is for the last edge only (wasn't caught by the general case)
            if len(visited) == self.number_vertices() - 1 and self.is_edge(v, start) and self.get_cost_redo(v,
                                                                                                            start) < \
                    mini:
                mini = self.get_cost_redo(v, start)
                x = start
            # If we found the next vertex
            if x != -1:
                mincost += mini
                visited.append(x)
                v = x
            # If we haven't found the next vertex, we find it here
            else:
                for i in self.parse_vertices_redo():
                    if i not in visited:
                        v = i
                        break
        # This completes the cycle
        if v != start:
            visited.append(start)
            mincost += self.get_cost_redo(v, start)
        return mincost

    """
    DFS function to count reachable vertices from V
    """

    def dfs_count(self, v, visited):
        count = 1
        visited[v] = True
        for i in self._out_vertices[v]:
            if not visited[i]:
                count = count + self.dfs_count(i, visited)
        return count

    """
    Checks the validity of the given edge for putting it in the Euler path
    Validated if:
    1) V is the only adjacent vertex of U
    2) If there are multiple adjacents, then U-V is not a bridge, which we check and work with in the "else"
    """

    def validate_edge(self, u, v):
        if len(self._out_vertices[u]) == 1:
            return True
        else:
            # Check vertices reachable from U
            visited = [False] * (self.number_vertices())
            count1 = self.dfs_count(u, visited)

            # Remove u,v then check if there are others reachable from U
            cost = self.get_cost(u, v)
            self.delete_edge(u, v)
            visited = [False] * (self.number_vertices())
            count2 = self.dfs_count(u, visited)

            self.add_edge(u, v, cost)

            # If count1 is greater, then the u,v edge is a bridge
            if count1 > count2:
                return False
            else:
                return True

    """
    Recursive function to print every path by checking the adjacent vertices and checking the validity of the edge
    If edge is valid and u-v is not removed, we print it as part of the path
    """

    def print_euler(self, u):
        for v in self._out_vertices[u]:
            if self.validate_edge(u, v):
                print("%d-%d " % (u, v)),
                self.delete_edge(u, v)
                self.print_euler(v)

    """
    This function is used to call the whole printing of the euler tour. It finds a vertex with odd degree, then starts
    printing the tour beginning from said vertex
    """

    def print_euler_tour(self):
        u = 0
        for i in range(self.number_vertices()):
            if len(self._out_vertices[i]) % 2 != 0:
                u = i
                break
        print("\n")
        self.print_euler(u)


class FileGraph():
    def __init__(self, n, m, graph, filename):
        self.graph = graph(n)
        self._filename = filename
        self.__loadFile(filename, m)

    def __loadFile(self, filename, m):
        f = open(filename, 'r')
        line = f.readline()
        for i in range(m):
            line = f.readline()
            line = line.strip().split(" ")
            source = int(line[0])
            target = int(line[1])
            cost = int(line[2])
            try:
                self.graph.add_edge(source, target, cost)
                self.graph.add_edge(target, source, cost)

            except:
                continue
        f.close()


class RandomGraph:
    def __init__(self, n, m, graph, filename):
        self.graph = graph(n)
        self.__newGraph(n, m, filename)

    def __newGraph(self, n, m, filename):
        if m > n * n:
            f = open(filename, 'w')
            f.write("Graph cannot be generated.")
            return
        i = 0
        while i < m:
            x = randrange(0, n)
            y = randrange(0, n)
            c = randint(0, 1000)
            if not self.graph.is_edge(x, y):
                self.graph.add_edge(x, y, c)
                i += 1
            self.graph.write_to_file()

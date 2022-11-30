import copy


# Basic exception class taught in FP
class GraphError(Exception):
    def __init__(self, message=""):
        super().__init__(message)


# Graph class
class Graph:
    # The graph is created, the in / out vertices being dictionaries of lists
    def __init__(self, n=0):
        self.__out_vertices = dict()
        self.__in_vertices = dict()
        self.__cost = dict()
        self.__visited = set()
        for i in range(0, n):
            self.__out_vertices[i] = list()
            self.__in_vertices[i] = list()

    def DFSUtil(self, v):
        """
        INCERCARE DEPTH-FIRST SEARCH
        """
        # Mark the current node as visited
        # and print it
        if v not in self.__visited:
            self.__visited.add(v)
            print(v, end=' ')

            # Recur for all the vertices
            # adjacent to this vertex
            for neighbour in self.__out_vertices[v]:
                if neighbour not in self.__visited:
                    self.DFSUtil(neighbour)

    def DFS(self):
        """
        ALSO DFS
        """
        # Call the recursive helper function
        # to print DFS traversal
        for v in self.__out_vertices:
            if v not in self.__visited:
                print("Component: ")
                self.DFSUtil(v)
                print("\n")

    # <<<<<PARSING>>>>>
    # Parses the vertices, shown during seminar
    def parse_vertices(self):
        for vertex in self.__out_vertices:
            yield vertex

    # Parses the edges of the graph
    def parse_edges(self):
        for key in self.__cost:
            yield key[0], key[1], self.__cost[key]

    # Parses the out vertices of the given vertex
    def parse_out(self, x):
        if not self.is_vertex(x):
            raise GraphError("Incorrect vertex")
        for y in self.__out_vertices[x]:
            yield y

    # Parses the in vertices of the given vertex
    def parse_in(self, x):
        if not self.is_vertex(x):
            raise GraphError("Incorrect vertex")
        for y in self.__in_vertices[x]:
            yield y

    # <<<<<CHECKS>>>>>
    # Checks if the given value is actually a vertex
    def is_vertex(self, x):
        print(type(x in self.__out_vertices))
        return x in self.__out_vertices

    # Checks if the given values are actually an edge
    def is_edge(self, x, y):
        return y in self.__out_vertices[x]

    # Returns the in degree of the given vertex
    def in_degree(self, x):
        if not self.is_vertex(x):
            raise GraphError("Incorrect vertex")
        return len(self.__in_vertices[x])

    # Returns the out degree of the given vertex
    def out_degree(self, x):
        if not self.is_vertex(x):
            raise GraphError("Incorrect vertex")
        return len(self.__out_vertices[x])

    # <<<<<ADD/DELETE>>>>>
    # Basic add function that adds an edge with a cost
    def add_edge(self, x, y, cost = 0):
        if self.is_edge(x, y):
            return
        self.__out_vertices[x].append(y)
        self.__out_vertices[y].append(x)
        self.__in_vertices[y].append(x)
        self.__in_vertices[x].append(y)
        self.__cost[(x, y)] = cost

    # Basic delete function that deletes the edge after it finds it
    def delete_edge(self, x, y):
        if not self.is_edge(x, y):
            raise GraphError("Incorrect edge")
        self.__out_vertices[x].remove(y)
        self.__out_vertices[y].remove(x)
        self.__in_vertices[y].remove(x)
        self.__in_vertices[x].remove(y)
        self.__cost.pop((x, y))

    # Basic add function that adds a vertex
    def add_vertex(self, x):
        if self.is_vertex(x):
            raise GraphError("Incorrect vertex")
        self.__out_vertices[x] = list()
        self.__in_vertices[x] = list()

    # Basic delete function that deletes a vertex
    def delete_vertex(self, x):
        if not self.is_vertex(x):
            raise GraphError("Incorrect vertex")
        for i in self.parse_vertices():
            if self.is_edge(x, i):
                self.__in_vertices[i].remove(x)
                self.__cost.pop((x, i))
            if self.is_edge(i, x):
                self.__out_vertices[i].remove(x)
                self.__cost.pop((i, x))
        self.__out_vertices.pop(x)
        self.__in_vertices.pop(x)

    # <<<<<GETTERS/SETTERS>>>>>
    # Returns the cost of an edge
    def get_cost(self, x, y):
        if not self.is_edge(x, y):
            raise GraphError("Incorrect edge")
        return self.__cost[(x, y)]

    # Sets the cost of an edge
    def set_cost(self, x, y, new_cost):
        if not self.is_edge(x, y):
            raise GraphError("Incorrect edge")
        self.__cost[(x, y)] = new_cost

    # Returns the number of vertices
    def get_vertices(self):
        return len(self.__out_vertices)

    # Returns the number of edges
    def get_edges(self):
        return len(self.__cost)

    # Copies the graph
    def __copy__(self):
        return copy.deepcopy(self)

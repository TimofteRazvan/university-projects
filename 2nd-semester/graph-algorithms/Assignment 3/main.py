import random
from sys import maxsize

from Graph import *

# Build function from a file, takes values from a file, splits them and then builds the graph using them
def build_graph_file(filename):
    try:
        file = open(filename, "rt")
        line = file.readline()
        n, m = line.split(maxsplit=1, sep=" ")
        graph = Graph(int(n))
        for i in range(int(m)):
            line = file.readline()
            x, y, cost = line.split(maxsplit=2, sep=" ")
            graph.add_edge(int(x), int(y), int(cost))
        file.close()
        return graph
    except FileNotFoundError as fe:
        print(fe)

# Writes the graph to a file
def write_graph_file(graph, filename):
    file = open(filename, "wt")
    file.write(str(graph.get_vertices()) + " " + str(graph.get_edges()) + "\n")
    for key in graph.parse_edges():
        file.write(str(key[0]) + " " + str(key[1]) + " " + str(key[2]) + "\n")
    file.close()

#def BellmanFordGraph(graph, src, dst):
    #BellmanFord(graph, src, dst)

# Builds a random graph using the random library
def build_random_graph(n, m):
    if n * n < m:
        print("Incorrect graph")
        return Graph()
    else:
        graph = Graph(n)
        for i in range(m):
            v1 = random.randrange(n)
            v2 = random.randrange(n)
            while graph.is_edge(v1, v2):
                v1 = random.randrange(n)
                v2 = random.randrange(n)
            graph.add_edge(v1, v2, random.randrange(1000))
        return graph

# Displays all the edges
def display_edges(graph):
    for vertex in graph.parse_vertices():
        print(str(vertex) + ": ", end="")
        for y in graph.parse_out(vertex):
            print(str(y) + " ", end="")
        print("\n")

# Displays the menu
def display_menu():
    print("\n==================================")
    print("<<<<<<<DISPLAYS>>>>>>>")
    print("1. Display vertices")
    print("2. Display edges")
    print("3. Display nr of vertices")
    print("4. Display nr of edges")
    print("5. Display in-degree")
    print("6. Display out-degree")
    print("7. Display out-edges")
    print("8. Display in-edges")
    print("<<<<<<ADD/DELETE>>>>>>")
    print("9. Add vertex")
    print("10. Delete vertex")
    print("11. Add edge")
    print("12. Delete edge")
    print("<<<<<<<<CHECKS>>>>>>>>")
    print("13. Get cost")
    print("14. Set cost")
    print("15. Check vertex")
    print("16. Check edge")
    print("<<<<<<<<FILES>>>>>>>>")
    print("17. Read from file a graph")
    print("18. Write to a file the graph")
    print("19. Generate two random graphs")
    print("<<<<<<<<DEPTH>>>>>>>>")
    print("20. Depth-first searches all connected components")
    print("<<<<<<<<<EXIT>>>>>>>>>")
    print("21. Exit")
    print("==================================\n")

# The main function. Infinite loop to make sure the file name given exists // Infinite loop to keep going through the
# options given // Every option is numeric & self-explanatory
def main():
    graph = None
    while not graph:
        filename = input("File: ")
        graph = build_graph_file(filename)
    while True:
        display_menu()
        option = input("Option: ")
        try:
            if option == "1":
                for vertex in graph.parse_vertices():
                    print(str(vertex) + " ", end="")
            elif option == "2":
                for edge in graph.parse_edges():
                    print("[" + str(edge[0]) + "," + str(edge[1]) + "]" + ", Worth: " + str(edge[2]) + "  ",
                          end="")
            elif option == "3":
                print(str(graph.get_vertices()))
            elif option == "4":
                print(str(graph.get_edges()))
            elif option == "5":
                value = input("Vertex: ")
                print(str(graph.in_degree(int(value))))
            elif option == "6":
                value = input("Vertex: ")
                print(str(graph.out_degree(int(value))))
            elif option == "7":
                value = input("Vertex: ")
                for vertex in graph.parse_out(int(value)):
                    print(str(vertex) + " ", end="")
            elif option == "8":
                value = input("Vertex: ")
                for vertex in graph.parse_in(int(value)):
                    print(str(vertex) + " ", end="")
            elif option == "9":
                value = input("Vertex: ")
                graph.add_vertex(int(value))
                print("Success!")
            elif option == "10":
                value = input("Vertex: ")
                graph.delete_vertex(int(value))
                print("Success!")
            elif option == "11":
                source = input("Source: ")
                target = input("Target: ")
                cost = input("Cost: ")
                graph.add_edge(int(source), int(target), int(cost))
                print("Success!")
            elif option == "12":
                source = input("Source: ")
                target = input("Target: ")
                graph.delete_edge(int(source), int(target))
                print("Success!")
            elif option == "13":
                source = input("Source: ")
                target = input("Target: ")
                print("Cost: " + str(graph.get_cost(int(source), int(target))))
            elif option == "14":
                source = input("Source: ")
                target = input("Target: ")
                cost = input("Cost: ")
                graph.set_cost(int(source), int(target), int(cost))
                print("Success!")
            elif option == "15":
                value = input("Vertex: ")
                print(str(graph.is_vertex(int(value))))
            elif option == "16":
                source = input("Source: ")
                target = input("Target: ")
                print(str(graph.is_edge(int(source), int(target))))
            elif option == "17":
                graph = None
                while graph is None:
                    filename = input("File: ")
                    graph = build_graph_file(filename)
                print("Success!")
            elif option == "18":
                filename = input("File: ")
                write_graph_file(graph, filename)
                print("Success!")
            elif option == "19":
                graph1 = build_random_graph(20, 10)
                graph2 = build_random_graph(6, 40)
                write_graph_file(graph1, "random_graph1.txt")
                write_graph_file(graph2, "random_graph2.txt")
                print("Success!")
            elif option == "20":
                graph.DFS()
            elif option == "21":
                exit()
            else:
                print("Invalid option!")
        except (GraphError, ValueError) as err:
            print(err)

def BellmanFord(graph, V, E, src, dst):
    # initializes dis[] array with infinite/maxsize values for src->everyVertex
    dis = [maxsize] * V
    # We use a prev[] array
    prev = [0 for i in range(V)]
    # initialize distance of source as 0
    dis[src] = 0
    # Bellman-Ford algorithm
    for i in range(V-1):
        for j in range(E):
            if dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]]:
                dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2]
                prev[graph[j][1]] = graph[j][0]

    for i in range(E):
        x = graph[i][0]
        y = graph[i][1]
        weight = graph[i][2]
        if dis[x] != maxsize and dis[x] + weight < dis[y]:
            print("Graph contains negative weight cycle")
            exit(1)

    print("Destination", end=" "), print(dst, end=" "), print("| Source", end=" "), print(src)
    for i in range(V):
        if i == dst:
            print("Minimum Cost: %d" % (dis[i]))


V = 5  # vertices
E = 8  # edges

# every edge has three values (x, y, w)
graph = [[0, 1, -1], [0, 3, 4], [1, 2, 3],
         [1, 3, 14], [0, 2, 2], [3, 1, 1],
         [2, 3, -6], [4, 3, -3]]
src = int(input("Source vertex = "))
dst = int(input("Destination vertex = "))
BellmanFord(graph, V, E, src, dst)

main()

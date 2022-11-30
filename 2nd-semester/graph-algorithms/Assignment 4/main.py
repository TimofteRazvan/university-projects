import random
from sys import maxsize
from Graph import *

"""
OK BOYS AND GIRLS HERE'S HOW WE DO THIS:
Pe scurt, pentru BREADTH (ia toate nOuts, dupa merge la nOuts pt nOuts):
- avem un while cu care ne asiguram ca parcurgem toate componentele posibile
- in while, parcurgem cu un for nOut-ul verticelui curent
- in for, verificam daca verticele conectat cu verticele curent a fost vizitat, si daca nu, il adaugam la componenta
si il bagam in lista de visited
- afisam componentele

Pentru depth-first, tre sa mearga la primu nOut, primu nOut din nOut etc.
Cum am facut DFS(6), dar pentru fiecare element care n-o fost bagat deja intr-o componenta
"""

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
    print("<<<<<<<<TREE>>>>>>>>")
    print("20. Minimum Tree Kruskal")
    print("<<<<<<<<<EXIT>>>>>>>>>")
    print("21. Exit")
    print("==================================\n")

# random_graph1.txt
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
                mintree = []
                mincost = graph.minTreeKruskal(mintree)
                print('Minimal cost:', mincost)
                print('Edges in minimal spanning tree:')
                for edge in mintree:
                    print(edge)
            elif option == "21":
                exit()
            else:
                print("Invalid option!")
        except (GraphError, ValueError) as err:
            print(err)


def BellmanFord(graph, V, E, src, dst):
    # Initialize distance of all vertices as infinite.
    matrix = [ [ 0 for i in range(V) ] for j in range(E) ]
    dis = [maxsize] * V
    prev = [ 0 for i in range(V) ]
    # initialize distance of source as 0
    dis[src] = 0
    iteration = 0
    changed = True
    while changed:
        changed = False
        for j in range(E):
            #print("[EDGE]", end=" "), print(j)
            #print(dis[graph[j][0]], end=" + "), print(graph[j][2], end=" < "), print(dis[graph[j][1]])
            if dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]]:
                dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2]
                prev[graph[j][1]] = graph[j][0]
                #matrix[j][graph[j][1]] = dis[graph[j][1]]
                changed = True
        print("Iteration:", end=" "), print(iteration)
        yes = False
        for i in range(V-1):
            for j in range(2):
                if graph[i][j] == dst:
                    print("Minimum cost walk so far:", end=" "), print(dis[graph[i][j]])
                    yes = True
                    break
            if yes:
                break
        iteration += 1


            # Relax all edges |V| - 1 times. A simple
    # shortest path from src to any other
    # vertex can have at-most |V| - 1 edges

    """
    
    for i in range(V - 1):
        print(src, end=" "), print("->", end=" "), print(i)
        for j in range(E):
            print("Length: ", end=""), print(j)
            if dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]]:
                dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2]
                matrix[j][i] = dis[graph[j][1]]
            print("Minimum cost walk:", end=" "), print(dis[graph[j][1]])


    # check for negative-weight cycles.
    # The above step guarantees shortest
    # distances if graph doesn't contain
    # negative weight cycle. If we get a
    # shorter path, then there is a cycle.
    for i in range(E):
        x = graph[i][0]
        y = graph[i][1]
        weight = graph[i][2]
        if dis[x] != maxsize and dis[x] + \
                weight < dis[y]:
            print("Graph contains negative weight cycle")
            
    """

    print("Destination", end=" "), print(dst, end=" "), print("| Source", end=" "), print(src)
    for i in range(V):
        if i == dst:
            print("Destination: %d Cost: %d" % (i, dis[i]))

    print(src, end=" "), print("->", end=" "), print(dst)
    for i in range(E):
        #for j in range(V):
            #if j == 0:
            #print ("|", end=" "), print("L: ", end=""), print(i, end=" C: "), print(matrix[i][j], end=" "), print ("|", end=" ")
        print("")


V = 5  # Number of vertices in graph
E = 8  # Number of edges in graph

# Every edge has three values (u, v, w) where
# the edge is from vertex u to v. And weight
# of the edge is w.
graph = [[0, 1, -1], [0, 3, 4], [1, 2, 3],
         [1, 3, 2], [1, 0, 2], [3, 2, 5],
         [3, 1, 1], [4, 3, -3]]
BellmanFord(graph, V, E, 0, 2)

main()

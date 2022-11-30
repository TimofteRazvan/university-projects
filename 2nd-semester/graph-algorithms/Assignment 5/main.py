from Graph import *

# Displays the menu
def display_menu():
    print("\n==================================")
    print("<<<<<<<DISPLAYS>>>>>>>")
    print("1. Display vertices")
    print("2. Display edges")
    print("3. Display nr of vertices")
    print("4. Display nr of edges")
    print("<<<<<<ADD/DELETE>>>>>>")
    print("5. Add vertex")
    print("6. Delete vertex")
    print("7. Add edge")
    print("8. Delete edge")
    print("<<<<<<<<CHECKS>>>>>>>>")
    print("9. Get cost")
    print("10. Set cost")
    print("<<<<<<<HAMILTONIAN>>>>>>")
    print("11. Show Hamiltonian Cycle with cost <=  2x minimum cost")
    print("12. Double Tree Approximation")
    print("<<<<<<<<<EXIT>>>>>>>>>")
    print("13. Exit")
    print("==================================\n")


# random_graph1.txt
# The main function. Infinite loop to make sure the file name given exists // Infinite loop to keep going through the
# options given // Every option is numeric & self-explanatory
def main():
    graph_type = input("Graph type: ")
    print("1. Random")
    print("2. File")
    if graph_type == "1":
        while True:
            n = randint(1, 10)
            m = randint(1, 40)
            if m <= n * (n - 1):
                break
        random = RandomGraph(n, m, Graph, "result.txt")
    else:
        f = open("graph.txt", "r")
        line = f.readline()
        line = line.strip().split(" ")
        n = int(line[0])
        m = int(line[1])
        random = FileGraph(n, m, Graph, "graph.txt")
    while True:
        display_menu()
        option = input("Option: ")
        if option == "1":
            for vertex in random.graph.parse_vertices():
                print(str(vertex) + " ", end="")
        elif option == "2":
            for edge in random.graph.parse_edges():
                print("[" + str(edge[0]) + "," + str(edge[1]) + "]" + ", Worth: " + str(edge[2]) + "  ", end="")

        elif option == "3":
            print(str(random.graph.get_vertices()))

        elif option == "4":
            print(str(random.graph.get_edges()))

        elif option == "5":
            value = input("Vertex: ")
            random.graph.add_vertex(int(value))
            random.graph.write_to_file()

        elif option == "6":
            vertex = int(input("Vertex: "))
            random.graph.delete_vertex(vertex)
            random.graph.write_to_file()

        elif option == "7":
            source = int(input("Source: "))
            target = int(input("Target: "))
            cost = int(input("Cost: "))
            random.graph.add_edge(source, target, cost)
            random.graph.write_to_file()

        elif option == "8":
            source = int(input("Source: "))
            target = int(input("Target: "))
            random.graph.delete_edge(source, target)
            random.graph.write_to_file()

        elif option == "9":
            source = int(input("Source: "))
            target = int(input("Target: "))
            print(random.graph.get_cost(source, target))

        elif option == "10":
            source = int(input("Source: "))
            target = int(input("Target: "))
            cost = int(input("Cost: "))
            random.graph.set_cost(source, target, cost)
            random.graph.write_to_file()

        elif option == "11":
            cycle = []
            mincost = random.graph.shortest_algorithm(0, cycle)
            print("A Hamiltonian cycle of low cost is:")
            print(cycle)
            print("Cost:", mincost)

        elif option == "12":
            print("Eulerian Tour: ")
            random.graph.prim(0)

        elif option == "13":
            exit()
        else:
            print("Invalid option!")

main()

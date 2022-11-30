class UI:
    def __init__(self, service):
        self.__serv = service

    def add_ride_ui(self):
        start_x = int(input("Starting X: "))
        start_y = int(input("Starting Y: "))
        end_x = int(input("Ending X: "))
        end_y = int(input("Ending Y: "))
        self.__serv.add_ride(start_x, start_y, end_x, end_y)

    def update_taxi_ui(self, id, new_x, new_y, new_fare):
        self.__serv.update_taxi_fare(id, new_x, new_y, new_fare)

    def display_taxis(self):
        taxis = self.__serv.get_taxi_list()
        for key in taxis:
            print(taxis[key])

    def simulate_rides_ui(self):
        number = int(input("Number of simulations: "))
        self.__serv.simulate_rides(number)

    def menu(self):
        print("=====================")
        print("1. Add ride")
        print("2. Simulate rides")
        print("=====================")

    def start(self):
        self.menu()
        number_of_taxis = int(input("Number of operational taxis: "))
        self.__serv.initialize_taxis(number_of_taxis)
        while True:
            try:
                option = int(input("Option: "))
                if option == 1:
                    self.add_ride_ui()
                elif option ==2:
                    self.simulate_rides_ui()
                elif option == 3:
                    self.display_taxis()
                else:
                    exit()
            except ValueError as ve:
                print(str(ve))

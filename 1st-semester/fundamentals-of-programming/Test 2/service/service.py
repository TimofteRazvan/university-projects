from random import randint

from domain.taxi import Taxi


class TaxiService:
    def __init__(self, repository):
        self.__repo = repository

    def create_taxi(self, id, x, y, fare):
        taxi = Taxi(id, x, y, fare)
        return taxi

    def add_taxi(self, taxi):
        self.__repo.add(taxi)

    def get_taxi(self, id):
        return self.__repo[id]

    def get_taxi_list(self):
        taxi_list = self.__repo.get_all()
        return taxi_list

    def update_taxi_fare(self, id, new_x, new_y, new_fare):
        taxi_to_update = self.__repo[id]
        taxi = self.create_taxi(id, new_x, new_y, new_fare)
        self.__repo.update(taxi)
        taxi_to_update.fare = new_fare
        taxi_to_update.x = new_x
        taxi_to_update.y = new_y

    def add_ride(self, start_x, start_y, end_x, end_y):
        taxis = self.get_taxi_list()
        start = start_x + start_y
        fare = (end_x + end_y) - start
        abs(fare)
        closest = -1000
        closest_id = 0
        for taxi in taxis:
            distance_to_ride = start - (taxis[taxi].x + taxis[taxi].y)
            if distance_to_ride > closest:
                closest = distance_to_ride
                closest_id = taxi
        self.update_taxi_fare(closest_id, end_x, end_y, fare)
        return taxis[closest_id]

    def simulate_rides(self, number):
        while number > 0:
            start_x = randint(0, 100)
            start_y = randint(0, 100)
            end_x = randint(0, 100)
            end_y = randint(0, 100)
            print(self.add_ride(start_x, start_y, end_x, end_y))
            number -= 1

    def initialize_taxis(self, number_of_taxis):
        id = 0

        if number_of_taxis >= 1:
            x = randint(0, 100)
            y = randint(0, 100)
            self.add_taxi(self.create_taxi(id, x, y, 0))
            number_of_taxis -= 1

        list_of_taxis = self.get_taxi_list()
        while number_of_taxis > 0:
            bad_distance = True
            x = 0
            y = 0
            while bad_distance:
                x = randint(0, 100)
                y = randint(0, 100)
                for taxi in list_of_taxis:
                    if (x + y) - (list_of_taxis[taxi].x + list_of_taxis[taxi].y) > 5 or \
                            (x + y) - (list_of_taxis[taxi].x + list_of_taxis[taxi].y) < 5:
                        bad_distance = False
            id += 1
            self.add_taxi(self.create_taxi(id, x, y, 0))
            number_of_taxis -= 1


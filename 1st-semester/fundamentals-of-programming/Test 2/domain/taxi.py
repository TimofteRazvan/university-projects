class Taxi:
    def __init__(self, id, x, y, fare):
        self.__id = id
        self.__x = x
        self.__y = y
        self.__fare = fare

    @property
    def id(self):
        return self.__id

    @property
    def x(self):
        return self.__x

    @property
    def y(self):
        return self.__y

    @property
    def fare(self):
        return self.__fare

    @fare.setter
    def fare(self, new_fare):
        self.__fare = new_fare

    @x.setter
    def x(self, new_x):
        self.__x = new_x

    @y.setter
    def y(self, new_y):
        self.__y = new_y

    def __str__(self):
        return "ID: " + str(self.__id) + ", X: " + str(self.__x) + ", Y: " + str(self.__y) + ", Fare: " + \
               str(self.__fare)

    def __repr__(self):
        return str(self)

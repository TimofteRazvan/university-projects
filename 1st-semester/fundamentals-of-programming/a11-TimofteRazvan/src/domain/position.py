class Position:
    def __init__(self, x, y, value):
        self.__x = x
        self.__y = y
        self.__value = value

    @property
    def x(self):
        return self.__x

    @property
    def y(self):
        return self.__y

    @property
    def value(self):
        return self.__value

    @value.setter
    def value(self, new_value):
        self.__value = new_value

    def __str__(self):
        if not self.__value:
            return "_"
        elif self.__value == 1:
            return "*"
        elif self.__value == 2:
            return "X"
        else:
            return "O"

    def __repr__(self):
        return str(self)

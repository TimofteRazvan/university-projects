class Discipline:
    """
    The Discipline class representing disciplines:
    - Each has an unique integer ID
    - The names can be identical
    - Names must exist (no empty strings)
    - Names can be numerical or contain special characters (for example Computer Science 101)
    - Contains getters for the id and name, setters for the name and also a string representation
    """
    def __init__(self, id, name):
        self.__id = id
        self.__name = name

    @property
    def id(self):
        return self.__id

    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, new_discipline):
        self.__name = new_discipline

    def __str__(self):
        return "ID: " + str(self.__id) + ", Name: " + self.__name

    def __repr__(self):
        return str(self)

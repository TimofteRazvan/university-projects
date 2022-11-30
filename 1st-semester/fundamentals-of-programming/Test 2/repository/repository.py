from repository.repositoryException import RepositoryException


class TaxiRepository:
    def __init__(self):
        self.__data = dict()

    def add(self, taxi):
        if taxi.id in self.__data:
            raise RepositoryException("Entity with ID exists already")
        self.__data[taxi.id] = taxi

    def update(self, taxi):
        try:
            self.__data[taxi.id] = taxi
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

    def delete(self, id):
        try:
            del self.__data[id]
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

    def get_all(self):
        """
        Returns the disciplines with a simple return repo function
        :return: returns the disciplines dictionaries
        """
        taxis = self.__data
        return taxis

    def __getitem__(self, item):
        try:
            return self.__data[item]
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")
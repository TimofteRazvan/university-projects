from src.repository.repositoryException import RepositoryException


class DisciplineRepo:
    def __init__(self):
        self.__data = {}

    def add(self, discipline):
        """
        Adds a new discipline to the repo
        :param discipline: is a Discipline object
        """
        if discipline.id in self.__data:
            raise RepositoryException("Entity with ID exists already")
        self.__data[discipline.id] = discipline

    def update(self, discipline):
        """
        Updates an existing discipline, selected using the ID of the to-be-updated discipline
        :param discipline: is a Discipline object
        """
        try:
            self.__data[discipline.id] = discipline
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

    def delete(self, id):
        """
        Deletes a discipline from the repo using del, the KEY being the ID
        :param id: is an integer (checks if there is a discipline.id that is valid)
        """
        try:
            del self.__data[id]
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

    def get_all(self):
        """
        Returns the disciplines with a simple return repo function
        :return: returns the disciplines dictionaries
        """
        disciplines = self.__data
        return disciplines

    def __len__(self):
        return len(self.__data)

    def __getitem__(self, item):
        try:
            return self.__data[item]
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

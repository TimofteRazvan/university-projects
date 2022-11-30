from src.repository.repositoryException import RepositoryException


class StudentRepo:
    def __init__(self):
        self.__data = {}

    def add(self, student):
        """
        Adds a new student to the repo
        :param student: is a Student object
        """
        if student.id in self.__data:
            raise RepositoryException("Entity with ID exists already")
        self.__data[student.id] = student

    def update(self, student):
        """
        Updates an existing student, selected using the ID of the to-be-updated student
        :param student: is a Student object
        """
        try:
            self.__data[student.id] = student
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

    def delete(self, id):
        """
        Deletes a student from the repo using del, the KEY being the ID
        :param id: is an integer (checks if there is a student.id that is valid)
        """
        try:
            del self.__data[id]
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

    def get_all(self):
        """
        Returns the students with a simple return repo function
        :return: returns the students dictionaries
        """
        students = self.__data
        return students

    def __len__(self):
        return len(self.__data)

    def __getitem__(self, item):
        try:
            return self.__data[item]
        except KeyError:
            raise RepositoryException("Entity with ID non-existent")

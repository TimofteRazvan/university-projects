from src.repository.repositoryException import RepositoryException


class GradeRepo:
    def __init__(self):
        self.__data = list()

    def add(self, grade):
        try:
            self.__data.append(grade)
        except ValueError:
            raise RepositoryException("Invalid index")

    def delete(self, index):
        self.__data.pop(index)

    def get_all(self):
        grades = self.__data
        return grades

    def __len__(self):
        return len(self.__data)

    def __getitem__(self, item):
        try:
            return self.__data[item]
        except ValueError:
            raise RepositoryException("Invalid index")

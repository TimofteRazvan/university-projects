from src.domain.position import Position
from src.exceptions.repositoryException import RepositoryException


class BoardRepository:
    def __init__(self, size):
        self.__size = size
        self.__board = [[Position(i, j, 0) for i in range(self.__size)] for j in range(self.__size)]

    def set_board_value(self, x, y, value):
        try:
            self.__board[x][y] = value
        except IndexError:
            raise RepositoryException("No such position")

    def get_board(self):
        board = self.__board
        return board

    def get_size(self):
        size = self.__size
        return size

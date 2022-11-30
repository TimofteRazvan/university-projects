from random import randint

from src.domain.position import Position


class BoardService:
    def __init__(self, repo):
        self.__repo = repo
        self.__board = self.get_board_service()
        self.__size = self.get_board_size()

    def create_position(self, x, y, value):
        if x >= self.__size or y >= self.__size or x < 0 or y < 0:
            self.force_punishment()
            raise ValueError("Outside the board!")

        if self.__board[x][y].value > 0:
            self.force_punishment()
            raise ValueError("Already occupied!")
        position = Position(x, y, value)
        return position

    def add_board_value(self, position):
        self.__repo.set_board_value(position.x, position.y, position)
        self.fill_neighbours(position.x, position.y)

    def get_board_service(self):
        board = self.__repo.get_board()
        return board

    def get_board_size(self):
        size = self.__repo.get_size()
        return size

    def fill_neighbours(self, x, y):
        # Yea, I know this looks bad
        if y < self.__size - 1:
            if self.__board[x][y + 1].value == 0:
                self.__board[x][y + 1].value = 1
            if x < self.__size - 1:
                if self.__board[x + 1][y + 1].value == 0:
                    self.__board[x + 1][y + 1].value = 1
                if self.__board[x + 1][y].value == 0:
                    self.__board[x + 1][y].value = 1
            if x > 0:
                if self.__board[x - 1][y + 1].value == 0:
                    self.__board[x - 1][y + 1].value = 1
                if self.__board[x - 1][y].value == 0:
                    self.__board[x - 1][y].value = 1
        if y > 0:
            if self.__board[x][y - 1].value == 0:
                self.__board[x][y - 1].value = 1
            if x < self.__size - 1:
                if self.__board[x + 1][y - 1].value == 0:
                    self.__board[x + 1][y - 1].value = 1
                if self.__board[x + 1][y].value == 0:
                    self.__board[x + 1][y].value = 1
            if x > 0:
                if self.__board[x - 1][y - 1].value == 0:
                    self.__board[x - 1][y - 1].value = 1
                if self.__board[x - 1][y].value == 0:
                    self.__board[x - 1][y].value = 1

    def ai_move(self):
        x = randint(0, self.__size - 1)
        y = randint(0, self.__size - 1)
        while self.__board[x][y].value > 0:
            x = randint(0, self.__size - 1)
            y = randint(0, self.__size - 1)
        position = self.create_position(x, y, 3)
        self.__repo.set_board_value(x, y, position)
        self.fill_neighbours(x, y)

    def is_board_full(self):
        for i in range(self.__size):
            for j in range(self.__size):
                if not self.__board[i][j].value:
                    return False
        return True

    def force_punishment(self):
        for i in range(self.__size):
            for j in range(self.__size):
                self.__board[i][j].value = 3
            self.__board[0][0].value = 0

import unittest

from src.domain.position import Position
from src.repository.repository import BoardRepository
from src.service.service import BoardService


class BoardRepoTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = BoardRepository(3)
        self.__repo.set_board_value(0, 0, 2)
        self.__repo.set_board_value(2, 2, 3)

    def test_get_add(self):
        size = 3
        board = self.__repo.get_board()
        board_test = [[Position(i, j, 0) for i in range(size)] for j in range(size)]
        self.assertNotEqual(board, board_test)
        self.assertEqual(self.__repo.get_size(), size)
        self.assertEqual(board[0][0], Position(0, 0, 2).value)
        self.assertEqual(board[2][2], Position(2, 2, 3).value)
        self.assertEqual(str(board[0][0]), str(Position(0, 0, 2).value))
        self.assertEqual(str(Position(0, 0, 2)), 'X')
        self.assertEqual(str(Position(2, 2, 3)), 'O')

    def tearDown(self) -> None:
        pass


class BoardServTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__serv = BoardService(BoardRepository(3))
        self.__serv.add_board_value(Position(0, 0, 2))

    def test_create_add(self):
        board = self.__serv.get_board_service()
        pos_2_2_ai = self.__serv.create_position(2, 2, 3)
        self.__serv.add_board_value(pos_2_2_ai)
        self.assertEqual(pos_2_2_ai, board[2][2])

    def test_size(self):
        size = self.__serv.get_board_size()
        self.assertEqual(size, 3)

    def test_fill(self):
        board = self.__serv.get_board_service()
        self.assertEqual(board[1][1].value, 1)
        self.assertEqual(board[0][1].value, 1)
        self.assertEqual(board[1][0].value, 1)
        self.assertEqual(str(board[0][1]), '*')

    def test_full(self):
        condition = self.__serv.is_board_full()
        self.assertEqual(condition, False)
        self.__serv.add_board_value(Position(2, 2, 1))
        self.__serv.add_board_value(Position(0, 2, 2))
        self.__serv.add_board_value(Position(2, 0, 1))
        condition = self.__serv.is_board_full()
        self.assertEqual(condition, True)

    def test_punishment(self):
        board = self.__serv.get_board_service()
        with self.assertRaises(ValueError):
            self.__serv.create_position(0, 0, 3)
        self.assertEqual(board[0][0].value, 0)

    def tearDown(self) -> None:
        pass


unittest.main()

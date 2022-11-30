class UI:
    def __init__(self, serv):
        self.__serv = serv

    def add_board_value_ui(self):
        try:
            x = int(input("X: "))
            y = int(input("Y: "))
            value = 2
            position = self.__serv.create_position(y, x, value)
            self.__serv.add_board_value(position)
        except ValueError as ve:
            print(str(ve))
            return

    def display_board(self):
        board = self.__serv.get_board_service()
        size = self.__serv.get_board_size()
        for i in range(size):
            display = []
            for j in range(size):
                display.append(str(board[i][j]))
            print(display)
        print('\n')

    def start(self):

        is_human_turn = True
        try:
            player = int(input("Player 1 or Player 2? "))
            if player == 1:
                is_human_turn = True
            elif player == 2:
                is_human_turn = False
            else:
                print("There can only be 2 players!")
                exit()
        except ValueError as ve:
            print(str(ve))
        self.display_board()
        while not self.__serv.is_board_full():
            try:
                if is_human_turn:
                    self.add_board_value_ui()
                    self.display_board()
                else:
                    self.__serv.ai_move()
                    self.display_board()
                is_human_turn = not is_human_turn
            except ValueError as ve:
                print(str(ve))

        if self.__serv.is_board_full():
            if is_human_turn:
                print("You lose!")
                exit()
            print("You win!")
            exit()

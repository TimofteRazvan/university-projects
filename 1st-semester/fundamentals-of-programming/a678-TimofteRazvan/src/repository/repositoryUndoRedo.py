class UndoRedoRepoException(Exception):
    pass


class UndoRedoRepo:
    def __init__(self):
        self.__list_of_actions = list()
        self.__index = 0

    def add_action(self, action):
        self.__list_of_actions = self.__list_of_actions[:self.__index]
        self.__list_of_actions.append(action)
        self.__index += 1

    def undo(self):
        if self.__index == 0:
            raise UndoRedoRepoException("Can't undo anymore")
        self.__index -= 1
        self.__list_of_actions[self.__index].execute_undo()

    def redo(self):
        if self.__index == len(self.__list_of_actions):
            raise UndoRedoRepoException("Can't redo anymore")
        self.__list_of_actions[self.__index].execute_redo()
        self.__index += 1

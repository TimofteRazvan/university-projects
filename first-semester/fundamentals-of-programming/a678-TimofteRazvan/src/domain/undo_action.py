class AbstractUndoAction:
    def execute_undo(self):
        pass

    def execute_redo(self):
        pass


class UndoAction(AbstractUndoAction):
    def __init__(self, action_code, param, reverse_action_code, reverse_param, repo):
        self.__action_code = action_code
        self.__param = param
        self.__reverse_action_code = reverse_action_code
        self.__reverse_param = reverse_param
        self.__repo = repo

    def execute_undo(self):
        self.__action_code(self.__repo, self.__param)

    def execute_redo(self):
        self.__reverse_action_code(self.__repo, self.__reverse_param)


class ComplexUndoAction(AbstractUndoAction):
    def __init__(self):
        self.__list_of_actions = []

    @property
    def list_of_actions(self):
        return self.__list_of_actions

    def add_action(self, action):
        self.__list_of_actions.append(action)

    def execute_undo(self):
        for i in reversed(range(len(self.__list_of_actions))):
            self.__list_of_actions[i].execute_undo()

    def execute_redo(self):
        for i in reversed(range(len(self.__list_of_actions))):
            self.__list_of_actions[i].execute_redo()
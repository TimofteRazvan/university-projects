class UndoRedoService:
    def __init__(self, undo_redo_repository):
        self.__undo_redo_repo = undo_redo_repository

    def undo(self):
        self.__undo_redo_repo.undo()

    def redo(self):
        self.__undo_redo_repo.redo()

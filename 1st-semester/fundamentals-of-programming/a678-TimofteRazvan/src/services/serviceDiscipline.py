from copy import deepcopy
from random import shuffle

from src.domain.discipline import Discipline
from src.domain.undo_action import UndoAction
from src.repository.repositoryDiscipline import DisciplineRepo
from src.validation.validators import DisciplineValidator


class DisciplineService:
    def __init__(self, repository, undo_redo_repository):
        self.__repo = repository
        self.__undo_redo_repo = undo_redo_repository

    def add_discipline(self, discipline):
        """
        Adds to the discipline repo via the repo function add()
        Also adds the action, the id of the added discipline, the reverse action and the discipline added to
        the undo_redo repo
        :param discipline: is a Discipline object
        """
        self.__repo.add(discipline)
        undo_action = UndoAction(DisciplineRepo.delete, discipline.id, DisciplineRepo.add, discipline, self.__repo)
        self.__undo_redo_repo.add_action(undo_action)

    def create_discipline(self, id, name):
        discipline = Discipline(id, name)
        DisciplineValidator.validate_discipline(discipline)
        return discipline

    def get_discipline(self, id):
        return self.__repo[id]

    def get_discipline_list(self):
        """
        Returns the disciplines via the repo function get_all()
        :return: returns the discipline dictionaries
        """
        discipline_list = self.__repo.get_all()
        return discipline_list

    def update_discipline(self, id, new_discipline):
        """
        Using the given ID and the repo function update(), changes the name of the discipline
        Also adds the action, reverse action, the former discipline name and the new discipline to the undo_redo repo
        :param id: is an integer representing the ID
        :param new_discipline: is a string representing the new discipline name
        """
        discipline_to_update = self.get_discipline(id)
        discipline_before_update = deepcopy(discipline_to_update)
        discipline = self.create_discipline(id, new_discipline)
        self.__repo.update(discipline)
        discipline_to_update.name = new_discipline
        undo_action = UndoAction(DisciplineRepo.update, discipline_before_update, DisciplineRepo.update,
                                 discipline_to_update, self.__repo)
        self.__undo_redo_repo.add_action(undo_action)

    def delete_discipline(self, id):
        """
        Deletes the discipline with the given ID and the repo function delete()
        Also adds the action, reverse action, the former discipline with the ID and the ID itself to the undo_redo repo
        :param id: integer value representing the ID
        """
        undo_action = UndoAction(DisciplineRepo.add, self.__repo[id], DisciplineRepo.delete, id, self.__repo)
        self.__undo_redo_repo.add_action(undo_action)
        self.__repo.delete(id)

    def search_discipline(self, string):
        results = []
        for discipline in self.__repo.get_all():
            if str(string).lower() in str(self.__repo[discipline]).lower():
                results.append(self.__repo[discipline])
        return results

    def generate_discipline(self):
        d1 = "English"
        d2 = "Romanian"
        d3 = "Fundamentals of Programming"
        d4 = "Computer Science"
        d5 = "Architecture"
        d6 = "HTML"
        d7 = "Algebra"
        d8 = "Mathematical Analysis"
        d9 = "Communication & Professional Development"
        d10 = "Computational Logic"
        discipline_list = [d1, d2, d3, d4, d5, d6, d7, d8, d9, d10]
        shuffle(discipline_list)
        id = 100
        for discipline in discipline_list:
            id += 1
            self.add_discipline(Discipline(id, discipline))


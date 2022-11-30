from copy import deepcopy
from random import shuffle

from src.domain.student import Student
from src.domain.undo_action import UndoAction
from src.repository.repositoryStudent import StudentRepo
from src.validation.validators import StudentValidator


class StudentService:
    def __init__(self, repository, undo_redo_repository):
        self.__repo = repository
        self.__undo_redo_repo = undo_redo_repository

    def add_student(self, student):
        """
        Adds to the student repo via the repo function add()
        Also adds the action, the id of the added student, the reverse action and the student added to
        the undo_redo repo
        :param student: is a Student object
        """
        self.__repo.add(student)
        undo_action = UndoAction(StudentRepo.delete, student.id, StudentRepo.add, student, self.__repo)
        self.__undo_redo_repo.add_action(undo_action)

    def create_student(self, id, name):
        student = Student(id, name)
        StudentValidator.validate_student(student)
        return student

    def get_student(self, id):
        return self.__repo[id]

    def get_student_list(self):
        """
        Returns the student via the repo function get_all()
        :return: returns the student dictionaries
        """
        student_list = self.__repo.get_all()
        return student_list

    def update_student(self, id, new_name):
        """
        Using the given ID and the repo function update(), changes the name of the student
        Also adds the action, reverse action, the former student name and the new student to the undo_redo repo
        :param id: is an integer representing the ID
        :param new_name: is a string representing the new student name
        """
        student_to_update = self.get_student(id)
        student_before_update = deepcopy(student_to_update)
        student = self.create_student(id, new_name)
        self.__repo.update(student)
        student_to_update.name = new_name
        undo_action = UndoAction(StudentRepo.update, student_before_update, StudentRepo.update, student_to_update,
                                 self.__repo)
        self.__undo_redo_repo.add_action(undo_action)

    def delete_student(self, id):
        """
        Deletes the student with the given ID and the repo function delete()
        Also adds the action, reverse action, the former student with the ID and the ID itself to the undo_redo repo
        :param id: integer value representing the ID
        """
        undo_action = UndoAction(StudentRepo.add, self.__repo[id], StudentRepo.delete, id, self.__repo)
        self.__undo_redo_repo.add_action(undo_action)
        self.__repo.delete(id)

    def search_student(self, string):
        results = []
        for student in self.__repo.get_all():
            if str(string).lower() in str(self.__repo[student]).lower():
                results.append(self.__repo[student])
        return results

    def generate_students(self):
        n1 = "Timofte Razvan"
        n2 = "Girjaliu Robert"
        n3 = "Vali Uraganul"
        n4 = "Yuri Boyka"
        n5 = "Dwayne Johnson"
        n6 = "Solid Snake"
        n7 = "Jason Bourne"
        n8 = "Andrei Popa"
        n9 = "Joe Biden"
        n10 = "Richard III"
        student_list = [n1, n2, n3, n4, n5, n6, n7, n8, n9, n10]
        shuffle(student_list)
        id = 100
        for name in student_list:
            id += 1
            self.add_student(Student(id, name))

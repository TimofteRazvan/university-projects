from src.domain.grade import Grade
from src.domain.undo_action import UndoAction
from src.repository.repositoryGrade import GradeRepo
from src.validation.validators import GradeValidator


class GradeService:
    def __init__(self, grade_repository, discipline_repository, student_repository, undo_redo_repository):
        self.__grade_repo = grade_repository
        self.__discipline_repo = discipline_repository
        self.__student_repo = student_repository
        self.__undo_redo_repo = undo_redo_repository
        self.__grade_counter = 0

    def add_grade(self, grade):
        self.__grade_repo.add(grade)
        self.__grade_counter += 1
        undo_action = UndoAction(GradeRepo.delete, self.__grade_counter-1, GradeRepo.add, grade, self.__grade_repo)
        self.__undo_redo_repo.add_action(undo_action)

    def create_grade(self, discipline, student, value):
        grade = Grade(discipline, student, value)
        GradeValidator.validate_grade(grade)
        return grade

    def delete_student_grade(self, student):
        index = 0
        while index < len(self.__grade_repo):
            if str(student) in str(self.__grade_repo[index]):
                self.__grade_repo.delete(index)
            else:
                index += 1

    def delete_discipline_grade(self, discipline):
        index = 0
        while index < len(self.__grade_repo):
            if str(discipline) in str(self.__grade_repo[index]):
                self.__grade_repo.delete(index)
            else:
                index += 1

    def get_student_failures(self, student_id):
        disciplines = self.__discipline_repo.get_all()
        for discipline_id in disciplines:
            if self.get_student_average_discipline(student_id, discipline_id):
                if self.get_student_average_discipline(student_id, discipline_id) < 5:
                    return True
        return False

    def get_student_aggregated_averages(self, student_id):
        sum_of_averages = 0
        count_of_averages = 0
        disciplines = self.__discipline_repo.get_all()
        for discipline_id in disciplines:
            average_per_discipline = self.get_student_average_discipline(student_id, discipline_id)
            if average_per_discipline:
                sum_of_averages += average_per_discipline
            count_of_averages += 1
        if count_of_averages:
            return sum_of_averages / count_of_averages

    def get_student_aggregated_averages_all(self):
        students = self.__student_repo.get_all()
        list_of_averages = []
        for student_id in students:
            aggregated_average = self.get_student_aggregated_averages(student_id)
            if aggregated_average:
                list_of_averages.append(aggregated_average)
        return list_of_averages

    def get_student_average_discipline(self, student_id, discipline_id):
        count_of_grades = 0
        sum_of_grades = 0
        grades = self.__grade_repo.get_all()
        for grade in grades:
            if grade.discipline_id == discipline_id:
                if grade.student_id == student_id:
                    count_of_grades += 1
                    sum_of_grades += grade.value
        if count_of_grades:
            return sum_of_grades / count_of_grades

    def get_mandatory_disciplines(self):
        disciplines = self.__discipline_repo.get_all()
        dictionary_of_disciplines = {}
        for discipline_id in disciplines:
            average_per_discipline = self.get_discipline_average(discipline_id)
            if average_per_discipline:
                dictionary_of_disciplines[discipline_id] = average_per_discipline
        return dictionary_of_disciplines

    def get_discipline_average(self, discipline_id):
        sum_of_grades = 0
        count_of_grades = 0
        grades = self.__grade_repo.get_all()
        for grade in grades:
            if grade.discipline_id == discipline_id:
                sum_of_grades += grade.value
                count_of_grades += 1
        if count_of_grades:
            return sum_of_grades / count_of_grades

    def get_grade_list(self):
        grade_list = self.__grade_repo.get_all()
        return grade_list

import random

from src.domain.Student import Student
from src.repo.Repository import Repository


class StudentService:
    def __init__(self, repository):
        self._repo = repository

    def add_student_service(self, student):
        self._repo.add(student)

    def create(self, student_id, student_name, student_group):
        student = Student(student_id, student_name, student_group)
        return student

    def filter_service(self, group):
        return self._repo.filter(group)

    def get_all(self):
        return self._repo.get_all()

    def get_filtered(self):
        return self._repo.get_filtered()

    def init_students(self):
        s1 = 'Timofte Razvan'
        s2 = 'Girjaliu Robert'
        s3 = 'Chirica Maria'
        s4 = 'Gutovschi Daniel'
        s5 = 'Hutanu Diana'
        s6 = 'Popa Andrei'
        s7 = 'Carmen Horea'
        s8 = 'Cristina Mursa'
        s9 = 'Popa Andreea'
        s10 = 'Molotov Mihnea'
        name_list = [s1, s2, s3, s4, s5, s6, s7, s8, s9, s10]
        random.shuffle(name_list)
        id = 100
        for name in name_list:
            id += 1
            group = random.randint(910, 917)
            self.add_student_service(Student(id, name, group))

    def undo(self, operation_history):
        if len(operation_history) == 0:
            raise ValueError("Cannot undo anymore")
        else:
            if operation_history[-1] == 'add':
                self._repo._data.popitem()
                operation_history.pop(-1)
            elif operation_history[-1] == 'filter':
                for student in self.get_filtered():
                    self._repo.add(self._repo._filtered[student])
                operation_history.pop(-1)


def test_service():
    print("Beginning service tests...")
    repos = Repository()
    serv = StudentService(repos)
    assert len(repos) == 0
    serv.add_student_service(Student('25', 'Popa Andrei', '912'))
    student = Student('26', 'Popa Andrei', '912')
    serv._repo.add(student)
    serv.add_student_service(Student('27', 'Popa Andrei', '915'))
    assert len(repos) == 3
    result = serv.filter_service('912')
    assert len(result) == 1
    serv.add_student_service(Student('3', 'Popa Andrei', '912'))
    assert len(repos) == 4
    op_his = ['add']
    assert len(op_his) == 1
    serv._repo._data.popitem()
    assert len(repos) == 3
    serv.undo(op_his)
    assert len(repos) == 2
    print("Service tests finalized.")
    print("==========================")

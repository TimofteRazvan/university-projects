import unittest

from src.domain.discipline import Discipline
from src.domain.grade import Grade
from src.domain.student import Student
from src.repository.repositoryDiscipline import DisciplineRepo
from src.repository.repositoryException import RepositoryException
from src.repository.repositoryGrade import GradeRepo
from src.repository.repositoryStudent import StudentRepo
from src.repository.repositoryUndoRedo import UndoRedoRepo
from src.services.serviceDiscipline import DisciplineService
from src.services.serviceGrade import GradeService
from src.services.serviceStudent import StudentService


class DisciplineRepoTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = DisciplineRepo()
        self.__repo.add(Discipline(100, 'Mathematics'))
        self.__repo.add(Discipline(101, 'English'))
        self.__repo.add(Discipline(102, 'IT'))
        self.__repo.add(Discipline(103, 'Biology'))
        self._discipline_104 = Discipline(104, 'Physics')
        self.__repo.add(self._discipline_104)

    def test_repo_elements(self):
        self.assertEqual(len(self.__repo), 5)
        self.assertEqual(self.__repo[104], self._discipline_104)
        self.assertEqual(str(self.__repo[102]), str(Discipline(102, 'IT')))

    def test_repo_remove(self):
        self.__repo.delete(102)
        self.assertEqual(len(self.__repo), 4)

    def test_repo_update(self):
        self.__repo.update(Discipline(101, 'dsadsa'))
        self.assertEqual(str(self.__repo[101]), str(Discipline(101, 'dsadsa')))

    def test_repo_exception(self):
        with self.assertRaises(RepositoryException):
            self.__repo.delete(200)

    def tearDown(self) -> None:
        pass


class GradeRepoTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = GradeRepo()
        self.__repo.add(Grade(100, 100, 10))
        self.__repo.add(Grade(100, 101, 9))
        self.__repo.add(Grade(100, 101, 8))
        self.__repo.add(Grade(101, 100, 7))
        self._grade_9 = Grade(101, 101, 9)
        self.__repo.add(self._grade_9)

    def test_repo_elements(self):
        self.assertEqual(len(self.__repo), 5)
        self.assertEqual(self.__repo[-1], self._grade_9)
        self.assertEqual(str(self.__repo[-1]), str(self._grade_9))

    def tearDown(self) -> None:
        pass


class StudentRepoTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__repo = StudentRepo()
        self.__repo.add(Student(100, 'Timofte Razvan'))
        self.__repo.add(Student(101, 'Almanac Iosif'))
        self.__repo.add(Student(102, 'Yuri Boyka'))
        self.__repo.add(Student(103, 'Girjaliu Robert'))
        self._student_104 = Student(104, 'Colmen Carmen')
        self.__repo.add(self._student_104)

    def test_repo_elements(self):
        self.assertEqual(len(self.__repo), 5)
        self.assertEqual(self.__repo[104], self._student_104)
        self.assertEqual(str(self.__repo[102]), str(Student(102, 'Yuri Boyka')))

    def test_repo_remove(self):
        self.__repo.delete(102)
        self.assertEqual(len(self.__repo), 4)

    def test_repo_update(self):
        self.__repo.update(Student(102, 'Yuri'))
        self.assertEqual(str(self.__repo[102]), str(Student(102, 'Yuri')))

    def test_repo_exception(self):
        with self.assertRaises(RepositoryException):
            self.__repo.delete(200)

    def tearDown(self) -> None:
        pass


class DisciplineServiceTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__serv = DisciplineService(DisciplineRepo(), UndoRedoRepo())
        self.__serv.add_discipline(self.__serv.create_discipline(100, 'Maths'))
        self.__serv.add_discipline(self.__serv.create_discipline(101, 'English'))
        self.__serv.add_discipline(self.__serv.create_discipline(102, 'Rom'))
        self.__serv.add_discipline(self.__serv.create_discipline(103, 'Bio'))
        self._discipline_104 = self.__serv.create_discipline(104, 'Physics')
        self.__serv.add_discipline(self._discipline_104)
        self._discipline_list = self.__serv.get_discipline_list()

    def test_service_elements(self):
        self.assertEqual(len(self._discipline_list), 5)

    def test_service_create(self):
        self._discipline_105 = self.__serv.create_discipline(105, 'Chemistry')
        self.__serv.add_discipline(self._discipline_105)
        self.assertEqual(self._discipline_105, self._discipline_list[105])

    def test_service_update(self):
        self.__serv.update_discipline(101, 'Sociology')
        self.assertEqual(str(Discipline(101, 'Sociology')), str(self._discipline_list[101]))

    def test_service_delete(self):
        self.__serv.delete_discipline(100)
        self.assertEqual(len(self._discipline_list), 4)

    def tearDown(self) -> None:
        pass


class GradeServiceTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__serv = GradeService(GradeRepo(), DisciplineRepo(), StudentRepo(), UndoRedoRepo())
        self.__serv.add_grade(self.__serv.create_grade(100, 101, 10))
        self.__serv.add_grade(self.__serv.create_grade(100, 101, 10))
        self.__serv.add_grade(self.__serv.create_grade(100, 101, 10))
        self.__serv.add_grade(self.__serv.create_grade(100, 101, 10))
        self._grade_101_101_9 = self.__serv.create_grade(101, 101, 9)
        self.__serv.add_grade(self._grade_101_101_9)
        self._grade_list = self.__serv.get_grade_list()

    def test_service_elements(self):
        self.assertEqual(len(self._grade_list), 5)

    def test_service_create(self):
        self._grade_100_102_10 = self.__serv.create_grade(100, 102, 10)
        self.__serv.add_grade(self._grade_100_102_10)
        self.assertEqual(self._grade_100_102_10, self._grade_list[-1])

    def tearDown(self) -> None:
        pass


class StudentServiceTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__serv = StudentService(StudentRepo(), UndoRedoRepo())
        self.__undo_redo_repo = UndoRedoRepo()
        self.__serv.add_student(self.__serv.create_student(100, 'Timofte Razvan'))
        self.__serv.add_student(self.__serv.create_student(101, 'Girjaliu Robert'))
        self.__serv.add_student(self.__serv.create_student(102, 'Chirica Maria'))
        self.__serv.add_student(self.__serv.create_student(103, 'Vlad Ioan Tomescu'))
        self._student_104 = self.__serv.create_student(104, 'Mircea Gabi')
        self.__serv.add_student(self._student_104)
        self._student_list = self.__serv.get_student_list()

    def test_service_elements(self):
        self.assertEqual(len(self._student_list), 5)

    def test_service_create(self):
        self._student_105 = self.__serv.create_student(105, 'Alex Vancea')
        self.__serv.add_student(self._student_105)
        self.assertEqual(self._student_105, self._student_list[105])
        self.assertEqual(len(self._student_list), 6)

    def test_service_update(self):
        self.__serv.update_student(101, 'Toma Alex')
        self.assertEqual(str(Student(101, 'Toma Alex')), str(self._student_list[101]))

    def test_service_delete(self):
        self.__serv.delete_student(100)
        self.assertEqual(len(self._student_list), 4)

    def tearDown(self) -> None:
        pass


unittest.main()

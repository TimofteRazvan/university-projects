from src.repository.repositoryStudent import StudentRepo
from src.repository.repositoryDiscipline import DisciplineRepo
from src.repository.repositoryGrade import GradeRepo
from src.repository.repositoryUndoRedo import UndoRedoRepo
from src.services.serviceStudent import StudentService
from src.services.serviceDiscipline import DisciplineService
from src.services.serviceGrade import GradeService
from src.services.serviceUndoRedo import UndoRedoService
from src.ui.ui import UI

repo_student = StudentRepo()
repo_discipline = DisciplineRepo()
repo_grade = GradeRepo()
repo_undo_redo = UndoRedoRepo()

service_student = StudentService(repo_student, repo_undo_redo)
service_discipline = DisciplineService(repo_discipline, repo_undo_redo)
service_grade = GradeService(repo_grade, repo_discipline, repo_student, repo_undo_redo)
service_undo_redo = UndoRedoService(repo_undo_redo)

ui = UI(service_student, service_discipline, service_grade, service_undo_redo)

ui.start()


from ui.UI import UI
from repo.Repository import test_repo, Repository
from services.Service import test_service, StudentService


ui = UI(StudentService(Repository()))
test_repo()
test_service()
ui.start()

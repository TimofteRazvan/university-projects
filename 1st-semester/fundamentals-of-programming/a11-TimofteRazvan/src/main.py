from src.repository.repository import BoardRepository
from src.service.service import BoardService
from src.ui.ui import UI

size = 0
try:
    size = int(input("Board size: "))
except ValueError as ve:
    print(str(ve))

repo = BoardRepository(size)

serv = BoardService(repo)

ui = UI(serv)

ui.start()

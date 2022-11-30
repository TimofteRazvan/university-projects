from repository.repository import TaxiRepository
from service.service import TaxiService
from ui.ui import UI

repo = TaxiRepository()
service = TaxiService(repo)
ui = UI(service)

ui.start()

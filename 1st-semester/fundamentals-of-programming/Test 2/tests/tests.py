import unittest

from repository.repository import TaxiRepository
from service.service import TaxiService
from domain.taxi import Taxi


class TaxiServiceTest(unittest.TestCase):
    def setUp(self) -> None:
        self.__serv = TaxiService(TaxiRepository())
        taxi_list = self.__serv.get_taxi_list()
        taxi_list[1000] = self.__serv.create_taxi(1000, 10, 10, 0)
        self.__serv.add_ride(10, 10, 25, 36)

    def test_add_ride(self):
        taxi_list = self.__serv.get_taxi_list()
        self.assertEqual(str(taxi_list[1000]), str(Taxi(1000, 25, 36, 41)))

    def tearDown(self) -> None:
        pass

unittest.main()

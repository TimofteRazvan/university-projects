from functions import *


def test_check_codes():
    assert check_codes(1234, 4567) == 0
    assert check_codes(1234, 1234) == 4
    assert check_codes(4325, 7861) == 0


def test_check_runners():
    assert check_runners(1234, 3421) == 4
    assert check_runners(2345, 3678) == 1


def test_check_digits():
    assert check_digits(1234) == True
    assert check_digits(1111) == False
    assert check_digits(2311) == False
    assert check_digits(9329) == False


def start_tests():
    print("starting tests...")
    test_check_digits()
    test_check_runners()
    test_check_codes()
    print("all tests successful")

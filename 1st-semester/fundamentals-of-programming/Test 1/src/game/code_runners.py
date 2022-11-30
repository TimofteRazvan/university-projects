"""
Implement the solution here. 
You may add other source files.
Make sure you commit & push the source code before the end of the test.

Solutions using user-defined classes will not be graded.
"""
import time
from tests import *


def computer_win():
    print("Computer wins!")
    exit()


def player_win():
    print("Player wins!")
    exit()


def time_out():
    print("Out of time!")
    exit()


def display_data(secret_number, number):
    print('Codes: ' + str(check_codes(secret_number, number)) + ' ' + 'Runners: ' +
          str(check_runners(secret_number, number)))


def start_game():
    period = 60
    start = time.time()
    secret_number = int(generate_secret_number())
    while True:
        number = input("Number = ")
        if not check_digits(number):
            computer_win()
        if correct_code(number, secret_number):
            player_win()
        display_data(secret_number, number)
        if time.time() >= start + period:
            time_out()


start_tests()
start_game()

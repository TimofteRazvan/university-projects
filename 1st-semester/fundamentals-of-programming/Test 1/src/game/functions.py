from random import randint


def check_digits(command_number):
    try:
        number = int(command_number)
        if number < 1000 or number > 9999:
            return False
        n1 = number // 1000 % 10
        n2 = number // 100 % 10
        n3 = number // 10 % 10
        n4 = number % 10
        if n1 == n2 or n1 == n3 or n1 == n4 or n2 == n3 or n2 == n4 or n3 == n4:
            return False
        return True
    except ValueError as ve:
        print(str(ve))


def generate_secret_number():
    number = randint(1000, 9999)
    while not check_digits(number):
        number = randint(1000, 9999)
    return number


def check_codes(command_number1, command_number2):
    number1 = int(command_number1)
    number2 = int(command_number2)
    codes = 0
    while number1 and number2:
        if number1 % 10 == number2 % 10:
            codes += 1
        number1 //= 10
        number2 //= 10
    return codes


def check_runners(command_number1, command_number2):
    number1 = int(command_number1)
    number2 = int(command_number2)
    runners = 0
    index = -1
    d1 = str(number2 % 10)
    d2 = str(number2 // 10 % 10)
    d3 = str(number2 // 100 % 10)
    d4 = str(number2 // 1000 % 10)
    digits = [d1, d2, d3, d4]
    while number1:
        index += 1
        if str(number1 % 10) in digits:
            if not str(number1 % 10) == digits[index]:
                runners += 1
        number1 //= 10
    return runners


def correct_code(command_number1, command_number2):
    number1 = int(command_number1)
    number2 = int(command_number2)
    return number1 == number2

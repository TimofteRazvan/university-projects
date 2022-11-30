class Grade:
    def __init__(self, discipline_id, student_id, value):
        self.__discipline_id = discipline_id
        self.__student_id = student_id
        self.__value = value

    @property
    def discipline_id(self):
        return self.__discipline_id.id

    @property
    def student_id(self):
        return self.__student_id.id

    @property
    def value(self):
        return self.__value

    def __str__(self):
        return "Discipline: " + str(self.__discipline_id) + " ; Student: " + str(self.__student_id) + " ; Grade: " + \
            str(self.__value)

    def __repr__(self):
        return str(self)

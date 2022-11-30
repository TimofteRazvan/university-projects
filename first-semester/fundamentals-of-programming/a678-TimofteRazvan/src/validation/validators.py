from src.validation.validError import ValidError


class StudentValidator:
    @staticmethod
    def validate_student(student):
        errors = ""
        if student.id < 0:
            errors += "Invalid ID\n"
        if student.name == "":
            errors += "Invalid name\n"
        if len(errors):
            raise ValidError(errors)


class DisciplineValidator:
    @staticmethod
    def validate_discipline(discipline):
        errors = ""
        if discipline.id < 0:
            errors += "Invalid ID\n"
        if discipline.name == "":
            errors += "Invalid discipline\n"
        if len(errors):
            raise ValidError(errors)


class GradeValidator:
    @staticmethod
    def validate_grade(grade):
        errors = ""
        if grade.value > 10 or grade.value < 0:
            errors += "Invalid grade\n"
        if len(errors):
            raise ValidError(errors)
from src.exceptions.validException import ValidError


class PositionValidator:
    @staticmethod
    def validate_position(position):
        errors = ""
        if position.x < 0 or position.y < 0:
            errors += "Outside the board!"
        if position.value:
            errors += "Already occupied!"
        if len(errors):
            raise ValidError

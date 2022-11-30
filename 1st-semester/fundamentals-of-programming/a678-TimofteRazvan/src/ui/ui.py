class UI:
    def __init__(self, student_service, discipline_service, grade_service, undo_redo_service):
        self.__student_serv = student_service
        self.__discipline_serv = discipline_service
        self.__grade_serv = grade_service
        self.__undo_redo_serv = undo_redo_service

    def menu(self):
        print("==========================")
        print("1. Add student")
        print("2. Remove student")
        print("3. Update student")
        print("4. List all students")
        print("")
        print("5. Add discipline")
        print("6. Remove discipline")
        print("7. Update discipline")
        print("8. List all disciplines")
        print("")
        print("9. Add grade")
        print("10. List all grades")
        print("")
        print("11. Search for student")
        print("12. Search for discipline")
        print("")
        print("13. Show all failing students")
        print("14. Show students with best school situation")
        print("15. Show all disciplines with at least ONE grade")
        print("")
        print("16. Undo")
        print("17. Redo")
        print("")
        print("18. Exit")
        print("==========================")

    def add_student_ui(self):
        try:
            id = int(input("ID: "))
            name = input("Name: ")
            student = self.__student_serv.create_student(id, name)
            self.__student_serv.add_student(student)
        except ValueError as ve:
            print(str(ve))
            return

    def display_students(self):
        students = self.__student_serv.get_student_list()
        for key in students:
            print(students[key])

    def add_discipline_ui(self):
        try:
            id = int(input("ID: "))
            name = input("Discipline: ")
            discipline = self.__discipline_serv.create_discipline(id, name)
            self.__discipline_serv.add_discipline(discipline)
        except ValueError as ve:
            print(str(ve))
            return

    def display_disciplines(self):
        disciplines = self.__discipline_serv.get_discipline_list()
        for key in disciplines:
            print(disciplines[key])

    def add_grade_ui(self):
        try:
            discipline_id = int(input("Discipline ID: "))
            student_id = int(input("Student ID: "))
            value = int(input("Grade: "))
            discipline = self.__discipline_serv.get_discipline(discipline_id)
            student = self.__student_serv.get_student(student_id)
            grade = self.__grade_serv.create_grade(discipline, student, value)
            self.__grade_serv.add_grade(grade)
        except ValueError as ve:
            print(str(ve))
            return

    def display_grades(self):
        try:
            grades = self.__grade_serv.get_grade_list()
            for x in range(0, len(grades)):
                print(grades[x])
        except TypeError:
            print("List is empty")
            return

    def delete_student(self):
        key = int(input("ID: "))
        student = self.__student_serv.get_student(key)
        self.__grade_serv.delete_student_grade(student)
        self.__student_serv.delete_student(key)

    def delete_discipline(self):
        key = int(input("ID: "))
        discipline = self.__discipline_serv.get_discipline(key)
        self.__grade_serv.delete_discipline_grade(discipline)
        self.__discipline_serv.delete_discipline(key)

    def update_student(self):
        key = int(input("ID: "))
        new_name = input("New student name: ")
        self.__student_serv.update_student(key, new_name)

    def update_discipline(self):
        key = int(input("ID: "))
        new_discipline = input("New discipline: ")
        self.__discipline_serv.update_discipline(key, new_discipline)

    def search_student_ui(self):
        string = str(input(">> "))
        results = self.__student_serv.search_student(string)
        for result in results:
            print(result)

    def search_discipline_ui(self):
        string = str(input(">> "))
        results = self.__discipline_serv.search_discipline(string)
        for result in results:
            print(result)

    def display_student_failures_ui(self):
        print("Failing students:")
        students = self.__student_serv.get_student_list()
        for student_id in students:
            if self.__grade_serv.get_student_failures(student_id) is True:
                print(self.__student_serv.get_student(student_id))

    def display_student_aggregated_averages_ui(self):
        list_of_averages = self.__grade_serv.get_student_aggregated_averages_all()
        list_of_averages.sort(reverse=True)
        for average in list_of_averages:
            print(average)

    def display_mandatory_disciplines_ui(self):
        dictionary_of_disciplines = self.__grade_serv.get_mandatory_disciplines()
        sorted_dictionary = sorted(dictionary_of_disciplines.items(), reverse=True, key=lambda item: item[1])
        disciplines = self.__discipline_serv.get_discipline_list()
        for discipline in sorted_dictionary:
            discipline_id = discipline[0]
            print(disciplines[discipline_id])

    def undo(self):
        self.__undo_redo_serv.undo()

    def redo(self):
        self.__undo_redo_serv.redo()

    def start(self):
        self.menu()
        self.__student_serv.generate_students()
        self.__discipline_serv.generate_discipline()
        while True:
            try:
                option = int(input("Option: "))
                if option == 1:
                    self.add_student_ui()
                elif option == 2:
                    self.delete_student()
                elif option == 3:
                    self.update_student()
                elif option == 4:
                    self.display_students()
                elif option == 5:
                    self.add_discipline_ui()
                elif option == 6:
                    self.delete_discipline()
                elif option == 7:
                    self.update_discipline()
                elif option == 8:
                    self.display_disciplines()
                elif option == 9:
                    self.add_grade_ui()
                elif option == 10:
                    self.display_grades()
                elif option == 11:
                    self.search_student_ui()
                elif option == 12:
                    self.search_discipline_ui()
                elif option == 13:
                    self.display_student_failures_ui()
                elif option == 14:
                    self.display_student_aggregated_averages_ui()
                elif option == 15:
                    self.display_mandatory_disciplines_ui()
                elif option == 16:
                    self.undo()
                elif option == 17:
                    self.redo()
                elif option == 18:
                    exit()
                else:
                    print("Not an option.")
            except ValueError as ve:
                print(str(ve))

class UI:
    def __init__(self, service):
        self._serv = service
        self._op_history = []

    def menu(self):
        print("List initialized.")
        print("==========================")
        print("1. Add a student")
        print("2. Display students")
        print("3. Filter by group")
        print("4. Undo")
        print("5. Exit")
        print("==========================")

    def add_student_ui(self):
        try:
            sid = int(input("ID: "))
            name = input("Name: ")
            group = int(input("Group: "))
            student = self._serv.create(sid, name, group)
            self._serv.add_student_service(student)
            self._op_history.append('add')
        except ValueError as ve:
            print(str(ve))
            return

    def display_students(self):
        students = self._serv.get_all()
        try:
            for student in students:
                print(students[student])
        except TypeError:
            print("List is empty.")

    def filter_ui(self):
        try:
            filter_group = int(input("Filter: "))
            result = self._serv.filter_service(filter_group)
            self._serv._repo._data = result
            self._op_history.append('filter')
        except ValueError as ve:
            print(str(ve))
            return

    def start(self):
        self.menu()
        self._serv.init_students()
        while True:
            try:
                option = int(input("Option: "))
                if option == 1:
                    self.add_student_ui()
                elif option == 2:
                    self.display_students()
                elif option == 3:
                    self.filter_ui()
                elif option == 4:
                    self._serv.undo(self._op_history)
                elif option == 5:
                    exit()
                else:
                    print("Not an option.")
            except ValueError as ve:
                print(str(ve))

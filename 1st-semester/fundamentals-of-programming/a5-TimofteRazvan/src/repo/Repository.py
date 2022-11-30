from src.domain.Student import Student


class RepositoryException(Exception):
    def __init__(self, message):
        self._message = message

    @property
    def message(self):
        return self._message

    def __str__(self):
        return self._message


class Repository:
    def __init__(self):
        self._data = {}
        self._filtered = {}

    def add(self, student):
        if student.sid in self._data:
            raise RepositoryException("Entity with ID exists already")
        self._data[student.sid] = student

    def get_all(self):
        return self._data

    def get_filtered(self):
        return self._filtered

    def __len__(self):
        return len(self._data)

    def __getitem__(self, item):
        try:
            return self._data[item]
        except KeyError:
            raise RepositoryException("Student ID non-existent")

    def filter(self, group=None):
        result = {}
        try:
            for student in self._data:
                student_in_repo = self._data[student]
                if not int(student_in_repo.group) == int(group):
                    result[student] = student_in_repo
                else:
                    self._filtered[student] = student_in_repo
        except TypeError:
            print("List is empty.")
        return result


def test_repo():
    print("==========================")
    print("Beginning repo tests...")
    repos = Repository()
    repos.add(Student('1', 'Timofte Razvan', '917'))
    repos.add(Student('2', 'Girjaliu Robert', '911'))
    repos.add(Student('3', 'Chirica Maria', '913'))
    repos.add(Student('4', 'Gutovschi Daniel', '912'))
    repos.add(Student('5', 'Hutanu Diana', '911'))
    student_6 = Student('6', 'Popa Andrei', '912')
    repos.add(student_6)
    assert len(repos) == 6
    assert repos['6'] == student_6
    assert str(repos['1']) == str(Student('1', 'Timofte Razvan', '917'))
    result = repos.filter(group='917')
    assert len(result) == 5
    result = repos.filter(group='912')
    assert len(result) == 4
    try:
        assert repos[50] is None
    except RepositoryException:
        pass
    except Exception:
        assert False
    print("Repo tests finalized.")
    print("==========================")

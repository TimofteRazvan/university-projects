class Student:
    def __init__(self, sid, name, group):
        self._sid = sid
        self._name = name
        self._group = group

    @property
    def sid(self):
        return self._sid

    @property
    def name(self):
        return self._name

    @property
    def group(self):
        return self._group

    def __str__(self):
        return "ID: " + str(self._sid) + ", Name: " + self._name + ", Group: " + str(self._group)

    def __repr__(self):
        return str(self)

#include "ExtendedTest.h"
#include "ShortTest.h"

#include "SortedMap.h"


#include <iostream>
using namespace std;

bool ascending(TKey c1, TKey c2) {
	if (c1 <= c2) {
		return true;
	}
	else {
		return false;
	}
}

bool odd(TValue v)
{
	if (v % 2 == 1)
		return true;
	return false;
}

int main() {
	testAll();
	std::cout << "\n";
	testAllExtended();
	SortedMap sm(ascending);
	int cMin = 0;
	int cMax = 10;

	cout << "Before filter: \n";
	for (int i = cMin; i <= cMax; i++)
	{
		sm.add(i, i + 1);
		cout << sm.search(i) << " ";
	}

	sm.filter(odd);

	cout << "\nAfter filter: \n";
	for (int i = cMin; i <= cMax; i++)
	{
		if (sm.search(i) != NULL_TVALUE)
			cout << sm.search(i) << " ";
	}

	cout << "\nThat's all!" << endl;
	system("pause");
	return 0;
}



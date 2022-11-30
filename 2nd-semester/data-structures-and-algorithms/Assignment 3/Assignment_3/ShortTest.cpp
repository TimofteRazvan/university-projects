#include <assert.h>
#include <iostream>

#include "SortedMap.h"
#include "SMIterator.h"
#include "ShortTest.h"
#include <exception>
using namespace std;

bool relatie1(TKey cheie1, TKey cheie2) {
	if (cheie1 <= cheie2) {
		return true;
	}
	else {
		return false;
	}
}

void testAll(){
    std::cout << "Creating\n";
	SortedMap sm(relatie1);
    std::cout << "Created\n";
	assert(sm.size() == 0);
	assert(sm.isEmpty());
    std::cout << "To add\n";
    sm.add(1,2);
    std::cout << "Added\n";
    assert(sm.size() == 1);
    std::cout << "Size passed\n";
    assert(!sm.isEmpty());
    std::cout << "Empty 1 passed\n";
    assert(sm.search(1)!=NULL_TVALUE);
    std::cout << "Search passed\n";
    TValue v =sm.add(1,3);
    std::cout << "Add 2 passed\n";
    assert(v == 2);
    assert(sm.search(1) == 3);
    std::cout << "Search yielded appropriate results\n";
    std::cout << "Begin iterator test\n";
    SMIterator it = sm.iterator();
    it.first();
    while (it.valid()){
    	TElem e = it.getCurrent();
    	assert(e.second != NULL_TVALUE);
    	it.next();
    }
    std::cout << "End iterator test\n";
    assert(sm.remove(1) == 3);
    std::cout << "Remove passed\n";
    assert(sm.isEmpty());
    std::cout << "Remove isEmpty passed\n";
}


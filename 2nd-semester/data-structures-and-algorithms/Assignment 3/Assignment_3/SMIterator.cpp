#include "SMIterator.h"
#include "SortedMap.h"
#include <exception>

using namespace std;

SMIterator::SMIterator(const SortedMap& m) : map(m){
	current = m.head;
}
// Theta(1)

void SMIterator::first(){
	current = map.head;
}
// Theta(1)

void SMIterator::next(){
	if (current == -1)
		throw exception();
	current = map.next[current];
}
// Theta(1)

bool SMIterator::valid() const{
	return current != -1;
}
// Theta(1)

TElem SMIterator::getCurrent() const{
	if (current == -1)
		throw exception();
	return map.elems[current];
}
// Theta(1)



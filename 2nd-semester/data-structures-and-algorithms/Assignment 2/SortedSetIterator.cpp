#include "SortedSetIterator.h"
#include <exception>

using namespace std;

SortedSetIterator::SortedSetIterator(const SortedSet& m) : multime(m)
{
	currentElement = m.head;
}
// Theta(1)


void SortedSetIterator::first() {
	currentElement = multime.head;
}
// Theta(1)


void SortedSetIterator::next() {
	if (currentElement == nullptr)
		throw exception();
	currentElement = currentElement->next;
}
// Theta(1)

TElem SortedSetIterator::getCurrent()
{
	if (currentElement == nullptr)
		throw exception();
	return currentElement->info;
}
// Theta(1)

bool SortedSetIterator::valid() const {
	return currentElement != nullptr;
}
// Theta(1)


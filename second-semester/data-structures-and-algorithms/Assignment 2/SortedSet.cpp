#include "SortedSet.h"
#include "SortedSetIterator.h"
#include <iostream>

SortedSet::SLLNode::SLLNode(TElem e, PNode n) {
	info = e;
	next = n;
}
// Theta(1)

SortedSet::SortedSet(Relation r) {
	head = nullptr;
	relation = r;
	nrElements = 0;
}
// Theta(1)

bool SortedSet::add(TComp elem) {
	PNode current = head;
	while (current != nullptr)
	{
		if (current->info == elem)
			return false;
		current = current->next;
	}

	PNode pn = new SLLNode(elem, nullptr);
	bool change = false;
	pn->next = head;
	head = pn;
	nrElements++;
	PNode future = head;
	future = future->next;
	PNode former = head;
	while (pn != nullptr && future != nullptr && relation(elem, future->info) == false)
	{
		change = true;
		former = former->next;
		future = future->next;
	}
	if (change)
		head = pn->next;
	former->next = pn;
	pn->next = future;
	if (future == nullptr)
		pn->next = nullptr;
		
	return true;
}
// O(nrElements*2) - loops twice through all the elements in the WC
// In the BC, complexity is nrElements, as it searches once and then immediately adds to the beginning
// CAN BE MORE EFFICIENT: WHILE SEARCHING ELEMENT, IF RELATION CHANGES, ELEMENT CAN BE ADDED THEN, THUS
// THE MOST EFFICIENT VARIATION IS O(nrElements)

bool SortedSet::remove(TComp elem) {
	PNode current = head;
	PNode prev = nullptr;
	if (current != nullptr && current->info == elem)
	{
		head = current->next;
		nrElements--;
		delete current;
		return true;
	}

	while (current != nullptr && current->info != elem)
	{
		prev = current;
		current = current->next;
	}

	if (current == nullptr)
		return false;

	prev->next = current->next;
	delete current;
	nrElements--;
	return true;
}
// O(nrElements)
// Because it loops once through the linked list, and only gets to the end in the WC (element doesn't exist)
// In the BC, the first element (head) is to be deleted, thus complexity is 1


bool SortedSet::search(TComp elem) const {
	PNode current = head;
	while (current != nullptr)
	{
		if (current->info == elem)
			return true;
		current = current->next;
	}
	return false;
}
// O(nrElements)
// Loops once through all the elements in WC when element isn't found
// In BC, head is the element, so complexity is 1


void SortedSet::intersection(const SortedSet& s)
{
	PNode current = head;
	PNode aux = s.head;
	while (current != nullptr && aux != nullptr)
	{
		PNode prev = current;
		if (current->info != aux->info)
			remove(current->info);
		current = prev->next;
		aux = aux->next;
		
	}	
}
// O(nrElements^2)
// WC: The RHS set has all elements different from LHS set, thus going through both (Theta(nrElements))
// search() and remove() loops, which go through all the elements, multiple times	(Theta(1))
// BC: The RHS set has all the elements the same as the LHS set, thus not looping through remove()

int SortedSet::size() const {
	return nrElements;
}
// Theta(1)



bool SortedSet::isEmpty() const {
	if (nrElements == 0)
		return true;
	return false;
}
// Theta(1)

SortedSetIterator SortedSet::iterator() const {
	return SortedSetIterator(*this);
}
// Theta(1)


SortedSet::~SortedSet() {
	while (head != nullptr)
	{
		PNode p = head;
		head = head->next;
		delete p;
	}
}
// Theta(nrElements)
// Will always loop through all the elements, both in BC and WC



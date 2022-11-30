#include "SMIterator.h"
#include <iostream>
#include "SortedMap.h"
#include <exception>
using namespace std;

int SortedMap::allocateP()
{
	if (this->firstFree == -1)
	{
		resize();
	}
	this->firstFree = this->next[firstFree];
	
	return this->nrElements;
}
// WC: firstFree is -1 (We need to resize the array) -- Theta(cap*2)
// BC: firstfree is NOT -1 (We do not need to resize) -- Theta(1)
// Total: O(nrElements)

void SortedMap::resize() {
	this->cap = this->cap*2;
	auto aux_elems = new TElem[this->cap];
	int* aux_next = new int[this->cap];
	int* aux_prev = new int[this->cap];

	for (int i = 0; i < this->nrElements; i++)
	{
		aux_elems[i] = this->elems[i];
		aux_next[i] = this->next[i];
		aux_prev[i] = this->prev[i];
	}
	for (int i = this->nrElements; i < this->cap; i++)
	{
		aux_prev[i] = i - 1;
		aux_next[i] = i + 1;
	}
	aux_next[this->cap - 1] = -1;
	aux_prev[this->cap - 1] = this->cap - 2;

	this->prev = aux_prev;
	this->next = aux_next;
	this->elems = aux_elems;
	this->firstFree = this->nrElements;
}
// Theta(cap*2)

void SortedMap::freeP(int i)
{
	this->next[i] = this->firstFree;
	this->firstFree = i;
}
// Theta(1)

SortedMap::SortedMap(Relation r) {
	this->cap = 1;
	this->head = -1;
	this->tail = -1;
	this->relation = r;
	this->nrElements = 0;
	this->elems = new TElem[this->cap];
	this->next = new int[this->cap];
	this->prev = new int[this->cap];
	for (int i = 0; i < cap; i++)
	{
		this->next[i] = i + 1;
		this->prev[i] = i - 1;
	}
	next[cap - 1] = -1;
	firstFree = 0;
}
// Theta(1)


TValue SortedMap::add(TKey k, TValue v) {
	
	if (this->head == -1)
	{
		int newPosition = this->allocateP();
		TElem newElement(k, v);
		this->elems[newPosition] = newElement;
		this->next[newPosition] = -1;
		this->prev[newPosition] = tail;
		this->nrElements++;
		this->head = newPosition;
		this->tail = newPosition;
	}
	else
	{
		int current = this->head;
		while (current != -1 && this->elems[current].first != k)
		{
			current = this->next[current];
		}
		if (current != -1)
		{
			TValue old = this->elems[current].second;
			this->elems[current].second = v;
			return old;
		}
		else
		{
			TKey aux_k = k;
			TValue aux_v = v;
			int parser = this->head;
			while (parser != -1 && this->relation(this->elems[parser].first, k))
				parser = this->next[parser];
			while (parser != -1)
			{
				TKey copykey = this->elems[parser].first;
				TValue copyvalue = this->elems[parser].second;
				this->elems[parser].first = aux_k;
				this->elems[parser].second = aux_v;
				parser = this->next[parser];
				aux_k = copykey;
				aux_v = copyvalue;
			}
			TElem newElement(aux_k, aux_v);
			int newPosition = this->allocateP();
			this->elems[newPosition] = newElement;
			this->next[newPosition] = -1;
			this->prev[newPosition] = tail;
			this->nrElements++;
			this->next[this->tail] = newPosition;
			this->tail = newPosition;
		}
	}
	return NULL_TVALUE;
}
// BC: First element to be added / First element found -- Theta(1)
// WC: Element not found. Last element to be added regarding relation() -- Theta(nrElements)
// Total: O(nrElements)

TValue SortedMap::search(TKey k) const {
	int current = this->head;
	while (current != -1 && this->elems[current].first != k)
	{
		current = this->next[current];
	}
	if (current != -1)
		return this->elems[current].second;
	return NULL_TVALUE;
}
// BC: First element is the searched one -- Theta(1)
// WC: Element not found -- Theta(nrElements)
// Total: O(nrElements)


TValue SortedMap::remove(TKey k) {
	int current = this->head;
	while (current != -1 && this->elems[current].first != k)
	{
		current = this->next[current];
	}
	if (current != -1)
	{
		TValue value = this->elems[current].second;
		if (current == this->head)
			this->head = this->next[current];
		else if (current == this->tail)
			this->tail = this->prev[current];
		else
			this->next[this->prev[current]] = this->next[current];
		freeP(current);
		this->nrElements--;
		return value;
	}
	return NULL_TVALUE;
}
// BC: First element to be deleted is the first one -- Theta(1)
// WC: Element not found -- Theta(nrElements)
// Total: O(nrElements)

int SortedMap::size() const {
	return this->nrElements;
}
// Theta(1)

bool SortedMap::isEmpty() const {
	if (this->nrElements == 0)
		return true;
	return false;
}
// Theta(1)

SMIterator SortedMap::iterator() const {
	return SMIterator(*this);
}
// Theta(1)

void SortedMap::filter(Condition cond)
{
	auto aux_elements = new TElem[this->cap];
	
	int current = this->head;
	while (current != -1)
	{
		if (cond(this->elems[current].second))
			aux_elements[current] = this->elems[current];
		current = this->next[current];
	}
	this->elems = aux_elements;
}
// Theta(nrElements)

SortedMap::~SortedMap() {
	delete[] this->elems;
	delete[] this->next;
	delete[] this->prev;
}
// Theta(nrElements)
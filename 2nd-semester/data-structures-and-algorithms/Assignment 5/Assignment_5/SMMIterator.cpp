#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>

SMMIterator::SMMIterator(const SortedMultiMap& d) : map(d){
	BSTNode* startNode = d.root;
	this->current = 0;
	this->capacity = 1000;
	this->nrElements = 0;
	this->elements = new BSTNode * [capacity];
	this->order(startNode);
}
// Theta(capacity)

void SMMIterator::first(){
	this->current = 0;
}
// Theta(1)

void SMMIterator::next(){
	if (this->current >= this->nrElements)
	{
		throw std::exception("Iterator exception!\n");
	}
	this->current++;
}
// Theta(1)

bool SMMIterator::valid() const{
	if (this->current >= this->nrElements) 
	{
		return false;
	}
	return true;
}
// Theta(1)

TElem SMMIterator::getCurrent() const{
	if (this->current >= this->nrElements) 
	{
		throw std::exception("Iterator exception!\n");
	}
	return this->elements[this->current]->elem;
}
// Theta(1)

void SMMIterator::add(BSTNode* element)
{
	if (this->nrElements == this->capacity)
		this->resize();
	this->elements[this->nrElements] = element;
	this->nrElements++;
}
// BC: Theta(1) - no resize
// WC: Theta(nrElements) - resize
// T: O(nrElements)

void SMMIterator::resize()
{
	BSTNode** newElements = new BSTNode* [this->capacity * 2];
	int i;
	for (i = 0; i < this->nrElements; i++) {
		newElements[i] = this->elements[i];
	}
	this->capacity = this->capacity * 2;
	delete[] this->elements;
	this->elements = newElements;
}
// Theta(nrElements)

void SMMIterator::order(BSTNode* startNode)
{
	if (startNode != nullptr) 
	{
		this->order(startNode->left);
		this->add(startNode);
		this->order(startNode->right);
	}
}
// Theta(nrElements);


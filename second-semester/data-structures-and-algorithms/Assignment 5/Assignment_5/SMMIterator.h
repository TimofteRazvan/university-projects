#pragma once

#include "SortedMultiMap.h"


class SMMIterator{
	friend class SortedMultiMap;
private:
	//DO NOT CHANGE THIS PART
	const SortedMultiMap& map;
	SMMIterator(const SortedMultiMap& map);
	int current;

	// For making an artificial vector that allows for easy ordering & moving through the elements
	BSTNode** elements;
	int nrElements;
	int capacity;

public:
	// sets current to first
	void first();

	// sets current to next index
	void next();

	// checks if the iterator is valid & can continue
	bool valid() const;

	// returns the current element
   	TElem getCurrent() const;

	// adds to the dynamic array of nodes for easy iterating
	void add(BSTNode* element);

	// resizes the dynamic array of nodes
	void resize();

	// orders the dynamic array of nodes
	void order(BSTNode* startNode);
};


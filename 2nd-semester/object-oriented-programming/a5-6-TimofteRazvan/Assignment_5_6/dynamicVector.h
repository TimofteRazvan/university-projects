#pragma once
#include "coat.h"

typedef Coat TElement;

class DynamicVector
{
private:
	TElement* elems;
	int size;
	int capacity;

public:
	// Dynamic Vector creator
	DynamicVector(int capacity = 10);

	// Dynamic Vector copier
	DynamicVector(const DynamicVector& v);

	// Dynamic Vector destroyer
	~DynamicVector();

	// Changes the meaning of the '=' operator
	DynamicVector& operator=(const DynamicVector& v);

	// Adds an element to the array of TElements
	void add(const TElement& e);

	// Deletes an element from the array
	void del(int i);

	// Gets the size of the array
	int getSize() const;

	// Gets all the elements of the array
	TElement* getAllElems() const;

private:
	// Resizes /doubles the array in case the size = capacity
	void resize(double factor = 2);
};

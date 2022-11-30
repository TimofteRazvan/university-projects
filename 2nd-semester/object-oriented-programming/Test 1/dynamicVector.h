#pragma once
//#include "coat.h"
#include <assert.h>

template <typename T>
class DynamicVector
{
private:
	T* elems;
	int size;
	int capacity;

public:
	// Dynamic Vector creator
	DynamicVector(int capacity = 10);

	// Dynamic Vector copier
	DynamicVector(const DynamicVector<T>& v);

	// Dynamic Vector destroyer
	~DynamicVector();

	// Changes the meaning of the '=' operator to assign the right vector to the left one
	DynamicVector<T>& operator=(const DynamicVector<T>& v);

	// Changes the meaning of the '+' operator to add to the array the element in question
	void operator+(const T& e);

	// Changes the [] operator
	T& operator[](int pos);

	// Adds an element to the array of TElements
	void add(const T& e);

	// Deletes an element from the array
	void del(int i);

	// Gets the size of the array
	int getSize() const;

	// Gets all the elements of the array
	T* getAllElems() const;

private:
	// Resizes /doubles the array in case the size = capacity
	void resize(double factor = 2);
};

/*
void operator+(const TElement& e, DynamicVector& v);
*/

template <typename T>
DynamicVector<T>::DynamicVector(int capacity)
{
	this->size = 0;
	this->capacity = capacity;
	this->elems = new T[capacity];
}

template <typename T>
DynamicVector<T>::DynamicVector(const DynamicVector<T>& v)
{
	this->size = v.size;
	this->capacity = v.capacity;
	this->elems = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->elems[i] = v.elems[i];
}

template <typename T>
DynamicVector<T>::~DynamicVector()
{
	delete[] this->elems;
}

template <typename T>
DynamicVector<T>& DynamicVector<T>::operator=(const DynamicVector<T>& v)
{
	if (this == &v)
		return *this;

	this->size = v.size;
	this->capacity = v.capacity;

	delete[] this->elems;
	this->elems = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->elems[i] = v.elems[i];

	return *this;
}

template <typename T>
void DynamicVector<T>::operator+(const T& e)
{
	if (this->size == this->capacity)
		this->resize();
	this->elems[this->size] = e;
	this->size++;
}

template <typename T>
T& DynamicVector<T>::operator[](int pos)
{
	return this->elems[pos];
}

/*
template <typename> T
void operator+(const TElement& e, DynamicVector<T>& v)
{
	v + e;
}
*/

template <typename T>
void DynamicVector<T>::add(const T& e)
{
	if (this->size == this->capacity)
		this->resize();
	this->elems[this->size] = e;
	this->size++;
}

template <typename T>
void DynamicVector<T>::del(int i)
{
	for (int j = i; j < this->size - 1; j++)
	{
		this->elems[j] = this->elems[j + 1];
	}
	this->size--;
}

template <typename T>
void DynamicVector<T>::resize(double factor)
{
	this->capacity *= static_cast<int>(factor);

	T* els = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		els[i] = this->elems[i];

	delete[] this->elems;
	this->elems = els;
}

template <typename T>
T* DynamicVector<T>::getAllElems() const
{
	return this->elems;
}

template <typename T>
int DynamicVector<T>::getSize() const
{
	return this->size;
}
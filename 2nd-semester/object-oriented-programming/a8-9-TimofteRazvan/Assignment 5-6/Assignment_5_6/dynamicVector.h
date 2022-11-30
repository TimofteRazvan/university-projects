/*
#pragma once
#include "coat.h"
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

	// Tests the dynamic vector & its elements
	void testDynamicVector();

private:
	// Resizes /doubles the array in case the size = capacity
	void resize(double factor = 2);
};

/*
void operator+(const TElement& e, DynamicVector& v);
*/

/*
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

/*
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

template <typename T>
void DynamicVector<T>::testDynamicVector()
{
	DynamicVector<Coat> dynamic;
	assert(dynamic.getSize() == 0);
	string red = "Red", yellow = "Yellow", blue = "Blue", black = "Black";
	string s = "S", l = "L", x = "X", xl = "XL", xxl = "XXL";
	Coat coat1(xxl, red, 250, 30, "Link_1");
	Coat coat2(xxl, yellow, 250, 30, "Link_2");
	Coat coat3(xxl, blue, 250, 30, "Link_3");
	Coat coat4(xxl, black, 250, 30, "Link_4");
	Coat coat5(xl, blue, 350, 30, "Link_5");
	Coat coat6(xl, red, 350, 30, "Link_6");
	Coat coat7(xl, yellow, 350, 30, "Link_7");
	Coat coat8(l, red, 300, 30, "Link_8");
	Coat coat9(l, black, 300, 30, "Link_9");
	Coat coat10(s, yellow, 300, 30, "Link_10");
	dynamic.add(coat1);
	dynamic.add(coat2);
	dynamic.add(coat3);
	dynamic.add(coat4);
	dynamic.add(coat5);
	dynamic.add(coat6);
	dynamic.add(coat7);
	dynamic.add(coat8);
	dynamic.add(coat9);
	dynamic.add(coat10);

	assert(dynamic.getSize() == 10);
	Coat* all = dynamic.getAllElems();
	assert(all[0].getColour() == red);
	assert(all[5].getPhotograph() == "Link_6");
	dynamic.del(5);
	assert(all[5].getPhotograph() == "Link_7");
	assert(dynamic.getSize() == 9);

	DynamicVector<Coat> copy;
	copy = dynamic;
	assert(copy.getSize() == 9);

	Coat* allcopy = copy.getAllElems();
	assert(allcopy[0].getColour() == red);
	assert(allcopy[5].getPhotograph() == "Link_7");
	copy.del(5);
	assert(copy.getSize() == 8);
	assert(allcopy[5].getPhotograph() == "Link_8");

	int old_capacity = dynamic.capacity;
	dynamic.resize();
	assert(old_capacity != dynamic.capacity);
	assert(copy.capacity != dynamic.capacity);

}
*/
#pragma once
#include "coat.h"


template<typename T1>
class Comparator
{
public:
	Comparator() {};
	virtual bool compare(T1 first, T1 second) const = 0;
	~Comparator() {};
};

template<typename T1>
class ComparatorByAscendingPrice : public Comparator<T1>
{
public:
	//ComparatorByAscendingPrice() {};
	bool compare(T1 first, T1 second) const override;
};

template<typename T1>
class ComparatorByAscendingQuantity : public Comparator<T1>
{
public:
	//ComparatorByAscendingQuantity() {};
	bool compare(T1 first, T1 second) const override;
};

template<typename T1>
inline bool ComparatorByAscendingPrice<T1>::compare(T1 first, T1 second) const
{
	if (first.getPrice() >= second.getPrice())
		return true;
	return false;
}

template<typename T1>
inline bool ComparatorByAscendingQuantity<T1>::compare(T1 first, T1 second) const
{
	if (first.getQuantity() <= second.getQuantity())
		return true;
	return false;
}

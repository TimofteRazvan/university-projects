#pragma once
//DO NOT INCLUDE SETITERATOR

//DO NOT CHANGE THIS PART
typedef int TElem;
typedef TElem TComp;
typedef bool(*Relation)(TComp, TComp);
#define NULL_TELEM -11111
class SortedSetIterator;


class SortedSet {
	friend class SortedSetIterator;
private:
	//TODO - Representation

public:
	//constructor
	// SO BASICALLY, the idea is that the relation is given at the beginning when the set is created and kept in mind always, it can't be changed while the set exists
	// So like, I have a Relation variable in 'private' named relation, which is going to be assigned when the set is created
	// Ex: define Relation relation; where 'relation' is rGreater or rLessEqual given as SortedSet s(rGreater) or SortedSet s(rLessEqual)
	// Should be easy, might have some hiccups with remove
	SortedSet(Relation r);

	//adds an element to the sorted set
	//if the element was added, the operation returns true, otherwise (if the element was already in the set) 
	//it returns false
	bool add(TComp e);

	
	//removes an element from the sorted set
	//if the element was removed, it returns true, otherwise false
	// To remove, basically stop when current.next is the wanted removable node, in which case we destroy the node and change the current.next to current.next.next or whatever, in opposite order
	bool remove(TComp e);

	//checks if an element is in the sorted set
	bool search(TElem elem) const;


	//returns the number of elements from the sorted set
	int size() const;

	//checks if the sorted set is empty
	bool isEmpty() const;

	//returns an iterator for the sorted set
	SortedSetIterator iterator() const;

	// destructor
	~SortedSet();


};

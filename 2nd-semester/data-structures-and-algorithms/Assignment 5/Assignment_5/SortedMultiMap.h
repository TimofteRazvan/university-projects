#pragma once
//DO NOT INCLUDE SMMITERATOR

//DO NOT CHANGE THIS PART
#include <vector>
#include <utility>
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -111111
#define NULL_TELEM pair<TKey, TValue>(-111111, -111111);
using namespace std;
class SMMIterator;
typedef bool(*Relation)(TKey, TKey);

struct BSTNode {
    TElem elem;
    BSTNode* parent;
    BSTNode* left;
    BSTNode* right;
};

class SortedMultiMap {
	friend class SMMIterator;
    private:
        BSTNode* root;
        Relation relation;
        int nrElements;
        
    public:

    // constructor
    SortedMultiMap(Relation r);

    //finds the max(right-most) value of the left side of the given node, used in remove
    BSTNode* utilMax(BSTNode* currentNode);

	//adds a new key value pair to the sorted multi map
    void add(TKey c, TValue v);

	//returns the values belonging to a given key
    vector<TValue> search(TKey c) const;

    //used in recursive searching through the BST
    BSTNode* searchNode(TKey c, TValue v);

	//removes a key value pair from the sorted multimap
	//returns true if the pair was removed (it was part of the multimap), false if nothing is removed
    bool remove(TKey c, TValue v);

    // removes if given node is root
    void removeRoot(BSTNode* toRemove);

    // removes if given node is node
    void removeNode(BSTNode* toRemove);

    //returns the number of key-value pairs from the sorted multimap
    int size() const;

    //verifies if the sorted multi map is empty
    bool isEmpty() const;

    // returns an iterator for the sorted multimap. The iterator will returns the pairs as required by the relation (given to the constructor)	
    SMMIterator iterator() const;

    // deletes the SMM recursively
    void deleteSMM(BSTNode* currentNode);

    void replace(TKey k, TValue oldValue, TValue newValue);

    // destructor
    ~SortedMultiMap();
};

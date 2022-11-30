#include "Matrix.h"
#include <exception>
#include <iostream>
using namespace std;


Matrix::Matrix(int nrLines, int nrCols) : cols(nrCols), lines(nrLines)
{
	this->capacity = this->lines * this->cols;
    this->size = 0;
    this->arr = new HashNode*[this->capacity];
    for (int i = 0; i < this->capacity; i++)
    {
        this->arr[i] = NULL;
    }
}
// Theta(capacity)

int Matrix::nrLines() const {
	return this->lines;
}
// Theta(1)

int Matrix::nrColumns() const {
	return this->cols;
}
// Theta(1)

TElem Matrix::element(int i, int j) const 
{
    if (i < 0 || i >= lines || j < 0 || j > cols)
        throw exception("Invalid matrix indexes!\n");
    int key = (i + j) * (i + j + 1) / 2 + i;
    int hashIndex = (key % capacity);

    int counter = 0;

    while (arr[hashIndex] != NULL) {

        // if counter > capacity
        if (counter++ > capacity)
            break;

        // if found
        if (arr[hashIndex]->key == key)
            return arr[hashIndex]->elem;

        for (int j = 0; j < capacity; j++)
        {
            int newHash = (hashIndex + j * j) % capacity;
            if (arr[newHash] == NULL)
            {
                return NULL_TELEM;
            }
            else if (arr[newHash]->key == key)
                return arr[newHash]->elem;
        }
    }
    return NULL_TELEM;
    /*
    int key = (i + j) * (i + j + 1) / 2 + i;
    int hashIndex = key % this->capacity;

    if (this->arr[hashIndex] == NULL)
        return NULL_TELEM;
    return this->arr[hashIndex]->elem;
    */
}
// BC: Theta(1) - Element(0,0) is not null
// WC: Theta(N * L); N - length of array; L - size of hash table - Element is not found
// T: O(N * L)

TElem Matrix::modify(int i, int j, TElem e) {
    {
        if (i < 0 || i >= lines || j < 0 || j > cols)
            throw exception("Invalid matrix indexes!\n");
        int key = (i + j) * (i + j + 1) / 2 + i;
        struct HashNode* temp = new HashNode;
        temp->key = key;
        temp->elem = e;
        
        int hashIndex = key % capacity;
        
        TElem old = NULL_TELEM;
        if (this->arr[hashIndex] == NULL)
        {
            this->size++;
            this->arr[hashIndex] = temp;
        }
        else if (arr[hashIndex]->key == key)
        {
            old = this->arr[hashIndex]->elem;
            this->arr[hashIndex] = temp;
        }
        else
        {
            for (int j = 0; j < capacity; j++)
            {
                int newHash = (hashIndex + j * j) % capacity;
                if (arr[newHash] == NULL || arr[newHash]->key == key)
                {
                    this->arr[newHash] = temp;
                    break;
                }
                    
            }
            /*
            while (arr[hashIndex] != NULL && arr[hashIndex]->key != key) {
                hashIndex++;
                hashIndex %= capacity;
            }
            */
        }
        
        return old;
    }
}
// BC: Theta(1) - Position is un-occupied or occupied by element with same key
// WC: Theta(N * L); N - length of array; L - size of hashtable; - Position occupied, everything is occupied
// T: O(N * L)

std::pair<int, int> Matrix::positionOf(TElem elem) const
{
    int key;
    for (int i = 0; i < capacity; i++)
        if (arr[i] != NULL && arr[i]->elem == elem)
            key = arr[i]->key;
    pair<int, int> position(-1, -1);
    for (int i = 0; i < lines; i++)
        for (int j = 0; j < cols; j++)
            if ((i + j) * (i + j + 1) / 2 + i == key)
            {
                pair<int, int> keyposition(i, j);
                position = keyposition;
                return position;
            }
                
    return position;
}
// BC: Theta(1) - Element is the first pair (0,0)
// WC: Theta(capacity) - Element isn't found
// T: O(capacity)



#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>
#include <vector>
#include <exception>
using namespace std;

SortedMultiMap::SortedMultiMap(Relation r)
{
	this->root = nullptr;
	this->relation = r;
	this->nrElements = 0;
}
// Theta(1);

// Finds "maximum" on left side of given node, returns right-most left node
BSTNode* SortedMultiMap::utilMax(BSTNode* current)
{
	BSTNode* node = current->left;
	bool ok = false;
	while (node->right != nullptr) 
	{
		node = node->right;
		ok = true;
	}
	if (ok) 
	{
		node->parent->right = nullptr;
	}
	return node;
}
// Theta(height)

void SortedMultiMap::add(TKey c, TValue v) 
{
	// BSTNode* root = this->root;
	if (this->root == nullptr) 
	{
		// BSTNode* newNode = new BSTNode();
		auto* newNode = new BSTNode();
		newNode->elem.first = c;
		newNode->elem.second = v;
		this->root = newNode;
		this->nrElements++;
	}

	else 
	{
		BSTNode* current = this->root;
		BSTNode* previous = nullptr;
		
		while (current != nullptr) 
		{

			if (this->relation(c, current->elem.first)) 
			{
				previous = current;
				current = current->left;
			}

			else 
			{
				previous = current;
				current = current->right;
			}
		}
		// BST* newNode = new BSTNode();
		auto* newNode = new BSTNode();
		newNode->elem.first = c;
		newNode->elem.second = v;
		newNode->parent = previous;
		this->nrElements++;
		if (this->relation(c, previous->elem.first)) 
		{

			previous->left = newNode;
		}
		else 
		{

			previous->right = newNode;
		}
	}
}
// BC: Theta(1) - Node to be added is the root
// WC: Theta(height) - Node to be added is at the very end of the tree
// T: O(height)

vector<TValue> SortedMultiMap::search(TKey c) const 
{
	vector<TValue> matching;
	BSTNode* current = this->root;
	while (current != nullptr) 
	{
		if (this->relation(c, current->elem.first)) 
		{

			if (c == current->elem.first) 
			{
				matching.push_back(current->elem.second);
			}
			current = current->left;
		}
		else 
		{

			current = current->right;
		}
	}
	return matching;
}
// BC: Theta(1) - First node is the searched one
// WC: Theta(nrElements) - Searched node is inexistent
// T: O(nrElements)

BSTNode* SortedMultiMap::searchNode(TKey c, TValue v)
{
	BSTNode* result = nullptr;
	BSTNode* current = this->root;
	bool found = false;
	while (current != nullptr && !found) 
	{
		if (this->relation(c, current->elem.first)) 
		{
			if (c == current->elem.first) 
			{
				if (v == current->elem.second) 
				{
					result = current;
					found = true;
				}
			}
			current = current->left;
		}
		else 
		{
			current = current->right;
		}
	}
	return result;
}
// BC: Theta(1) - First node is the searched one
// WC: Theta(nrElements) - Searched node is inexistent
// T: O(nrElements)

bool SortedMultiMap::remove(TKey c, TValue v) 
{
	bool removed = false;
	BSTNode* node = this->searchNode(c, v); // WC: nrElements
	if (node != nullptr)
	{
		if (node == this->root) 
		{
			this->removeRoot(node);
		}
		else 
		{
			this->removeNode(node);
		}
		removed = true;
		this->nrElements--;
	}
	return removed;
}
// BC: Theta(1) - Element is root
// WC: Theta(nrElements + height) - Element is near/at the bottom
// T: O(nrElements + height)

void SortedMultiMap::removeRoot(BSTNode* node)
{
	if (node->right != nullptr && node->left != nullptr) 
	{
		BSTNode* replacement = this->utilMax(node);
		// check if the left side has any right sides
		if (replacement == node->left) {
			// No new links
			replacement->right = node->right;
			replacement->right->parent = replacement;
			replacement->parent = nullptr;
			BSTNode* toDelete = node;
			this->root = replacement;
			delete toDelete;
		}
		else 
		{
			// New links
			replacement->right = node->right;
			replacement->right->parent = replacement;
			replacement->parent = nullptr;
			replacement->left = node->left;
			replacement->left->parent = replacement;
			BSTNode* toDelete = node;
			this->root = replacement;
			delete toDelete;
		}
	}
	else if (node->right == nullptr && node->left == nullptr) 
	{
		BSTNode* toDelete = node;
		this->root = nullptr;
		delete toDelete;
	}
	else 
	{
		if (node->right != nullptr) 
		{
			// Right root
			BSTNode* newRoot = node->right;
			BSTNode* toDelete = node;
			newRoot->parent = nullptr;
			this->root = newRoot;
			delete toDelete;
		}
		else 
		{
			// Left root
			BSTNode* newRoot = node->left;
			BSTNode* toDelete = node;
			newRoot->parent = nullptr;
			this->root = newRoot;
			delete toDelete;
		}
	}
}
// BC: Theta(1)
// WC: Theta(height)
// T: O(height)

void SortedMultiMap::removeNode(BSTNode* node)
{
	if (node->right != nullptr && node->left != nullptr) {
		// BSTNode replacement = this->utilMax(node);
		// BSTNode* toRemove = node;
		// BSTNode* root = this->root;
		BSTNode* replacement = this->utilMax(node);
		if (replacement == node->left) {
			// No new links
			replacement->right = node->right;
			replacement->right->parent = replacement;
			replacement->parent = node->parent;
			if (replacement->parent->left == node) {
				// Replace left child if the removed node was on the left of the parent
				replacement->parent->left = replacement;
			}
			else {
				// Replace right child if the removed node was on the right of the parent
				replacement->parent->right = replacement;
			}
			BSTNode* toDelete = node;
			node = replacement;
			delete toDelete;
			// replacement->left = node->left;
			// replacement->left->parent = replacement;
		}
		else {
			// New links
			replacement->right = node->right;
			replacement->right->parent = replacement;
			replacement->parent = node->parent;
			if (replacement->parent->left == node) {
				// Replace left child if the removed node was on the left of the parent
				replacement->parent->left = replacement;
			}
			else {
				// Replace right child if the removed node was on the right of the parent
				replacement->parent->right = replacement;
			}
			replacement->left = node->left;
			replacement->left->parent = replacement;
			BSTNode* toDelete = node;
			node = replacement;
			delete toDelete;
		}
	}
	else if (node->right == nullptr && node->left == nullptr) {
		// if a leaf node
		if (node->parent->left == node) {
			// if on the left of the parent
			// node->left = nullptr;
			node->parent->left = nullptr;
			delete node;
		}
		else if (node->parent->right == node) {
			// if on the right of the parent
			// node->right = nullptr;
			node->parent->right = nullptr;
			delete node;
		}
	}
	else {
		if (node->parent->left == node) {
			// if node is on the left
			if (node->left != nullptr) {
				// right is empty, link left to parent of node
				node->parent->left = node->left;
				node->left->parent = node->parent;
			}
			else {
				// left is empty, link right to parent of node
				node->parent->left = node->right;
				node->right->parent = node->parent;
			}
			delete node;
		}
		else {
			// if node is on the right
			if (node->left != nullptr) {
				// Right is empty, link left to parent of node
				// node->parent->left = node->left;
				node->parent->right = node->left;
				// node->left->parent = node->parent->left;
				node->left->parent = node->parent;
			}
			else {
				// Left is empty, link right to parent of node
				// node->parent->right = node->right;
				node->parent->right = node->right;
				// node->right->parent = node->parent->right;
				node->right->parent = node->parent;
			}
			delete node;
		}
	}
}
// BC: Theta(1)
// WC: Theta(height)
// T: O(height)

int SortedMultiMap::size() const 
{
	return this->nrElements;
}
// Theta(1)

bool SortedMultiMap::isEmpty() const 
{
	if (this->nrElements == 0)
		return true;
	return false;
}
// Theta(1)

SMMIterator SortedMultiMap::iterator() const 
{
	return SMMIterator(*this);
}
// Theta(1)

void SortedMultiMap::deleteSMM(BSTNode* currentNode) 
{
	if (currentNode != nullptr) 
	{
		if (currentNode->left != nullptr) 
		{
			this->deleteSMM(currentNode->left);
		}
		if (currentNode->right != nullptr) 
		{
			this->deleteSMM(currentNode->right);
		}
	}
}
// Theta(nrElements)

void SortedMultiMap::replace(TKey k, TValue oldValue, TValue newValue)
{
	BSTNode* current = this->root;
	bool found = false;
	while (current != nullptr && !found)
	{
		if (this->relation(k, current->elem.first))
		{
			if (k == current->elem.first)
			{
				if (oldValue == current->elem.second)
				{
					current->elem.second = newValue;
					found = true;
				}
			}
			current = current->left;
		}
		else
		{
			current = current->right;
		}
	}
}
// BC: Theta(1) - Element is the first to be found
// WC: Theta(nrElements) - Element is not found
// T: O(nrElements)

SortedMultiMap::~SortedMultiMap() 
{
	this->deleteSMM(this->root);
}
// Theta(nrElements)
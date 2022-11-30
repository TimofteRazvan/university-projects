#pragma once
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct {
	char* name;
	char* category;
	int quantity;
	char* date;
}Product;

/// Creates a product 'object'. Returns it.
Product* createProduct(char* name, char* category, int quantity, char* date);

/// Destroys a product and frees the memory.
void destroyProduct(Product* prod);

/// Returns the name of the product.
char getName(Product* prod);

/// Returns the type of the product.
char getType(Product* prod);

/// Returns the quantity of the product.
int getQuantity(Product* prod);

/// Returns the date of the product.
char getDate(Product* prod);

/// Returns a copy of the product.
Product* copyProduct(Product* prod);


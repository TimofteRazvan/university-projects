#include "domain.h"

Product* createProduct(char* name, char* category, int quantity, char* date)
{
	Product* prod = malloc(sizeof(Product));
	prod->name = malloc(strlen(name) + 1);
	prod->category = malloc(strlen(category) + 1);
	prod->date = malloc(strlen(date) + 1);
	strcpy(prod->name, name);
	strcpy(prod->category, category);
	strcpy(prod->date, date);
	prod->quantity = quantity;
	return prod;
}

void destroyProduct(Product* prod)
{
	free(prod->name);
	free(prod->category);
	free(prod->date);
	free(prod);
}

char getName(Product* prod)
{
	return prod->name;
}

char getType(Product* prod)
{
	return prod->category;
}

int getQuantity(Product* prod)
{
	return prod->quantity;
}

char getDate(Product* prod)
{
	return prod->date;
}

Product* copyProduct(Product* prod)
{
	if (prod == NULL)
		return;
	Product* prod_copy = createProduct(prod->name, prod->category, prod->quantity, prod->date);
	return prod_copy;
}
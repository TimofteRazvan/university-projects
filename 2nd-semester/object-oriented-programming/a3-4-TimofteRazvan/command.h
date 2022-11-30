#pragma once
#include "domain.h"

typedef struct {
	char* line;
	Product* product;
	Product* product_new;
}Command;

// Creates a new command, which is used in 'history' for handling past commands given by the user.
// Product is the product added / deleted, product_new is used for updating.
Command* createCommand(char* line, Product* product, Product* product_new);

// Destroys the command to free up space similar to Products for the Repository.
void destroyCommand(Command* command);
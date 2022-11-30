#pragma once
#include "repository.h"
#include "history.h"

typedef struct {
	Repository* repo;
	History* history;
	History* future;
}Controller;

/// Creates a controller type 'object. Returns it.
Controller* createController(Repository* repo, History* history, History* future);

/// Destroys the controller and frees up memory.
void destroyController(Controller* ctrl);

/// Adds a product to the controller, which will communicate with the repository.
/// Return values same as in the repository function.
int addProductCtrl(char* name, char* category, int quantity, char* date);

/// Deletes a product from the controller, which will communicate with the repository.
/// Return values same as in the repository function.
int deleteProductCtrl(Controller* ctrl, char* name, char*category);

/// Updates a product from the controller, which will communicate with the repository.
/// Return values same as in the repository function.
int updateProductCtrl(Controller* ctrl, char* name, char* category, char* new_name, char* new_category, int new_quantity, char* new_date);

/// Searches a product via given string.
/// Return values same as in the repository function.
Repository* searchProductCtrl(Controller* ctrl, char* string);

// Searches by category within the controller and works with the dates.
Repository* searchCategoryCtrl(Controller* ctrl, char* string, char* exp_date, char* cur_date);

/// Orders a given repository via the 'quantity' quantifier.
void orderFoundProducts(Repository* found);

/// Orders the repository in ascending order by name.
void orderProductsName(Controller* ctrl);

// Gets the 'list' of products / repo for better handling of privacy.
Repository* getProducts(Controller* ctrl);

// Generates around 10 pre-determined products and inserts them into the Repository.
void generateProducts(Controller* ctrl);

// Undoes the last action. Returns 1 if no more undos are possible.
// Also creates the timeline for the redo function within itself.
int undoCtrl(Controller* ctrl);

// Redoes the last undone action. Returns 1 if no more redos are possible.
int redoCtrl(Controller* ctrl);

// A test function for the controller's functions. More need to be added after finally finishing everything.
void testController();
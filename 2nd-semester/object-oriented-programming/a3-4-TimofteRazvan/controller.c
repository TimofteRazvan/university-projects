#include "controller.h"

Controller* createController(Repository* repo, History* history, History* future)
{
	Controller* ctrl = malloc(sizeof(Controller));
	ctrl->repo = repo;
	ctrl->history = history;
	ctrl->future = future;
	return ctrl;
}

void destroyController(Controller* ctrl)
{
	destroyRepo(ctrl->repo);
	//destroyHistory(ctrl->history);
	//destroyHistory(ctrl->future);
	free(ctrl);
}

int addProductCtrl(Controller* ctrl, char* name, char* category, int quantity, char* date)
{
	Product* product = createProduct(name, category, quantity, date);
	Product* product_new = createProduct(name, category, quantity, date);
	char add[4] = "add";
	Command* command = createCommand(add, product, product_new);
	addCommand(ctrl->history, command);
	return addProduct(ctrl->repo, product);
}

int deleteProductCtrl(Controller* ctrl, char* name, char*category)
{
	char* date = "";
	char delete[7] = "delete";
	int quantity = 0;
	for (int i = 0; i < ctrl->repo->size; i++)
	{
		if (strcmp(ctrl->repo->array[i].name, name) == 0 && strcmp(ctrl->repo->array[i].category, category) == 0)
		{
			date = ctrl->repo->array[i].date;
			quantity = ctrl->repo->array[i].quantity;
		}
	}
	Product* product = createProduct(name, category, quantity, date);
	Product* product_new = createProduct(name, category, quantity, date);
	Command* command = createCommand(delete, product, product_new);
	addCommand(ctrl->history, command);
	return deleteProduct(ctrl->repo, name, category);
}

int updateProductCtrl(Controller* ctrl, char* name, char* category, char* new_name, char* new_category, int new_quantity, char* new_date)
{
	char* date = "";
	char update[7] = "update";
	int quantity = 0;
	for (int i = 0; i < ctrl->repo->size; i++)
	{
		if (strcmp(ctrl->repo->array[i].name, name) == 0 && strcmp(ctrl->repo->array[i].category, category) == 0)
		{
			date = ctrl->repo->array[i].date;
			quantity = ctrl->repo->array[i].quantity;
		}
	}
	Product* product = createProduct(name, category, quantity, date);
	Product* product_new = createProduct(new_name, new_category, new_quantity, new_date);
	Command* command = createCommand(update, product, product_new);
	addCommand(ctrl->history, command); 
	return updateProduct(ctrl->repo, name, category, new_name, new_category, new_quantity, new_date);
}

Repository* searchProductCtrl(Controller* ctrl, char* string)
{
	return searchProduct(ctrl->repo, string);
}

Repository* searchCategoryCtrl(Controller* ctrl, char* string, char* exp_date, char* cur_date)
{
	return searchCategory(ctrl->repo, string, exp_date, cur_date);
}

void orderFoundProducts(Repository* found)
{
	for (int i = 0; i < found->size - 1; i++)
		for (int j = i + 1; j < found->size; j++)
			if (found->array[i].quantity > found->array[j].quantity)
			{
				// Product* copy = copyProduct(found->array[i]);
				// found->array[i] = copyProduct(found->array[j]);
				Product copy = found->array[i];
				found->array[i] = found->array[j];
				found->array[j] = copy;
			}
}

void orderProductsName(Controller* ctrl)
{
	for (int i = 0; i < ctrl->repo->size - 1; i++)
		for (int j = i + 1; j < ctrl->repo->size; j++)
			if (strcmp(ctrl->repo->array[i].name, ctrl->repo->array[j].name) > 0)
			{
				// Product* copy = copyProduct(ctrl->repo->array[i]);
				// ctrl->repo->array[i] = copyProduct(ctrl->repo->array[j]);
				Product copy = ctrl->repo->array[i];
				ctrl->repo->array[i] = ctrl->repo->array[j];
				ctrl->repo->array[j] = copy;
			}
}

Repository * getProducts(Controller * ctrl)
{
	return ctrl->repo;
}

int undoCtrl(Controller* ctrl)
{
	if (ctrl->history->size > 0)
	{
		Product* product = ctrl->history->array[ctrl->history->size - 1]->product;
		Product* product_new = ctrl->history->array[ctrl->history->size - 1]->product_new;
		if (strstr(ctrl->history->array[ctrl->history->size - 1]->line, "add"))
		{
			deleteProduct(ctrl->repo, product->name, product->category);
			Command* command = createCommand("delete", product, product_new);
			addCommand(ctrl->future, command);
		}
		else
			if (strstr(ctrl->history->array[ctrl->history->size - 1]->line, "delete"))
			{
				addProduct(ctrl->repo, product);
				Command* command = createCommand("add", product, product_new);
				addCommand(ctrl->future, command);
			}
			else
				if (strstr(ctrl->history->array[ctrl->history->size - 1]->line, "update"))
				{
					updateProduct(ctrl->repo, product_new->name, product_new->category, product->name, product->category,
						product->quantity, product->date);
					Command* command = createCommand("update", product, product_new);
					addCommand(ctrl->future, command);
				}
		ctrl->history->size--;
		return 0;
	}
	else
		return 1;
}

int redoCtrl(Controller* ctrl)
{
	if (ctrl->future->size > 0)
	{
		Product* product = ctrl->future->array[ctrl->future->size - 1]->product;
		Product* product_new = ctrl->future->array[ctrl->future->size - 1]->product_new;
		if (strstr(ctrl->future->array[ctrl->future->size - 1]->line, "add"))
		{
			deleteProduct(ctrl->repo, product->name, product->category);
			Command* command = createCommand("delete", product, product_new);
			addCommand(ctrl->history, command);
		}
		else
			if (strstr(ctrl->future->array[ctrl->future->size - 1]->line, "delete"))
			{
				addProduct(ctrl->repo, product);
				Command* command = createCommand("add", product, product_new);
				addCommand(ctrl->history, command);
			}
			else
				if (strstr(ctrl->future->array[ctrl->future->size - 1]->line, "update"))
				{
					updateProduct(ctrl->repo, product->name, product->category, product_new->name, product_new->category,
						product_new->quantity, product_new->date);
					Command* command = createCommand("update", product, product_new);
					addCommand(ctrl->history, command);
				}
		ctrl->future->size--;
		return 0;
	}
	else
		return 1;
}

void generateProducts(Controller* ctrl)
{
	Product* product1 = createProduct("Rosie", "Controversial", 25, "12.10.2022");
	Product* product2 = createProduct("Caramida", "Controversial", 10, "1.1.9999");
	Product* product3 = createProduct("Varza", "Legume", 32, "10.9.2022");
	Product* product4 = createProduct("Salata", "Legume", 31, "10.8.2022");
	Product* product5 = createProduct("Morcov", "Legume", 30, "10.12.2022");
	Product* product6 = createProduct("Cartof", "Legume", 29, "10.10.2021");
	Product* product7 = createProduct("Rosie_Albastra", "Biohazard", 500, "10.10.2027");
	Product* product8 = createProduct("Banana", "Fructe", 36, "30.6.2022");
	Product* product9 = createProduct("Kiwi", "Fructe", 38, "11.11.2033");
	Product* product10 = createProduct("Rosie_Verde", "Controversial", 5, "28.10.2022");
	addProduct(ctrl->repo, product1);
	addProduct(ctrl->repo, product2);
	addProduct(ctrl->repo, product3);
	addProduct(ctrl->repo, product4);
	addProduct(ctrl->repo, product5);
	addProduct(ctrl->repo, product6);
	addProduct(ctrl->repo, product7);
	addProduct(ctrl->repo, product8);
	addProduct(ctrl->repo, product9);
	addProduct(ctrl->repo, product10);
}

void testController()
{
	int capacity = 100;
	History* history = createHistory();
	History* future = createHistory();
	Repository* repo = createRepo(capacity);
	Controller* ctrl = createController(repo, history, future);
	Product* product = createProduct("Rosie", "Legume", 32, "10.10.2022");

	addProductCtrl(ctrl, "Rosie", "Legume", 32, "10.10.2022");
	if (ctrl->repo->size == 0)
		printf("Failed Adding Test! Size is inconsistent! \n");
	if (ctrl->repo->array[0].name == product->name)
		printf("Failed Adding Test! Elements are inconsistent! \n");

	deleteProductCtrl(ctrl, "Rosie", "Legume");
	if (ctrl->repo->size != 0)
		printf("Failed Deleting Test! Size is inconsistent! \n");

	undoCtrl(ctrl);
	if (ctrl->repo->size == 0)
		printf("Failed Undo Test! Size is inconsistent! \n");

	redoCtrl(ctrl);
	if (ctrl->repo->size != 0)
		printf("Failed Redo Test! Size is inconsistent! \n");


	updateProductCtrl(ctrl, "Rosie", "Legume", "Mar", "Fructe", 22, "11.12.2022");
	if (ctrl->repo->array[0].name == "Rosie")
		printf("Failed Updating Test! Object remained the same! \n");

	Repository* found = searchProductCtrl(ctrl, NULL);
	if (ctrl->repo != found)
		printf("Failed Searching Test! Empty string should return the base repo! \n");

	Product* product_2 = createProduct("Rosie Verde", "Legume", 30, "12.12.2022");
	addProductCtrl(ctrl, "Rosie Verde", "Legume", 30, "12.12.2022");

	orderFoundProducts(found);

	if (found->array[1].quantity >= found->array[0].quantity)
		printf("Failed Sorting Test! The products have not been ordered in ascending order by quantity! \n");

	destroyController(ctrl);

	printf("Controller successfully tested.");
}

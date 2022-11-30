#include "command.h"

Command* createCommand(char* line, Product* product, Product* product_new)
{
    Command* command = malloc(sizeof(Command));
    command->line = malloc(strlen(line) + 1);
    command->product = malloc(sizeof(Product));
    command->product_new = malloc(sizeof(Product));
    strcpy(command->line, line);
    command->product = product;
    command->product_new = product_new;
    return command;
}

void destroyCommand(Command* command)
{
    if (command == NULL)
        return;
    free(command->line);
    destroyProduct(command->product);
    destroyProduct(command->product_new);
    free(command);
}

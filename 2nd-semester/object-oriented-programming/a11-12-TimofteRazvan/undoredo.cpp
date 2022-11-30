#include "undoredo.h"

UndoRedoAdd::UndoRedoAdd(const Coat& coat, Repo& newRepo) : coat(coat), repo(newRepo) {

}

void UndoRedoAdd::undo() {
    this->repo.deleteCoat(this->coat.getPhotograph());
}

void UndoRedoAdd::redo() {
    this->repo.addCoat(this->coat);
}

UndoRedoRemove::UndoRedoRemove(const Coat& coat, Repo& newRepo) : coat(coat), repo(newRepo) {

}

void UndoRedoRemove::undo() {
    this->repo.addCoat(this->coat);
}

void UndoRedoRemove::redo() {
    this->repo.deleteCoat(this->coat.getPhotograph());
}

UndoRedoUpdate::UndoRedoUpdate(const Coat& oldCoat, const Coat& newCoat, Repo& newRepo) : oldCoat(oldCoat), 
newCoat(newCoat), repo(newRepo) {

}

void UndoRedoUpdate::undo() {
    this->repo.updateCoat(this->newCoat.getPhotograph(), this->oldCoat.getSize(), this->oldCoat.getColour(), 
        this->oldCoat.getPrice(), this->oldCoat.getQuantity(), this->oldCoat.getPhotograph());
}

void UndoRedoUpdate::redo() {
    this->repo.updateCoat(this->oldCoat.getPhotograph(), this->newCoat.getSize(), this->newCoat.getColour(), 
        this->newCoat.getPrice(), this->newCoat.getQuantity(), this->newCoat.getPhotograph());
}

UndoRedoUser::UndoRedoUser(const Coat& coat, Repo& newRepo) : coat(coat), repo(newRepo) {
}

void UndoRedoUser::undo() {
    this->repo.deleteFromCart(this->coat);
}

void UndoRedoUser::redo() {
    this->repo.addToCart(this->coat);
}
#pragma once
#include "repository.h"

class UndoRedoAction {
public:
    virtual void undo() = 0;
    virtual void redo() = 0;
    virtual ~UndoRedoAction() = default;
};

class UndoRedoAdd : public UndoRedoAction {
private:
    Coat coat;
    Repo& repo;
public:
    UndoRedoAdd(const Coat& coat, Repo& newRepo);
    void undo() override;
    void redo() override;
    ~UndoRedoAdd() override = default;
};

class UndoRedoRemove : public UndoRedoAction {
private:
    Coat coat;
    Repo& repo;
public:
    UndoRedoRemove(const Coat& coat, Repo& newRepo);
    void undo() override;
    void redo() override;
    ~UndoRedoRemove() override = default;
};

class UndoRedoUpdate : public UndoRedoAction {
private:
    Coat oldCoat;
    Coat newCoat;
    Repo& repo;
public:
    UndoRedoUpdate(const Coat& oldCoat, const Coat& newCoat, Repo& newRepo);
    void undo() override;
    void redo() override;
    ~UndoRedoUpdate() override = default;
};

class UndoRedoUser : public UndoRedoAction {
private:
    Coat coat;
    Repo& repo;
public:
    UndoRedoUser(const Coat& coat, Repo& newRepo);
    void undo() override;
    void redo() override;
    ~UndoRedoUser() override = default;
};
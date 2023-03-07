package com.example.assignment_7l;

import Domain.ADT.Dictionary.MyDictionary;
import Domain.ADT.Table.MyLatchTable;
import Domain.ADT.Heap.MyHeap;
import Domain.ADT.List.MyList;
import Domain.ADT.Stack.MyStack;
import Domain.Expression.*;
import Domain.ProgramState.PrgState;
import Domain.Statement.*;
import Domain.Type.BoolType;
import Domain.Type.IntType;
import Domain.Type.RefType;
import Domain.Type.StringType;
import Domain.Value.BoolValue;
import Domain.Value.IntValue;
import Domain.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;
import Controller.Controller;
import Exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PROGRAM CHOOSER FX:CONTROLLER CLASS.
 * This class is used in creating the window that will allow the user to click and select a program.
 */
public class ProgramChooserController {
    private ProgramExecutorController programExecutorController;

    @FXML
    private ListView<IStatement> programsListView;

    @FXML
    private Button displayButton;

    /**
     * Sets the Executor Controller
     * @param programExecutorController : The controller (given in Main.java) (FXML LOADER)
     */
    public void setProgramExecutorController(ProgramExecutorController programExecutorController) {
        this.programExecutorController = programExecutorController;
    }

    /**
     * Used in getting a list of all the programs that can be run (taken from the Interpreter class)
     * @return : ObservableList of runnable programs
     */
    @FXML
    private ObservableList<IStatement> getAllStatements() {
        List<IStatement> iStatementList = new ArrayList<>();

        /*
         * The first example
         * int v;
         * v=2;
         * print(v);
         */
        IStatement ex1 = new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        iStatementList.add(ex1);

        /*
         * The second example
         * int a;
         * int b;
         * a=2 + 3 * 5;
         * b=a + 1;
         * print(b);
         */
        IStatement ex2 = new CompoundStatement(new VarDeclareStatement("a",new IntType()),
                new CompoundStatement(new VarDeclareStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)), new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));
        iStatementList.add(ex2);

        /*
         * The third example
         * boolean a;
         * int v;
         * a=true;
         * if(a)
         *     v=2;
         * else
         *     v=3;
         * print(v);
         */
        IStatement ex3 = new CompoundStatement(new VarDeclareStatement("a", new BoolType()),
                new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        iStatementList.add(ex3);

        /*
         * The fourth example
         * string varf;
         * varf = "test.in";
         * OpenReadFile(varf);
         * int varc;
         * ReadFile(varf, varc);
         * print(varc);
         * ReadFile(varf, varc);
         * print(varc);
         * CloseReadFile(varf);
         */
        IStatement ex4 = new CompoundStatement(new VarDeclareStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")),
                                new CompoundStatement(new VarDeclareStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFile(new VariableExpression("varf"))))))))));
        iStatementList.add(ex4);

        /*
         * The fifth example
         * int a;
         * int b;
         * a=5;
         * b=7;
         * if(a > b)
         *      print(a);
         * else
         *      print(b);
         */
        IStatement ex5 = new CompoundStatement(new VarDeclareStatement("a", new IntType()),
                new CompoundStatement(new VarDeclareStatement("b", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new AssignStatement("b", new ValueExpression(new IntValue(7))),
                                        new IfStatement(new RelationalExpression(">", new VariableExpression("a"), new VariableExpression("b")),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        iStatementList.add(ex5);

        /*
         * The sixth example (while statement)
         * int v;
         * v=4;
         * while(v > 0){
         *      print(v);
         *      v = v-1;
         * }
         * print(v);
         */
        IStatement ex6 = new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntValue(0))),
                                    new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                        new PrintStatement(new VariableExpression("v")))));
        iStatementList.add(ex6);

        /*
         * The seventh statement (ref & heap)
         * Ref(int) v;
         * New(v, 20);
         * Ref(Ref(int)) a;
         * New(a, v);
         * print(v);
         * print(a);
         */
        IStatement ex7 = new CompoundStatement(new VarDeclareStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDeclareStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));
        iStatementList.add(ex7);

        /*
         * The eighth example (readHeap)
         * Ref(int) v;
         * New(v, 20);
         * Ref(Ref(int)) a;
         * New(a, v);
         * print(ReadHeap(v));
         * print(ReadHeap(ReadHeap(a)) + 5);
         */
        IStatement ex8 = new CompoundStatement(new VarDeclareStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDeclareStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',
                                                        new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        iStatementList.add(ex8);

        /*
        FORK
         */
        IStatement ex9 = new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                new CompoundStatement(new VarDeclareStatement("a", new RefType(new IntType())),
                        new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                    new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        iStatementList.add(ex9);

        IStatement ex10 = new CompoundStatement(
                new VarDeclareStatement("v1", new RefType(new IntType())),
                new CompoundStatement(new VarDeclareStatement("v2", new RefType(new IntType())),
                        new CompoundStatement(new VarDeclareStatement("v3", new RefType(new IntType())),
                                new CompoundStatement(new VarDeclareStatement("cnt", new IntType()),
                                        new CompoundStatement(new NewStatement("v1", new ValueExpression(new IntValue(2))),
                                                new CompoundStatement(new NewStatement("v2", new ValueExpression(new IntValue(3))),
                                                        new CompoundStatement(new NewStatement("v3", new ValueExpression(new IntValue(4))),
                                                                new CompoundStatement(new NewLatchStatement("cnt", new ReadHeapExpression(new VariableExpression("v2"))),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                                                                                                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                        new CompoundStatement(new CountDownStatement("cnt"),
                                                                                                                new ForkStatement(
                                                                                                                        new CompoundStatement(new WriteHeapStatement("v2", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))),
                                                                                                                                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v2"))),
                                                                                                                                        new CompoundStatement(new CountDownStatement("cnt"),
                                                                                                                                                new ForkStatement(
                                                                                                                                                        new CompoundStatement(new WriteHeapStatement("v3", new ArithmeticExpression('*', new ReadHeapExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))),
                                                                                                                                                                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v3"))),
                                                                                                                                                                        new CountDownStatement("cnt")))))))))))),
                                                                                new CompoundStatement(new AwaitStatement("cnt"),
                                                                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),
                                                                                                new CompoundStatement(new CountDownStatement("cnt"),
                                                                                                        new PrintStatement(new ValueExpression(new IntValue(100)))))))))))))));
        iStatementList.add(ex10);

        return FXCollections.observableArrayList(iStatementList);
    }

    /**
     * Initializes the ChooserController by setting its items as the runnable programs, and allowing them to be selected
     */
    @FXML
    public void initialize() {
        programsListView.setItems(getAllStatements());
        programsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Used in the FXML file to assign a function to the CLICK event. In this case, clicking display will put the
     * given program into the Executor window.
     * @param actionEvent LEFT MOUSE BUTTON
     */
    @FXML
    private void displayProgram(ActionEvent actionEvent) {
        IStatement selectedIStatement = programsListView.getSelectionModel().getSelectedItem();
        if (selectedIStatement == null) {
            // If the statement hasn't been selected
            Alert statementAlert = new Alert(Alert.AlertType.ERROR);
            statementAlert.setTitle("NO STATEMENT");
            statementAlert.showAndWait();
        } else {
            int id = programsListView.getSelectionModel().getSelectedIndex();
            try {
                // If selected, then it goes through the same motions as in the INTERPRETER class:
                // Typecheck; Set program state; Set repository; Set controller; Set ExecutorController controller
                selectedIStatement.typeCheck(new MyDictionary<>());
                PrgState prgState = new PrgState(new MyStack<>(), new MyDictionary<>(),
                        new MyList<>(), new MyDictionary<>(), new MyHeap(), selectedIStatement, new MyLatchTable());
                IRepository repo = new Repository(prgState, "ex" + (id + 1) + ".txt");
                Controller ctrl = new Controller(repo);
                programExecutorController.setController(ctrl);
            } catch (InterpreterException | IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EXCEPTION CAUGHT");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        }
    }
}
package View;

import Domain.ADT.Heap.MyHeap;
import Domain.ADT.Table.MyLatchTable;
import Domain.Statement.IStatement;
import Domain.Statement.*;
import Domain.Type.*;
import Domain.Expression.*;
import Domain.Value.*;
import Domain.ADT.Stack.*;
import Domain.ADT.List.*;
import Domain.ADT.Dictionary.*;
import Domain.ProgramState.PrgState;
import Repository.*;
import Controller.Controller;
import View.Command.*;
import Exception.*;

import java.io.IOException;

public class Interpreter {
    /**
     * Main function - allows running the Interpreter. Every example follows the same pattern.
     * 1. Make the Statement Interface that will hold the CompoundStatement composing the whole Example
     * 2. Create a ProgramState specific to the Example
     * 3. Create a Repository specific to the Example
     * 4. Create a Controller specific to the Example
     * @param args Unused console arguments
     */
    public static void main(String[] args){
        TextMenu menu = new TextMenu();

        /*
         * The first example
         * int v;
         * v=2;
         * print(v);
         */
        IStatement ex1 = new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        try {
            ex1.typeCheck(new MyDictionary<>());
            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex1, new MyLatchTable());
            IRepository repo1 = new Repository(prg1, "ex1.txt");
            Controller ctrl1 = new Controller(repo1);
            menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctrl1));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }




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
                        new CompoundStatement(new AssignStatement("a",
                                new ArithmeticExpression('+',
                                        new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression('*',
                                                new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",
                                        new ArithmeticExpression('+',
                                                new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));
        try {
            ex2.typeCheck(new MyDictionary<>());
            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex2, new MyLatchTable());
            IRepository repo2 = new Repository(prg2, "ex2.txt");
            Controller ctrl2 = new Controller(repo2);
            menu.addCommand(new RunExampleCommand("2", ex2.toString(), ctrl2));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

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
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        try {
            ex3.typeCheck(new MyDictionary<>());
            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex3, new MyLatchTable());
            IRepository repo3 = new Repository(prg3, "ex3.txt");
            Controller ctrl3 = new Controller(repo3);
            menu.addCommand(new RunExampleCommand("3", ex3.toString(), ctrl3));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

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

        try {
            ex4.typeCheck(new MyDictionary<>());
            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex4, new MyLatchTable());
            IRepository repo4 = new Repository(prg4, "ex4.txt");
            Controller ctrl4 = new Controller(repo4);
            menu.addCommand(new RunExampleCommand("4", ex4.toString(), ctrl4));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

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
                                        new IfStatement(new RelationalExpression(">", new VariableExpression("a"),
                                                new VariableExpression("b")),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));

        try {
            ex5.typeCheck(new MyDictionary<>());
            PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex5, new MyLatchTable());
            IRepository repo5 = new Repository(prg5, "ex5.txt");
            Controller ctrl5 = new Controller(repo5);
            menu.addCommand(new RunExampleCommand("5", ex5.toString(), ctrl5));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

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
                        new CompoundStatement(new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntValue(0))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

        try {
            ex6.typeCheck(new MyDictionary<>());
            PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex6, new MyLatchTable());
            IRepository repo6 = new Repository(prg6, "ex6.txt");
            Controller ctrl6 = new Controller(repo6);
            menu.addCommand(new RunExampleCommand("6", ex6.toString(), ctrl6));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

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

        try {
            ex7.typeCheck(new MyDictionary<>());
            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex7, new MyLatchTable());
            IRepository repo7 = new Repository(prg7, "ex7.txt");
            Controller ctrl7 = new Controller(repo7);
            menu.addCommand(new RunExampleCommand("7", ex7.toString(), ctrl7));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

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

        try {
            ex8.typeCheck(new MyDictionary<>());
            PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex8, new MyLatchTable());
            IRepository repo8 = new Repository(prg8, "ex8.txt");
            Controller ctrl8 = new Controller(repo8);
            menu.addCommand(new RunExampleCommand("8", ex8.toString(), ctrl8));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }

        /*
        FORK
         */
        IStatement ex9 = new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                new CompoundStatement(new VarDeclareStatement("a", new RefType(new IntType())),
                        new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        try {
            ex9.typeCheck(new MyDictionary<>());
            PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(),
                    new MyDictionary<>(), new MyHeap(), ex9, new MyLatchTable());
            IRepository repo9 = new Repository(prg9, "ex9.txt");
            Controller ctrl9 = new Controller(repo9);
            menu.addCommand(new RunExampleCommand("9", ex9.toString(), ctrl9));
        } catch (IOException | InterpreterException exception) {
            System.out.println(exception.getMessage());
        }


        menu.show();
    }
}

package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import Controller.Controller;
import Domain.ADT.Dictionary.MyDictionary;
import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.List.MyIList;
import Domain.ADT.List.MyList;
import Domain.ADT.Stack.MyIStack;
import Domain.ADT.Stack.MyStack;
import Domain.Expression.ArithmeticExpression;
import Domain.Expression.ValueExpression;
import Domain.Expression.VariableExpression;
import Domain.ProgramState.PrgState;
import Domain.Statement.*;
import Domain.Type.BoolType;
import Domain.Type.IntType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Exception.MyException;
import Exception.ADTException;
import Exception.ExecuteException;
import Exception.EvaluateException;
import Repository.IRepository;
import Repository.Repository;

public class View {
    /**
     * The function that we call in main to utilize the program
     * Console-based, reads input from user with readOption
     * Input 0 = exit
     * Input 1 = example 1
     * Input 2 = example 2
     * Input 3 = example 3
     */
    public void start() {
        boolean done = false;
        while (!done) {
            try {
                System.out.println("1. Example_1\n2. Example_2\n3. Example_3\n");
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 0) {
                    done = true;
                } else if (option == 1) {
                    example1();
                } else if (option == 2) {
                    example2();
                } else if (option == 3) {
                    example3();
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * First example as given in Teams
     * @throws MyException If any function throws it
     * @throws IOException If any function throws it
     */
    private void example1() throws MyException, IOException, ADTException {
        IStatement ex1 = new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        runStatement(ex1);
    }

    /**
     * Second example as given in Teams
     * @throws MyException If any function throws it
     * @throws IOException If any function throws it
     */
    private void example2() throws MyException, IOException, ADTException {
        IStatement ex2 = new CompoundStatement(new VarDeclareStatement("a",new IntType()),
                new CompoundStatement(new VarDeclareStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        runStatement(ex2);
    }

    /**
     * Third example as given in Teams
     * @throws MyException If any function throws it
     * @throws IOException If any function throws it
     */
    private void example3() throws MyException, IOException, ADTException {
        IStatement ex3 = new CompoundStatement(new VarDeclareStatement("a", new BoolType()),
                new CompoundStatement(new VarDeclareStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        runStatement(ex3);
    }

    /**
     * The function that begins the whole execution of the program state
     * Sets the exeStack, symTable, out
     * Sets the PrgState state
     * Sets the repository, controller
     * Displays step-by-step by setting the displayFlag if the user wants to see the execution step-by-step
     * Displays the result
     * @param statement = the CompoundStatement with which the execution begins
     * @throws MyException If any function throws it
     * @throws IOException If any function throws it
     */
    private void runStatement(IStatement statement) throws MyException, IOException, ADTException {
        MyIStack<IStatement> executionStack = new MyStack<>();
        MyIDictionary<String, IValue> symbolTable = new MyDictionary<>();
        MyIList<IValue> output = new MyList<>();
        PrgState state = new PrgState(executionStack, symbolTable, output, statement);
        IRepository repository = new Repository(state, "log.txt");
        Controller controller = new Controller(repository);

        System.out.println("Show steps?");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "yes"));
        controller.allSteps();
        System.out.println("Result= " + state.getOut().toString().replace('[', ' ').replace(']', ' '));
    }

}

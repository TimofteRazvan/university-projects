package View.Command;

import Controller.Controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import Exception.InterpreterException;

public class RunExampleCommand extends Command {
    private final Controller controller;

    /**
     * Constructor for RunExampleCommand Class - a command that runs a hardcoded example
     * @param key Command ID
     * @param description Command DESCRIPTION
     * @param controller Controller that will handle this example
     */
    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Function that executes the example/command.
     * 1. Checks if the USER wants to see the steps one by one
     * 2. Sets the displayFlag accordingly
     * 3. Via the given Controller specific to the example-to-be-run, executes all the steps
     */
    @Override
    public void execute() {
        try {
            System.out.println("Do you want to display the steps?[yes/no]");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();
            controller.setDisplayFlag(Objects.equals(option, "yes"));
            controller.allSteps();
        } catch (InterpreterException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

package View;

import Domain.ADT.Dictionary.MyDictionary;
import Domain.ADT.Dictionary.MyIDictionary;
import View.Command.Command;

import Exception.InterpreterException;

import java.util.Objects;
import java.util.Scanner;

public class TextMenu {
    private MyIDictionary<String, Command> commands;

    /**
     * Constructor for TextMenu class
     */
    public TextMenu() {
        this.commands = new MyDictionary<>();
    }

    /**
     * Adds a new command to the commands dictionary
     * @param command Command to be added
     */
    public void addCommand(Command command) {
        this.commands.put(command.getKey(), command);
    }

    /**
     * Loops through commands and displays the ID and DESCRIPTION for each one
     */
    private void displayMenu() {
        for (Command command: commands.values()) {
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    /**
     * Allows the user to input a COMMAND ID; If exists within the COMMAND DICTIONARY, executes;
     * Exception if the COMMAND ID does not match any of the keys within COMMAND DICTIONARY
     */
    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.println("\nOption: ");
            String key = scanner.nextLine();
            if (Objects.equals(key, "0")) {
                System.exit(0);
            }
            try {
                Command command = commands.lookUp(key);
                command.execute();
            } catch (InterpreterException exception){
                exception.printStackTrace();
            }
        }
    }
}

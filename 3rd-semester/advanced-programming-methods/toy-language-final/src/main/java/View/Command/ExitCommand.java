package View.Command;

public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand
     * @param key Command ID
     * @param description Command DESCRIPTION
     */
    public ExitCommand(String key, String description) {
        super(key, description);
    }

    /**
     * Simulates the execution of an absolute exit command "System.exit(0)"
     */
    @Override
    public void execute() {
        System.exit(0);
    }
}

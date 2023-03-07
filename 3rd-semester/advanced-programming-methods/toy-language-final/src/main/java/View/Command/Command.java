package View.Command;

public abstract class Command {
    private final String key;
    private final String description;

    /**
     * Constructor for Command class
     * @param key String representing the command's ID
     * @param description String representing the command's DESCRIPTION
     */
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /**
     * Getter for the command's ID/Key
     * @return The command's ID/Key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Getter for the command's DESCRIPTION
     * @return The command's DESCRIPTION
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Abstract function to be differently implemented by subclasses
     */
    public abstract void execute();
}

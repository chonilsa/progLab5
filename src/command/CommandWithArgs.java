package command;

/**
 * The interface Command with args.
 */
public interface CommandWithArgs extends Command{
    /**
     * Sets command arguments.
     *
     * @param arguments the arguments
     */
    void setCommandArguments(String[] arguments);
}

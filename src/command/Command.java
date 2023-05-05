package command;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute.
     */
    void execute();

    /**
     * Gets description.
     *
     * @return the description
     */
    default String getDescription() {
        return "Описание работы данной команды еще не реализовано";
    }
}

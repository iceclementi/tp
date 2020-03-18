package seedu.nuke.command.deletecommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.TaskCommand;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_TASK_REMOVED;

public class DeleteTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "delt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description " + ": Add a task to the module.";

    private final String description;

    public DeleteTaskCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute() {
        try {
            Module currentModule = (Module) Command.getCurrentDirectory();
            Task toDelete = currentModule.getTaskManager().delete(description);

            //add the task to the data manager
            moduleManager.removeTask(currentModule.getTaskManager(), toDelete);
            return new CommandResult(MESSAGE_TASK_REMOVED);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}

package seedu.nuke.command.misc;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DirectoryTraversalOutOfBoundsException;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_DIRECTORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_FAILED_DIRECTORY_TRAVERSAL;

public class ChangeDirectoryCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String FORMAT = COMMAND_WORD + " <next directory name>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + System.lineSeparator()
            + "traverse to another directory (.. represents parent directory)" + System.lineSeparator()
            + FORMAT + System.lineSeparator();
    private String nextDirectoryName;

    /**
     * Constructs the command to traverse down into the next directory with the given name.
     *
     * @param nextDirectoryName
     *  The name of the next directory
     */
    public ChangeDirectoryCommand(String nextDirectoryName) {
        this.nextDirectoryName = nextDirectoryName;
    }

    /**
     * Constructs the command to traverse up from the current directory.
     */
    public ChangeDirectoryCommand() {
        this(null);
    }

    /**
     * Executes the Change Directory Command to traverse up or down the current Directory.
     *
     * @return
     *  The Command result of the execution
     */
    @Override
    public CommandResult execute() {
        try {
            if ((nextDirectoryName != null)) {
                Directory nextDirectory = DirectoryTraverser.findNextDirectory(nextDirectoryName);
                DirectoryTraverser.traverseDown(nextDirectory);
            } else {
                DirectoryTraverser.traverseUp();
            }
            // No feedback for successful traversal
            return new CommandResult(null);
        } catch (DirectoryTraversalOutOfBoundsException e) {
            return new CommandResult(MESSAGE_FAILED_DIRECTORY_TRAVERSAL);
        } catch (DataNotFoundException e) {
            return new CommandResult(MESSAGE_DIRECTORY_NOT_FOUND);
        }
    }
}

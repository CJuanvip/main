package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ChangeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * ChangeTimeCommandParser.
 */
public class ChangeTimeCommandParser implements Parser<ChangeTimeCommand> {
    /**
     * ChangeTimeCommand
     *
     * @param args
     * @return
     * @throws ParseException
     */
    public ChangeTimeCommand parse(String args) throws ParseException {


        try {
            String[] stringCommand = args.trim().split(" ");
            if (stringCommand.length != 4) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTimeCommand.MESSAGE_USAGE));
            }
            String nameA = stringCommand[0];
            String nameB = stringCommand[2];
            int numA = Integer.parseInt(stringCommand[1]);
            int numB = Integer.parseInt(stringCommand[3]);
            if (nameA.toString().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTimeCommand.MESSAGE_USAGE));
            }
            if (nameB.toString().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTimeCommand.MESSAGE_USAGE));
            }
            if (numA < 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTimeCommand.MESSAGE_USAGE));
            }
            if (numB < 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTimeCommand.MESSAGE_USAGE));
            }
            return new ChangeTimeCommand(args);

        } catch (IllegalArgumentException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTimeCommand.MESSAGE_USAGE));
        }
    }
}

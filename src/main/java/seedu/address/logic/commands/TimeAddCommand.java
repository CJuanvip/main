package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Time;

/**
 * Adds a time slot of a Person in the address book.
 */
public class TimeAddCommand extends Command {
    public static final String COMMAND_WORD = "addtime";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial time slot of a person. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TIME + "TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TIME + "mon 1300 1500 ";

    public static final String MESSAGE_SUCCESS = "Time slot successfully added";
    public static final String MESSAGE_PERSON_NOT_FOUND = "This person does not exists in the address book";
    public static final String MESSAGE_TIME_IS_NOT_AVAILABLE = "The time has already been taken";

    private String[] personToFind;

    private final Time toAdd;

    /**
     * Creates a TimeAddCommand to add the specified {@code Time}
     */
    public TimeAddCommand(String personName, Time time) {
        requireNonNull(time);
        toAdd = time;
        personToFind = personName.split("\\s+");
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ArrayList<Time> allTimeSlot = new ArrayList();

        for (Person ppl : model.getFilteredPersonList()) {
            allTimeSlot.addAll(ppl.getTime());
        }
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(personToFind)));

        Person targetPerson = model.getFilteredPersonList().get(0);

        if (allTimeSlot.contains(toAdd)) {
            throw new CommandException(MESSAGE_TIME_IS_NOT_AVAILABLE);
        }
        if (!model.hasPerson(targetPerson)) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }


        targetPerson.addTime(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}


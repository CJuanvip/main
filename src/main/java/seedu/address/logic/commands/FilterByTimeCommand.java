package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Time;
import seedu.address.model.person.TimeFilterPredicate;

/**
 * Finds and lists the list of person whose time is between A to B.
 */

public class FilterByTimeCommand extends FilterCommand {
    public static final String COMMAND_WORD = "filterByTime";

    public static final String MESSAGE_SUCCESS = "Already filtered by Time!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": filter by tutorial time slot. "
            + "Parameters: "
            + "TIME ";

    private Time time;

    /**
     * filter by grade command
     *
     * @param args
     */

    public FilterByTimeCommand(String args) {
        this.time =new Time(args);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {

        // Execute the display of student's grades here
        requireNonNull(model);
        model.updateFilteredPersonList(new
                TimeFilterPredicate(time));

        ObservableList<Person> targetList = model.getFilteredPersonList();
        // Returns the command result
        if (targetList.isEmpty()) {
            return new CommandResult("Cannot find " + time.toString() + " education within the students list!");
        }


        List<String> personNameList = new ArrayList<>();
        for (Person ppl : targetList) {
            personNameList.add(ppl.getName().fullName);
        }

        return new CommandResult("The person whose education is "
                + time.toString() + " : " + personNameList.toString());
    }
}

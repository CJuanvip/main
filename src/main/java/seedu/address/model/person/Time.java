package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Student's tutorial time slot in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */

public class Time {

    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Time should consist of the Day followed by a space and then the time of the tutorial.\n"
            + "Day should consists of the first 3 letters of the day without caps.\n"
            + "Time should consist of the start and end time of the tutorial in 24hour time format.\n"
            + "Start time should only contain 4 numbers and it should not be blank.\n"
            + "It will be followed by ' ' and then the end time.\n"
            + "End time should only contain 4 numbers and it should not be blank.\n";

    /*
     * The first character of the address must not be a whitespace,
     * then the day should be entered first, followed by a whitespace,
     * then the start time followed by a -,
     * and lastly the end time of the tutorial.
     */
    public static final String DAY_PART_REGEX = "[a-t]{3}";
    public static final String START_TIME_REGEX = "\\d{4}";
    public static final String END_TIME_REGEX = "\\d{4}";
    public static final String TIME_VALIDATION_REGEX = DAY_PART_REGEX + " "
            + START_TIME_REGEX + " " + END_TIME_REGEX;

    enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

    public Day day;
    public int startTime;
    public int endTime;

    /**
     * Constructs an {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_TIME_CONSTRAINTS);
        setTime(time);
    }

    /**
     * Sets the Day, Start and End time based on user input.
     */
    public void setTime(String time) {
        String[] splittedTime = time.split("\\s+");
        startTime = Integer.valueOf(splittedTime[1]);
        endTime = Integer.valueOf(splittedTime[2]);

        switch (splittedTime[0]) {
        case "sun":
            day = Day.Sunday;
            break;
        case "mon":
            day = Day.Monday;
            break;
        case "tue":
            day = Day.Tuesday;
            break;
        case "wed":
            day = Day.Wednesday;
            break;
        case "thu":
            day = Day.Thursday;
            break;
        case "fri":
            day = Day.Friday;
            break;
        case "sat":
            day = Day.Saturday;
            break;
        }
    }

    /**
     * Returns if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return day.toString() + " " + startTime + " " + endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && day.equals(((Time) other).day) // state check
                && startTime == (((Time) other).startTime) // state check
                && endTime == (((Time) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, startTime, endTime);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.editeventcommand.EventDescriptor;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;

/**
 * Represents a command that edits an event object.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "editevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an event in the event list. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_START_DATE_TIME + "START DATE TIME "
            + PREFIX_END_DATE_TIME + "END DATE TIME"
            + "[" + PREFIX_RECURRENCE + "INTERVAL] "
            + "Example: " + COMMAND_WORD + " "
            + "2 "
            + PREFIX_DESCRIPTION + "Do CS2103T Tutorial "
            + PREFIX_START_DATE_TIME + "2023-03-10 16:00 "
            + PREFIX_END_DATE_TIME + "2023-03-10 18:00"
            + PREFIX_RECURRENCE + "weekly ";

    private static final String MESSAGE_EDIT_EVENT_SUCCESS = "Event edited: %1$s";
    private static final String MESSAGE_INVALID_EVENT = "This event does not exist in the Calendar!";

    private static final String MESSAGE_INVALID_INTERVAL = "END DATE TIME ("
            + PREFIX_END_DATE_TIME + ") should be after START DATE TIME ("
            + PREFIX_START_DATE_TIME + ")";

    // public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    protected final EventDescriptor editEventDescriptor;
    private final Index index;

    /**
     * @param editEventDescriptor details to edit the event with
     */
    public EditEventCommand(EventDescriptor editEventDescriptor) {
        requireNonNull(editEventDescriptor);

        this.index = editEventDescriptor.getIndex();
        this.editEventDescriptor = new EventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> eventList = model.getEvents();

        if (index.getZeroBased() >= eventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = eventList.get(index.getZeroBased());
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!model.hasEvent(eventToEdit)) {
            throw new CommandException(MESSAGE_INVALID_EVENT);
        }

        if (!DateTime.isValidInterval(editedEvent.getStartDateTime(), editedEvent.getEndDateTime())) {
            throw new CommandException(MESSAGE_INVALID_INTERVAL);
        }

        model.setEvent(eventToEdit, editedEvent);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    public EventDescriptor getEditEventDescriptor() {
        return this.editEventDescriptor;
    }

    /**
     * Creates a new edited event to replace the original
     *
     * @param eventToEdit
     * @param editEventDescriptor
     * @return the new Event to be set
     */
    protected static Event createEditedEvent(Event eventToEdit, EventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        Description description = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        DateTime startDateTime = editEventDescriptor.getStartDateTime().orElse(eventToEdit.getStartDateTime());
        DateTime endDateTime = editEventDescriptor.getEndDateTime().orElse(eventToEdit.getEndDateTime());
        Recurrence recurrence = editEventDescriptor.getRecurrence().orElse(eventToEdit.getRecurrence());
        Set<Person> taggedPeople = editEventDescriptor.getTaggedPeople().orElse(eventToEdit.getTaggedPeople());

        if (recurrence.isRecurring()) {
            return new RecurringEvent(description, startDateTime, endDateTime, recurrence, taggedPeople);
        } else {
            return new OneTimeEvent(description, startDateTime, endDateTime, taggedPeople);
        }
    }

    /**
     * Returns true if the two events have the same identity and data fields.
     * This defines a weaker notion of equality between two events.
     */
    public boolean equals(Object other) {
        EditEventCommand otherEvent = (EditEventCommand) other;
        return other == this
            || (other instanceof EditEventCommand
            && this.editEventDescriptor.equals(otherEvent));
    }
}

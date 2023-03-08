package seedu.address.ui.body.calendar;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.calendarfx.model.Entry;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;
import seedu.address.ui.UiPart;

/**
 * A UI component class to replace the default CalendarFX popover.
 */
public class CalendarEntryPopover extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarEntryPopover.fxml";
    private static final String UNKNOWN_RECURRENCE_LABEL = "unknown";

    @FXML
    private Label description;
    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label recurrence;

    /**
     * Creates a {@code CalendarEntryPopover} with the given {@code Entry}.
     */
    public CalendarEntryPopover(Entry<?> entry) {
        super(FXML);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy (EEE) hh:mm a");
        Event event = entry.getUserObject() instanceof Event
                ? (Event) entry.getUserObject()
                : null;

        description.setText(entry.getTitle());
        startDateTime.setText(entry.getStartAsLocalDateTime().format(formatter));
        endDateTime.setText(entry.getEndAsLocalDateTime().format(formatter));

        String recurrenceText = event == null
                ? UNKNOWN_RECURRENCE_LABEL
                : Objects.requireNonNullElse(event.getRecurrence().toString(), UNKNOWN_RECURRENCE_LABEL);
        recurrence.setText(recurrenceText);
    }
}

package seedu.address.ui.body.calendar;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.calendarfx.model.Entry;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component class to replace the default CalendarFX popover.
 */
public class CalendarEntryPopover extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarEntryPopover.fxml";

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

        description.setText(entry.getTitle());
        startDateTime.setText(entry.getStartAsLocalDateTime().format(formatter));
        endDateTime.setText(entry.getEndAsLocalDateTime().format(formatter));
        recurrence.setText(Objects.requireNonNullElse(entry.getRecurrenceRule(), "One-time"));
    }
}

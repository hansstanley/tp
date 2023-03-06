package seedu.address.ui.body.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.DetailedWeekView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.ui.UiPart;

/**
 * A UI component containing a calendar.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarPanel.fxml";

    @FXML
    private StackPane calendarPlaceholder;

    private Calendar<String> calendar;
    private DetailedWeekView weekView;

    /**
     * Creates a {@code CalendarPanel}.
     */
    public CalendarPanel(ObservableList<String> events) {
        super(FXML);

        buildCalendar();
        fillCalendar(events);
        startLiveUpdating();
    }

    private void buildCalendar() {
        calendar = new Calendar<>("Events");
        calendar.setStyle(Calendar.Style.STYLE5);
        calendar.setReadOnly(true); // disables entry modification

        CalendarSource calendarSource = new CalendarSource("Calendars");
        calendarSource.getCalendars().add(calendar);

        weekView = new DetailedWeekView();
        weekView.setContextMenuCallback(null); // disables the context menu
        weekView.setEntryFactory(param -> null); // disables entry creation
        // Changes the entry popover
        weekView.setEntryDetailsPopOverContentCallback(param -> new CalendarEntryPopover(param.getEntry()).getRoot());
        weekView.getCalendarSources().add(calendarSource);

        calendarPlaceholder.getChildren().add(weekView);
    }

    private void fillCalendar(List<String> events) {
        Objects.requireNonNull(calendar);
        Objects.requireNonNull(events);
        int i = 0;
        for (String event : events) {
            Entry<String> entry = new Entry<>(event);
            entry.setUserObject(event);
            entry.setInterval(
                    LocalDateTime.now().plusHours(i),
                    LocalDateTime.now().plusHours(i + 1)
            );
            if (i % 2 == 0) {
                entry.setRecurrenceRule("RRULE:FREQ=DAILY");
            }
            calendar.addEntry(entry);
            i++;
        }
    }

    /**
     * Updates the current date and time of the calendar view every 10 seconds.
     */
    private void startLiveUpdating() {
        Objects.requireNonNull(weekView);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            weekView.setToday(LocalDate.now());
            weekView.setTime(LocalTime.now());
        }, 10, 10, TimeUnit.SECONDS);
    }
}

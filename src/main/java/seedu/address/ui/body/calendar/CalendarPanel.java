package seedu.address.ui.body.calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.WeekPage;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.event.Event;
import seedu.address.ui.UiPart;

/**
 * A UI component containing a calendar.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "body/calendar/CalendarPanel.fxml";

    @FXML
    private StackPane calendarPlaceholder;

    private Calendar<Event> calendar;
    private WeekPage weekPage;

    /**
     * Creates a {@code CalendarPanel}.
     */
    public CalendarPanel(ObservableList<Event> events) {
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

        weekPage = new WeekPage();
        weekPage.setContextMenuCallback(null); // disables the context menu
        weekPage.setEntryFactory(param -> null); // disables entry creation
        // Changes the entry popover
        weekPage.setEntryDetailsPopOverContentCallback(param -> new CalendarEntryPopover(param.getEntry()).getRoot());
        weekPage.getCalendarSources().add(calendarSource);

        calendarPlaceholder.getChildren().add(weekPage);
    }

    private void fillCalendar(List<Event> events) {
        Objects.requireNonNull(calendar);
        Objects.requireNonNull(events);
        for (Event event : events) {
            Entry<Event> entry = new Entry<>(event.getDescription().getDescription());
            entry.setUserObject(event);
            entry.setInterval(event.getStartDateTime().getDateTime(), event.getEndDateTime().getDateTime());
            switch (event.getRecurrence().interval) {
            case DAILY:
                entry.setRecurrenceRule("RRULE:FREQ=DAILY");
                break;
            case WEEKLY:
                entry.setRecurrenceRule("RRULE:FREQ=WEEKLY");
                break;
            case MONTHLY:
                entry.setRecurrenceRule("RRULE:FREQ=MONTHLY");
                break;
            case YEARLY:
                entry.setRecurrenceRule("RRULE:FREQ=YEARLY");
                break;
            default:
                entry.setRecurrenceRule(null);
            }
            calendar.addEntry(entry);
        }
    }

    /**
     * Updates the current date and time of the calendar view every 10 seconds.
     */
    private void startLiveUpdating() {
        Objects.requireNonNull(weekPage);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            weekPage.setToday(LocalDate.now());
            weekPage.setTime(LocalTime.now());
        }, 10, 10, TimeUnit.SECONDS);
    }
}

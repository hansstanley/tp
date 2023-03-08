package seedu.address.ui.body;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.ui.UiPart;
import seedu.address.ui.body.address.AddressPanel;
import seedu.address.ui.body.calendar.CalendarPanel;

/**
 * A UI component representing the body section of the app with tabs.
 */
public class BodyPanel extends UiPart<Region> {
    private static final String FXML = "body/BodyPanel.fxml";

    /**
     * An enum to identify tabs.
     */
    public enum TabType {
        ADDRESS_BOOK,
        CALENDAR
    }

    @FXML
    private TabPane bodyTabs;

    private final Logic logic;

    private final AddressPanel addressPanel;
    private final CalendarPanel calendarPanel;
    private final Tab addressBookTab;
    private final Tab calendarTab;

    /**
     * Creates a {@code BodyPanel} with the given {@code Logic}.
     */
    public BodyPanel(Logic logic) {
        super(FXML);

        this.logic = logic;

        addressPanel = new AddressPanel(logic.getFilteredPersonList());
        addressBookTab = new Tab();
        addressBookTab.setText("Address Book");
        addressBookTab.setContent(addressPanel.getRoot());

        // TODO: replace eventList with data from logic.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String date1 = LocalDateTime.now().format(formatter);
        String date2 = LocalDateTime.now().plusHours(1).format(formatter);
        String date3 = LocalDateTime.now().plusDays(1).plusHours(2).format(formatter);
        String date4 = LocalDateTime.now().plusDays(1).plusHours(4).format(formatter);
        Event event1 = new OneTimeEvent(new Description("Event 1"), new DateTime(date1), new DateTime(date2));
        Event event2 = new OneTimeEvent(new Description("Event 2"), new DateTime(date3), new DateTime(date4));
        Event event3 = new RecurringEvent(new Description("Recurring Event 3"),
                new DateTime(date1), new DateTime(date2), new Recurrence("daily"));
        Event event4 = new RecurringEvent(new Description("Recurring Event 4"),
                new DateTime(date3), new DateTime(date4), new Recurrence("weekly"));
        ObservableList<Event> eventList = FXCollections.observableArrayList(event1, event2, event3, event4);
        calendarPanel = new CalendarPanel(eventList);
        calendarTab = new Tab();
        calendarTab.setText("Calendar");
        calendarTab.setContent(calendarPanel.getRoot());

        bodyTabs.getTabs().addAll(addressBookTab, calendarTab);
    }

    /**
     * Switches to the specified tab.
     *
     * @param tabType Identifier for tabs.
     */
    public void selectTab(TabType tabType) {
        SingleSelectionModel<Tab> selectionModel = bodyTabs.getSelectionModel();
        switch (tabType) {
        case ADDRESS_BOOK:
            selectionModel.select(addressBookTab);
            break;
        case CALENDAR:
            selectionModel.select(calendarTab);
            break;
        default:
        }
    }

    public AddressPanel getAddressPanel() {
        return addressPanel;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }
}

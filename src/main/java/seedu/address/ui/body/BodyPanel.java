package seedu.address.ui.body;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;
import seedu.address.ui.body.address.AddressPanel;
import seedu.address.ui.body.calendar.CalendarPanel;
import seedu.address.ui.body.notepad.NotepadPanel;

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
        CALENDAR,
        NOTEPAD
    }

    @FXML
    private TabPane bodyTabs;

    private final Logic logic;

    private final AddressPanel addressPanel;
    private final CalendarPanel calendarPanel;
    private final NotepadPanel notepadPanel;
    private final Tab addressBookTab;
    private final Tab calendarTab;
    private final Tab notepadTab;

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

        calendarPanel = new CalendarPanel();
        calendarTab = new Tab();
        calendarTab.setText("Calendar");
        calendarTab.setContent(calendarPanel.getRoot());

        notepadPanel = new NotepadPanel();
        notepadTab = new Tab();
        notepadTab.setText("Notes");
        notepadTab.setContent(notepadPanel.getRoot());

        bodyTabs.getTabs().addAll(addressBookTab, calendarTab, notepadTab);
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
        case NOTEPAD:
            selectionModel.select(notepadTab);
            break;
        default:
        }
    }
}

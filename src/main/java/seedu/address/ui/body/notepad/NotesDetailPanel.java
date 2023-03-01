package seedu.address.ui.body.notepad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays detail information of a {@code Note}.
 */
public class NotesDetailPanel extends UiPart<Region> {
    private static final String FXML = "body/notepad/NotesDetailPanel.fxml";

    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label createdAt;
    @FXML
    private Label body;

    /**
     * Creates a blank {@code NotesDetailPanel}.
     */
    public NotesDetailPanel() {
        super(FXML);
        clearNote();
    }

    public void setNote(String note) {
        clearNote();
        if (note == null) {
            return;
        }

        title.setText(note);
        createdAt.setText(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a").format(LocalDateTime.now()));
        body.setText(note);
    }

    /**
     * Sets the index of the {@code Note} to be displayed to the user.
     * If the given {@code index} is less than 1, it is assumed that
     * no {@code Note} is selected.
     *
     * @param index 1-based index of the corresponding {@code Note}.
     */
    public void setDisplayedIndex(int index) {
        if (index < 1) {
            id.setText("Select a note.");
        } else {
            id.setText(String.format("Index: %d", index));
        }
    }

    /**
     * Empties the fields, resulting in a blank {@code NotesDetailPanel}.
     */
    public void clearNote() {
        setDisplayedIndex(-1);

        title.setText(null);
        createdAt.setText(null);
        body.setText(null);
    }
}

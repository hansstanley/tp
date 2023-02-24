package seedu.address.ui.body.notepad;

import java.util.Date;

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

    public void setNote(String note, int displayedIndex) {
        clearNote();

        id.setText(displayedIndex + ".");
        title.setText(note);
        createdAt.setText(new Date().toString());
        body.setText(note);
    }

    /**
     * Empties the fields, resulting in a blank {@code NotesDetailPanel}.
     */
    public void clearNote() {
        id.setText("Select a note.");
        title.setText(null);
        createdAt.setText(null);
        body.setText(null);
    }
}

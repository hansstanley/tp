package seedu.address.ui.body.notepad;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays summary information of a {@code Note}.
 */
public class NotesListCard extends UiPart<Region> {
    private static final String FXML = "body/notepad/NotesListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label createdAt;

    /**
     * Creates a {@code NotesListCard} with the given {@code Note}
     * and index to display.
     */
    public NotesListCard(String note, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        title.setText(note);
        createdAt.setText(new Date().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NotesListCard)) {
            return false;
        }

        // state check
        NotesListCard card = (NotesListCard) other;
        return id.getText().equals(card.id.getText());
    }
}

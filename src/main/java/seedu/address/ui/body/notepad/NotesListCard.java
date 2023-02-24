package seedu.address.ui.body.notepad;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

import java.util.Date;

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

    public NotesListCard(String note, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        title.setText(note);
        createdAt.setText(new Date().toString());
    }
}

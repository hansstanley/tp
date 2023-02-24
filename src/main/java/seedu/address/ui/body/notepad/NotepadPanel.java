package seedu.address.ui.body.notepad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.ui.UiPart;

import java.util.List;

public class NotepadPanel extends UiPart<Region> {
    private static final String FXML = "body/notepad/NotepadPanel.fxml";

    @FXML
    private StackPane notesListPanelPlaceholder;
    @FXML
    private StackPane notesDetailPanelPlaceholder;

    public NotepadPanel() {
        super(FXML);

        NotesDetailPanel notesDetailPanel = new NotesDetailPanel();
        notesDetailPanelPlaceholder.getChildren().add(notesDetailPanel.getRoot());

        NotesListPanel notesListPanel = new NotesListPanel(getDemoNotes(), notesDetailPanel);
        notesListPanelPlaceholder.getChildren().add(notesListPanel.getRoot());
    }

    private ObservableList<String> getDemoNotes() {
        return FXCollections.observableArrayList(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc nunc nulla, fringilla at "
                        + "eleifend non, fermentum vitae orci. Pellentesque pulvinar odio sit amet lorem commodo "
                        + "bibendum. Vivamus augue tellus, feugiat a quam non, varius tincidunt elit. Donec ut "
                        + "lacus at urna tristique egestas. Praesent sodales varius arcu ac tempus. Donec semper "
                        + "erat ut dignissim cursus. Aliquam erat volutpat. Praesent a eros molestie mauris "
                        + "consequat egestas. Praesent ac leo vel massa iaculis sagittis id ut odio. Donec "
                        + "tristique eu purus ut fringilla. Nunc quis nisl tempus, imperdiet purus id, laoreet metus.",
                "Suspendisse potenti. Phasellus imperdiet sapien at lectus faucibus elementum. Aliquam a rutrum "
                        + "velit. Nulla nulla justo, scelerisque nec risus in, volutpat bibendum turpis. Donec "
                        + "porttitor id urna in suscipit. Aenean nec consequat purus. Suspendisse vehicula vitae.",
                "Sed vehicula molestie ullamcorper. Nam accumsan ipsum interdum, lacinia urna sed, consectetur "
                        + "libero. Nulla sagittis, ante a luctus luctus, sem nibh congue turpis, a interdum erat "
                        + "enim sit amet erat. Nulla non leo non metus imperdiet facilisis. Proin eget leo a quam."
        );
    }
}

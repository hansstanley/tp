package seedu.address.ui.body.notepad;

import java.util.Objects;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of notes.
 */
public class NotesListPanel extends UiPart<Region> {
    private static final String FXML = "body/notepad/NotesListPanel.fxml";

    @FXML
    private ListView<String> notesListView;
    @FXML
    private Label count;

    private String selectedNote;

    /**
     * Creates a {@code NotesListPanel} with the given {@code ObservableList}.
     *
     * @param notesList {@code ObservableList} of {@code Note}s.
     * @param panel Component to show {@code Note} details.
     */
    public NotesListPanel(ObservableList<String> notesList, NotesDetailPanel panel) {
        super(FXML);
        notesListView.setItems(notesList);
        notesListView.setCellFactory(listView -> new NotesListCell());
        notesListView.setFocusTraversable(false);
        notesListView.setOnMouseClicked(event -> {
            String clickedNote = notesListView.getSelectionModel().getSelectedItem();
            if (Objects.equals(clickedNote, selectedNote)) {
                // the same Note was clicked on
                clearSelection();
            } else {
                selectedNote = clickedNote;
            }
        });

        /* Updates NotesDetailPanel accordingly when
         * the selected Note and index changes.
         */
        MultipleSelectionModel<String> model = notesListView.getSelectionModel();
        model.selectedItemProperty().addListener((observable, oldValue, newValue) -> panel.setNote(newValue));
        model.selectedIndexProperty().addListener((observable, oldValue, newValue) -> panel
                .setDisplayedIndex(newValue.intValue() + 1));

        count.textProperty().bind(getCountProperty(notesList));
    }

    /**
     * Clears the selection in the {@code ListView}.
     */
    public void clearSelection() {
        notesListView.getSelectionModel().clearSelection();
        selectedNote = null;
    }

    private StringBinding getCountProperty(ObservableList<String> list) {
        return Bindings.createStringBinding(() -> String.format("%d note(s)", list.size()), list);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Note}
     * using a {@code NotesListCard}.
     */
    static class NotesListCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NotesListCard(item, getIndex() + 1).getRoot());
                if (getIndex() == 0) {
                    getStyleClass().add("first-cell");
                } else {
                    getStyleClass().remove("first-cell");
                }
            }
        }
    }
}

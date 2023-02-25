package seedu.address.ui.body.notepad;

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
            MultipleSelectionModel<String> selectionModel = notesListView.getSelectionModel();
            String selectedNote = selectionModel.getSelectedItem();
            int selectedIndex = selectionModel.getSelectedIndex();
            panel.setNote(selectedNote, selectedIndex + 1);
        });

        count.textProperty().bind(getCountProperty(notesList));
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

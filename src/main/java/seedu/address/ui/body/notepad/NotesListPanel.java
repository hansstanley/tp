package seedu.address.ui.body.notepad;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class NotesListPanel extends UiPart<Region> {
    private static final String FXML = "body/notepad/NotesListPanel.fxml";

    @FXML
    private ListView<String> notesListView;

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
            }
        }
    }
}

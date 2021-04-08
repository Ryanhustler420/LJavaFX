package sample.models.table;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TableRow;

public class BoldableRowFactory<T extends AbstractTableItem> extends TableRow<T> {

    private final SimpleBooleanProperty bold = new SimpleBooleanProperty();
    private T currentItem = null;

    public BoldableRowFactory() {
        super();

        bold.addListener((observable, oldValue, newValue) -> {
            if (currentItem != null) updateItem(currentItem, newValue);
        });

        itemProperty().addListener((observable, oldValue, newValue) -> {
            bold.unbind();
            if (newValue != null) {
                bold.bind(newValue.getReadProperty());
                currentItem = newValue;
            }
        });

    }

    @Override
    final protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        this.currentItem = item;
        if (item != null && !item.isRead()) {
            setStyle("-fx-font-weight: bold");
        } else {
            setStyle("");
        }
    }

}

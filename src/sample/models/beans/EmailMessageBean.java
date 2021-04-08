package sample.models.beans;

import javafx.beans.property.SimpleStringProperty;
import sample.models.table.AbstractTableItem;

import java.util.HashMap;
import java.util.Map;

public class EmailMessageBean extends AbstractTableItem {

    private static Map<String, Integer> formattedValues = new HashMap<>();

    private SimpleStringProperty subject;
    private SimpleStringProperty sender;
    private SimpleStringProperty size;
    private String body;

    public EmailMessageBean(boolean isRead) {
        super(isRead);
    }

    public EmailMessageBean(String subject, String sender, Integer size, String body, boolean isRead) {
        super(isRead);
        this.subject = new SimpleStringProperty(subject);
        this.sender = new SimpleStringProperty(sender);
        this.size = new SimpleStringProperty(formatSize(size));
        this.body = body;
    }

    public String getSubject() {
        return subject.get();
    }

    public String getSender() {
        return sender.get();
    }

    public String getSize() {
        return size.get();
    }

    public String getBody() {
        return body;
    }

    public static Map<String, Integer> getFormattedValues() {
        return formattedValues;
    }

    private String formatSize(Integer size) {
        String returnValue;
        if (size <= 0) returnValue = "0";
        else if (size < 1024) returnValue = size + " B";
        else if (size < 1048576) returnValue = size / 1024 + " kB";
        else returnValue = size / 1048576 + "MB";
        formattedValues.put(returnValue, size);
        return returnValue;
    }

}

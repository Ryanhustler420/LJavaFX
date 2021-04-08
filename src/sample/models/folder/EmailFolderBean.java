package sample.models.folder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import sample.models.beans.EmailMessageBean;
import sample.views.ViewFactory;

public class EmailFolderBean<T> extends TreeItem<String> {

    private boolean topElement = false;
    private int unreadMessageCount;
    private String name;
    private String completeName;
    private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

    /*
     * Constructor for top elements
     * @param rootName
     * */

    public EmailFolderBean(String rootName) {
        super(rootName, ViewFactory.singleton.resolveIcon(rootName));
        this.name = rootName;
        this.completeName = rootName;
        data = null;
        topElement = true;
        this.setExpanded(true);
    }

    public EmailFolderBean(String rootName, String completeName) {
        super(rootName, ViewFactory.singleton.resolveIcon(rootName));
        this.name = rootName;
        this.completeName = completeName;
    }

    private void updateValue() {
        if (unreadMessageCount > 0) {
            this.setValue(name + " (" + unreadMessageCount + ") ");
        } else {
            this.setValue(name);
        }
    }

    public void incrementUnreadMessagesCount(int newMessages) {
        unreadMessageCount += newMessages;
        updateValue();
    }

    public void decrementUnreadMessagesCount() {
        if (unreadMessageCount > 0) {
            unreadMessageCount--;
            updateValue();
        }
    }

    public void addEmail(EmailMessageBean messageBean) {
        data.add(messageBean);
        if (!messageBean.isRead()) {
            incrementUnreadMessagesCount(1);
        }
    }

    public boolean isTopElement() {
        return topElement;
    }

    public ObservableList<EmailMessageBean> getData() {
        return data;
    }

}


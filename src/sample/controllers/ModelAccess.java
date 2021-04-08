package sample.controllers;

import sample.models.beans.EmailMessageBean;
import sample.models.folder.EmailFolderBean;

public class ModelAccess {

    private EmailMessageBean selectedMessage;

    public EmailMessageBean getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessageBean selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    private EmailFolderBean<String> selectedFolder;

    public EmailFolderBean<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailFolderBean<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }
}

package sample.models;

import sample.models.beans.EmailMessageBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class SampleData {

    public final ObservableList<EmailMessageBean> Inbox = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from Bajaj Automobile", "plasure@automobile.com", 500000000, "<html>Just some dummy tag 1</html"),
            new EmailMessageBean("You company Reliance Ltd working with us", "reliance@gmail.com", 145500, "<html>Just some dummy tag 2</html"),
            new EmailMessageBean("New invoice has been sent recently", "invoice@gmail.com", 12000, "<html>Just some dummy tag 3</html")
    );

    public final ObservableList<EmailMessageBean> Sent = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from Raisehand", "ah@ah.com", 500000000, "<html>Just some dummy tag 4</html"),
            new EmailMessageBean("Yes you can work with us Reliance", "raisehand.io@gmail.com", 145500, "<html>Just some dummy tag 5</html")
    );

    public final ObservableList<EmailMessageBean> Spam = FXCollections.observableArrayList(
            new EmailMessageBean("100% payment guaranty", "ah@ah.com", 500000000, "<html>Just some dummy tag 6</html"),
            new EmailMessageBean("Profit ++ Happening", "profit@gmail.com", 145500, "<html>Just some dummy tag 7</html"),
            new EmailMessageBean("Your bank account has been freeze", "icici@gmail.com", 12000, "<html>Just some dummy tag 8</html")
    );

    public Map<String, ObservableList<EmailMessageBean>> emailFolders = new HashMap<>();

    public SampleData() {
        emailFolders.put("Inbox", Inbox);
        emailFolders.put("Sent", Sent);
        emailFolders.put("Spam", Spam);
    }

}

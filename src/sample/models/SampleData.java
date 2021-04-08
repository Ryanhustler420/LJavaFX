package sample.models;

import sample.models.beans.EmailMessageBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SampleData {

    public static final ObservableList<EmailMessageBean> Inbox = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from Bajaj Automobile", "plasure@automobile.com", 500000000, "<html>Just some dummy tag 1</html", false),
            new EmailMessageBean("You company Reliance Ltd working with us", "reliance@gmail.com", 145500, "<html>Just some dummy tag 2</html", false),
            new EmailMessageBean("New invoice has been sent recently", "invoice@gmail.com", 12000, "<html>Just some dummy tag 3</html", false)
    );

    public static final ObservableList<EmailMessageBean> Sent = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from Raisehand", "ah@ah.com", 500000000, "<html>Just some dummy tag 4</html", true),
            new EmailMessageBean("Yes you can work with us Reliance", "raisehand.io@gmail.com", 145500, "<html>Just some dummy tag 5</html", false)
    );

    public static final ObservableList<EmailMessageBean> Spam = FXCollections.observableArrayList(
            new EmailMessageBean("100% payment guaranty", "ah@ah.com", 500000000, "<html>Just some dummy tag 6</html", false),
            new EmailMessageBean("Profit ++ Happening", "profit@gmail.com", 145500, "<html>Just some dummy tag 7</html", false),
            new EmailMessageBean("Your bank account has been freeze", "icici@gmail.com", 12000, "<html>Just some dummy tag 8</html", false)
    );

}

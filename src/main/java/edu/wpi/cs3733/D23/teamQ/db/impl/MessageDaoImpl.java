package edu.wpi.cs3733.D23.teamQ.db.impl;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageDaoImpl {
    private List<Message> messages = new ArrayList<>();

    public boolean addRow(Message m) {


        messages.add(m);
        return true;
    }

    public boolean populate() throws SQLException {
        return false;
    }

    public List<Message> retrieveMessages(String p1, String p2){
        List<Message> messageList = new ArrayList<>();
        String[] people = {p1, p2};
        Arrays.sort(people);
        for (Message m : messages){
            String uname1 = m.getSender().getUsername();
            String uname2 = m.getReceiver().getUsername();
            String[] people2 = {uname1, uname2};
            Arrays.sort(people2);
            if (people.equals(people2)){
                messageList.add(m);
            }
        }
        return messageList;
    }
}
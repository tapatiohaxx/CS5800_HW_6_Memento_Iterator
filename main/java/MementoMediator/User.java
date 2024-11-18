package MementoMediator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class User {
    private String name;
    private ChatServer server;
    private Stack<Message> messageHistory = new Stack<>(); // Stack to keep track of sent messages

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    public void sendMessage(List<String> recipients, String content) {
        Message message = new Message(name, recipients, content);
        messageHistory.push(message);
        server.sendMessage(name, recipients, content);
    }

    public void receiveMessage(Message message) {
        // Method to handle message reception, could be just printing out or storing
        System.out.println(name + " received from " + message.getSender() + ": " + message.getContent());
    }

    public void retractReceivedMessage(Message message) {
        // This would handle the case where a message needs to be removed from this user's history
        System.out.println(name + " had a message retracted: " + message.getContent());
    }

    public void undoLastMessage() {
        if (!messageHistory.isEmpty()) {
            Message lastMessage = messageHistory.pop();
            server.retractMessage(name, lastMessage);
        } else {
            System.out.println("No message to undo.");
        }
    }

    public String getName() {
        return name;
    }


}




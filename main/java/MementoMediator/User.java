package MementoMediator;
import java.util.Iterator;
import java.util.Stack;
import java.util.List;
public class User implements IterableByUser {
    private String name;
    private ChatServer server;
    private ChatHistory history;
    private Stack<Message> sentMessages = new Stack<>();

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
        this.history = new ChatHistory();
    }

    public void sendMessage(List<String> recipients, String content) {
        Message message = new Message(name, recipients, content);
        history.addMessage(message);
        sentMessages.push(message);
        server.sendMessage(name, recipients, content);
    }

    public void receiveMessage(Message message) {
        history.addMessage(message);
    }

    public void retractReceivedMessage(Message message) {
        history.removeMessage(message);
        System.out.println(name + " message retracted: " + message.getContent());
    }

    public void undoLastMessage() {
        if (!sentMessages.isEmpty()) {
            Message lastSent = sentMessages.pop();
            server.retractMessage(this, lastSent);
        } else {
            System.out.println("No message to undo.");
        }
    }
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatServer getServer() {
        return server;
    }

    public void setServer(ChatServer server) {
        this.server = server;
    }

    public ChatHistory getHistory() {
        return history;
    }

    public void setHistory(ChatHistory history) {
        this.history = history;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return history.iterator(userToSearchWith);
    }
}







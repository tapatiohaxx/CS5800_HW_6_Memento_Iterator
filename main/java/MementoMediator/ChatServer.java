package MementoMediator;
import java.util.*;

public class ChatServer {
    private Map<String, User> users = new HashMap<>();
    private Set<String> blockedUsers = new HashSet<>();

    public void registerUser(User user) {
        users.put(user.getName(), user);
    }

    public void unregisterUser(String username) {
        users.remove(username);
    }

    public void sendMessage(String sender, List<String> recipients, String content) {
        Message message = new Message(sender, recipients, content);
        for (String recipient : recipients) {
            if (!blockedUsers.contains(recipient) && users.containsKey(recipient)) {
                users.get(recipient).receiveMessage(message);
            }
        }
    }

    public void retractMessage(String sender, Message message) {
        for (String recipient : message.getRecipients()) {
            if (users.containsKey(recipient)) {
                users.get(recipient).retractReceivedMessage(message);
            }
        }
        System.out.println("Message retracted by " + sender + ": " + message.getContent());
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void blockUser(String username) {
        blockedUsers.add(username);
    }

    public void unblockUser(String username) {
        blockedUsers.remove(username);
    }
}




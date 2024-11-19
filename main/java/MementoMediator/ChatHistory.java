package MementoMediator;
import java.util.*;

public class ChatHistory {
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }
    public void removeMessage(Message message) {
        messages.remove(message);
        System.out.println("Message removed from history: " + message.getContent());
    }

    public Iterator<Message> iterator(User userToSearchWith) {
        return new Iterator<Message>() {
            private final List<Message> filteredMessages = new ArrayList<>();
            private int currentIndex = 0;

            {
                for (Message msg : messages) {
                    if (msg.getSender().equals(userToSearchWith.getName()) ||
                            msg.getRecipients().contains(userToSearchWith.getName())) {
                        filteredMessages.add(msg);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return currentIndex < filteredMessages.size();
            }

            @Override
            public Message next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return filteredMessages.get(currentIndex++);
            }
        };
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(new ArrayList<>(messages));
    }
}





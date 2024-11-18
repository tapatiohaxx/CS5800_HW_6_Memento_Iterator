package MementoMediator;

import java.util.*;

public class ChatHistory implements IterableByUser {
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new Iterator<Message>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                while (index < messages.size()) {
                    Message current = messages.get(index);
                    if (current.getSender().equals(userToSearchWith.getName()) ||
                            current.getRecipients().contains(userToSearchWith.getName())) {
                        return true;
                    }
                    index++;
                }
                return false;
            }

            @Override
            public Message next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return messages.get(index++);
            }
        };
    }
}



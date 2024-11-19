package MementoMediator;

import java.util.Arrays;
import java.util.Iterator;

public class ChatApplication {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        alice.sendMessage(Arrays.asList("Bob"), "Hello Bob!");
        alice.sendMessage(Arrays.asList("Bob"), "How are you today?");
        bob.sendMessage(Arrays.asList("Alice"), "Hi Alice, nice to hear from you!");

        // Iterate messages that Alice has received from Bob
        Iterator<Message> messagesFromBob = alice.iterator(bob);
        while (messagesFromBob.hasNext()) {
            Message msg = messagesFromBob.next();
            System.out.println("Alice received from Bob: " + msg.getContent());
        }
    }
}

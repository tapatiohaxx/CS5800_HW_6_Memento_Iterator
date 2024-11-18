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

        Iterator<Message> messagesFromAlice = bob.iterator(alice);
        while (messagesFromAlice.hasNext()) {
            Message msg = messagesFromAlice.next();
            System.out.println("Bob received from Alice: " + msg.getContent());
        }
    }
}





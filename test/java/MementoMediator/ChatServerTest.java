package MementoMediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;

public class ChatServerTest {
    private ChatServer server;
    private User alice;
    private User bob;

    @BeforeEach
    public void setUp() {
        server = new ChatServer();
        alice = new User("Alice", server);
        bob = new User("Bob", server);
        server.registerUser(alice);
        server.registerUser(bob);
    }

    @Test
    public void testSendMessage() {
        alice.sendMessage(Arrays.asList("Bob"), "Hello Bob!");
        Iterator<Message> it = bob.iterator(alice);
        assertTrue(it.hasNext());
        assertEquals("Hello Bob!", it.next().getContent());
    }

    @Test
    public void testBlockUser() {
        server.blockUser("Bob");
        alice.sendMessage(Arrays.asList("Bob"), "Hello Bob!");
        Iterator<Message> it = bob.iterator(alice);
        assertFalse(it.hasNext());
    }

    @Test
    public void testUnregisterUser() {
        server.unregisterUser("Bob");
        assertEquals(null, server.getUser("Bob"));
    }

    @Test
    void registerUser() {
        server.registerUser(alice);
        assertNotNull(server.getUser("Alice"), "Alice should be registered in the server.");
    }

    @Test
    void unregisterUser() {
        server.registerUser(alice);
        server.unregisterUser("Alice");
        assertNull(server.getUser("Alice"), "Alice should be removed from the server.");
    }

    @Test
    void sendMessage() {
        server.registerUser(alice);
        server.registerUser(bob);
        alice.sendMessage(java.util.Arrays.asList("Bob"), "Hello Bob!");
        // Assuming Bob’s receiveMessage method adds received messages to a testable structure.
        assertTrue(bob.getHistory().getMessages().contains("Hello Bob!"), "Bob should receive the message from Alice.");
    }

    @Test
    void retractMessage() {
        server.registerUser(alice);
        server.registerUser(bob);
        Message msg = new Message("Alice", java.util.Arrays.asList("Bob"), "Hello Bob!");
        alice.sendMessage(java.util.Arrays.asList("Bob"), "Hello Bob!");
        server.retractMessage(alice, msg);
        // Assuming you have a way to check that Bob's message was retracted, such as a flag or message removal
        assertFalse(bob.getHistory().getMessages().contains("Hello Bob!"), "The message should be retracted from Bob’s history.");
    }

    @Test
    void blockUser() {
        server.registerUser(alice);
        server.registerUser(bob);
        server.blockUser("Bob");
        assertTrue(server.isBlocked("Bob"), "Bob should be blocked.");
        alice.sendMessage(java.util.Arrays.asList("Bob"), "Hello Bob!");

        assertFalse(bob.getHistory().getMessages().contains("Hello Bob!"), "Blocked Bob should not receive messages.");
    }

    @Test
    void unblockUser() {
        server.registerUser(alice);
        server.registerUser(bob);
        server.blockUser("Bob");
        server.unblockUser("Bob");
        assertFalse(server.isBlocked("Bob"), "Bob should be unblocked.");
        alice.sendMessage(java.util.Arrays.asList("Bob"), "Hello again, Bob!");
        assertTrue(bob.getHistory().getMessages().contains("Hello again, Bob!"), "Unblocked Bob should now receive messages.");
    }
}

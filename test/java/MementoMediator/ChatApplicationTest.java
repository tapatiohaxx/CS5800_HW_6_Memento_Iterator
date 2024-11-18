package MementoMediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;

public class ChatApplicationTest {
    private ChatServer server;
    private User alice;
    private User bob;
    private User charlie;

    @BeforeEach
    public void setUp() {
        server = new ChatServer();
        alice = new User("Alice", server);
        bob = new User("Bob", server);
        charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);
    }

    @Test
    public void testUserRegistration() {
        assertNotNull(server.getUser("Alice"), "Alice should be registered.");
        assertNotNull(server.getUser("Bob"), "Bob should be registered.");
        assertNotNull(server.getUser("Charlie"), "Charlie should be registered.");
    }

    @Test
    public void testSendMessageAndReceive() {
        alice.sendMessage(Arrays.asList("Bob"), "Hello Bob!");
        Iterator<Message> it = bob.iterator(alice);
        assertTrue(it.hasNext(), "Bob should have received a message from Alice.");
        Message received = it.next();
        assertEquals("Hello Bob!", received.getContent(), "The content of the message should match.");
    }

    @Test
    public void testUndoMessageFunctionality() {
        alice.sendMessage(Arrays.asList("Bob"), "Hello Bob!");
        alice.undoLastMessage();
        Iterator<Message> it = bob.iterator(alice);
        assertFalse(it.hasNext(), "Bob should not have any messages from Alice after undo.");
    }

    @Test
    public void testUserBlocking() {
        server.blockUser("Charlie");
        alice.sendMessage(Arrays.asList("Charlie"), "Hello Charlie!");
        Iterator<Message> it = charlie.iterator(alice);
        assertFalse(it.hasNext(), "Charlie should not receive any messages due to being blocked.");
    }

    @Test
    public void testMultipleMessagesAndUsers() {
        alice.sendMessage(Arrays.asList("Bob", "Charlie"), "Hello everyone!");
        Iterator<Message> bobMessages = bob.iterator(alice);
        Iterator<Message> charlieMessages = charlie.iterator(alice);

        assertTrue(bobMessages.hasNext(), "Bob should have received a message.");
        assertEquals("Hello everyone!", bobMessages.next().getContent(), "Bob's message content should match.");

        assertFalse(charlieMessages.hasNext(), "Charlie should not have received any messages as he is blocked.");
    }
}

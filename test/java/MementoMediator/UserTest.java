package MementoMediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;

public class UserTest {
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
    public void testReceiveMessage() {
        bob.sendMessage(Arrays.asList("Alice"), "Hi Alice!");
        Iterator<Message> it = alice.iterator(bob);
        assertTrue(it.hasNext());
        assertEquals("Hi Alice!", it.next().getContent());
    }

    @Test
    public void testIteratorNoMessages() {
        Iterator<Message> it = alice.iterator(bob);
        assertFalse(it.hasNext());
    }
}

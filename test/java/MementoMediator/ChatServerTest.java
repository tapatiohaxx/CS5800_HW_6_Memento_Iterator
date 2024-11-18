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
    }

    @Test
    void unregisterUser() {
    }

    @Test
    void sendMessage() {
    }

    @Test
    void retractMessage() {
    }

    @Test
    void blockUser() {
    }

    @Test
    void unblockUser() {
    }
}

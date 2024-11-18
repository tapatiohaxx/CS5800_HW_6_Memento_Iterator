package MementoMediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ChatHistoryTest {
    private ChatHistory history;
    private User alice;
    private User bob;

    @BeforeEach
    void setUp() {
        history = new ChatHistory();
        alice = new User("Alice", null); // Null server for simplicity in unit tests
        bob = new User("Bob", null);
    }

    @Test
    void testAddAndRetrieveMessages() {
        history.addMessage(new Message("Alice", java.util.Arrays.asList("Bob"), "Hi Bob!"));
        Iterator<Message> it = history.iterator(bob);
        assertTrue(it.hasNext(), "There should be a message for Bob.");
        Message retrieved = it.next();
        assertEquals("Hi Bob!", retrieved.getContent(), "The message content should match.");
    }

    @Test
    void testEmptyHistory() {
        Iterator<Message> it = history.iterator(alice);
        assertFalse(it.hasNext(), "There should be no messages in an empty history.");
    }
}

package MementoMediator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void testMessageProperties() {
        Message message = new Message("Alice", java.util.Arrays.asList("Bob"), "Hello, Bob!");
        assertEquals("Alice", message.getSender(), "The sender should be Alice.");
        assertTrue(message.getRecipients().contains("Bob"), "The recipients should contain Bob.");
        assertEquals("Hello, Bob!", message.getContent(), "The message content should be 'Hello, Bob!'.");
    }
}


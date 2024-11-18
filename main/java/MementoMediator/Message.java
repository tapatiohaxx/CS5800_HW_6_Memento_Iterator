package MementoMediator;

import java.util.Date;
import java.util.List;

public class Message {
    private String sender;
    private List<String> recipients;
    private String content;
    private Date timestamp;

    public Message(String sender, List<String> recipients, String content) {
        this.sender = sender;
        this.recipients = recipients;
        this.content = content;
        this.timestamp = new Date(); // Timestamp when the message was created
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}



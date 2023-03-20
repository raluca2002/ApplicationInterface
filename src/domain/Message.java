package domain;

import utils.Constants;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message extends Entity<Long> {


    private Long from;
    private Long to;
    private String messageText;
    private LocalDateTime data;
    private Long idReplay;

    public Message(Long from, Long to, String messageText, LocalDateTime data, Long idReplay) {
        this.from = from;
        this.to = to;
        this.messageText = messageText;
        this.data = data;
        this.idReplay = idReplay;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getMessage() {
        return messageText;
    }

    public void setMessage(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from= " + from +
                ", to= " + to +
                ", message= " + messageText +
                ", date=" + data.format(Constants.DATE_TIME_FORMATTER) +
                ", idReplay= " + idReplay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(messageText, that.messageText) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, messageText, data);
    }

    public Long getIdReplay() {
        return idReplay;
    }

    public void setIdReplay(Long idReplay) {
        this.idReplay = idReplay;
    }
}

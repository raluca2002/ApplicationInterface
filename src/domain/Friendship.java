package domain;

import utils.Constants;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Objects;

public class Friendship extends Entity<Long>{
    private final Long id1;
    private final Long id2;

    private final LocalDateTime friendsFrom;

    public Friendship(Long id1, Long id2) {
        this.id1 = id1;
        this.id2 = id2;
        this.friendsFrom = LocalDateTime.now();
    }

    public Friendship(Long id1, Long id2, LocalDateTime date) {
        this.id1 = id1;
        this.id2 = id2;
        this.friendsFrom = date;
    }

    public Long getId1() {
        return id1;
    }

    public Long getId2() {
        return id2;
    }

    public LocalDateTime getFriendsFrom() {
        return friendsFrom;
    }

    @Override
    public String toString(){
        return "Friendship(" + "id1=" + id1 + ", id2=" + id2 + ", date=" + friendsFrom.format(Constants.DATE_TIME_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(id1, that.id1) && Objects.equals(id2, that.id2) && Objects.equals(friendsFrom, that.friendsFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2, friendsFrom);
    }
}

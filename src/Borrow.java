import java.util.Objects;
import java.util.UUID;
import java.time.LocalDate;

public final class Borrow {
    public final Book book;
    public final User user;
    public final int days;
    public LocalDate brwdate;
    public final UUID id;

    Borrow(Book book, User user, int days, LocalDate brwdate) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.user = user;
        this.days = days;
        this.brwdate = brwdate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Borrow) obj;
        return Objects.equals(this.book, that.book) &&
                Objects.equals(this.user, that.user) &&
                this.days == that.days &&
                Objects.equals(this.brwdate, that.brwdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, user, days, brwdate);
    }

    @Override
    public String toString() {
        return "Borrow[" +
                "book=" + book + ", " +
                "user=" + user + ", " +
                "days=" + days + ", " +
                "brwdate=" + brwdate + ']';
    }

}
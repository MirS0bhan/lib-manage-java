import java.util.Objects;

public final class Book {
    public String title;
    public String author;

    public Book(
            String title,
            String author
    ) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Book) obj;
        return Objects.equals(this.title, that.title) &&
                Objects.equals(this.author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "Book[" +
                "title=" + title + ", " +
                "author=" + author + ']';
    }
}
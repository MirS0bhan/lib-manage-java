import java.util.ArrayList;
import java.util.List;

record Borrow(Book book, User user, int days){}


class Library {
    private final int max_borrow_day = 15;
    final List<User> users = new ArrayList<>();
    final List<Book> books = new ArrayList<>();
    final List<Borrow> borrows = new ArrayList<>();


    public void registerUser(User user) {
        users.add(user);
    }

    public User getUser(String username){
        for (User user : users) {
            if (user.name.equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Book getBook(String book_name){
        for (Book book : books) {
            if (book.title.equals(book_name)) {
                return book;
            }
        }
        return null;
    }

    public void updateUser(User user, String newName, int newAge) {
        user.name = newName;
        user.age = newAge;
    }

    public void deleteUser(User userd) {
        users.removeIf(user -> user.equals(userd));
    }

    public User queryUser(String name) {
        for (User user : users) {
            if (user.name.contains(name) || user.name.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public Borrow barrowBook(Book book, User user){
        Borrow borrow = new Borrow(book, user, max_borrow_day);
        borrows.add(borrow);
        return borrow;
    }

    public void registerBook(Book book) {
        books.add(book);
    }

    public void updateBook(String title, String newTitle, String newAuthor) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                book.title = newTitle;
                book.author = newAuthor;
                return;
            }
        }
    }

    public void deleteBook(String title) {
        books.removeIf(book -> book.title.equals(title));
    }

    public Book queryBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                return book;
            }
        }
        return null;
    }
}
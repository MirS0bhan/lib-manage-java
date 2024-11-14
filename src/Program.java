import java.time.LocalDate;
import java.util.Scanner;

public class Program {
    public String line = "\n+------------------------------------+\n";
    Scanner scanner = new Scanner(System.in);
    Library myLibrary = new Library();

    String intro =
            " \n welcome to your library admin system "
                    + line +
                    " select section number to enter:\n" +
                    " 1) Books\n" +
                    " 2) Users\n" +
                    " 3) Borrow\n" +
                    " 4) statics\n"
                    + line ;

    String book_section =
            " \n you are in the books aisle "
                    + line +
                    " select what you want to do:\n" +
                    " 1.1) register\n" +
                    " 1.2) update\n" +
                    " 1.3) delete\n" +
                    " 1.4) query\n" +
                    " 1.0) back"
                    + line ;

    String users_section =
            " \n you are in the users section "
                    + line +
                    " select what you want to do:\n" +
                    " 2.1) register\n" +
                    " 2.2) update\n" +
                    " 2.3) delete\n" +
                    " 2.4) query\n" +
                    " 2.0) back"
                    + line ;

    String borrow_section =
            " \n you are in the borrow section "
                    + line +
                    " select what you want to do:\n" +
                    " 2.1) new\n" +
                    " 2.2) return \n" +
                    " 2.3) renew \n" +
                    " 2.4) all" +
                    " 2.0) back"
                    + line ;


    public void start() {
        print(intro);

        String state;
        main: while(true) {
            print("0) enter command: ");
            state = scanner.next();
            switch (state) {
                case "1", "book":
                    bookSection(); break;
                case "2", "user":
                    userSection(); break;
                case "3", "borrow":
                    borrowSection();break;
                case "4", "statics":
                    displayLibraryStats(); break;

                case "help":
                    print(intro);break;
                case "exit":
                    break main;
                default:
                    print("0) unknown command");
            }
        }
    }

//    public boolean exitState(String state){
//        return !(state.equals("exit") || state.equals("back"));
//    }

    public void userSection() {
        print(users_section);
        String state;
        w: while(true) {
            print("1) enter command: ");
            state = scanner.next();
            switch (state) {
                case "1", "register":
                    registerUser(); break;
                case "2", "update":
                    updateUser(); break;
                case "3", "delete":
                    deleteUser(); break;
                case "4", "query":
                    queryUser(); break;

                case "5", "all":
                    myLibrary.users.forEach(user -> print(user));break ;

                case "help":
                    print(users_section);break;

                case "back", "exit", ";":
                    break w;
                default:
                    print("unknown command");
            }
        }
    }

    public void bookSection() {
        print(book_section);
        String state;
        w: while (true){
            print("1) enter command: ");
            state = scanner.next();
            switch (state) {
                case "1", "register":
                    registerBook();
                    break;
                case "2":
                    updateBook();
                    break;
                case "3":
                    deleteBook();
                    break;
                case "4":
                    queryBook();
                    break;

                case "5", "all":
                    myLibrary.books.forEach(book -> print(book));break;

                case "back", "exit", ";":
                    break w;
                default:
                    print("unknown command");
                    break;

            }
        }
    }

    public void borrowSection() {
        print(borrow_section);
        String state;
        w:
        while (true) {
            print("3) enter command: ");
            state = scanner.next();
            switch (state) {
                case "1", "new":
                    borrowBook();
                    break;
                case "2", "renew":
                    renewBorrow(); break;
                case "3", "return":
                    returnBorrow(); break;
                case "4", "all":
                    myLibrary.borrows.forEach(borrow -> print(borrow));break ;

                case "back", "exit", ";":
                    break w;
                case "help":
                    print(users_section);
                    break;
                default:
                    print("unknown command");
            }
        }
    }

    private void returnBorrow() {
        Book book = getBook("3.2");
        if(myLibrary.borrows.removeIf(borrow -> borrow.book == book)){
            print("book "+book+" returned"); return;
        }

        print("book "+book+" has not borrowed");

    }

    public void borrowBook(){
        print("3.1) enter book name");
        Book book = myLibrary.getBook(scanner.next());

        print("3.1) enter user name: ");
        User user = myLibrary.getUser(scanner.next());

        Borrow brw = myLibrary.barrowBook(book, user);
        print("book borrowed sucssesfuly " + brw);
    }

    public void renewBorrow(){
        // find the borrow object by book
        Book book = getBook("3.2");
        Borrow borrow = myLibrary.borrows.stream().filter(borrow1 -> borrow1.book==book).findFirst().get();

        // update the borrow time
        borrow.brwdate = LocalDate.now();
        print(borrow + "has renewed");
    }

    public void registerUser() {
        print("2.1) enter user name ");
        String user_name = scanner.next();

        print("2.1) enter user age: ");
        int user_age = scanner.nextShort();

        User user = new User(user_name, user_age);
        myLibrary.registerUser(user);
        print("User " + user + " registered successfully");
    }

    public void updateUser() {
        User user = getUser("2.2)");

        print("2.2) enter new user name: ");
        String new_name = scanner.next();

        print("2.2) enter new user age: ");
        int new_age = scanner.nextShort();

        myLibrary.updateUser(user, new_name, new_age);
        print("User " + user.name + " updated successfully");
    }

    public void deleteUser() {
        User user = getUser("2.3)");

        myLibrary.deleteUser(user);
        print("User " + user.name + " deleted successfully");
    }

    public void queryUser() {
        print("2.4) enter the name of the user to query: ");
        String user_name = scanner.next();

        User user = myLibrary.queryUser(user_name);
        if (user != null) {
            print("User found: " + user);
        } else {
            print("User " + user_name + " not found");
        }
    }

    public void registerBook() {
        print("1.1) enter book title: ");
        String book_title = scanner.next();

        print("1.1) enter book author: ");
        String book_author = scanner.next();

        Book book = new Book(book_title, book_author);
        myLibrary.registerBook(book);
        print("Book " + book + " registered successfully");
    }

    public void updateBook() {
        print("1.2) enter the title of the book to update: ");
        String book_title = scanner.next();

        print("1.2) enter new book title: ");
        String new_title = scanner.next();

        print("1.2) enter new book author: ");
        String new_author = scanner.next();


        myLibrary.updateBook(book_title, new_title, new_author);
        print("Book " + book_title + " updated successfully");
    }

    public void deleteBook() {
        print("1.3) enter the title of the book to delete: ");
        String book_title = scanner.next();

        myLibrary.deleteBook(book_title);
        print("Book " + book_title + " deleted successfully");
    }

    public void queryBook() {
        print("1.4) enter the title of the book to query: ");
        String book_title = scanner.next();

        Book book = myLibrary.queryBook(book_title);
        if (book != null) {
            print("Book found: " + book);
        } else {
            print("Book " + book_title + " not found");
        }
    }

    public User getUser(String level){
        print(level +" enter the user name ");
        return myLibrary.getUser(scanner.next());
    }

    public Book getBook(String level){
        print(level +" enter the book title ");
        return myLibrary.getBook(scanner.next());
    }

    <T> void print(T input) {
        System.out.println(input);
    }

    public void displayLibraryStats() {
        int available_books = myLibrary.books.size() - myLibrary.borrows.size();


        String statsDisplay = String.format(
                "=============================\n" +
                "|        Library Stats       |\n" +
                "=============================\n" +
                "| Total Users:               | %d\n" +
                "|----------------------------|\n" +
                "| Total Books:               | %d\n" +
                "|----------------------------|\n" +
                "| Borrowed Books:            | %d\n" +
                "|----------------------------|\n" +
                "| Available Books:           | %d\n" +
//                "|----------------------------|\n" +
//                "| Total Authors:             | %d\n" +
//                "|----------------------------|\n" +
//                "| Most Popular Book:         | %s\n" +
                "=============================\n"
        , myLibrary.users.size(), myLibrary.books.size(),myLibrary.borrows.size(), available_books);

        System.out.print(statsDisplay);
    }
}
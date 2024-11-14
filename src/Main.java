public class Main {
    public static void main(String[] args) {
        Program program = new Program();


        program.myLibrary.registerUser(new User("sobhan", 19));
        program.myLibrary.registerBook(new Book("imam_ali", "sobhjan"));

        program.start();
    }
}
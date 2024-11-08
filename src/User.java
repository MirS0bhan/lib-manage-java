import java.util.Objects;

public final class User {
    public int id = 0;
    public String name;
    public int age;
    public Borrow[] borrows;

    public User(
            String name,
            int age
    ) {

        this.name = name;
        this.age = age;
        this.borrows = new Borrow[1024];
    }

    public Borrow[] borrows() {
        return borrows;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.age, that.age) &&
                Objects.equals(this.borrows, that.borrows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, borrows);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "age=" + age + ']';
    }


}
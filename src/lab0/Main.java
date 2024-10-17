package lab0;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Effective Java", "Joshua Bloch", "1234567890"));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "0987654321"));
        library.addBook(new Book("Design Patterns", "Erich Gamma", "1122334455"));

        // Using AuthorFilter to filter books by author "Joshua Bloch"
        AuthorFilter authorFilter = new AuthorFilter("Joshua Bloch");
        List<Book> filteredBooksByAuthor = LibrarySearch.filterBooks(library.getBooks(), authorFilter);
        System.out.println("Books by Joshua Bloch: " + filteredBooksByAuthor);

        // Using TitleFilter to filter books by title "Clean Code"
        TitleFilter titleFilter = new TitleFilter("Clean Code");
        List<Book> filteredBooksByTitle = LibrarySearch.filterBooks(library.getBooks(), titleFilter);
        System.out.println("Books with title 'Clean Code': " + filteredBooksByTitle);
    }
}
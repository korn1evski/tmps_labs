package lab0;

import java.util.ArrayList;
import java.util.List;

public class LibrarySearch {
    public static List<Book> filterBooks(List<Book> books, BookFilter filter) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (filter.filter(book)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
}
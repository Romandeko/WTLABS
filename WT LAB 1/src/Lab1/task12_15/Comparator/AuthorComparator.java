package Lab1.task12_15.Comparator;

import java.util.Comparator;
import Lab1.task12_15.Book;

public class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthor().compareTo(o2.getAuthor());
    }
}

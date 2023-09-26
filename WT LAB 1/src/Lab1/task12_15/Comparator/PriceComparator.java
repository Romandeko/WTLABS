package Lab1.task12_15.Comparator;

import Lab1.task12_15.Book;

import java.util.Comparator;

public class PriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPrice()-o2.getPrice();
    }
}

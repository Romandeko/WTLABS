package Lab1Tests;

import Lab1.task1.T1;
import Lab1.task12_15.Book;
import Lab1.task12_15.Comparator.AuthorComparator;
import Lab1.task12_15.Comparator.TitleComparator;
import Lab1.task12_15.ShellSort;
import Lab1.task2.T2;
import Lab1.task3.T3;
import Lab1.task4.T4;
import Lab1.task5.T5;
import Lab1.task6.T6;
import Lab1.task7.T7;
import Lab1.task8.T8;
import Lab1.task9.T9;
import Lab1.task12_15.T12_15;
import Lab1.task9.*;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Tests_All {
    private static final double EPS = 0.01;
    Comparator<Book> titleAuthorComparator = new TitleComparator().thenComparing(new AuthorComparator());
    T1 t1 = new T1();
    T2 t2 = new T2();
    T3 t3 = new T3();
    T4 t4 = new T4();
    T5 t5 = new T5();
    T6 t6 = new T6();
    T7 t7 = new T7();
    T8 t8 = new T8();
    T9 t9 = new T9();
    T12_15 t12_15 = new T12_15();

    @Test
    public void T1_1() {
        assertEquals(2, t1.calculate(1, 2), EPS);
    }

    @Test
    public void T2_1() {
        assertEquals(true, t2.check(0, 0));
    }

    @Test
    public void T2_2() {
        assertEquals(false, t2.check(0, 100));
    }

    @Test
    public void T3_1() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            t3.calculate(1, 0, 0.1);
        });
        assertEquals("a > b", exception.getMessage());
    }

    @Test
    public void T3_2() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            t3.calculate(1, 2, -0.1);
        });
        assertEquals("h <= 0", exception.getMessage());
    }

    @Test
    public void T3_3() {
        List<T3.Answer> result = t3.calculate(1, 2, 0.3);

        assertEquals(result.get(0).x, 1.0, EPS);
        assertEquals(result.get(0).Fx, 1.55, EPS);
        assertEquals(result.get(1).x, 1.3, EPS);
        assertEquals(result.get(1).Fx, 3.60, EPS);
        assertEquals(result.get(2).x, 1.6, EPS);
        assertEquals(result.get(2).Fx, -34.23, EPS);
        assertEquals(result.get(3).x, 1.90, EPS);
        assertEquals(result.get(3).Fx, -2.92, EPS);

    }

    @Test
    public void T4() {
        assertEquals(t4.calculate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), "[2, 3, 5, 7]");
    }

    @Test
    public void T5_1() {
        assertEquals(0, t5.activate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void T5_2() {
        assertEquals(1, t5.activate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1}));
    }

    @Test
    public void T5_3() {
        assertEquals(5, t5.activate(new int[]{1, 2, 3, 0, -1, 6, 7, 8, 9, 1, 0, -1}));
    }

    @Test
    public void T6() {
        int[][] expected = new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 1, 2}};
        assertEquals(t6.activate(new int[]{1, 2, 3}), expected);
    }

    @Test
    public void T7() {
        int[] initial = new int[]{5, 1, 9, 3, 7, 4, 8, 6, 2};
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(Arrays.toString(t7.activate(initial)), Arrays.toString(expected));
    }

    @Test
    public void T8() {
        int[] array1 = new int[]{1, 5, 7, 8};
        int[] array2 = new int[]{2, 3, 4, 6, 9};
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println(t8.activate(array1, array2).toString() + "  " + expected.toString());
        assertEquals(Arrays.toString(t8.activate(array1, array2)), Arrays.toString(expected));
    }

    @Test
    public void T9_1() {
        Ball ball1 = new Ball(1, Color.YELLOW);
        Ball ball2 = new Ball(1, Color.BLUE);

        Bucket bucket = new Bucket(3);
        bucket.addBall(ball1);
        bucket.addBall(ball2);
        assertEquals(2, t9.getWeight(bucket), 0.01);
    }

    @Test
    public void T9_2() {
        Ball ball1 = new Ball(1, Color.YELLOW);
        Ball ball2 = new Ball(1, Color.BLUE);
        Ball ball3 = new Ball(1, Color.BLUE);

        Bucket bucket = new Bucket(9);
        bucket.addBall(ball1);
        bucket.addBall(ball2);
        bucket.addBall(ball3);

        assertEquals(t9.countBlue(bucket), 2);
    }

    @Test
    public void T12_15() {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        bookArrayList.add(new

                Book("title4", "author3", 14));
        bookArrayList.add(new

                Book("title3", "author6", 9));
        bookArrayList.add(new

                Book("title2", "author0", 4));
        bookArrayList.add(new

                Book("title1", "author-1", 1));
        bookArrayList.add(new

                Book("title0", "author312", 4));

        ShellSort.sort(bookArrayList, titleAuthorComparator);

        assertEquals(bookArrayList.toString(), "[Title:title0|Author:author312|Price:4|Edition:0, Title:title1|Author:author-1|Price:1|Edition:0, Title:title2|Author:author0|Price:4|Edition:0, Title:title3|Author:author6|Price:9|Edition:0, Title:title4|Author:author3|Price:14|Edition:0]");
    }
}


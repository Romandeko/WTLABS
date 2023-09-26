package Lab1.task3;

import java.util.ArrayList;
import java.util.List;

public class T3 {
    public List<Answer> calculate(double a, double b, double h) {
        if (a > b) {
            throw new IllegalArgumentException("a > b");
        }

        if (h <= 0) {
            throw new IllegalArgumentException("h <= 0");
        }

        List<Answer> answers = new ArrayList<>(calculateSize(a, b, h));

        for (double x = a; x <= b; x += h) {
            answers.add(new Answer(x, calculateY(x)));
        }

        return answers;
    }

    private int calculateSize(double a, double b, double h) {
        return (int) ((b - a) / h) + 1;
    }

    private double calculateY(double x) {
        return Math.tan(x);
    }

    public class Answer{
        public double x;
        public double Fx;

        Answer(double x, double Fx) {
            this.x = x;
            this.Fx = Fx;
        }

    }

}

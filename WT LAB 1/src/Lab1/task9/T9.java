package Lab1.task9;


import java.util.function.Predicate;

public class T9 {
    public int getWeight(Bucket bucket) {
        return bucket.countWeight();
    }

    public int countBlue(Bucket bucket) {
        return bucket.countBlue();
    }
}
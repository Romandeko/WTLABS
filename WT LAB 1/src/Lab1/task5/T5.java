package Lab1.task5;

import java.util.ArrayList;
import java.util.List;

public class T5 {
    public int activate(int[] input) {
        List<Integer> sub = new ArrayList<>();

        for (Integer integer : input) {
            if (sub.isEmpty() || integer > sub.get(sub.size() - 1)) {
                sub.add(integer);
            } else {
                sub.set(ceilIndex(sub, integer), integer);
            }
        }

        return input.length - sub.size();
    }

    private int ceilIndex(List<Integer> sub, int val) {
        int l = 0;
        int r = sub.size() - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (sub.get(mid) >= val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

}

package Lab1.task7;

public class T7 {
    public int[] activate(int[] input) {
        int n = input.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && input[j] < input[j - h]; j -= h) {
                    int temp = input[j];
                    input[j] = input[j - h];
                    input[j - h] = temp;
                }
            }
            h /= 3;
        }

        return input;
    }

}

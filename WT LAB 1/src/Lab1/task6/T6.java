package Lab1.task6;

public class T6 {
    public int[][] activate(int[] input) {
        int[][] output = new int[input.length][input.length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                output[i][j] = input[(i + j) % input.length];
            }
        }

        return output;
    }

}

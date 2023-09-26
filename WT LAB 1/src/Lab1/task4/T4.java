package Lab1.task4;

import java.util.ArrayList;

public class T4 {
    public static void main(String[] args) {
        //i took N = 4;
        int[] sourceArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        calculate(sourceArray);
    }

    public static String calculate(int[] mas) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        boolean hasPrimeNumbers = false;
        for (int i = 0; i < mas.length; i++)
            if (isPrime(mas[i])) {
                hasPrimeNumbers = true;
                res.add(mas[i]);
            }
        if (!hasPrimeNumbers)
            System.out.println("No prime numbers");
        return res.toString();
    }

    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(n) + 1; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
}

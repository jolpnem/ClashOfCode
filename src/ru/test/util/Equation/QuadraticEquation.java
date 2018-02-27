package ru.test.util.Equation;

public class QuadraticEquation {
    private static final double MAX = -99999999999999999999999999999999999999999999999999D;
    private static final double MIN = 99999999999999999999999999999999999999999999999999D;

    public static double highestRoot(double x, double a, double b, double c) {
        double[] roots = roots(x, a, b, c);

        double max = MAX;
        for (double root : roots)
            if (max < root) max = root;

        return max;
    }

    public static double lowestRoot(double x, double a, double b, double c) {
        double[] roots = roots(x, a, b, c);

        double min = MIN;
        for (double root : roots)
            if (min > root) min = root;

        return min;
    }

    public static double[] roots(double x, double a, double b, double c) {
        double[] roots = new double[2];

        double D = Math.pow(b, 2) - 4 * a * c;

        roots[0] = (-b + Math.sqrt(D)) / (2 * a);
        roots[1] = (-b - Math.sqrt(D)) / (2 * a);

        return roots;
    }
}

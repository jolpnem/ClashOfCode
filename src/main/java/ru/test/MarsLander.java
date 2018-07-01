package ru.test;

import ru.test.util.Formulas.FreeFallFormulas;

public class MarsLander {
    private static final double GRAVITY = 3.711;

    public static void main(String args[]) {
//        Scanner in = new Scanner(System.in);
//        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
//        for (int i = 0; i < surfaceN; i++) {
//            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
//            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
//        }
//
//        // game loop
//        while (true) {
//            int X = in.nextInt();
//            int Y = in.nextInt();
//            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
//            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
//            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
//            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
//            int power = in.nextInt(); // the thrust power (0 to 4).

        int S = 600;
        int V0 = Math.abs(100);
        double a = GRAVITY;
        double endSpeed = FreeFallFormulas.endSpeedFromDistance(V0, a, S);

        double timeWithoutPower = FreeFallFormulas.timeFromStartSpeed(V0, a, S);
        double timeWithPower = FreeFallFormulas.timeFromStartAndEndSpeeds(V0, 35, a - 4);

        System.err.println(endSpeed);
        System.err.println(timeWithoutPower);
        System.err.println(timeWithPower);
        System.err.println("path " + S);
        System.err.println("speed " + V0);
        System.err.println(Math.sqrt(V0 + 2 * a * S));
        System.err.println(V0);
//
//            if(Y < 2300) power = 3;
//            System.out.println(rotate + " " + power);
//        }
    }
}

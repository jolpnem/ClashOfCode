package ru.test.util.Formulas;

public class FreeFallFormulas {
    public static double distanceFromTime(double startSpeed, double endSpeed, double time) {
        return time * (endSpeed - startSpeed) / 2;
    }

    public static double distanceFromAcceleration(double startSpeed, double endSpeed, double acceleration) {
        return (Math.pow(endSpeed, 2) + Math.pow(startSpeed, 2)) / (2 * acceleration);
    }

    public static double distanceFromStartSpeed(double startSpeed, double time, double acceleration) {
        return startSpeed * time + acceleration * Math.pow(time, 2) / 2;
    }

    public static double distanceFromEndSpeed(double endSpeed, double time, double acceleration) {
        return endSpeed * time - acceleration * Math.pow(time, 2) / 2;
    }

    public static double timeFromStartAndEndSpeeds(double startSpeed, double endSpeed, double acceleration) {
        return (endSpeed - startSpeed) / acceleration;
    }

    public static double timeFromStartSpeed(double startSpeed, double acceleration, double distance) {
        return (Math.sqrt(Math.pow(startSpeed, 2) + 2 * acceleration * distance) - startSpeed) / acceleration;
    }

    public static double timeFromEndSpeed(double endSpeed, double acceleration, double distance) {
        return -(Math.sqrt(Math.pow(endSpeed, 2) + 2 * acceleration * distance) - endSpeed) / acceleration;
    }

    public static double startSpeedFromTime(double endSpeed, double acceleration, double time) {
        return endSpeed - time * acceleration;
    }

    public static double startSpeedFromDistance(double endSpeed, double acceleration, double distance) {
        return Math.sqrt(Math.pow(endSpeed, 2) - 2 * acceleration * distance);
    }

    public static double endSpeedFromTime(double startSpeed, double acceleration, double time) {
        return startSpeed + time * acceleration;
    }

    public static double endSpeedFromDistance(double startSpeed, double acceleration, double distance) {
        return Math.sqrt(Math.pow(startSpeed, 2) + 2 * acceleration * distance);
    }
}

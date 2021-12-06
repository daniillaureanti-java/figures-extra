package com.epam.rd.autotasks.figures;

abstract class Figure{

    public abstract Point centroid();
    public abstract boolean isTheSame(Figure figure);

    public abstract double area();

    public abstract String pointsToString();

    public abstract Point leftmostPoint();

    /**
     *
     * @param a vector
     * @param b vector
     * @return return angel between two vectors
     */
    protected double getAngle(Point a, Point b) {
        return Math.toDegrees(Math.acos((a.getX() * b.getX() + a.getY() * b.getY()) /
                (Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * Math.sqrt(Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)))));
    }

    /**
     *
     * @param start of Point
     * @param end of Point
     * @return return length of segment between two point
     */
    protected double lengthOfSegment(Point start, Point end) {
        double distanceX = StrictMath.pow(start.getX() - end.getX(), 2);
        double distanceY = StrictMath.pow(start.getY() - end.getY(), 2);
        return Math.sqrt(distanceX + distanceY);
    }

    /**
     *
     * @param start of Point
     * @param end of Point
     * @return return vector
     */
    protected Point getVector(Point start, Point end) {
        return new Point(end.getX() - start.getX(), end.getY() - start.getY());
    }

}

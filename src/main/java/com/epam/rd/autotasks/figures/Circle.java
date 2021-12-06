package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private final Point A;
    private Point X;
    private final double radius;

    public Circle(Point A, double radius) {
        this.A = A;
        this.radius = radius;
        if (A == null || radius <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Point centroid() {
        return A;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Circle) {
            return isEqual(this.A.getX(), ((Circle) figure).A.getX()) && isEqual(this.A.getY(), ((Circle) figure).A.getY()) &&
                    isEqual(this.radius, ((Circle) figure).radius);
        }
        return false;
    }


    private boolean isEqual(double x1, double x2) {
        return Math.abs(x1 - x2) < 0.00000000001d;
    }

    @Override
    public double area() {
        return (double) (Math.PI * (radius * radius));
    }

    @Override
    public String pointsToString() {
        return "(" + A.getX() + "," + A.getY() + ")";
    }

    @Override
    public String toString() {
        return "Circle[(" + A.getX() + "," + A.getY() + ")" + radius + "]";
    }

    @Override
    public Point leftmostPoint() {
        Point X = new Point(A.getX() - radius, A.getY());
        return X;
    }
}

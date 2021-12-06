package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private final Point a, b, c;
    private double ab, ac, bc;
    private Point X;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null)
            throw new IllegalArgumentException();

        this.a = a;
        this.b = b;
        this.c = c;
        this.ab = lengthOfSegment(a, b);
        this.ac = lengthOfSegment(a, c);
        this.bc = lengthOfSegment(b, c);

        Point vectorAB = getVector(a, b);
        Point vectorAC = getVector(a, c);
        Point vectorBC = getVector(c, b);
        Point vectorBC_1 = getVector(b, c);

        double angleAB = getAngle(vectorAB, vectorAC);
        double angleAC = getAngle(vectorAB, vectorBC);
        double angleBC = getAngle(vectorAC, vectorBC_1);


        if (this.ab + this.ac < this.bc ||
                this.ab + this.bc < this.ac ||
                this.ac + this.bc < this.ab)
            throw new RuntimeException("The triangle possibly degenerate");
        if (Math.abs(angleAB + angleAC + angleBC - 180) >= 1.e-8 ||
                Math.abs(angleAB) <= 1.e-5 || Math.abs(angleAC) <= 1.e-5 || Math.abs(angleBC) <= 1.e-5 ||
                Math.abs(angleAB - 180) <= 1.e-5 || Math.abs(angleAC - 180) <= 1.e-5 || Math.abs(angleBC - 180) <= 1.e-5)
            throw new IllegalArgumentException("The triangle possibly degenerate");


    }

    @Override
    public double area() {
        double AB = Math.sqrt(((b.getX() - a.getX()) * (b.getX() - a.getX())) + ((b.getY() - a.getY()) * (b.getY() - a.getY())));
        double BC = Math.sqrt(((c.getX() - b.getX()) * (c.getX() - b.getX())) + ((c.getY() - b.getY()) * (c.getY() - b.getY())));
        double AC = Math.sqrt(((c.getX() - a.getX()) * (c.getX() - a.getX())) + ((c.getY() - a.getY()) * (c.getY() - a.getY())));
        double semi_perimeter = (AB + BC + AC) / 2;
        return (double) (Math.sqrt(semi_perimeter * (semi_perimeter - AB) * (semi_perimeter - BC) * (semi_perimeter - AC)));

    }

    @Override
    public String pointsToString() {
        return "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")";
    }

    @Override
    public String toString() {
        return "Triangle[" + "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")" + "]";
    }

    @Override
    public Point leftmostPoint() {
        if ((a.getX() <= b.getX()) && (a.getX() <= c.getX())) {
            return X = new Point(a.getX(), a.getY());
        } else if ((b.getX() <= a.getX()) && (b.getX() <= c.getX())) {
            return X = new Point(b.getX(), b.getY());
        } else {
            return X = new Point(c.getX(), c.getY());
        }
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Triangle) {
            Point[] array = {((Triangle) figure).a, ((Triangle) figure).b, ((Triangle) figure).c};
            Point[] arrayThis = {this.a, this.b, this.c};
            int count = 0;
            for (Point arrayThi : arrayThis) {
                for (Point point : array) {
                    if (isEqual(arrayThi.getX(), point.getX()) && isEqual(arrayThi.getY(), point.getY())) {
                        count++;
                        break;
                    }
                }
            }
            return count == 4;
        }
        return false;
    }

    private boolean isEqual(double x1, double x2) {
        return Math.abs(x1 - x2) < 0.00000000001d;
    }


    @Override
    public Point centroid() {
        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }

    private double leng(Point a, Point b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        return Math.sqrt(x * x + y * y);
    }
}
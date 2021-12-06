package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    private Point x, a, b, c, d;


    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        checkLength();
        checkTriangleDegenerate();
        checkConvex();

        if (area() <= 0) {
            throw new IllegalArgumentException();
        }

    }

    private void checkLength() {
        double AB = lengthOfSegment(a, b);
        double BC = lengthOfSegment(c, b);
        double CD = lengthOfSegment(c, d);
        double AD = lengthOfSegment(a, d);
        double BD = lengthOfSegment(b, d);
        double AC = lengthOfSegment(a, c);

        if ((AB <= 0 || BC <= 0 || CD <= 0 || AD <= 0 || BD <= 0 || AC <= 0)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkConvex() {
        double angleBAD = getAngle(getVector(a, b), getVector(a, d));
        double angleABC = getAngle(getVector(b, a), getVector(b, c));
        double angleBCD = getAngle(getVector(c, b), getVector(c, d));
        double angleCDA = getAngle(getVector(d, c), getVector(d, a));

        double angleBAD_01 = 360 - (angleABC + angleBCD + angleCDA);
        double angleABC_01 = 360 - (angleBAD + angleBCD + angleCDA);
        double angleBCD_01 = 360 - (angleBAD + angleABC + angleCDA);
        double angleCDA_01 = 360 - (angleBAD + angleABC + angleBCD);
        if (!(isEqual(angleBAD, angleBAD_01) && isEqual(angleABC, angleABC_01) &&
                isEqual(angleBCD, angleBCD_01) && isEqual(angleCDA, angleCDA_01)))
            throw new IllegalArgumentException();
    }

    private void checkTriangleDegenerate() {
        new Triangle(a, b, c);
        new Triangle(a, b, d);
        new Triangle(a, c, d);
        new Triangle(b, d, c);
    }


    @Override
    public double area() {

        double AB = Math.sqrt(((b.getX() - a.getX()) * (b.getX() - a.getX())) + ((b.getY() - a.getY()) * (b.getY() - a.getY())));
        double BC = Math.sqrt(((c.getX() - b.getX()) * (c.getX() - b.getX())) + ((c.getY() - b.getY()) * (c.getY() - b.getY())));
        double CD = Math.sqrt(((d.getX() - c.getX()) * (d.getX() - c.getX())) + ((d.getY() - c.getY()) * (d.getY() - c.getY())));
        double AD = Math.sqrt(((a.getX() - d.getX()) * (a.getX() - d.getX())) + ((a.getY() - d.getY()) * (a.getY() - d.getY())));
        double BD = Math.sqrt(((d.getX() - b.getX()) * (d.getX() - b.getX())) + ((d.getY() - b.getY()) * (d.getY() - b.getY())));
        double semi_perimeter1 = (AB + BD + AD) / 2;
        double Triangle1 = Math.sqrt(semi_perimeter1 * (semi_perimeter1 - AB) * (semi_perimeter1 - BD) * (semi_perimeter1 - AD));
        double semi_perimeter2 = (BC + BD + CD) / 2;
        double Triangle2 = Math.sqrt(semi_perimeter2 * (semi_perimeter2 - BC) * (semi_perimeter2 - BD) * (semi_perimeter2 - CD));
        return Triangle1 + Triangle2;
    }

    @Override
    public String pointsToString() {
        return "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")" + "(" + d.getX() + "," + d.getY() + ")";
    }

    @Override
    public String toString() {
        return "Quadrilateral[(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")" + "(" + d.getX() + "," + d.getY() + ")]";
    }

    @Override
    public Point leftmostPoint() {
        if ((a.getX() <= b.getX()) && (a.getX() <= c.getX()) && (a.getX() <= d.getX())) {
            return x = new Point(a.getX(), a.getY());
        } else if ((b.getX() <= a.getX()) && (b.getX() <= c.getX()) && (a.getX() <= d.getX())) {
            return x = new Point(b.getX(), b.getY());
        } else if ((c.getX() <= a.getX()) && (c.getX() <= b.getX()) && (c.getX() <= d.getX())) {
            return x = new Point(c.getX(), c.getY());
        } else {
            return x = new Point(d.getX(), d.getY());
        }
    }

    @Override
    public Point centroid() {
        Segment s1 = new Segment(new Triangle(a, b, c).centroid(), new Triangle(a, c, d).centroid());
        Segment s2 = new Segment(new Triangle(a, b, d).centroid(), new Triangle(b, c, d).centroid());
        return s1.intersection(s2);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Quadrilateral) {
            Point[] array = {((Quadrilateral) figure).a, ((Quadrilateral) figure).b, ((Quadrilateral) figure).c, ((Quadrilateral) figure).d};
            Point[] arrayThis = {this.a, this.b, this.c, this.d};
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
}

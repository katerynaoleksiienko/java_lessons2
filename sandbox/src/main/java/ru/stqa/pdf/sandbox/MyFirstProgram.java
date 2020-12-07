package ru.stqa.pdf.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Point p1 = new Point(2, 9);
        Point p2 = new Point(8, 5);
        System.out.println(p1.distance(p2));
        System.out.println(distance(p1, p2));
    }
    public static double distance(Point p1, Point p2){
        double a = Math.sqrt( Math.pow((p2.x - p1.x), 2)+Math.pow((p2.y - p1.y), 2));
        return a;
    }
}


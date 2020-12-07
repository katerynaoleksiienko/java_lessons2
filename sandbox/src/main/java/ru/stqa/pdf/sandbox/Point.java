package ru.stqa.pdf.sandbox;

public class Point {
    double x ;
    double y ;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point p){
        double b = Math.sqrt( Math.pow((p.x - this.x), 2)+Math.pow((p.y - this.y), 2));
        return b;
    }
}
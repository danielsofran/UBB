import InterfataFunctionala.Area;

import java.util.Arrays;
import java.util.List;

public class Shape {
    private static <E> void printArea(List<E> l, Area<E> areaF) {
        l.forEach(e -> System.out.println(areaF.computeArea(e)));
    }
    public static void main(String[] args) {
        Area<Cerc> cercArea = (Cerc c) -> (float) (Math.PI * c.getRadius() * c.getRadius());
        Cerc cerc1 = new Cerc(1);
        Cerc cerc2 = new Cerc(2);
        float area1 = cercArea.computeArea(cerc1);
        System.out.println("Area of cerc1 is: " + area1);

        Area<Square> SquareArea = (Square r) -> r.getEdge() * r.getEdge();
        printArea(Arrays.asList(cerc1, cerc2), cercArea);
    }
}

class Cerc {
    private float radius;
    public Cerc(float radius) {
        this.radius = radius;
    }
    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
}

class Square{
    private float edge;
    public Square(float edge) {
        this.edge = edge;
    }
    public float getEdge() {
        return edge;
    }
    public void setEdge(float edge) {
        this.edge = edge;
    }
}
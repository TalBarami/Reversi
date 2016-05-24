package Entities;

/**
 * Created by Tal on 24/05/2016.
 */
public class Point {
    public int x;
    public int y;
    public int value;
    public boolean flipable;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        value = 0;
        flipable = true;
    }

    public String toString(){
        return "{"+x+","+y+","+value+","+flipable+"}";
    }
}

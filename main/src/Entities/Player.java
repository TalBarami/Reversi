package Entities;

/**
 * Created by Tal on 24/05/2016.
 */
public class Player {
    public String name;
    public boolean isComp;
    public int color;
    public int score;

    public Player(String name, boolean isComp, int color, int score){
        this.name = name;
        this.isComp = isComp;
        this.color = color;
        this.score = score;
    }

    public String toString(){
        return "{"+name+","+isComp+","+color+","+score+"}";
    }
}

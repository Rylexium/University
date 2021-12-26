package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Rylexium
 */
public class MovSquare extends Rectangle implements Movable, FigureCollisions<MovSquare> {

    public MovSquare(double s, double sh, double sw, double x, double y,
                     double x_speed, double y_speed,
                     Color color,
                     ArrayList<MovSquare> lst){
        super();
        sideSize = s;
        sceneHeight = sh;
        sceneWidth = sw;
        nbs = lst;
        this.color = color;
        super.setWidth(sideSize);
        super.setHeight(sideSize);
        super.setX(x);
        super.setY(y);

        xSpeed = x_speed;
        ySpeed = y_speed;

        LinearGradient lg = new LinearGradient(
                0, 0,
                0.35, 0.35,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, color)
        );
        super.setFill(lg);

        ma = new MovableAdapter(
                this::getX,
                this::setX,
                this::getY,
                this::setY,

                xSpeed,
                ySpeed,

                0,
                sideSize,
                0,
                sideSize,

                new SimpleDoubleProperty(sceneHeight),
                new SimpleDoubleProperty(sceneWidth)
        );
    }
    public MovSquare(double s, double sh, double sw, Color color, ArrayList<MovSquare> lst) {
        super();
        sideSize = s;
        sceneHeight = sh;
        sceneWidth = sw;
        nbs = lst;
        this.color = color;
        super.setWidth(sideSize);
        super.setHeight(sideSize);
        super.setX(Math.random() * (sceneWidth - sideSize) + 1);
        super.setY(Math.random() * (sceneHeight - sideSize) + 1);

        for(int i=0; i < nbs.size(); i++){
            int count = 0;
            while(!nbs.get(i).equals(this) && this.isCollisions(lst.get(i)) && count != 4){
                super.setX(Math.random() * (sceneWidth - sideSize) + 1);
                super.setY(Math.random() * (sceneWidth - sideSize) + 1);
                count+=1;
            }
            if(count < 4)break;
        }
        xSpeed = Math.random()*3 + 1;
        ySpeed = Math.random()*3 + 1;

        LinearGradient lg = new LinearGradient(
                0, 0,
                0.35, 0.35,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, color)
        );
        super.setFill(lg);

        ma = new MovableAdapter(
                this::getX,
                this::setX,
                this::getY,
                this::setY,

                xSpeed,
                ySpeed,

                0,
                sideSize,
                0,
                sideSize,

                new SimpleDoubleProperty(sceneHeight),
                new SimpleDoubleProperty(sceneWidth)
        );
    }
    public void move(){
        ma.move(nbs, this);
    }

    @Override
    public Boolean isCollisions(MovSquare first) {

        double r1 = Math.sqrt((getHeight() * getHeight()) + (getWidth() * getWidth())) / 2; //r1 for square
        double r2 = Math.sqrt((first.getHeight() * first.getHeight()) + (first.getWidth() * first.getWidth())) / 2;

        r1 -= getHeight()/10;
        r2 -= first.getHeight()/10;

        double r = Math.sqrt( Math.pow(getX() - first.getX(), 2) + Math.pow(getY() - first.getY(), 2) );
        return r <= (r1 + r2);
    }

    double sideSize;
    double xSpeed;
    double ySpeed;
    double sceneHeight;
    double sceneWidth;
    Color color;
    ArrayList<MovSquare> nbs;
    MovableAdapter ma;
}

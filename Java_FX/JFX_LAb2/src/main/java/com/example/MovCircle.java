package com.example;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author Rylexium
 */
public class MovCircle extends Circle implements Movable, FigureCollisions<MovCircle> {

    public MovCircle(double r, double sh, double sw,
                     double x, double y,
                     double x_speed, double y_speed,
                     Color color,
                     ArrayList<MovCircle> lst){
        super();
        radius = r;
        sceneHeight = sh;
        sceneWidth = sw;
        nbs = lst;
        this.color = color;
        super.setRadius(radius);
        super.setCenterX(x);
        super.setCenterY(y);

        xSpeed = x_speed;
        ySpeed = y_speed;

        RadialGradient rg = new RadialGradient(
                0, 0,
                0.35, 0.35,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, color)
        );
        super.setFill(rg);

        ma = new MovableAdapter(
                this::getCenterX,
                this::setCenterX,
                this::getCenterY,
                this::setCenterY,

                xSpeed,
                ySpeed,

                radius,
                radius,
                radius,
                radius,

                new SimpleDoubleProperty(sceneHeight),
                new SimpleDoubleProperty(sceneWidth));
    }

    public MovCircle(double r, double sh, double sw, Color color, ArrayList<MovCircle> lst) {
        super();
        radius = r;
        sceneHeight = sh;
        sceneWidth = sw;
        nbs = lst;
        this.color = color;
        super.setRadius(radius);
        super.setCenterX(Math.random() * (sceneWidth - 2*radius) + radius);
        super.setCenterY(Math.random() * (sceneHeight - 2*radius) + radius);

        //когда спавним проверяем
        for(int i=0; i < nbs.size(); i++){
            int count = 0;
            while(!nbs.get(i).equals(this) && this.isCollisions(lst.get(i)) && count != 4){
                super.setCenterX(Math.random() * (sceneWidth - 2*radius) + radius);
                super.setCenterY(Math.random() * (sceneHeight - 2*radius) + radius);
                count+=1;
            }
            if(count < 4)break;
        }



        xSpeed = Math.random()*3 + 1;
        ySpeed = Math.random()*3 + 1;

        RadialGradient rg = new RadialGradient(
                0, 0,
                0.35, 0.35,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, color)
        );
        super.setFill(rg);

        ma = new MovableAdapter(
                this::getCenterX,
                this::setCenterX,
                this::getCenterY,
                this::setCenterY,

                xSpeed,
                ySpeed,

                radius,
                radius,
                radius,
                radius,

                new SimpleDoubleProperty(sceneHeight),
                new SimpleDoubleProperty(sceneWidth)
        );
    }

    public void move() {
        ma.move(nbs, this);
    }

    @Override
    public Boolean isCollisions(MovCircle first) {
        double r = Math.sqrt( Math.pow(first.getCenterX() - this.getCenterX(), 2) +
                              Math.pow(first.getCenterY() - this.getCenterY(), 2));
        return r <= (first.getRadius() + this.getRadius());
    }

    double radius;
    double xSpeed;
    double ySpeed;
    double sceneHeight;
    double sceneWidth;
    Color color;
    ArrayList<MovCircle> nbs;
    MovableAdapter ma;

}

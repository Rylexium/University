package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Rylexium
 */
public class MovableAdapter {

    public MovableAdapter(
            DoubleSupplier getxc,
            DoubleConsumer setxc,
            DoubleSupplier getyc,
            DoubleConsumer setyc,
            double xD,
            double yD,
            double leftX,
            double rightX,
            double topY,
            double bottomY,
            DoubleProperty scH,
            DoubleProperty scW
    )
    {
        getXCoord = getxc;
        setXCoord = setxc;
        getYCoord = getyc;
        setYCoord = setyc;

        this.xD = xD;
        this.yD = yD;

        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = bottomY;

        this.scH = scH;
        this.scW = scW;

    }

     void collisionsWithWindow(){
        setXCoord.accept(getXCoord.getAsDouble()+ xD);
        setYCoord.accept(getYCoord.getAsDouble()+ yD);

        if (getXCoord.getAsDouble()<= leftX){
            setXCoord.accept(leftX);
            xD = -xD;
        }

        if (getXCoord.getAsDouble() >= scW.doubleValue() - rightX){
            setXCoord.accept(scW.doubleValue() - rightX);
            xD = -xD;
        }

        if (getYCoord.getAsDouble() <= topY) {
            setYCoord.accept(topY);
            yD = -yD;
        }

        if (getYCoord.getAsDouble() >= scH.doubleValue() - bottomY) {
            setYCoord.accept(scH.doubleValue() - bottomY);
            yD = -yD;
        }
    }

    public void move(List<MovCircle> lst, MovCircle circle){
        collisionsWithWindow();

        for(int i=0; i < lst.size(); i++){
            if(!lst.get(i).equals(circle) && lst.get(i).isCollisions(circle)){
                swapSpeed(lst.get(i), circle);

                if (circle.getCenterY() - circle.radius > lst.get(i).getCenterY() - lst.get(i).radius ||
                        circle.getCenterY() + circle.radius < lst.get(i).getCenterY() + lst.get(i).radius){
                    xD = -xD;
                }
                if(circle.getCenterX() - circle.radius > lst.get(i).getCenterX() - lst.get(i).radius ||
                        circle.getCenterX() + circle.radius < lst.get(i).getCenterX() + lst.get(i).radius){
                    yD = -yD;
                }

                break;
            }
        }
    }

    public void move(ArrayList<MovSquare> lst, MovSquare square){
        collisionsWithWindow();

        for(int i=0; i < lst.size(); i++){
            if(!lst.get(i).equals(square) && lst.get(i).isCollisions(square)){
                swapSpeed(lst.get(i), square);
                xD = -xD;
                yD = -yD;
                break;
            }
        }
    }
    private void swapSpeed(MovCircle first, MovCircle second){
        double tmpX = first.xSpeed;
        double tmpY = first.ySpeed;

        first.xSpeed = second.xSpeed;
        first.ySpeed = second.ySpeed;

        second.xSpeed = tmpX;
        second.ySpeed = tmpY;
    }

    private void swapSpeed(MovSquare first, MovSquare second){
        double tmpX = first.xSpeed;
        double tmpY = first.ySpeed;

        first.xSpeed = second.xSpeed;
        first.ySpeed = second.ySpeed;

        second.xSpeed = tmpX;
        second.ySpeed = tmpY;
    }

    public void handle(KeyEvent ke) {
        KeyCode kc = ke.getCode();
        if (ke.getEventType() == KeyEvent.KEY_PRESSED){
            switch(kc) {
                case DOWN:
                    xD = 0;
                    yD = 1;
                    break;
                case UP:
                    xD = 0;
                    yD = -1;
                    break;
                case LEFT:
                    xD = -1;
                    yD = 0;
                    break;
                case RIGHT:
                    xD = 1;
                    yD = 0;
                    break;
            }
        }

    }

    DoubleSupplier getXCoord;
    DoubleSupplier getYCoord;
    DoubleConsumer setXCoord;
    DoubleConsumer setYCoord;

    double xD;
    double yD;

    DoubleProperty scH;
    DoubleProperty scW;

    double leftX;
    double rightX;
    double topY;
    double bottomY;

}

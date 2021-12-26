package com.example;

import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Rylexium
 */
public class App extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        shapesNodes = new ArrayList<>();
        shapesNodes.add(new ArrayList<>());
        shapesNodes.add(new ArrayList<>());

        movableShapes = new ArrayList<>();
        movableShapes.add(new ArrayList<>());
        movableShapes.add(new ArrayList<>());

        mcs = new ArrayList<>();
        for (int i = 0; i < START_NUMBER_CIRCLES; i++){
            mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH, Color.RED, mcs));
        }
        MovCircle staticCircle = new MovCircle(80, HEIGHT, WIDTH, (double)WIDTH/2, (double)HEIGHT/2, 0, 0, Color.GREEN, mcs);

        mss = new ArrayList<>();
        for (int i = 0; i < START_NUMBER_SQUARES; i++){
            mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH, Color.BLUE, mss));
        }
        MovSquare staticSquare = new MovSquare(100, HEIGHT, WIDTH, (double)WIDTH/2, (double)HEIGHT/2, 0, 0, Color.GREEN, mss);

        for (MovCircle mc: mcs){
            shapesNodes.get(0).add(mc);
            movableShapes.get(0).add(mc);
        }

        for (MovSquare ms: mss){
            shapesNodes.get(1).add(ms);
            movableShapes.get(1).add(ms);
        }

        Group root = new Group();

        FlowPane menu = new FlowPane();


        String[] btnTexts = {"Квадраты", "Шары"};
        String[] textPause = {"Пауза", "Возобновить"};
        String[] textSideFigure = {"Радиус", "Сторона"};
        Button btncs = new Button();
        Button accept = new Button("Применить");
        Button pause = new Button("Пауза");
        Label label1 = new Label("Скорость:");
        label1.setPrefHeight(22);
        TextField textField = new TextField();
        textField.setPrefWidth(60);

        ColorPicker colorPicker = new ColorPicker(Color.RED);

        Label label2 = new Label("Количество");
        label2.setPrefHeight(22);
        Button btnOfPlusFigure= new Button("+");
        btnOfPlusFigure.setPrefWidth(30);
        Button btnOfMinusFigure = new Button("-");
        btnOfMinusFigure.setPrefWidth(30);

        Label label3 = new Label(textSideFigure[0]);
        label3.setPrefHeight(22);
        Button btnOfPlusSide= new Button("+");
        btnOfPlusSide.setPrefWidth(30);
        Button btnOfMinusSide = new Button("-");
        btnOfMinusSide.setPrefWidth(30);

        Button btnCreateStaticFigure = new Button("Создать статическую фигуру");
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, accept, btncs, pause, colorPicker,
                label2, btnOfPlusFigure, btnOfMinusFigure,
                label3, btnOfPlusSide, btnOfMinusSide, btnCreateStaticFigure);
        hb.setSpacing(10);


        int[] idx = {0};
        btncs.setOnAction(
                eh -> {
                    idx[0] = 1 - idx[0];
                    btncs.setText(btnTexts[idx[0]]);
                    root.getChildren().clear();
                    menu.getChildren().clear();
                    root.getChildren().addAll(shapesNodes.get(idx[0]));
                    menu.getChildren().add(hb);
                    root.getChildren().add(menu);
                    if (idx[0] == 0) {
                        colorPicker.setValue(mcs.get(0).color);
                        pause.setText(isPauseСircle?textPause[0]:textPause[1]);
                    } else {
                        colorPicker.setValue(mss.get(0).color);
                        pause.setText(isPauseSquare?textPause[0]:textPause[1]);
                    }
                    label3.setText(textSideFigure[idx[0]]);
                    stage.setTitle("Какие-то " + btnTexts[idx[0]].toLowerCase());
                    root.requestFocus();
                }
        );

        accept.setOnAction( e -> {
            if(textField.getText().equals(""))  return;

            try{Double.parseDouble(textField.getText());}
            catch (Exception err){return;}

            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                pause.setText(isPauseСircle?textPause[1]:textPause[0]);
                ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                    if(!mcs.get(i).equals(staticCircle))
                        tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                mcs.get(i).xSpeed + Double.parseDouble(textField.getText()) / 2,
                                mcs.get(i).ySpeed + Double.parseDouble(textField.getText()) / 2,
                                mcs.get(i).color,
                                tmp_mcs));
                }
                mcs.clear();
                mcs = tmp_mcs;
                if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(idx[0]).add(mc);
                    movableShapes.get(idx[0]).add(mc);
                }

            }
            else {
                pause.setText(isPauseSquare?textPause[1]:textPause[0]);
                ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                    if(!mss.get(i).equals(staticSquare))
                        tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                mss.get(i).getX(), mss.get(i).getY(),
                                mss.get(i).xSpeed + Double.parseDouble(textField.getText()) / 2,
                                mss.get(i).ySpeed + Double.parseDouble(textField.getText()) / 2,
                                colorPicker.getValue(),
                                tmp_mss));
                }
                mss.clear();
                mss = tmp_mss;
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }
            }
            root.requestFocus();
            root.getChildren().addAll(shapesNodes.get(idx[0]));
        });

        pause.setOnAction(e->{
            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                pause.setText(isPauseСircle?textPause[1]:textPause[0]);
                ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                    if(!mcs.get(i).equals(staticCircle))
                        tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                isPauseСircle? 0 : Math.random() * 3 + 1,
                                isPauseСircle? 0 : Math.random() * 3 + 1,
                                mcs.get(i).color,
                                tmp_mcs));
                }
                mcs.clear();
                mcs = tmp_mcs;
                if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(idx[0]).add(mc);
                    movableShapes.get(idx[0]).add(mc);
                }
                isPauseСircle = !isPauseСircle;
            }
            else {
                pause.setText(isPauseSquare?textPause[1]:textPause[0]);
                ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                    if(!mss.get(i).equals(staticSquare))
                        tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                mss.get(i).getX(), mss.get(i).getY(),
                                isPauseSquare? 0 : Math.random() * 3 + 1,
                                isPauseSquare? 0 : Math.random() * 3 + 1,
                                colorPicker.getValue(),
                                tmp_mss));
                }
                mss.clear();
                mss = tmp_mss;
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }
                isPauseSquare = !isPauseSquare;
            }
            root.requestFocus();
            root.getChildren().addAll(shapesNodes.get(idx[0]));
        });

        colorPicker.setOnAction( e -> {
            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                pause.setText(isPauseСircle?textPause[0]:textPause[1]);
                ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                    if(!mcs.get(i).equals(staticCircle))
                        tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                mcs.get(i).xSpeed,
                                mcs.get(i).ySpeed,
                                colorPicker.getValue(),
                                tmp_mcs));
                }
                mcs.clear();
                mcs = tmp_mcs;
                if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(0).add(mc);
                    movableShapes.get(0).add(mc);
                }
            }
            else {
                pause.setText(isPauseSquare?textPause[0]:textPause[1]);
                ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                    if(!mss.get(i).equals(staticSquare))
                        tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                mss.get(i).getX(), mss.get(i).getY(),
                                mss.get(i).xSpeed, mss.get(i).ySpeed,
                                colorPicker.getValue(),
                                tmp_mss));
                }
                mss.clear();
                mss = tmp_mss;
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }

            }
            root.requestFocus();
            root.getChildren().addAll(shapesNodes.get(idx[0]));
        });
        btnOfPlusFigure.setOnAction( e -> {
            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH, colorPicker.getValue(), mcs));
                if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(0).add(mc);
                    movableShapes.get(0).add(mc);
                }
                START_NUMBER_CIRCLES += 1;
            }
            else {
                mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH, colorPicker.getValue(), mss));
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }
                START_NUMBER_SQUARES += 1;
            }
            root.requestFocus();
            root.getChildren().addAll(shapesNodes.get(idx[0]));
        });
        btnOfMinusFigure.setOnAction(e -> {
            if(idx[0] == 0 && START_NUMBER_CIRCLES == 0) return;
            if(idx[0] == 1 && START_NUMBER_SQUARES == 0) return;

            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                mcs.remove(staticCircle);

                if(!mcs.isEmpty()) mcs.remove(mcs.size() - 1);

                if (isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(idx[0]).add(mc);
                    movableShapes.get(idx[0]).add(mc);
                }
                if(START_NUMBER_CIRCLES > 0)
                    START_NUMBER_CIRCLES -= 1;
            }
            else {
                mss.remove(mss.size() - 1);
                if(!mss.isEmpty()) mss.remove(mss.size() - 1);
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }
                if(START_NUMBER_SQUARES > 0)
                    START_NUMBER_SQUARES -= 1;
            }
            root.requestFocus();
            root.getChildren().addAll(shapesNodes.get(idx[0]));
        });

        btnOfPlusSide.setOnAction( f -> {
            if(CIRCLE_SIZE == 50 || SQUARE_SIZE == 80)return;

            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                CIRCLE_SIZE += 5;
                ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                    if(!mcs.get(i).equals(staticCircle))
                        tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                mcs.get(i).xSpeed,
                                mcs.get(i).ySpeed,
                                mcs.get(i).color,
                                tmp_mcs));
                }
                mcs.clear();
                mcs = tmp_mcs;
                if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(idx[0]).add(mc);
                    movableShapes.get(idx[0]).add(mc);
                }
            }
            else {
                SQUARE_SIZE += 10;
                ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                    if(!mss.get(i).equals(staticSquare))
                        tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                mss.get(i).getX(), mss.get(i).getY(),
                                mss.get(i).xSpeed, mss.get(i).ySpeed,
                                colorPicker.getValue(),
                                tmp_mss));
                }
                mss.clear();
                mss = tmp_mss;
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }

            }
            root.requestFocus();
            root.getChildren().addAll(shapesNodes.get(idx[0]));
        });

        btnOfMinusSide.setOnAction( f ->{
            if(CIRCLE_SIZE == 5 || SQUARE_SIZE == 10)return;

            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0] == 0) {
                CIRCLE_SIZE -= 5;
                ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                    if(!mcs.get(i).equals(staticCircle))
                        tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                mcs.get(i).xSpeed,
                                mcs.get(i).ySpeed,
                                mcs.get(i).color,
                                tmp_mcs));
                }
                mcs.clear();
                mcs = tmp_mcs;
                if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                for (MovCircle mc: mcs){
                    shapesNodes.get(idx[0]).add(mc);
                    movableShapes.get(idx[0]).add(mc);
                }
            }
            else {
                SQUARE_SIZE -= 10;
                ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                    if(!mss.get(i).equals(staticSquare))
                        tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                mss.get(i).getX(), mss.get(i).getY(),
                                mss.get(i).xSpeed, mss.get(i).ySpeed,
                                colorPicker.getValue(),
                                tmp_mss));
                }
                mss.clear();
                mss = tmp_mss;
                if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }

            }

            root.getChildren().addAll(shapesNodes.get(idx[0]));
            root.requestFocus();
        });

        btnCreateStaticFigure.setOnAction( f ->{
            root.getChildren().removeAll(shapesNodes.get(idx[0]));
            shapesNodes.get(idx[0]).clear();
            movableShapes.get(idx[0]).clear();

            if(idx[0]==0){
                ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                    if(!mcs.get(i).equals(staticCircle))
                        tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                mcs.get(i).xSpeed,
                                mcs.get(i).ySpeed,
                                mcs.get(i).color,
                                tmp_mcs));
                }
                mcs.clear();
                mcs = tmp_mcs;
                if(!isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) {
                    START_NUMBER_CIRCLES += 1;
                    mcs.add(staticCircle);
                }
                else {
                    mcs.remove(staticCircle);
                    START_NUMBER_CIRCLES -= 1;
                }
                isCreateStaticCircle = !isCreateStaticCircle;
                for (MovCircle mc: mcs){
                    shapesNodes.get(0).add(mc);
                    movableShapes.get(0).add(mc);
                }
            }
            else {
                ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                    if(!mss.get(i).equals(staticSquare))
                        tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                mss.get(i).getX(), mss.get(i).getY(),
                                mss.get(i).xSpeed, mss.get(i).ySpeed,
                                colorPicker.getValue(),
                                tmp_mss));
                }
                mss.clear();
                mss = tmp_mss;
                if(!isCreateStaticSquare && indexOf(mss, staticSquare) == -1) {
                    START_NUMBER_SQUARES += 1;
                    mss.add(staticSquare);
                }
                else {
                    mss.remove(staticSquare);
                    START_NUMBER_SQUARES -= 1;
                }
                isCreateStaticSquare = !isCreateStaticSquare;
                for (MovSquare ms: mss){
                    shapesNodes.get(1).add(ms);
                    movableShapes.get(1).add(ms);
                }
            }

            root.getChildren().addAll(shapesNodes.get(idx[0]));
            root.requestFocus();
        });
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode().getName().equals("Up")) {
                    root.getChildren().removeAll(shapesNodes.get(idx[0]));
                    shapesNodes.get(idx[0]).clear();
                    movableShapes.get(idx[0]).clear();

                    if(idx[0] == 0) {
                        ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                            if(!mcs.get(i).equals(staticCircle))
                                tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                        mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                        mcs.get(i).xSpeed,
                                        mcs.get(i).ySpeed,
                                        mcs.get(i).color,
                                        tmp_mcs));
                        }
                        mcs.clear();
                        mcs = tmp_mcs;
                        staticCircle.setCenterY(staticCircle.getCenterY() - staticFigureSpeed);
                        if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                        for (MovCircle mc: mcs){
                            shapesNodes.get(0).add(mc);
                            movableShapes.get(0).add(mc);
                        }

                    }
                    else {
                        ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                            if(!mss.get(i).equals(staticSquare))
                                tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                        mss.get(i).getX(), mss.get(i).getY(),
                                        mss.get(i).xSpeed, mss.get(i).ySpeed,
                                        colorPicker.getValue(),
                                        tmp_mss));
                        }
                        mss.clear();
                        mss = tmp_mss;
                        if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                        for (MovSquare ms: mss){
                            shapesNodes.get(1).add(ms);
                            movableShapes.get(1).add(ms);
                        }

                    }

                    root.getChildren().addAll(shapesNodes.get(idx[0]));
                }
                if (ke.getCode().getName().equals("Down")) {
                    root.getChildren().removeAll(shapesNodes.get(idx[0]));
                    shapesNodes.get(idx[0]).clear();
                    movableShapes.get(idx[0]).clear();

                    if(idx[0] == 0) {
                        ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                            if(!mcs.get(i).equals(staticCircle))
                                tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                        mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                        mcs.get(i).xSpeed,
                                        mcs.get(i).ySpeed,
                                        mcs.get(i).color,
                                        tmp_mcs));
                        }
                        mcs.clear();
                        mcs = tmp_mcs;
                        staticCircle.setCenterY(staticCircle.getCenterY() + staticFigureSpeed);
                        if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                        for (MovCircle mc: mcs){
                            shapesNodes.get(0).add(mc);
                            movableShapes.get(0).add(mc);
                        }

                    }
                    else {
                        ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                            if(!mss.get(i).equals(staticSquare))
                                tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                        mss.get(i).getX(), mss.get(i).getY(),
                                        mss.get(i).xSpeed, mss.get(i).ySpeed,
                                        colorPicker.getValue(),
                                        tmp_mss));
                        }
                        mss.clear();
                        mss = tmp_mss;
                        if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                        for (MovSquare ms: mss){
                            shapesNodes.get(1).add(ms);
                            movableShapes.get(1).add(ms);
                        }

                    }

                    root.getChildren().addAll(shapesNodes.get(idx[0]));
                }
                if (ke.getCode().getName().equals("Left")) {
                    root.getChildren().removeAll(shapesNodes.get(idx[0]));
                    shapesNodes.get(idx[0]).clear();
                    movableShapes.get(idx[0]).clear();

                    if(idx[0] == 0) {
                        ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                            if(!mcs.get(i).equals(staticCircle))
                                tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                        mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                        mcs.get(i).xSpeed,
                                        mcs.get(i).ySpeed,
                                        mcs.get(i).color,
                                        tmp_mcs));
                        }
                        mcs.clear();
                        mcs = tmp_mcs;
                        staticCircle.setCenterX(staticCircle.getCenterX() - staticFigureSpeed);
                        if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                        for (MovCircle mc: mcs){
                            shapesNodes.get(0).add(mc);
                            movableShapes.get(0).add(mc);
                        }

                    }
                    else {
                        ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                            if(!mss.get(i).equals(staticSquare))
                                tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                        mss.get(i).getX(), mss.get(i).getY(),
                                        mss.get(i).xSpeed, mss.get(i).ySpeed,
                                        colorPicker.getValue(),
                                        tmp_mss));
                        }
                        mss.clear();
                        mss = tmp_mss;
                        if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                        for (MovSquare ms: mss){
                            shapesNodes.get(1).add(ms);
                            movableShapes.get(1).add(ms);
                        }

                    }

                    root.getChildren().addAll(shapesNodes.get(idx[0]));
                }
                if (ke.getCode().getName().equals("Right")) {
                    root.getChildren().removeAll(shapesNodes.get(idx[0]));
                    shapesNodes.get(idx[0]).clear();
                    movableShapes.get(idx[0]).clear();

                    if(idx[0] == 0) {
                        ArrayList<MovCircle> tmp_mcs = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_CIRCLES; i++) {
                            if(!mcs.get(i).equals(staticCircle))
                                tmp_mcs.add(new MovCircle(CIRCLE_SIZE, HEIGHT, WIDTH,
                                        mcs.get(i).getCenterX(), mcs.get(i).getCenterY(),
                                        mcs.get(i).xSpeed,
                                        mcs.get(i).ySpeed,
                                        mcs.get(i).color,
                                        tmp_mcs));
                        }
                        mcs.clear();
                        mcs = tmp_mcs;
                        staticCircle.setCenterX(staticCircle.getCenterX() + staticFigureSpeed);
                        if(isCreateStaticCircle && indexOf(mcs, staticCircle) == -1) mcs.add(staticCircle);
                        for (MovCircle mc: mcs){
                            shapesNodes.get(0).add(mc);
                            movableShapes.get(0).add(mc);
                        }

                    }
                    else {
                        ArrayList<MovSquare> tmp_mss = new ArrayList<>();
                        for (int i = 0; i < START_NUMBER_SQUARES; i++) {
                            if(!mss.get(i).equals(staticSquare))
                                tmp_mss.add(new MovSquare(SQUARE_SIZE, HEIGHT, WIDTH,
                                        mss.get(i).getX(), mss.get(i).getY(),
                                        mss.get(i).xSpeed, mss.get(i).ySpeed,
                                        colorPicker.getValue(),
                                        tmp_mss));
                        }
                        mss.clear();
                        mss = tmp_mss;
                        if(isCreateStaticSquare && indexOf(mss, staticSquare) == -1) mss.add(staticSquare);
                        for (MovSquare ms: mss){
                            shapesNodes.get(1).add(ms);
                            movableShapes.get(1).add(ms);
                        }

                    }
                    root.getChildren().addAll(shapesNodes.get(idx[0]));
                }

                if (ke.getCode().getName().equals("Add")){
                    if(staticFigureSpeed <= 21)
                        staticFigureSpeed += 3;
                }
                if (ke.getCode().getName().equals("Subtract")){
                    if(staticFigureSpeed > 3)
                        staticFigureSpeed -= 3;
                }
            }
        });

        btncs.setText(btnTexts[idx[0]]);

        root.getChildren().addAll(shapesNodes.get(idx[0]));
        menu.getChildren().add(hb);
        root.getChildren().add(menu);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Какие-то " + btnTexts[idx[0]].toLowerCase());
        stage.show();
        root.requestFocus();
        KeyFrame kf = new KeyFrame(Duration.millis(10),
                e ->
                {
                    for (Movable m: movableShapes.get(idx[0])){
                        m.move();
                    }
                }
        );

        Timeline tl = new Timeline(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    private int indexOf(ArrayList<MovCircle> lst, MovCircle search){
        for(int i=0; i<lst.size(); i++){
            if(lst.get(i).equals(search))
                return i;
        }
        return -1;
    }

    private int indexOf(ArrayList<MovSquare> lst, MovSquare search){
        for(int i=0; i<lst.size(); i++){
            if(lst.get(i).equals(search))
                return i;
        }
        return -1;
    }


    private ArrayList<MovCircle> mcs;
    private ArrayList<MovSquare> mss;
    private ArrayList<ArrayList<Node>> shapesNodes;
    private ArrayList<ArrayList<Movable>> movableShapes;
    private Boolean isCreateStaticCircle = false;
    private Boolean isCreateStaticSquare = false;
    private Integer staticFigureSpeed = 3;
    private Boolean isPauseСircle = true;
    private Boolean isPauseSquare = true;
    final private int HEIGHT = 768;
    final private int WIDTH = 1024;
    private int CIRCLE_SIZE = 25;
    private int START_NUMBER_CIRCLES = 15;

    private int SQUARE_SIZE = 50;
    private int START_NUMBER_SQUARES = 1;
}
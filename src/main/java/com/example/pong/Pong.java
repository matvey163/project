package com.example.pong;

import javafx.animation.AnimationTimer;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Pong extends Application {
    public static Scene scene;
    public static Pane gameRoot = new Pane();
    public static Pane appRoot = new Pane();
    public static AnimationTimer timer;
    public static ArrayList<Wall> walls= new ArrayList<>();
    public Auto car;

    Parent createScene(){
        car = new Auto();

        //создаем препятствия
        for(int i = 1; i < 100; i++){

            int num = new Random().nextInt(3);
            WallPosition position;
            switch(num){
                case 0:     position = WallPosition.LEFT;
                            break;
                case 1:     position = WallPosition.CENTER;
                            break;
                case 2:     position = WallPosition.RIGHT;
                            break;
                default:    position = WallPosition.LEFT;
                            break;
            }

            Wall nWall = new Wall(position);
            nWall.setTranslateY(-i*300);




            walls.add(nWall);
            gameRoot.getChildren().addAll(nWall);
        }
        gameRoot.setPrefSize(600,800);
        gameRoot.getChildren().addAll(car);
        appRoot.getChildren().addAll(gameRoot);


        BackgroundImage bImage = new BackgroundImage(
                new Image("https://psv4.userapi.com/c237131/u225580831/docs/d50/f7c0c6044f1a/doroga.gif?extra=yNW5bsqA7Z2FtKEh3uuhW0NWN4LJosUNdM7Edu_mg2yFt2i8RYoGD5GxZ67LXqswA9azKbt5S08qpMz6HlfK2nLJggoa426dHIf2Gg0myDqG7LlmVCKTrQB1QnWcxfIaCzqLSAj2rASau0dGkJYJjhpU9Q"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(bImage);
        appRoot.setBackground(background);


        return appRoot;
    }


    public void update(){
        car.moveY();
        car.translateYProperty().addListener((obs, old, newValue)->{
            int offset = newValue.intValue();
            if (offset < 300)   gameRoot.setLayoutY(-(offset-300));

        });


    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(createScene());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();

            }
        };
        timer.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String key = keyEvent.getText();

                if(key.equals("a") && car.getTranslateX()>4 && Auto.over == 1)
                    car.setTranslateX(car.getTranslateX()-20);

                if(key.equals("d") && car.getTranslateX()<500 && Auto.over == 1)
                    car.setTranslateX(car.getTranslateX()+20);

                if(key.equals(" ")){
                    Auto.gameOver.setVisible(false);
                    timer.start();
                    car.setTranslateY(300);
                    car.setCarImage();

                }

            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

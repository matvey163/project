package com.example.pong;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Auto extends Pane {
    public static Label gameOver;
    Rectangle object;
    private ImageView imv;

    public Auto(){
        object = new Rectangle(100,150, Color.BLUEVIOLET);
        imv = new ImageView(new Image("https://psv4.userapi.com/c536436/u450631298/docs/d39/898b04f229e6/RetroCar.png?extra=7o-PfumvoXGZqq_0TKoCqfy8eZZ60w004b1Vio0eneHR5exL9Z9qYPyDWydZJ42biNBOAX2VPTuVBxsk6ncN1A2vKrDLqNsBLRpGAJX_p3aFuuvXb7HBxVR1XdRVWkqjacKSbEFLJmeTX4x_7Z51w2g8GA",100,160,false,true));
        getChildren().addAll(imv);
        setTranslateY(400);
    }

    public void setCarImage(){
        imv.setImage(new Image("https://psv4.userapi.com/c536436/u450631298/docs/d39/898b04f229e6/RetroCar.png?extra=7o-PfumvoXGZqq_0TKoCqfy8eZZ60w004b1Vio0eneHR5exL9Z9qYPyDWydZJ42biNBOAX2VPTuVBxsk6ncN1A2vKrDLqNsBLRpGAJX_p3aFuuvXb7HBxVR1XdRVWkqjacKSbEFLJmeTX4x_7Z51w2g8GA",100,160,false,true));
    }

    public void moveY(){
        setTranslateY(getTranslateY()-2);

        for(Wall w : Pong.walls){
            if(this.getBoundsInParent().intersects(w.getBoundsInParent())){
                gameOver = new Label("GAME OVER!!!");
                gameOver.setTranslateY(380); gameOver.setTranslateX(250);
                gameOver.setFont(new Font(24));
                Pong.appRoot.getChildren().addAll(gameOver);
                imv.setImage(new Image("https://static.tildacdn.com/tild3461-3862-4263-a236-373539326534/ddbc12dba7c5adbcde08.png",300,300,false,true));
                Pong.timer.stop();
            }
        }
    }

}
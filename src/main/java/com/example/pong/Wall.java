package com.example.pong;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Pane {
    private Rectangle object;
    private WallPosition pos;
    
    public Wall(WallPosition pos){
        object = new Rectangle(200,30, Color.BLUE);
        getChildren().addAll(object);
        this.pos = pos;
        switch(pos){
            case LEFT:      setTranslateX(0);
                            break;
            case CENTER:    setTranslateX(200);
                            break;
            case RIGHT:     setTranslateX(400);
                            break;
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 *
 * @author Doetheman
 */
public class PlayArea extends Pane{
   
    public PlayArea(){
    }
    
   public void setPlayAreaBackground( String bgImage ) {
      
   try {
       Image image1 = new Image( new FileInputStream("images/space3.jpg"));
       BackgroundImage myBI = new BackgroundImage( image1, BackgroundRepeat.NO_REPEAT,
          BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
       this.setBackground( new Background(myBI));
   }
   catch ( java.io.FileNotFoundException ex) {
       System.err.println("Could not find image for loading.");
   }
   
   
}
}

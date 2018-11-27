/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Doetheman
 */
public class MeteorBlasterGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       ProfilePane PP = new ProfilePane("gameData.txt","meteorBlasterConfig.txt");
 
        Scene scene = new Scene(PP, 300, 250);
        
        primaryStage.setTitle("Meteor Blaster!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

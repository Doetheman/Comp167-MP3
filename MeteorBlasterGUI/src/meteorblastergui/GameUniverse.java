/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import meteorblasterbackend.GamerProfile;
import meteorblasterbackend.GamerProfileCollection;
import meteorblasterbackend.MeteorConfigData;
import meteorblasterbackend.MeteorData;
import meteorblasterbackend.MeteorIO;

/**
 *
 * @author Doetheman
 */
/**
 *
 * @author Doetheman
 */
public class GameUniverse extends BorderPane {

    private Craft craft;
    private GamerProfile gamerProfile;
    private MeteorField meteor;
    // private MeteorField gamerProfile;
    private MeteorData mD;
    private Meteor m;
    private PhotonTorpedo destroyer;
    private PlayArea playA;
    private GamerTimer gamerTimer;
    private myListener HearMe;
    private Random rand;
    private ArrayList<MovingSprite> movingMeteors;
   /** private static boolean L = false;
    private static boolean R = false;
    private static boolean F = false;
    * **/
    private boolean thrust = false;

    public GameUniverse(String meteorConfigFile, GamerProfileCollection gnCollection) {
        MeteorConfigData MCD = MeteorIO.readConfigData(meteorConfigFile);
        playA = new PlayArea();
        playA.setPlayAreaBackground("images/space3.jpg");
                HearMe = new myListener();
        craft = new Craft(playA, MCD.getCraftData());
        gamerTimer = new GamerTimer();
       movingMeteors = new ArrayList<>();
        
       destroyer = new PhotonTorpedo(playA, craft.getX() ,craft.getY(), craft.getFacingDirection());
       
        meteor = new MeteorField(playA, MCD.getMeteorData() );
        
        meteor.createMeteors(9, MCD.getMeteorData(), 750, 500);
        for(int i = 0 ; i <meteor.getNumMeteors();i++){
            movingMeteors.add(meteor.getMeteors(i));
        }
        
        this.setCenter(playA);
        playA.getChildren().add(craft);
        
      gamerTimer.start();
    }

    private class GamerTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            for(int i = 0; i < meteor.getNumMeteors(); i++){
                meteor.getMeteors(i).move();
            
            }
            
            for(int bam = 0; bam < craft.getNumTorpedoes(); bam++){
                craft.getTorpedoes(bam).move();
            }
          
                
        }
    }
    public void registerListeners() {
    this.setOnKeyPressed(getHearMe());
    }
        private class myListener implements EventHandler<KeyEvent>{
    

            @Override
            public void handle(KeyEvent event) {
              
               if (event.getCode() == KeyCode.A) {
                    craft.rotate(-35);
                    craft.move();
               }
                if (event.getCode() == KeyCode.W) {
                    craft.move();
                    craft.toggleCraft();
                }   
                if (event.getCode() == KeyCode.D) {
                   craft.rotate(35);
                   craft.move();
                }

                if (event.getCode() == KeyCode.SPACE) {
                    craft.fireTorpedo(playA);
                }
                if(event.getCode() != KeyCode.W){
                    craft.decreaseSpeed();
                    craft.setCoastImage();
                }
            if(craft.getY()>=playA.getHeight()){
                craft.setY(0);
            }
            if(craft.getY()<=0){
                craft.setY(playA.getHeight()+1);
            }
            
            if(craft.getX()<=0){
            craft.setX(playA.getWidth()+1);
        }
            if(craft.getX()>= playA.getWidth()-1){
                craft.setX(0);
            }
            }
       
   
    }
                

    public void endGame() {
        Alert Gover = new Alert(AlertType.INFORMATION, "GAMEROVER RECIEVE YOUR L");
        Gover.show();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return (getCraft() + " " + getGamerProfile() + " " + getPlayArea());
    }

    /**
     * @return the craft
     */
    public Craft getCraft() {
        return craft;
    }

    /**
     * @param craft the craft to set
     */
    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    /**
     * @return the gamerProfile
     */
    public GamerProfile getGamerProfile() {
        return gamerProfile;
    }

    /**
     * @param gamerProfile the gamerProfile to set
     */
    public void setGamerProfile(GamerProfile gamerProfile) {
        this.gamerProfile = gamerProfile;
    }

    /**
     * @return the playArea
     */
    public PlayArea getPlayArea() {
        return playA;
    }

    /**
     * @param playArea the playArea to set
     */
    public void setPlayArea(PlayArea playArea) {
        this.playA = playArea;
    }

    /**
     * @return the HearMe
     */
    public myListener getHearMe() {
        return HearMe;
    }

    /**
     * @param HearMe the HearMe to set
     */
    public void setHearMe(myListener HearMe) {
        this.HearMe = HearMe;
    }

}

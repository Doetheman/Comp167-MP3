/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import meteorblasterbackend.MeteorData;

/**
 *
 * @author Doetheman
 */
public class MeteorField extends PlayArea {

    private Image meteorSheet;
    private ArrayList<Meteor> meteors;
    public PlayArea playA;

    /**
     *
     * @param playArea
     * @param meteorData
     */
    public MeteorField(PlayArea playArea, MeteorData meteorData) {
        playA = playArea;
        try {

            meteorSheet = new Image(new FileInputStream(meteorData.getMeteorSpriteFile()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MeteorField.class.getName()).log(Level.SEVERE, null, ex);
        }

        meteors = new ArrayList<Meteor>();

    }

    /**
     *
     * @param numMeteors
     * @param meteorData
     * @param maxX
     * @param maxY
     */
    public void createMeteors(int numMeteors, MeteorData meteorData, int x1, int y1) {

        Random rand = new Random();
        for (int i = 0; i < numMeteors; i++) {
            int x = rand.nextInt(x1);
            int y = rand.nextInt(y1);

            Meteor meteor = new Meteor(playA, meteorData, meteorSheet, x, y);
            meteors.add(meteor);

            playA.getChildren().add(meteor);
        }

        //meteors.add(new Meteor( playA,  meteorData, getMeteorSheet(), randX, randY));
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    /**
     *
     * @return
     */
    public int getNumMeteors() {
        return meteors.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public Meteor getMeteors(int index) {
        return meteors.get(index);
    }

    /**
     *
     * @param index
     * @param t
     */
    public void setMeteors(int index, Meteor t) {
        meteors.set(index, t);
    }

    /**
     *
     * @param item
     */
    public void addMeteors(Meteor item) {
        meteors.add(item);
    }
    
    
    /**
     *
     * @param index
     * @return
     */
    public Meteor deleteMeteors(int index) {
        return meteors.remove(index);
    }

    /**
     * @return the meteorSheet
     */
    public Image getMeteorSheet() {
        return meteorSheet;
    }

    /**
     * @param meteorSheet the meteorSheet to set
     */
    public void setMeteorSheet(Image meteorSheet) {
        this.meteorSheet = meteorSheet;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String temp = meteorSheet.toString();
        for (int i = 0; i < meteors.size(); i++) {
            temp += System.lineSeparator() + meteors.get(i);
        }
        return ("#MeteorField" + super.toString() + temp);
    }
}

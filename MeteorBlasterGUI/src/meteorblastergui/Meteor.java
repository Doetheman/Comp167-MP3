/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import meteorblasterbackend.MeteorData;

/**
 *
 * @author Doetheman
 */
public class Meteor extends MovingSprite {

    private int MIN_SIZE;
    private int points;
    private int size;
    private double meteorImageWidth;
    private double meteorImageHeight;

    /**
     *
     * @param meteorData
     * @param image
     * @param xLoc
     * @param yLoc
     */
    public Meteor(PlayArea playArea, MeteorData meteorData, Image image, double xLoc, double yLoc) {
        super();
        MIN_SIZE = meteorData.getMinSize();
        points = 0;
        meteorImageWidth = 125;
        meteorImageHeight = 125;
        size = 0;

        setDirection(Math.random() * 359);

        setSpeed(Math.random() * 3);

        this.setParentWidth(playArea.getPrefWidth());
        this.setParentHeight(playArea.getPrefHeight());

        setViewport(new Rectangle2D((int) (Math.random() * 4) * 125, (int) (Math.random() * 4) * 125, 125, 125));

        this.setImage(image);

        this.setX(xLoc);
        this.setY(yLoc);

        //meteors.add(new Meteor( playA,  meteorData, getMeteorSheet(), randX, randY));

    }

    /**
     * @return the MIN_SIZE
     */
    public int getMIN_SIZE() {
        return MIN_SIZE;
    }

    /**
     * @param MIN_SIZE the MIN_SIZE to set
     */
    public void setMIN_SIZE(int MIN_SIZE) {
        this.MIN_SIZE = MIN_SIZE;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the meteorImageWidth
     */
    public double getMeteorImageWidth() {
        return meteorImageWidth;
    }

    /**
     * @param meteorImageWidth the meteorImageWidth to set
     */
    public void setMeteorImageWidth(double meteorImageWidth) {
        this.meteorImageWidth = meteorImageWidth;
    }

    /**
     * @return the meteorImageHeight
     */
    public double getMeteorImageHeight() {
        return meteorImageHeight;

    }

    /**
     * @param meteorImageHeight the meteorImageHeight to set
     */
    public void setMeteorImageHeight(double meteorImageHeight) {
        this.meteorImageHeight = meteorImageHeight;
    }

    @Override
    public String toString() {
        return "#Meteor" + super.toString() + " " + MIN_SIZE + System.lineSeparator()
                + points + System.lineSeparator() + meteorImageWidth + System.lineSeparator()
                + meteorImageHeight + System.lineSeparator();
    }

}

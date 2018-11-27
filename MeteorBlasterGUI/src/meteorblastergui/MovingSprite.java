/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import javafx.scene.image.ImageView;

/**
 *
 * @author Doetheman
 */
public abstract class MovingSprite extends ImageView implements Movable{
    private double direction;
    private double speed;
    private double maxSpeed;
    private double parentWidth;
    private double parentHeight;
    private final static double TP = 2 * Math.PI;

    public MovingSprite(){
        direction = 180.0;
        maxSpeed = 5;
        speed = 5;
        parentWidth = 0;
        parentHeight = 0;       
    }
    
    @Override
    public void move(){
        this.setX(this.getX() + speed * Math.cos(direction * TP/360));
        this.setY(this.getY() + speed * Math.sin(direction * TP/360));
        
       
    }
    
    /**
     *
     * @return
     */
    @Override
public final double getCenterX() {
    return this.getBoundsInParent().getMinX() + this.getBoundsInParent().getWidth()/2;
}
    
    @Override
    public double getCenterY(){
        return 0;
    }
    
    @Override
public double getBoundingRadius() {
    return Math.max( this.getBoundsInParent().getHeight(), this.getBoundsInParent().getWidth())/2;
}
    /**
     * @return the direction
     */
    public double getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the maxSpeed to set
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return the parentWidth
     */
    public double getParentWidth() {
        return parentWidth;
    }

    /**
     * @param parentWidth the parentWidth to set
     */
    public void setParentWidth(double parentWidth) {
        this.parentWidth = parentWidth;
    }

    /**
     * @return the parentHeight
     */
    public double getParentHeight() {
        return parentHeight;
    }

    /**
     * @param parentHeight the parentHeight to set
     */
    public void setParentHeight(double parentHeight) {
        this.parentHeight = parentHeight;
    }
    
    @Override
    public String toString(){
        return"#movingSprite"+System.lineSeparator()+direction + " " +speed+" " + maxSpeed+ " " + parentWidth +" " + parentHeight+System.lineSeparator();
    }
 
}

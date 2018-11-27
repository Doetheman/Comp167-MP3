/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Doetheman
 */
public class PhotonTorpedo extends Torpedo {
    private Line icon;
    private int iconWidth;
    private int iconHeight;
    /**
     * 
     * @param playArea
     * @param locX
     * @param locY
     * @param dir 
     */
    public PhotonTorpedo(  PlayArea playArea, double locX, double locY, double dir){ 
        super();
      /** try {
            this.setImage(new Image(new FileInputStream("images/torpedo.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhotonTorpedo.class.getName()).log(Level.SEVERE, null, ex);
        }
        * **/
        
        
        icon = new Line();
        this.setDirection(dir);
        icon.setStartX(locX);
        icon.setStartY(locY);
        icon.setEndX(5);
        icon.setEndY(5);
        iconWidth = 5;
        iconHeight = 5;
        
        
        
        icon.setRotate(dir);
        playArea.getChildren().add(icon);
    }

    /**
     * @return the icon
     */
    public Shape getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Line icon) {
        this.icon = icon;
    }

    /**
     * @return the iconWidth
     */
    public int getIconWidth() {
        return iconWidth;
    }

    /**
     * @param iconWidth the iconWidth to set
     */
    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    /**
     * @return the iconHeight
     */
    public int getIconHeight() {
        return iconHeight;
    }

    /**
     * @param iconHeight the iconHeight to set
     */
    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }
    @Override
     public void move(){
        icon.setStartX(this.getX() + this.getSpeed() * Math.cos(this.getDirection() * (2 * Math.PI)/360));
        icon.setStartY(this.getY() + this.getSpeed() * Math.sin(this.getDirection() * (2 * Math.PI)/360));
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return("#PhotonTorpedo"+System.lineSeparator()+super.toString()+System.lineSeparator()+Integer.toString( iconWidth) 
                +" "+ Integer.toString(iconHeight)+System.lineSeparator());
    }

    
}

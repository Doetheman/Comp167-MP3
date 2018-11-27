/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;

/**
 *
 * @author Doetheman
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import meteorblasterbackend.CraftData;

/**
 *
 * @author Doetheman
 */
public class Craft extends MovingSprite {

    private double DECREASE_AMT;
    private double INCREASE_AMT;
    private double facingDirection;
    private Image[] images;
    private ArrayList<Torpedo> torpedoes;
    private URL phaserResource;
    private AudioClip phaserClip;
    private double shieldStrength;
    private int toggle;
    public Craft(PlayArea playArea, CraftData cd) {
        
        DECREASE_AMT = cd.getThrustDecreaseAmount();
        INCREASE_AMT = cd.getThrustIncreaseAmount();
        facingDirection = super.getDirection();
        images = new Image[cd.getNumImageFiles()];

        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = new Image(new FileInputStream(cd.getImageFile(i)));
            }
            this.setImages(images);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Craft.class.getName()).log(Level.SEVERE, null, ex);
        }

        torpedoes = new ArrayList<>();
        phaserResource = meteorblasterbackend.CraftData.class.getResource(cd.getPhaserSoundFile());
           this.setImage(images[0]);
        phaserClip = new AudioClip(phaserResource.toString());
        /* try {
            phaserResource = new URL(cd.getPhaserSoundFile());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Craft.class.getName()).log(Level.SEVERE, null, ex);
        }vvvvvvvvvvvvvv
         */
       
        shieldStrength = 100.0;
        
    }
    
    /**
     * 
     * @param angle sets facing direction
     */
    public void rotate(double angle) {
        
        this.setRotate(this.getRotate()+angle);
        this.setDirection(this.getRotate());
//        double newA = facingDirection + angle;
//        if (newA > 359.0) {
//            newA -= 360.0;
//        } else if (newA < 0) {
//            newA += 360.0;
//
//        }
//        setFacingDirection(angle);
    }
    /**
     * Increases the speed
     */
    public void increaseSpeed() {
        setSpeed(getSpeed() + getINCREASE_AMT());
    }
    /**
     * Decreases the speed 
     */
    public void decreaseSpeed() {
        setSpeed(getSpeed() - getDECREASE_AMT());
    }
    /**
     * Add torpedoes to arrayList
     */
    public void fireTorpedo(PlayArea playA) {
        
        //Torpedo boom = new Torpedo();
        PhotonTorpedo boombaby = new PhotonTorpedo(playA, getX(), getY(), getDirection());
        addTorpedoes(boombaby);
       // addTorpedoes(boom);
    }
    /**
     * Deletes torpedo
     */
    public void abortTorpedoes() {
        for (int i = 0; i < torpedoes.size(); i++) {
            deleteTorpedo(i);
        }
    }

    /**
     * @return the DECREASE_AMT returns decrease amount
     */
    public double getDECREASE_AMT() {
        return DECREASE_AMT;
    }

    /**
     * @param DECREASE_AMT sets DECREASE_AMT
     */
    public void setDECREASE_AMT(double DECREASE_AMT) {
        this.DECREASE_AMT = DECREASE_AMT;
    }

    /**
     * @return the INCREASE_AMT returns INCREASE_AMT
     */
    public double getINCREASE_AMT() {
        return INCREASE_AMT;
    }

    /**
     * @param INCREASE_AMT sets INCREASE_AMT
     */
    public void setINCREASE_AMT(double INCREASE_AMT) {
        this.INCREASE_AMT = INCREASE_AMT;
    }

    /**
     * @return the facingDirection returns facingDirection
     */
    public double getFacingDirection() {
        return facingDirection;
    }

    /**
     * @param facingDirection the facingDirection to set
     */
    public void setFacingDirection(double facingDirection) {
        this.facingDirection = facingDirection;
    }

    /**
     * @param i
     * @return the images
     */
    public Image getImages(int i) {
        return images[i];
    }

    /**
     * @param images the images to set
     */
    public void setImages(Image images[]) {
        this.images = images;
    }

    /**
     * @param index
     * @return the torpedoes
     */
    public Torpedo getTorpedoes(int index) {
        return torpedoes.get(index);
    }
    /**
     * 
     * @return 
     */
    public int getNumTorpedoes() {
        return torpedoes.size();
    }

    /**
     * @param index sets index in ArrayList
     * @param t adds torpedo
     */
    public void setTorpedoes(int index, Torpedo t) {
        torpedoes.set(index, t);
    }
    /**
     * 
     * @param item add torpedoes 
     */
    public void addTorpedoes(Torpedo item) {
        torpedoes.add(item);
    }
    /**
     * 
     * @param index deletes torpedo at index
     */
    public Torpedo deleteTorpedo(int index) {
        return torpedoes.remove(index);
    }

    /**
     * @return the phaserResource
     */
    public URL getPhaserResource() {
        return phaserResource;
    }

    /**
     * @param phaserResource the phaserResource to set
     */
    public void setPhaserResource(URL phaserResource) {
        this.phaserResource = phaserResource;
    }

    /**
     * @return the phaserClip
     */
    public AudioClip getPhaserClip() {
        return phaserClip;
    }

    /**
     * @param phaserClip the phaserClip to set
     */
    public void setPhaserClip(AudioClip phaserClip) {
        this.phaserClip = phaserClip;
    }

    /**
     * @return the shieldStrength
     */
    public double getShieldStrength() {
        return shieldStrength;
    }

    /**
     * @param shieldStrength the shieldStrength to set
     */
    public void setShieldStrength(double shieldStrength) {
        this.shieldStrength = shieldStrength;
    }
    
    public void setCoastImage(){
        this.setImage(images[0]);
    }
    
    public void toggleCraft(){
        this.setImage(images[toggle]);
        toggle ++;
        if( toggle == 3){
            toggle = 0;
        }
    
    }
   

    /**
     *
     * @return outputs objects as string 
     */
    @Override
    public String toString() {

        String temp = "";
        String s = System.lineSeparator();

        for (int i = 0; i < torpedoes.size(); i++) {
            temp += torpedoes.get(i).toString();
        }

        return "#Craft" + System.lineSeparator() + super.toString() + 
                System.lineSeparator()+ DECREASE_AMT + " " + INCREASE_AMT + " " + facingDirection +  System.lineSeparator()
                + temp + " " + phaserClip + " " + shieldStrength + s + phaserResource+ System.lineSeparator();
    }
}

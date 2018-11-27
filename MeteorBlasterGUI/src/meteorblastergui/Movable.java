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
public interface Movable {
    double getCenterX();
    double getCenterY();

    void move();
    double getBoundingRadius();
}

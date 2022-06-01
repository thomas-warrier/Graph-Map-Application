/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grama.ihm;

import grama.calcule.vector.Vector2D;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author wwwazz
 */
public interface Drawable {

    public void draw(Graphics g, Vector2D center, Font font, boolean highlight);
}

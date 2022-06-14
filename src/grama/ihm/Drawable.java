/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grama.ihm;

import grama.calcule.vector.Vector2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * pour dessiner des composants graphiques
 */
public interface Drawable {

    /**
     * Dessine l'objet
     * @param g l'objet graphique
     * @param center le centre où dessiner
     * @param font la police
     * @param highlight la couleur spécial si besoin sinon mettre null
     */
    public void draw(Graphics g, Vector2D center, Font font, Color highlight);
}

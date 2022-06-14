/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.ihm;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 * Permet d'ajouté une couleur rectangulaire à une légendre
 * @author twarr
 */
public class LegendLigneComponent extends JLabel{
    private Color color;

    public LegendLigneComponent(Color color) {
        this.color = color;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, 30, 4);

    }
}

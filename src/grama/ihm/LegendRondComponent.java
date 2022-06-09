/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.ihm;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author twarr
 */
public class LegendRondComponent extends JLabel{
    private Color color;
    public LegendRondComponent(Color color) {
        this.color = color;
        
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(0, 0, 20, 20);
    }
}

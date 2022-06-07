package grama.formater;

import grama.calcule.vector.Vector2D;
import grama.exceptions.FormatFileException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class StringFormater {
    /**
     * cette méthode permet de lire un fichier (méthode trés similaire a celle du cour)
     * @param file
     * @return String
     * @throws IOException 
     */
    //use bufer reader
    public static String readFile(File file) throws IOException {
        if (file == null) {
            return "";
        }
        int unsignedByte;

        FileInputStream stream = new FileInputStream(file);
        StringBuilder str = new StringBuilder();// more efficient than  StringBuffer
        while ((unsignedByte = stream.read()) > -1) {
            str.append((char) unsignedByte);
        }

        stream.close();
        return str.toString();
    }

    public static String[] getCoupleFormatCharStr(String couple) throws FormatFileException {
        String[] splited = couple.trim().split(",");
        for (int i = 0; i < splited.length; i++) {
            splited[i] = splited[i].trim();
        }
        if (splited.length != 2 || splited[0].length() != 1) {
            throw new FormatFileException(couple);
        }

        return splited;
    }

    public static void drawCenteredString(Graphics g, String text, Vector2D location, Font font) {
        
        FontMetrics metrics = g.getFontMetrics(font);
        Graphics2D g2d = (Graphics2D) g.create();
        FontMetrics fm = g2d.getFontMetrics();

        int x = (int) (location.x - fm.stringWidth(text) / 2.0);
        int y = (int) (location.y - fm.getHeight() / 2.0) + fm.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
        
    }
}

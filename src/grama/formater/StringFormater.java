package grama.formater;

import grama.exceptions.FormatFileException;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author wwwazz
 */
public class StringFormater {

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

    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}

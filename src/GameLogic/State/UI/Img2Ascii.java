package GameLogic.State.UI;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class Img2Ascii {

    private BufferedImage img;
    private double pixval;

    public String convertToAscii(String imgname) {
        try {
            img = ImageIO.read(Img2Ascii.class.getResourceAsStream(imgname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color pixcol = new Color(img.getRGB(j, i));
                pixval = (((pixcol.getRed() * 0.30) + (pixcol.getBlue() * 0.59) + (pixcol.getGreen() * 0.11)));
                ret.append(strChar(pixval)).append("  ");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    public String strChar(double g) {
        String str = " ";
        g = 256 - g;
        if (g >= 240) {
            str = " ";
        } else if (g >= 210) {
            str = ".";
        } else if (g >= 190) {
            str = ":";
        } else if (g >= 170) {
            str = "-";
        } else if (g >= 120) {
            str = "^";
        } else if (g >= 110) {
            str = "|";
        } else if (g >= 80) {
            str = "6";
        } else if (g >= 60) {
            str = "8";
        } else {
            str = "#";
        }
        return str;
    }

    public static String asAscii(String file){
        Img2Ascii obj = new Img2Ascii();
        String s = obj.convertToAscii(file);
        return s;

    }

}
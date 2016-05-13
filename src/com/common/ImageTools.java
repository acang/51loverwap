package com.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageTools {
  public ImageTools() {
  }
  public static void pressText(String pressText, String targetImg,
           String fontName, int fontStyle, Color color, int fontSize, int x,
           int y) {
         try {
           File _file = new File(targetImg);
           Image src = ImageIO.read(_file);

           int wideth = src.getWidth(null);
           int height = src.getHeight(null);
           BufferedImage image = new BufferedImage(wideth, height,
                     BufferedImage.TYPE_INT_RGB);
           Graphics g = image.createGraphics();
           g.drawImage(src, 0, 0, wideth, height, null);
           // String s="www.qhd.com.cn";
           g.setColor(color);
           g.setFont(new Font(fontName, fontStyle, fontSize));

           //g.drawString(pressText, wideth - fontSize - x, height - fontSize
           //          / 2 - y);
           g.drawString(pressText, 5, 5);
           g.dispose();
           FileOutputStream out = new FileOutputStream(targetImg+"1.jpg");
           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
           encoder.encode(image);
           out.close();
         } catch (Exception e) {
           System.out.println(e);
         }
  }

  public static void main(String args[])
  {
     ImageTools.pressText("»Æ½¡ËÉ","d:/phsrc/2.jpg","»ªÎÄ²ÊÔÆ",Font.BOLD,Color.BLUE,24,5,5);
  }

}

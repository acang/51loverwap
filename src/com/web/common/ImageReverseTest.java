 package com.web.common;

  import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;


public class ImageReverseTest {

	public static File rotateImage(String filename, int angle)
	{
        File file = new File(filename);
        if (file.exists() && file.canRead() && file.isFile())
	{
		BufferedImage buffimg = null;
		try {
			buffimg = ImageIO.read(file);
            int width = buffimg.getWidth();
            int height = buffimg.getHeight();
            System.out.println("w:" + width);
            System.out.println("h:" + height);
			}
		catch (IOException e) {
			//e.printStackTrace();
			}
		int w = buffimg.getWidth();
		int h = buffimg.getHeight();  		  //目标图片
		BufferedImage tempimg = null;
		Graphics2D graphics2d = null;
		int type = buffimg.getColorModel().getTransparency();
		if (angle%360 == 0) {
			return file;
			} else if (angle%180 == 0) {
				//图片形状不变
				tempimg = new BufferedImage(w, h, type);
				graphics2d = tempimg.createGraphics();
				//Math.toRadians(angle), w / 2, h / 2, 三参数中,前�\uFFFD�为角度，后两�\uFFFD�为原图片左上角移动后的对齐�\uFFFD
				graphics2d.rotate(Math.toRadians(angle), w / 2, h / 2);
				} else if (angle % 90 == 0) {
					//图片形状变了
					tempimg = new BufferedImage(h, w, type);
					graphics2d = tempimg.createGraphics();
					//使之顺时针为-,逆时针为+
					angle = -angle;
					//Math.toRadians(angle),x,y, 前�\uFFFD�为角度，后两�\uFFFD�为原图�\uFFFD 移动后的对齐�\uFFFD
					if(angle <0)//顺时针旋�\uFFFD-90(h/2,h/2)
						graphics2d.rotate(Math.toRadians(angle), w/ 2, w / 2);
					else//逆时针旋�\uFFFD+90(w/2,w/2)
						graphics2d.rotate(Math.toRadians(angle), h / 2, h / 2);
					} else {
						return file;
						}
		//把buffimg写到目标图片中去
		graphics2d.drawImage(buffimg, 0, 0, null);
		graphics2d.dispose();
		buffimg = tempimg;
		// 让用于显示的缓冲区图像指向过滤后的图�\uFFFD
		try {
			ImageIO.write(buffimg, getsuffix(filename), file);
			}
		catch (IOException e) {
			//e.printStackTrace();
			}
		buffimg.flush();
		}
	return file;
	}
	/**  	  * 取得图片格式
	 *  * @param filename
	 *   * @return  	  */
	public static String getsuffix(String filename) {
		String cat = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
		return cat;
		}


    public static void main(String[] args){
        ImageReverseTest.rotateImage("c:/2.jpg",90);
    }

	}



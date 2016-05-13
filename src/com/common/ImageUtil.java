package com.common;

import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;


import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
//size=2:16:3
//size=4:32:5
//size=6:48:8
//size=8:64:10
//size=10:96:13
//size=12:112:15
//size=14:128:16
//size=16:144:19
//size=18:160:21
//size=20:176:24
//size=22:192:26
//size=24:208:29
//size=26:224:31
//size=28:256:32
//size=30:272:35
//size=32:288:37
//size=34:304:40
//size=36:320:42
//size=38:336:44
//size=40:352:47
//size=42:368:48
//size=44:384:51
//size=46:416:53
//size=48:432:56
//size=50:448:58
//size=52:464:60
//size=54:480:63
//size=56:496:65
//size=58:512:67
//size=60:528:69
//size=62:544:72
//size=64:576:74
//size=66:592:76
//size=68:608:79
//size=70:624:81
//size=72:640:83
//size=74:656:85
//size=76:672:87
//size=78:688:90
//size=80:704:92
//size=82:736:95
//size=84:752:97
//size=86:768:99
//size=88:784:101
//size=90:800:103
//size=92:816:106
//size=94:832:108
//size=96:848:111
//size=98:864:113


public class ImageUtil
{
	public static void getFixedBoundIcon(String srcfilePath, String filename,int height, int width, String outPath) throws Exception
	{
	}


        public static int getFontSizeByWidth(int width)
        {
          if(width >= 0 && width < 16)
              return 2;
          if(width >= 16 && width < 32)
              return 4;
          if(width >= 32 && width < 48)
              return 6;
          if(width >= 48 && width < 64)
              return 8;
          if(width >= 64 && width < 96)
              return 10;
          if(width >= 96 && width < 112)
              return 12;
          if(width >= 112 && width < 128)
              return 14;
          if(width >= 128 && width < 144)
              return 16;
          if(width >= 144 && width < 160)
              return 18;
          if(width >= 160 && width < 176)
              return 20;
          if(width >= 176 && width < 192)
              return 22;
          if(width >= 192 && width < 208)
              return 24;
          if(width >= 208 && width < 224)
              return 26;
          if(width >= 224 && width < 256)
              return 28;
          if(width >= 256 && width < 272)
              return 30;
          if(width >= 272 && width < 288)
              return 32;
          if(width >= 288 && width < 304)
              return 34;
          if(width >= 304 && width < 320)
              return 36;
          if(width >= 320 && width < 336)
              return 38;
          if(width >= 336 && width < 352)
              return 40;
          if(width >= 352 && width < 368)
              return 42;
          if(width >= 368 && width < 384)
              return 44;
          if(width >= 384 && width < 416)
              return 46;
          if(width >= 416 && width < 432)
              return 48;
          if(width >= 432 && width < 448)
              return 50;
          if(width >= 448 && width < 464)
              return 52;
          if(width >= 464 && width < 480)
              return 54;
          if(width >= 480 && width < 496)
              return 56;
          if(width >= 496 && width < 512)
              return 58;
          if(width >= 512 && width < 528)
              return 60;
          if(width >= 528 && width < 544)
              return 62;
          if(width >= 544 && width < 576)
              return 64;
          if(width >= 576 && width < 592)
              return 66;
          if(width >= 592 && width < 608)
              return 68;
          if(width >= 608 && width < 624)
              return 70;
          if(width >= 624 && width < 640)
              return 72;
          if(width >= 640 && width < 656)
              return 74;
          if(width >= 656 && width < 672)
              return 76;
          if(width >= 672 && width < 688)
              return 78;
          if(width >= 688 && width < 704)
              return 80;
          if(width >= 704 && width < 736)
              return 82;
          if(width >= 736 && width < 752)
              return 84;
          if(width >= 752 && width < 768)
              return 86;
          if(width >= 768 && width < 784)
              return 88;
          if(width >= 784 && width < 800)
              return 90;
          if(width >= 800 && width < 816)
              return 92;
          if(width >= 816 && width < 832)
              return 94;
          if(width >= 832 && width < 848)
              return 96;
          if(width >= 848 && width < 864)
              return 98;
          return 98;
        }


       public static void resizePhotoJpg(String srcFilePath,String srcFilename,String outpath,String outFilename,int width,int height) throws Exception
       {
         Opener o = new Opener();
         ImagePlus imp = o.openImage(srcFilePath+File.separator + srcFilename);
         ImageProcessor ip = imp.getProcessor();
         int width_src = (int) (ip.getWidth());
         int height_src = (int) (ip.getHeight());


         double wR =(double)width/(double)width_src;
         double hR =(double)height/(double)height_src;

          ImageProcessor tmpIP=ip.resize((int) (width_src * wR), (int) (height_src * hR));
          String desfile = outpath + File.separator +outFilename;
          FileOutputStream fos =  new FileOutputStream(desfile);
          BufferedImage bi = new BufferedImage(tmpIP.getWidth(), tmpIP.getHeight(),BufferedImage.TYPE_INT_RGB);
          Graphics g = bi.createGraphics();
          g.drawImage(tmpIP.createImage(), 0, 0, null);
          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
          JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
          param.setQuality(0.885f, true);
          encoder.encode(bi, param);
          fos.close();
       }

    public static void resizePhotoJpg2(String srcFilePath,String outpath,int width,int height) throws Exception
    {
        Opener o = new Opener();
        ImagePlus imp = o.openImage(srcFilePath);
        ImageProcessor ip = imp.getProcessor();
        int width_src = (int) (ip.getWidth());
        int height_src = (int) (ip.getHeight());


        double wR =(double)width/(double)width_src;
        double hR =(double)height/(double)height_src;

        ImageProcessor tmpIP=ip.resize((int) (width_src * wR), (int) (height_src * hR));
        FileOutputStream fos =  new FileOutputStream(outpath);
        BufferedImage bi = new BufferedImage(tmpIP.getWidth(), tmpIP.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(tmpIP.createImage(), 0, 0, null);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
        param.setQuality(0.885f, true);
        encoder.encode(bi, param);
        fos.close();
    }

        /**
         * // 对一个图片文件进行加工(非gif文件)
         * @param srcFile
         * @throws Exception
         */
        public static void dealImage(String srcFilePath, String srcFilename, String outPath,String outFilename) throws Exception {

                Opener o = new Opener();
                ImagePlus imp = o.openImage(srcFilePath+File.separator + srcFilename);
                ImageProcessor ip = imp.getProcessor();
                int width_src = (int) (ip.getWidth());
                int height_src = (int) (ip.getHeight());
                String desc = outPath + File.separator + outFilename;
                FileOutputStream fos = new FileOutputStream(desc);
                BufferedImage bi = new BufferedImage(ip.getWidth(), ip.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.createGraphics();
                g.drawImage(ip.createImage(), 0, 0, null);
                String logo = Constant.PHOTO_WATERMARK;
                String url  = Constant.PHOTO_WATERMARK_URL;


//                font_size=int(jpeg.width/23)
//
//                jpeg.canvas.font.size=font_size
//                jpeg.canvas.brush.color=&H448FFF
//                Jpeg.Canvas.Font.Color = &H448FFF
//                Jpeg.Canvas.Font.Family = "楷体_GB2312"
//                Jpeg.Canvas.Font.Bold = true
//                Jpeg.Canvas.Print Jpeg.width/2, Jpeg.height-font_size*3, "   51交友中心"
//                Jpeg.Canvas.Print Jpeg.width/2, Jpeg.height-font_size*2,   "www.51lover.org"
//                Jpeg.Save Server.MapPath("img/"&fname)


                //int fontsize = ImageUtil.getFontSizeByWidth(width_src/2);
                int fontsize = width_src/23;
                g.setFont(new Font("楷体_GB2312", Font.BOLD, fontsize));

                Color color = new Color(68,143,255);


                g.setColor(color);
//                FontMetrics fm  = g.getFontMetrics();
//                int lineHeight = fm.getHeight();
//                byte[] logarray = logo.getBytes();
//                byte[] urlarray = url.getBytes();


//                int pre = 0;
//                for(int i =1;i < 100;i ++)
//                {
//                   if(i % 2 ==0)
//                   {
//                       Font font = new Font("宋体",Font.BOLD,i);
//                       g.setFont(font);
//                       fm = g.getFontMetrics();
//                       int width = fm.bytesWidth(urlarray,0,urlarray.length);
//                       System.out.println("if(width >= "+pre +" && width < " + width+")");
//                       System.out.println("    return "+i+";");
//                       pre = width ;
//                   }
//                }

//
//                                int x = width_src - fm.bytesWidth(logarray,0,logarray.length);
//                                if(x < 0)
//                                   x = 1;
//                                int y = height_src - (lineHeight*2);
//                                if(y < 0)
//                                   y = 1;
//                                g.drawString(logo, x, y);
//                                x = width_src -  fm.bytesWidth(urlarray,0,urlarray.length);
//                                if(x < 0)
//                                   x=1;
//                                y = height_src - (lineHeight);
//                                if(y < 1)
//                                   y = 1;

                                g.drawString(logo,width_src/2, height_src-fontsize*3);
                                g.drawString(url,width_src/2, height_src-fontsize*2);

                        g.dispose();
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
                        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
                        param.setQuality(0.885f, true);
                        encoder.encode(bi, param);
                fos.close();

        }


	/**
	 * // 处理GIF图片
	 */
	public static void dealGif(String srcFilePath, String srcFilename, String outPath,String outFilename,int width,int height) throws Exception {


		GifDecoder d = new GifDecoder();
		d.read(srcFilePath+File.separator+srcFilename);
		int frameCount = d.getFrameCount();
		// ImageStack stack = null;
		for (int i = 0; i < frameCount; i++)
		{
			d.getFrame(i);
			d.getDelay(i);
		}

		int width_src = d.getFrame(0).getWidth();
		int height_src = d.getFrame(0).getHeight();
                double wR =(double)width/(double)width_src;
                double hR =(double)height/(double)height_src;

    GifEncoder encoder = new GifEncoder();
    encoder.setRepeat(d.loopCount);
    encoder.setDelay(d.delay);

    encoder.start(outPath + File.separator + outFilename);

    ImageProcessor ip = null;



    for (int i = 0; i < d.getFrameCount(); i++) {
            ip = d.getFrame(i);
            ip = ip.resize((int) (width_src * wR), (int) (height_src * hR));
            encoder.addFrame(new ImagePlus("t" + i, ip));
    }
    encoder.finish();

	}

	/**
	 * 复制文件
	 * @param srcFile
	 * @param destFile
	 */
	public static void copyFile(String srcFile, String destFile) {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			byte[] bytes = new byte[2000];
			int readCount = fis.read(bytes,0,bytes.length);
			while (readCount >0) {

				fos.write(bytes, 0, readCount);
				fos.flush();
                                readCount = fis.read(bytes,0,bytes.length);
			}
		} catch (Exception ee) {
                        ee.printStackTrace();
			System.out.println("hsolJava,ImageTool,copyFile,ee:" + ee);
		} finally {
			try {
				fos.close();
			} catch (Exception eee) {
			}
			try {
				fis.close();
			} catch (Exception eee) {
			}
		}

	}




        public static void main(String args[])
        {
          String srcfilePath = "d:/";
          String srcfileName = "1.jpg";
          String destPath    = srcfilePath;
          String destFilename ="w1"+srcfileName;
          String dest2Filename = "w2"+srcfileName;
          try
          {
            ImageUtil.dealImage(srcfilePath, srcfileName, destPath,
                                destFilename);
            ImageUtil.resizePhotoJpg(srcfilePath, destFilename, destPath,
                                dest2Filename,122,140);
//          ImageUtil.dealGif(srcfilePath, srcfileName,destPath,
//                        destFilename,122,140);
          }catch(Exception e)
          {
             System.out.println(e.getMessage());
          }
//

        }


}

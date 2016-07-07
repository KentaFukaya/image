package histgram;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class pgmadapter {

	public static void dapter(pgmImage pi) throws IOException{
	System.out.println(".png to .jpg start");
	BufferedImage im = new BufferedImage(pi.getHeight(),pi.getWidht(),BufferedImage.TYPE_BYTE_GRAY);
	WritableRaster raster = im.getRaster();
	System.out.println("Size          : height = " + pi.getHeight() + ", width = " + pi.getWidht());
	for(int h=0;h<pi.getHeight();h++){
	    for(int w=0;w<pi.getWidht();w++){
	        raster.setSample(h,w,0,pi.getrowdata()[w][h]); 
	    }
	}
	System.out.println(".png to .jpg stop");
	javax.imageio.ImageIO.write(im, "jpg", new File (pi.getFilePath().toString().substring(0, pi.getFilePath().toString().length()-4)+".jpg"));
	}
	
}
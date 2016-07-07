package histgram;

import java.io.IOException;

public class Binarization {
    
	public static pgmImage Binariz(pgmImage pi,double p){
		String newname = pi.getFileName().substring(0, pi.getFileName().length()-4);
		pgmImage binaraizimage = new pgmImage("/"+newname+"/"+newname+"_Binariz.pgm");
		histgram hg = new histgram(pi);
		int threshold = 0;//è‡’l
		float area = 0;
		float maxarea = pi.getHeight()*pi.getHeight();
		binaraizimage.setFileType("P5");
		binaraizimage.setHeight(pi.getHeight());
		binaraizimage.setWidth(pi.getWidht());
		binaraizimage.initroedata(pi.getWidht(),pi.getHeight());
		binaraizimage.setmaxbrightness(255);
		
		//è‡’l‚ğ‹‚ß‚é
		for(int i = 255 ; i > 0 ; i --){
			area += hg.gethist().get(i);
			System.out.format("i = %3d area/maxarea = %3f\n",i,area/maxarea);
			if((area/maxarea) >= p){
				threshold = i;
				break;
			}
		}
		
		System.out.println("–ÊÏ : "+area/maxarea*100+"% è‡’l :"+threshold);
		
		for(int h = 0 ; h < pi.getHeight() ; h++){
			for(int w = 0 ; w < pi.getWidht() ; w++){
				if(pi.getrowdata()[w][h] >= threshold) binaraizimage.setrowdata(w, h, 255);
				else binaraizimage.setrowdata(w, h, 0);
			}
		}
		//jpg ‚É•ÏŠ·
		try {
			pgmadapter.dapter(binaraizimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return binaraizimage;
	}
}

package histgram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class histgram {
	private List<Integer>hist = new ArrayList<>();
	private int maxcount ;
	
	public histgram(pgmImage li){
		//list‚Ì‰Šú‰»
		for(int i = 0 ; i < 256 ; i++)
			hist.add(0);
		for(int [] i : li.getrowdata()){
			for(int s : i){
				if(hist.get(maxcount) < hist.get(s)+1) maxcount = s;
				hist.set(s, hist.get(s)+1);
			}
		}

	}
	
	public List<Integer> gethist(){
		return this.hist;
	}
	public void showhist(){
		for(int i = 0 ; i < 256 ; i++)
			System.out.format("namber : %3d count : %6d\n ",i,hist.get(i));
		    System.out.format("\n namber : %3d max   : %6d\n",maxcount,hist.get(maxcount));
	}
	
	public pgmImage makehistgram(pgmImage pi){
		String newname = pi.getFileName().substring(0, pi.getFileName().length()-4);
		pgmImage histimage = new pgmImage("/"+newname+"/"+newname+"_histgram.pgm");
		
        histimage.setFileType("P5");
        histimage.setHeight(256);
        histimage.setWidth(256);
        histimage.initroedata(256, 256);
        histimage.setmaxbrightness(255);
        
		List<Integer>histpaercent = new ArrayList<>();
		int maxhist = hist.get(maxcount);
		
		for(int i = 0 ; i < 256 ; i++){
			histpaercent.add(hist.get(i)*200/maxhist);
			System.out.format("namber : %3d count : %3d\n ",i,histpaercent.get(i));
		}
		for(int w = 0 ; w < histimage.getWidht() ; w++){
		for(int h = 0 ; h < histimage.getHeight() ; h++){
				if(256- histpaercent.get(h) > w ) histimage.setrowdata(w, h, 0);
				else histimage.setrowdata(w, h, 255);
			}
		}
		try {
			pgmadapter.dapter(histimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return histimage;
	}

}

package histgram;


import java.nio.file.Path;
import java.nio.file.Paths;


public class pgmImage {
	private String filename;
	private Path filepath;
	private String filetype;
	private int width ,height;
	private int maxbrightness;
	private int [] [] rowdata;

	public pgmImage(String fileName){
		this.filename = fileName;

		//get()で相対パスの取得、toAbsolutePath()で絶対パスに変換
		this.filepath = Paths.get("src","histgram","images",filename).toAbsolutePath();
	}

	/*ゲッター*/
	public String getFileName(){
		return this.filename;
	}
	public Path getFilePath(){
		return this.filepath;
	}
	public String getFileType(){
		return this.filetype;
	}
	public int [] [] getrowdata(){
		return this.rowdata;
	}
	public int getWidht(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public int getmaxbrightness(){
		return this.maxbrightness;
	}
    /*セッター*/
	public void setFilePath(Path path){
		this.filepath = path;
	}
	public void setFileType(String ft){
		this.filetype = ft;
	}
	public void setFileName(String filename){
		this.filename = filename;
	}
	public void setWidth(int w){
		this.width = w;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public void setrowdata( int width, int height ,int data){
		rowdata [width] [height] = data;
	}
	public void setmaxbrightness(int i){
		this.maxbrightness = i;
	}
	
	
	//rowdataの初期化
	public void initroedata(int width, int height ){
		rowdata = new int [width] [height] ;//明度のデータを入れるためのやつ
	}
	
	//すべてのデータの表示
	public void showrowdata(){
		int count = 0;
		for(int[] i :rowdata){
			System.out.format("height : %3d ",count++);
			for(int s : i) {
				System.out.format("%3d ",s);
			}
			    System.out.println("");
		}
	}

	
}

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

		//get()�ő��΃p�X�̎擾�AtoAbsolutePath()�Ő�΃p�X�ɕϊ�
		this.filepath = Paths.get("src","histgram","images",filename).toAbsolutePath();
	}

	/*�Q�b�^�[*/
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
    /*�Z�b�^�[*/
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
	
	
	//rowdata�̏�����
	public void initroedata(int width, int height ){
		rowdata = new int [width] [height] ;//���x�̃f�[�^�����邽�߂̂��
	}
	
	//���ׂẴf�[�^�̕\��
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

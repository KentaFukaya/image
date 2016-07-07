package histgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// コンソール入力用
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputname = null;

		// ファイル一覧の取得
		Path filepath = Paths.get("src", "histgram", "images").toAbsolutePath();
		String[] filenames = new File(filepath.toString()).list(new FilenameFilter(){
			@Override
			public boolean accept(File dir,String name){
				return name.endsWith(".pgm");//.pgmで終わらないファイルは表示しない
			}
		}
		);
		
		//ファイル一覧の表示
		System.out.println("ファイル一覧表示 \nPath: " + filepath.toString()+"\\" );
		for (String filename : filenames)
			System.out.println(filename);

		// コンソールからファイル名を入力
		do{
			System.out.println("ファイル名を入力してください  ");
		    inputname = reader.readLine();
		    filepath = Paths.get("src", "histgram", "images", inputname).toAbsolutePath();
		}while(!filepath.toFile().exists());//入力されたファイル名が存在しなとき繰り返す
		
		// pgmのファイルを読み込む
		pgmImage pi1 = new pgmImage(inputname);
		dataImage.loaddata(pi1);
		pgmadapter.dapter(pi1);
		//pi1.showrowdata();
		//saedata テスト用
		/*
		pi1.setFileName("test2.pgm");
		pi1.setFilePath(Paths.get("src","histgram","images","test2.pgm").toAbsolutePath());
		dataImage.savedata(pi1);
		*/
		
		// コンソールから入力
		System.out.println("1:histgram  2:binaraiz");
		inputname = reader.readLine();
		
		if (inputname.equals("1")) {
			// histgramのコンストラ歌
			histgram hg1 = new histgram(pi1);
			// histgramのセーブ
			dataImage.savedata(hg1.makehistgram(pi1));
		}else {
			  System.out.println("%を入力してください  0 ~ 100 (%)");
			  dataImage.savedata(Binarization.Binariz(pi1, new Scanner(System.in).nextDouble()/100));
		}
	}
}

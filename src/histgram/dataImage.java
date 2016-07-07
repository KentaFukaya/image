package histgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class dataImage {
	public static void loaddata(pgmImage pi) throws NumberFormatException, IOException {
		System.out.println("ファイル読み込み開始 ");
		System.out.println("------------------------------------------------------------------------------ ");
		System.out.println("FileName      : " + pi.getFileName() + " " + pi.getFilePath().isAbsolute());
		System.out.println("FilePath      : " + pi.getFilePath());

		// ファイル読み込み
		FileInputStream fis = new FileInputStream(pi.getFilePath().toString());
		// ファイルのデータ保存用
		int line;
		StringBuilder sb = new StringBuilder();

		int count = 0;// 改行の数を数える変数
		int h = 0, w = 0;// 配列rowdataのカウント用

		while ((line = fis.read()) != -1) {
			if (count < 3 && (char) line == '\n') {
				// 最初の改行のとき
				if (count == 0) {
					pi.setFileType(sb.toString());
					System.out.println("FileType      : " + pi.getFileType());
				} else if (count == 1) {// 一回の改行のとき
					String[] size = sb.toString().split(" ");
					pi.setHeight(Integer.valueOf(size[0]).intValue());
					pi.setWidth(Integer.valueOf(size[1]).intValue());
					System.out.println("Size          : height = " + pi.getHeight() + ", width = " + pi.getWidht());
					pi.initroedata(pi.getWidht() , pi.getHeight());
				} else if (count == 2) {
					pi.setmaxbrightness(Integer.valueOf(sb.toString()).intValue());
					System.out.println("maxBrightness : " + pi.getmaxbrightness());
				}
				sb = new StringBuilder();
				count++;
			} else {
				if (count < 3) {
					sb.append((char) line);// 形式のとき
				} else {// 明度データのとき
					pi.setrowdata(w,h, line);
					h++;
					if (h >= pi.getHeight()) {
						if ((w % 50 == 0) || (w == pi.getHeight() - 1))
							System.out.println("rowdata       : height = " + h + ", width = " + w);
						w++;
						h = 0;

					}
				}
			}
		}

		System.out.println("------------------------------------------------------------------------------ ");
		System.out.println("ファイル読み込み終了");

		fis.close();
	}

	public static void savedata(pgmImage pi) throws IOException {
		String newfilename = null;
		Path newfilepath = null;
		System.out.println("Filesave開始");
		System.out.println("------------------------------------------------------------------------------ ");

		// 拡張しを削除したfalenameを保存
		newfilename = pi.getFileName();
		newfilepath = Paths.get("src", "histgram", "images", newfilename).toAbsolutePath();
		System.out.println("newFIleName   : " + newfilename);
		System.out.println("newFIlePaht   : " + newfilepath.toString());

		// 新しいファイルの作成
		File file = new File(newfilepath.toString());
		if (!file.createNewFile()) {
			System.out.println(newfilename + " は既に存在します");
			file.delete();
			System.out.println(newfilename + " を削除します");
		}
		file.createNewFile();
		System.out.println(newfilename + " を作成しました　");

		/* 最初の三行の記述 */
		// ファイル読み込み
		FileWriter fw = new FileWriter(newfilepath.toString());
		// 1行目
		System.out.println("FileType      : " + pi.getFileType());
		fw.write(pi.getFileType());
		fw.write('\n');
		// 2行目
		System.out.println("Size          : height = " + pi.getHeight() + ", width = " + pi.getWidht());
		fw.write(Integer.toString(pi.getHeight()));
		fw.write(' ');
		fw.write(Integer.toString(pi.getWidht()));
		fw.write('\n');
		// 3行目
		System.out.println("maxBrightness : " + pi.getmaxbrightness());
		fw.write(Integer.toString(pi.getmaxbrightness()));
		fw.write('\n');
		fw.flush();
		fw.close();

		System.out.println("三行書き込み完了");
		FileOutputStream fos = new FileOutputStream(newfilepath.toString(), true);

		// dataの部分
	    for(int w=0;w<pi.getWidht();w++){
		for(int h=0;h<pi.getHeight();h++){
				fos.write(pi.getrowdata()[w][h]);
		    }
		}

		System.out.println("rowdata書き込み完了");

		fos.flush();
		fos.close();

		System.out.println("------------------------------------------------------------------------------ ");
		System.out.println("Filesave終了\n");
	}
}

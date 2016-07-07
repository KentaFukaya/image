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
		System.out.println("�t�@�C���ǂݍ��݊J�n ");
		System.out.println("------------------------------------------------------------------------------ ");
		System.out.println("FileName      : " + pi.getFileName() + " " + pi.getFilePath().isAbsolute());
		System.out.println("FilePath      : " + pi.getFilePath());

		// �t�@�C���ǂݍ���
		FileInputStream fis = new FileInputStream(pi.getFilePath().toString());
		// �t�@�C���̃f�[�^�ۑ��p
		int line;
		StringBuilder sb = new StringBuilder();

		int count = 0;// ���s�̐��𐔂���ϐ�
		int h = 0, w = 0;// �z��rowdata�̃J�E���g�p

		while ((line = fis.read()) != -1) {
			if (count < 3 && (char) line == '\n') {
				// �ŏ��̉��s�̂Ƃ�
				if (count == 0) {
					pi.setFileType(sb.toString());
					System.out.println("FileType      : " + pi.getFileType());
				} else if (count == 1) {// ���̉��s�̂Ƃ�
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
					sb.append((char) line);// �`���̂Ƃ�
				} else {// ���x�f�[�^�̂Ƃ�
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
		System.out.println("�t�@�C���ǂݍ��ݏI��");

		fis.close();
	}

	public static void savedata(pgmImage pi) throws IOException {
		String newfilename = null;
		Path newfilepath = null;
		System.out.println("Filesave�J�n");
		System.out.println("------------------------------------------------------------------------------ ");

		// �g�������폜����falename��ۑ�
		newfilename = pi.getFileName();
		newfilepath = Paths.get("src", "histgram", "images", newfilename).toAbsolutePath();
		System.out.println("newFIleName   : " + newfilename);
		System.out.println("newFIlePaht   : " + newfilepath.toString());

		// �V�����t�@�C���̍쐬
		File file = new File(newfilepath.toString());
		if (!file.createNewFile()) {
			System.out.println(newfilename + " �͊��ɑ��݂��܂�");
			file.delete();
			System.out.println(newfilename + " ���폜���܂�");
		}
		file.createNewFile();
		System.out.println(newfilename + " ���쐬���܂����@");

		/* �ŏ��̎O�s�̋L�q */
		// �t�@�C���ǂݍ���
		FileWriter fw = new FileWriter(newfilepath.toString());
		// 1�s��
		System.out.println("FileType      : " + pi.getFileType());
		fw.write(pi.getFileType());
		fw.write('\n');
		// 2�s��
		System.out.println("Size          : height = " + pi.getHeight() + ", width = " + pi.getWidht());
		fw.write(Integer.toString(pi.getHeight()));
		fw.write(' ');
		fw.write(Integer.toString(pi.getWidht()));
		fw.write('\n');
		// 3�s��
		System.out.println("maxBrightness : " + pi.getmaxbrightness());
		fw.write(Integer.toString(pi.getmaxbrightness()));
		fw.write('\n');
		fw.flush();
		fw.close();

		System.out.println("�O�s�������݊���");
		FileOutputStream fos = new FileOutputStream(newfilepath.toString(), true);

		// data�̕���
	    for(int w=0;w<pi.getWidht();w++){
		for(int h=0;h<pi.getHeight();h++){
				fos.write(pi.getrowdata()[w][h]);
		    }
		}

		System.out.println("rowdata�������݊���");

		fos.flush();
		fos.close();

		System.out.println("------------------------------------------------------------------------------ ");
		System.out.println("Filesave�I��\n");
	}
}

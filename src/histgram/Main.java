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
		// �R���\�[�����͗p
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputname = null;

		// �t�@�C���ꗗ�̎擾
		Path filepath = Paths.get("src", "histgram", "images").toAbsolutePath();
		String[] filenames = new File(filepath.toString()).list(new FilenameFilter(){
			@Override
			public boolean accept(File dir,String name){
				return name.endsWith(".pgm");//.pgm�ŏI���Ȃ��t�@�C���͕\�����Ȃ�
			}
		}
		);
		
		//�t�@�C���ꗗ�̕\��
		System.out.println("�t�@�C���ꗗ�\�� \nPath: " + filepath.toString()+"\\" );
		for (String filename : filenames)
			System.out.println(filename);

		// �R���\�[������t�@�C���������
		do{
			System.out.println("�t�@�C��������͂��Ă�������  ");
		    inputname = reader.readLine();
		    filepath = Paths.get("src", "histgram", "images", inputname).toAbsolutePath();
		}while(!filepath.toFile().exists());//���͂��ꂽ�t�@�C���������݂��ȂƂ��J��Ԃ�
		
		// pgm�̃t�@�C����ǂݍ���
		pgmImage pi1 = new pgmImage(inputname);
		dataImage.loaddata(pi1);
		pgmadapter.dapter(pi1);
		//pi1.showrowdata();
		//saedata �e�X�g�p
		/*
		pi1.setFileName("test2.pgm");
		pi1.setFilePath(Paths.get("src","histgram","images","test2.pgm").toAbsolutePath());
		dataImage.savedata(pi1);
		*/
		
		// �R���\�[���������
		System.out.println("1:histgram  2:binaraiz");
		inputname = reader.readLine();
		
		if (inputname.equals("1")) {
			// histgram�̃R���X�g����
			histgram hg1 = new histgram(pi1);
			// histgram�̃Z�[�u
			dataImage.savedata(hg1.makehistgram(pi1));
		}else {
			  System.out.println("%����͂��Ă�������  0 ~ 100 (%)");
			  dataImage.savedata(Binarization.Binariz(pi1, new Scanner(System.in).nextDouble()/100));
		}
	}
}

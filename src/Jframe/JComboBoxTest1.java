package Jframe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JComboBoxTest1 extends JFrame implements ItemListener {
	JLabel iconlabel;
	JComboBox<String> combo;
	String name = "";

	Path filepath = Paths.get("src", "histgram", "images").toAbsolutePath();// 見たい部分のpath

	public static void main(String[] args) {
		JComboBoxTest1 frame = new JComboBoxTest1();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ×マークを押したら、プログラムを終了する
		frame.setSize(800, 800);// サイズの指定
		frame.setLocationRelativeTo(null);// 起動時に中央に配置
		frame.setTitle("タイトル");// タイトルの指定
		frame.setVisible(true);// 表示

	}

	JComboBoxTest1() {

		/* ファイル名の配列を取得 */
		Path filepath = Paths.get("src", "histgram", "images").toAbsolutePath();// 見たい部分のpath
		String[] filenames = new File(filepath.toString()).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".pgm");// .pgmで終わらないファイルは表示しない
			}
		});

		combo = new JComboBox<>();// comboboxのコンストラクタ

		/* comboに名前を詰める */
		combo.addItem("--------");// からの選択肢の挿入
		for (String filename : filenames) {
			combo.addItem(filename);// ファイル名の挿入
		}

		combo.setPreferredSize(new Dimension(180, 40));// comboboxのサイズ指定
		combo.setMaximumRowCount(5);// comboboxの一覧表示可能数の設定
		combo.addItemListener(this);

		ImageIcon icon = new ImageIcon(filepath.toString() + name);
		icon.getImage().getScaledInstance((int) 200,200,
		        Image.SCALE_SMOOTH);
		iconlabel = new JLabel(icon);
		JPanel p = new JPanel();

		p.add(new JLabel("file:"));
		p.add(combo);

		p.add(iconlabel);

		getContentPane().add(p, BorderLayout.WEST);
	}

	// クリックリスナー
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			name = (String) combo.getSelectedItem();
			name = "/" + name.substring(0, name.length() - 4) + ".jpg";
			iconlabel.setIcon(new ImageIcon(filepath.toString() + name));
		}
	}
}

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

	Path filepath = Paths.get("src", "histgram", "images").toAbsolutePath();// ������������path

	public static void main(String[] args) {
		JComboBoxTest1 frame = new JComboBoxTest1();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �~�}�[�N����������A�v���O�������I������
		frame.setSize(800, 800);// �T�C�Y�̎w��
		frame.setLocationRelativeTo(null);// �N�����ɒ����ɔz�u
		frame.setTitle("�^�C�g��");// �^�C�g���̎w��
		frame.setVisible(true);// �\��

	}

	JComboBoxTest1() {

		/* �t�@�C�����̔z����擾 */
		Path filepath = Paths.get("src", "histgram", "images").toAbsolutePath();// ������������path
		String[] filenames = new File(filepath.toString()).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".pgm");// .pgm�ŏI���Ȃ��t�@�C���͕\�����Ȃ�
			}
		});

		combo = new JComboBox<>();// combobox�̃R���X�g���N�^

		/* combo�ɖ��O���l�߂� */
		combo.addItem("--------");// ����̑I�����̑}��
		for (String filename : filenames) {
			combo.addItem(filename);// �t�@�C�����̑}��
		}

		combo.setPreferredSize(new Dimension(180, 40));// combobox�̃T�C�Y�w��
		combo.setMaximumRowCount(5);// combobox�̈ꗗ�\���\���̐ݒ�
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

	// �N���b�N���X�i�[
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			name = (String) combo.getSelectedItem();
			name = "/" + name.substring(0, name.length() - 4) + ".jpg";
			iconlabel.setIcon(new ImageIcon(filepath.toString() + name));
		}
	}
}

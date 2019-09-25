package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import main.common;
import main.FileProfessor;
public class mainFrm extends JFrame {
	JTextArea txtrMain;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrm frame = new mainFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("字符数");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number;
				JFileChooser jf = new JFileChooser();
				int result = jf.showSaveDialog(null);
				File selectedFile = jf.getSelectedFile();
				String encoding = "GBK";
				
				InputStreamReader readFile;
				try {
					readFile = new InputStreamReader(new FileInputStream(selectedFile),encoding);
					BufferedReader fileContent = new BufferedReader(readFile);
					number = FileProfessor.countChars(fileContent);
					txtrMain.append("字符数: "+ number);
					}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("词数");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number;
				JFileChooser jf = new JFileChooser();
				int result = jf.showSaveDialog(null);
				File selectedFile = jf.getSelectedFile();
				String encoding = "GBK";
				
				InputStreamReader readFile;
				try {
					readFile = new InputStreamReader(new FileInputStream(selectedFile),encoding);
					BufferedReader fileContent = new BufferedReader(readFile);
					number = FileProfessor.countWords(fileContent);
					txtrMain.append("词数: "+ number);
					}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("行数");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number;
				JFileChooser jf = new JFileChooser();
				int result = jf.showSaveDialog(null);
				File selectedFile = jf.getSelectedFile();
				String encoding = "GBK";
				
				InputStreamReader readFile;
				try {
					readFile = new InputStreamReader(new FileInputStream(selectedFile),encoding);
					BufferedReader fileContent = new BufferedReader(readFile);
					number = FileProfessor.countLines(fileContent);
					txtrMain.append("行数: "+ number);
					}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("-a");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number;
				ArrayList tempList;
				InputStreamReader readFile;
				String encoding = "GBK";
				JFileChooser jf = new JFileChooser();
				int result = jf.showSaveDialog(null);
				File selectedFile = jf.getSelectedFile();
				try {
					readFile = new InputStreamReader(new FileInputStream(selectedFile),encoding);
					BufferedReader fileContent = new BufferedReader(readFile);
					tempList = FileProfessor.countSpecial(fileContent);
					txtrMain.append("空行数： "+ tempList.get(0));
					txtrMain.append("代码行数： "+ tempList.get(1));
					txtrMain.append("注释行数： "+ tempList.get(2));
					}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		txtrMain = new JTextArea();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrMain, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnNewButton_1)
							.addGap(18)
							.addComponent(btnNewButton_2)
							.addGap(10)
							.addComponent(btnNewButton_3)))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addGap(18)
					.addComponent(txtrMain, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import view.mainFrm;

public class FileProfessor {
	JFrame mainFrm;
	public  void operateByCommand(String[] arr, int len,int start, String fileName) {
		if(arr[0].equals("-x")) {
			mainFrm = new mainFrm();
			mainFrm.setVisible(true);
		}else {
			try {
				for(int i = start;i < len -1; i++) {
					String encoding = "GBK";
					File file = new File(fileName);
					InputStreamReader readFile = new InputStreamReader(new FileInputStream(file),encoding);
					BufferedReader fileContent = new BufferedReader(readFile);
					switch(arr[i]) {
						case "-c":
							countChars(fileContent);
							break;
						case "-w":
							countWords(fileContent);
							break;
						case "-l":
							countLines(fileContent);
							break;
						case "-a":
							countSpecial(fileContent);
							break;
						case "-s": 
							recursiveProcessing(arr);
							break;
						default:
							System.out.println("输入的指令不正确");
					}
				}
			}catch(Exception e) {
				System.out.println("找不到文件");
				e.printStackTrace();
			}
		}
	
	}
	public static int countChars(BufferedReader fileContent) throws IOException{
		String lineContent = null;
		int charNum = 0;
		while((lineContent = fileContent.readLine())!= null) {
			lineContent = lineContent.trim();
			for(int i = 0; i < lineContent.length(); i++) {
				char ch = lineContent.charAt(i);
				if(ch != '\n' && ch != '\t' && ch != ' ' ) {
					charNum++;
				}	
			}
		}
		System.out.println("字符数: " + charNum);
		return charNum;
	}
	public static int countWords(BufferedReader fileContent) throws IOException{
		String REGEX = "[a-zA-Z]+\\b";
		Pattern pattern = Pattern.compile(REGEX);
		String lineContent = null;
		int wordNum = 0;
		while((lineContent = fileContent.readLine()) != null) {
			lineContent = lineContent.trim();
			Matcher matcher = pattern.matcher(lineContent);
			while(matcher.find()) {
				wordNum++;
			}
		}
		System.out.println("词数: " + wordNum);
		return wordNum;
	}
	public static int countLines(BufferedReader fileContent) throws IOException{
		String lineContent = null;
		int lineNum = 0;
		while((lineContent = fileContent.readLine())!= null) {
			lineNum ++;
		}
		System.out.println("行数: " + lineNum);
		return lineNum;
	}
	public static ArrayList<Integer> countSpecial (BufferedReader fileContent) throws IOException{
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		String lineContent = null; 
		boolean isComment = false; 

		int codeLineNum = 0; 
		int blankLineNum = 0; 
		int annotationLineNum = 0; 
		
		while((lineContent = fileContent.readLine()) != null) {
			if(lineContent.contains("/*")) {
				annotationLineNum++;
				isComment = false;
			}else if(isComment) {
				annotationLineNum++;
				if(lineContent.contains("*/")) {
					isComment = false;
				}
			}else if(lineContent.contains("//")) {
				annotationLineNum++;
			}else if(lineContent.trim().length() > 1) {
				codeLineNum++;
			}else {
				blankLineNum++;
			}
		}
		System.out.println("空行数：" + blankLineNum);
		System.out.println("代码行数：" + codeLineNum);
		System.out.println("注释行数：" + annotationLineNum);
		resultList.add(blankLineNum);
		resultList.add(codeLineNum);
		resultList.add(annotationLineNum);
		return resultList;
	}
	public static void recursiveProcessing(String[] arr)throws IOException{
		String fileDir = arr[arr.length-1].substring(0,arr[arr.length-1].lastIndexOf("\\"));
		String fileFilter = arr[arr.length-1].substring(arr[arr.length-1].lastIndexOf("."));
 	
        List<File> fileList = new ArrayList<File>();
        File file = new File(fileDir);// 指定查找目录
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return;
        }
     // 遍历files中的所有文件
        for (File f : files) {
            if (f.isFile()&&f.getName().endsWith(fileFilter)) {
                fileList.add(f);
                //System.out.println(f.getName());
            }
        }
        for (File f1 : fileList) {
			System.out.println(f1.getName());
			for(int i = 0;i < 4;i++){
				/*原来发现不加这个循环，四个方法只有统计字符有结果，我觉得是BufferedReader的readLine()方法
				一直读取下一行的原因，所以增加了一个内循环，每次都重新生成新的BufferedReader，四个方法都能得到结果。*/
				String encoding = "GBK";
				InputStreamReader readFile = new InputStreamReader(new FileInputStream(f1),encoding);
				BufferedReader fileContent = new BufferedReader(readFile);
				switch(i){
					case 0:
						countChars(fileContent);
						break;
					case 1:
						countWords(fileContent);
						break;
					case 2:
						countLines(fileContent);
						break;
					case 3:
						countSpecial(fileContent);
						break;
				}
			}
			//画个分界线看起来清楚些
			System.out.println("-----------------------------");
        }
	}
}

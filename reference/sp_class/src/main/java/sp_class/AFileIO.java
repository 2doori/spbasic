package sp_class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * INPUT 폴더 하위에 위치한 파일들의 파일명(상대경로 포함) console에 출력
 * 
 * 파일 크리가 3kbyte가 넘는 파일들은 모두 OUTPUT 폴더로 복사 
 * 경로를 별도 복사 "D:\\교육\\sp기본\\1.INPUT"
 * project내에 포함될 경우  ".\\Input"
 * 
 * 
 * 
 *     	File directory = new File(path);
 *      File[] fList = directory.listFiles()   //file liset > listFiles()
 *      
 *      file.isDirectory() // boolean
 *      
 *      
 *      InputStream inputStream = new FileInputStream(inputFile);
 *      OutputStream outputStream = new FileOutputStream(outputFile);
 *      inputStream.close();  //입력 stream close
 *      outputStream.close();
 *      
 *      
 *      
 *      file 입력 
 *      FileReader fileReader = new FileReader(fileName); //입력 스트림
 *      BufferedReader bufferedReader = new BufferedReader(fileReader);
 *      bufferedReader.close();   //버퍼리더 닫기 
 *      
 * 
 */

public class AFileIO {

	// static String rootPath = ".\\Input";

	static String rootPath = "D:\\교육\\sp기본\\1.INPUT";
	static String filePath = "D:\\교육\\sp기본\\1.INPUT\\INPUT\\DOC\\TXT\\MyAll.txt";

	public static void main(String[] args) {
		//input > output으로 복사 
		FileSearchAll(rootPath);
		
		//txt 내용 console 출력 
		PrintFile(filePath);

		System.out.println(rootPath + " : " + rootPath.substring(rootPath.length()) + " : " + rootPath.length());
	}

	public static void FileSearchAll(String path) {
		File directory = new File(path);
		File[] fList = directory.listFiles();

		for (File file : fList) {
			if (file.isDirectory()) {
				FileSearchAll(file.getPath());
			} else {
				String partPath = path.substring(rootPath.length());
				System.out.println("." + partPath + "\\" + file.getName());
				if (file.length() > 3 * 1024) {
					MyCopyFile(partPath, file.getName());
				}
			}
		}
	}

	//File read & write - Stream
	public static void MyCopyFile(String partPath, String filename) {
		final int BUFFER_SIZE = 512;
		
		int readLen;
		try {
			// Create Folder
			// File destFolder = new File(".\\OUTPUT" + partPath);
			File destFolder = new File("D:\\교육\\sp기본\\1.INPUT\\OUTPUT" + partPath);
			if (!destFolder.exists()) {
				destFolder.mkdirs();
			}

			// Copy File
			/*
			 * InputStream inputStream = new
			 * FileInputStream(".\\INPUT"+partPath+"\\"+filename); OutputStream outputStream
			 * = new FileOutputStream(".\\OUTPUT"+partPath+"\\"+filename);
			 */

			InputStream inputStream = new FileInputStream("D:\\교육\\sp기본\\1.INPUT" + partPath + "\\" + filename);
			OutputStream outputStream = new FileOutputStream(
					"D:\\교육\\sp기본\\1.INPUT\\OUTPUT" + partPath + "\\" + filename);

			byte[] buffer = new byte[BUFFER_SIZE];

			while ((readLen = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readLen);
			}

			inputStream.close();  //입력 stream close
			outputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// File read & print
	public static void PrintFile(String fileName ) 
    {
    	String line = null;
    	try {
    		FileReader fileReader = new FileReader(fileName); //입력 스트림
    		BufferedReader bufferedReader = new BufferedReader(fileReader);
    		
    		while((line = bufferedReader.readLine()) != null) { //버퍼로부터 한 줄씩 입력
    			System.out.println(line);
    		}
    		bufferedReader.close();   //버퍼리더 닫기 
    		
    	}
    	catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
    	
    	
    	
    }
	

	

}

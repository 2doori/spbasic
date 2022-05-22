package sp_class;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *  6.네트워크 soket 실습 1
 *  서버에 접속하여, 파일을 전송하는 프로그램을 생성 
 *  client는 파일 전송 완료 후 종료
 *  server는 파일을 수신 완료 후 종료
 *  Server는 'QUIT' 입력 을 받으면 종료 
 * 
 * 
 * Socket socket = null;
 * DataOutputStream os = null;
 * 
 * socket = new Socket("127.0.0.1", 27015);
 * os = new DataOutputStream(socket.getOutputStream());
 * 
 * 
 * directory 및 file list
 * File directory = new File("./6.socket/6.ClientFiles");
 * File[] fList = directory.listFiles();
 * for (File file : fList) {
 * 
 *  //무조건 close 
 * finally {
 * 	if (os != null) { os.close(); }
 * 	if (socket != null) { socket.close(); }	
 * }
 * 
 */

public class FFileClient {
    public static void main(String[] args) throws IOException {
		Socket socket = null;
		DataOutputStream os = null;
		try {
			socket = new Socket("127.0.0.1", 27015);
			os = new DataOutputStream(socket.getOutputStream());
			
			byte[] buffer = new byte[4096];
			int length;
			
			// get all the files from a directory
			//File directory = new File("./ClientFiles");
			//File directory = new File("D:\\교육\\sp기본\\6.socket\\ClientFiles");

			File directory = new File("./6.socket/6.ClientFiles");
			File[] fList = directory.listFiles();
			for (File file : fList) {
				if (file.isFile()) {
					// 파일이름 전송 
					os.writeUTF(file.getName());
					// 파일크기 전송
					os.writeInt((int) file.length());
					
					FileInputStream is = null;
					try {
						is = new FileInputStream(file.getPath());
						while ((length = is.read(buffer)) != -1) {
							// 파일내용 전송
							os.write(buffer, 0, length);
						}
					} finally {
						if (is != null) { is.close(); }
					}	
				}
			}
			System.out.println("Sent All Files.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) { os.close(); }
			if (socket != null) { socket.close(); }
		}
    }   
}
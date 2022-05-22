package sp_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * 6.네트워크 soket 실습 2
 * Dataserver을 먼저 실행 
 * 
 * 
 * Socket s = new Socket("127.0.0.1", 9090);
 * s.getInputStream() // socket에서 받은 값을 출력 
 * 
 * BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
 * 
 * 
 */


public class FDateClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 9090);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);   //날짜 시간 return
    }
}
package sp_class;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


/*
 * 6.네트워크 soket 실습 1
 * 
 * clinet에서 server에 접속하면 server는 현재 날짜와 시각을 client로 전송하고 
 * client 는 전송 받은 값을 출력하시오  
 *
 * //서버
 * ServerSocket listener = new ServerSocket(9090); //9090포트로 socket 선언 및 대기 
 *
 *try {
            while (true) {
                Socket socket = listener.accept(); //clinet가 응답올때까지 대기후 오는 순간 socket에 할당
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
 * 
 * 
 * 
 * //날짜 DATE
 *  Date().toString()
 * 
 */

public class FDateServer {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                Socket socket = listener.accept(); //clinet가 응답올때까지 대기후 오는 순간 socket에 할당
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString() + "Test0");
                    out.println("TEST1");
                    out.println("TEST2");
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }
}
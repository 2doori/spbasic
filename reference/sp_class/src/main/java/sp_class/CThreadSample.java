package sp_class;



/*
 * 3.process 실습
 * 
 * Thread 2개를 만든 후 , main 함수와 Thread2개에서 동시에 0부터 9까지 어디서 출력하였는지 구분 할 수 있게 숫자 앞에서 
 * main thread1 thread2 표시 하시오 
 * 
 * 
 * class ThreadClass extends Thread { // 'Thread' Class를 상속
 * 
 */

class ThreadClass extends Thread { // 'Thread' Class를 상속받는다 
    String thread_name; 
    public ThreadClass(String name) { 
        this.thread_name = name; 
    } 

    public void run() { 
        for(int i=0; i<10; i++) { 
            System.out.println(thread_name+i); 
            try { 
                sleep(10); 
            } catch (InterruptedException e) { e.printStackTrace(); } 
        } 
    } 
} 

public class CThreadSample { 
    public static void main(String[] args) throws InterruptedException { 
        ThreadClass tc1 = new ThreadClass("[Thread1] ");
        ThreadClass tc2 = new ThreadClass("[Thread2] ");
        tc1.start();  //실행
        tc2.start();

        for (int i=0; i<10; i++)
        {
        	System.out.println("[Main] "+i);
        	Thread.sleep(10);  // 현재 실행 중인 스레드가 지정된 밀리초 동안 휴면(일시적으로 실행 중지)됨
        }
        
        tc1.join(); //이 스레드가 죽을 때까지 대기
        tc2.join();
    } 
}
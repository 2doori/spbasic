package sp_class;

import java.util.HashMap;
import java.util.Scanner;


/*
 * 2.2 QUEUE 실습 1
 * 
 * CREATE Q1 2  //입력
 * Queue Created  //출력
 * ENQUEUE Q1 MSG1_1
 * Enqueued
 * ENQUEUE Q1 MSG1_2
 * Enqueued
 * CREATE Q2 5
 * Queue Created
 * ENQUEUE Q2 MSG2_1
 * Enqueued
 * ENQUEUE Q2 MSG2_2
 * Enqueued
 * ENQUEUE Q2 MSG2_3
 * Enqueued
 * ENQUEUE Q2 MSG2_4
 * Enqueued
 * DEQUEUE Q2
 * MSG2_1(0)
 * DEQUEUE Q2
 * MSG2_2(1)
 * GET Q1
 * MSG1_1(0)
 * GET Q1
 * MSG1_2(1)
 * GET Q1
 * No Msg
 * ENQUEUE Q1 MSG1_3
 * Queue Full
 * SET Q1 1
 * Msg Set
 * GET Q1
 * MSG1_2(1)
 * DEL Q1 1
 * Deleted
 * 
 * 
 * 
 * QUEUE 선언
 * queues = new HashMap<String, MsgQueue>();
 * MsgQueue >> class 별도 생성 
 * 
 * 
 * HashMap<K, V> m = new HashMap<K, V>();
 * m.put(key, value);
 * m.get(key)   //value
 * 
 * if (m.containsKey(key))  // true 같은 키값의 "key"이 있어서 
 * 
 * 
 * 
 * 
 * 
 * console 입력 
 * Scanner sc = new Scanner(System.in);  // CREATE Q1 2
 * String line = sc.nextLine();    
 * String[] words = line.split(" "); 
 * String command = words[0];        	// CREATE
 * String qname = words[1];				// Q1
 * 
 */

public class BQueuesMain {
	static HashMap<String, BMsgQueue> queues;
	
	public static void main(String[] args) {
        // Queue Name - Id - (size, status, msg)
        queues = new HashMap<String, BMsgQueue>();
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            String line = sc.nextLine();
            String[] words = line.split(" ");
            String command = words[0];
            String qname = words[1];
            switch(command)
            {
                case "CREATE":
                    System.out.println(QCreate(qname, Integer.parseInt(words[2])));
                    break;
                case "ENQUEUE":
                    String message = words[2];
                    System.out.println(QEnqueue(qname, message));
                    break;
                case "DEQUEUE":
                    System.out.println(QDequeue(qname));
                    break;
                case "GET":
                    System.out.println(QGet(qname));
                    break;
                case "SET":
                    System.out.println(QSet(qname, Integer.parseInt(words[2])));
                    break;
                case "DEL":
                    System.out.println(QDel(qname, Integer.parseInt(words[2])));
                    break;
                default:
                    break;
            }
        }
	}

    static String QCreate(String name, int size)
    {
        if (queues.containsKey(name))
            return "Queue Exist";

        BMsgQueue q = new BMsgQueue(size);

        queues.put(name, q);

        return "Queue Created";
    }

    static String QEnqueue(String name, String msg)
    {    	 
        return queues.get(name).MsgEnqueue(msg);
    }

    static String QDequeue(String name)
    {
        return queues.get(name).MsgDequeue(); 
    }

    static String QGet(String name)
    {
        return queues.get(name).MsgGet();
    }

    static String QSet(String name, int id)
    {
        return queues.get(name).MsgSet(id);
    }
    static String QDel(String name, int id)
    {
        return queues.get(name).MsgDel(id);
    }	
}

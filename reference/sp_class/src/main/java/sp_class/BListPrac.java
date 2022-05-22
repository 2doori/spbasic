package sp_class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/*
 * 2.1 List 실습
 * 
 * 		국어 영어 수학
 * Kim	80	70	90
 * Lee	95	89	92
 * Park	88	70	94
 * Choi	70	100	92
 * Moon	100	80	93
 * Rho	62	99	88
 * Byun	71	98	77
 * Kang	62	73	81
 * Cho	99	85	66
 * Hong	62	70	92
 * 
 * 1. console.에 PRINT 입력시 이름순 (오름차순)으로 출력
 * 2. KOREAN, ENGLISH, MATH 입력시 성적순 (내림차순) - 동일시 오름차순 정렬 
 * 
 *  ArrayList<Grade> al = new ArrayList<Grade>(); 	// 선언
 *  al.add(g);										// 추가
 *  al.remove(index);
 * 
 * 
 * 
 * // console 입력 input 
 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
 * String strInput = br.readLine(); //입력 후 enter
 * 
 * 
 * 
 */

public class BListPrac {
    public static void main(String[] args) throws IOException {
        ArrayList<Grade> al = new ArrayList<Grade>();
       
        try {
            ////////////////////////////////////////////////////////////////
        	// BufferedReader in = new BufferedReader(new FileReader("List_Sample.txt"));
            BufferedReader in = new BufferedReader(new FileReader("D:\\교육\\sp기본\\2.list_map_queue\\List_Sample.txt"));
            String str;

            while ((str = in.readLine()) != null) {  //한 line 씩 읽음
                String words[] = str.split("\t");
                Grade g = new Grade(words[0],Integer.parseInt(words[1]),Integer.parseInt(words[2]),Integer.parseInt(words[3]));
                al.add(g);
                //al.remove(0);
            }
            in.close();
            ////////////////////////////////////////////////////////////////
        } catch (IOException e) {
            System.err.println(e); // 에러가 있다면 메시지 출력
            System.exit(1);
        }
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String strInput = br.readLine();
           
            switch(strInput) {
            case "PRINT": // 이름 순 출력
                Collections.sort(al, (g1, g2) -> g1.getName().compareTo(g2.getName()));
                break;
            case "KOREAN": // 국어 성적 순 출력
                // Lambda식
                Collections.sort(al, (g1, g2) -> (g2.getKorean() - g1.getKorean()) == 0 ? g1.getName().compareTo(g2.getName()) : g2.getKorean() - g1.getKorean());                
                break;
            case "ENGLISH": // 영어 성적 순 출력
                // Comparator
                Collections.sort(al, new Comparator<Grade>() {

                    @Override
                    public int compare(Grade x, Grade y) {
                        if (y.getEnglish() - x.getEnglish() == 0)
                        {
                            return x.getName().compareTo(y.getName());
                        }
                        else
                        {
                            return y.getEnglish() - x.getEnglish();
                        }
                    }
                   
                });                
                break;
            case "MATH":
                // Comparator
                Collections.sort(al, new sortByMath());                
                break;
			case "QUIT":
				return;
			default:
				break;
            }
           
            for (Grade val : al)
            {
                System.out.println(String.format("%s\t%d\t%d\t%d",val.getName(), val.getKorean(), val.getEnglish(), val.getMath()));
            }                
        }  
    }  
}

// getter setter 

class Grade
{
    private String strName;
    private int Korean;
    private int English;
    private int Math;

    public Grade(String str, int k, int e, int m)
    {
        strName = str;
        Korean = k;
        English = e;
        Math = m;
    }

    public String getName()
    {
        return strName;
    }
    public void setName(String strName)
    {
        this.strName = strName;
    }
    public int getKorean()
    {
        return Korean;
    }
    public void setProjectA(int n)
    {
        Korean = n;
    }
    public int getEnglish()
    {
        return English;
    }
    public void setProjectB(int n)
    {
        English = n;
    }
    public int getMath()
    {
        return Math;
    }
    public void setMath(int n)
    {
        Math = n;
    }
}

class sortByMath implements Comparator<Grade>{
    public int compare(Grade x, Grade y) {
        if (y.getMath() - x.getMath() == 0)
        {
            return x.getName().compareTo(y.getName());
        }
        else
        {
            return y.getMath() - x.getMath();
        }
    }
}
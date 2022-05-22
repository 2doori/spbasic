package ds;

import java.io.*;
import java.util.*;

public class ListTest {

    static List<Student> readTxt() {
        List<Student> csvList = new ArrayList<Student>();

        BufferedReader br = null;
        String line = "";

        try{
            br = new BufferedReader(new FileReader("./DS/List_Sample.txt"));
            while((line = br.readLine()) != null) {
                String[] lineArr = line.split("\t");
                Student student = new Student(lineArr[0],
                        Integer.parseInt(lineArr[1]),
                        Integer.parseInt(lineArr[2]),
                        Integer.parseInt(lineArr[3]));
                csvList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return csvList;
    }

    public static void main(String[] args) throws IOException {
        List<Student> al = readTxt();

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
                    Collections.sort(al, (g1, g2) ->
                            (g2.getKorean() - g1.getKorean()) == 0 ? g1.getName().compareTo(g2.getName()) : g2.getKorean() - g1.getKorean());
                    break;
                case "ENGLISH": // 영어 성적 순 출력
                    // Comparator
                    Collections.sort(al, new Comparator<Student>() {

                        @Override
                        public int compare(Student x, Student y) {
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
                    Collections.sort(al, new sortByMath2());
                    break;
                case "QUIT":
                    return;
                default:
                    break;
            }

            for (Student val : al)
            {
                System.out.println(String.format("%s\t%d\t%d\t%d",val.getName(), val.getKorean(), val.getEnglish(), val.getMath()));
            }
        }
    }
}


class Student
{
    private String strName;
    private int Korean;
    private int English;
    private int Math;

    public Student(String str, int k, int e, int m)
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

class sortByMath2 implements Comparator<Student>{
    public int compare(Student x, Student y) {
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


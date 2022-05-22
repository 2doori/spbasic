package ds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.*;


// https://recordsoflife.tistory.com/55
// https://yongho1037.tistory.com/704
public class MapTest {

    static List<Employee> readCsv() {
        List<Employee> csvList = new ArrayList<Employee>();

        BufferedReader br = null;
        String line = "";

        try{
            br = new BufferedReader(new FileReader("./DS/DS_Sample2_map.csv"));
            while((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                Employee employee = new Employee(lineArr[0],
                        lineArr[1],
                        lineArr[2],
                        Double.parseDouble(lineArr[3]),
                        Double.parseDouble(lineArr[4]),
                        Double.parseDouble(lineArr[5]));
                csvList.add(employee);
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

    public static void main(String[] args) {
        List<Employee> al = readCsv();

        Map<List<String>, Double> proj1mm = al.stream()
                .collect(groupingBy(emp->Arrays.asList(emp.getNo(), emp.getName())
                                , summingDouble(Employee::getProj1)));                ;
        Map<List<String>, Double> proj2mm = al.stream()
                .collect(groupingBy(emp->Arrays.asList(emp.getNo(), emp.getName())
                        , summingDouble(Employee::getProj2)));
        Map<List<String>, Double> proj3mm = al.stream()
                .collect(groupingBy(emp->Arrays.asList(emp.getNo(), emp.getName())
                        , summingDouble(Employee::getProj3)));

        Map<List<String>, Double> totalmm = new HashMap<>(proj1mm);
        proj2mm.forEach((key, value) -> totalmm.merge(key, value, (v1, v2) -> v1 + v2));
        proj3mm.forEach((key, value) -> totalmm.merge(key, value, (v1, v2) -> v1 + v2));

        출처: https://hianna.tistory.com/580 [어제 오늘 내일]
//        for (Employee val : al)
//        {
//            System.out.println(String.format("%s\t%s\t%s\t%.1f\t%.1f\t%.1f"
//                    ,val.getMonth(), val.getNo(), val.getName()
//                    , val.getProj1(), val.getProj2(), val.getProj3()));
//        }

        System.out.println(proj1mm);
        System.out.println(proj2mm);
        System.out.println(proj3mm);
        System.out.println(totalmm);

//        Map<List<String>, List<Employee>> grouped = al
//                .stream()
//                .collect(groupingBy(x -> {
//                    return new ArrayList<String>(Arrays.asList(x.getNo(), x.getName()));
//                }));
//        grouped
//                .entrySet()
//                .stream()
//                .forEach(x -> {
//                    System.out.println(x.getKey());
//                    x.getValue().stream()
//                            .forEach(p -> System.out.printf(" ( %s %s %f %f %f )%n",
//                                    p.getNo(), p.getName(), p.getProj1(), p.getProj2(), p.getProj3()));
//                });

        Map<List<String>, List<Double>> totalMap = new HashMap<>();
        proj1mm.entrySet().forEach(entry->{
            ArrayList<Double> mmList = new ArrayList<>();
            mmList.add(entry.getValue());
            mmList.add(proj2mm.get(entry.getKey()));
            mmList.add(proj3mm.get(entry.getKey()));
            mmList.add(totalmm.get(entry.getKey()));

            totalMap.put(entry.getKey(), mmList);
        });

        totalMap.entrySet().forEach(entry->{
            System.out.println(entry.getKey().get(0) + " " + entry.getKey().get(1) + " " +
                            entry.getValue().get(0)+ " " + entry.getValue().get(1)+ " " +
                            entry.getValue().get(2)+ " => " +  entry.getValue().get(3));
        });
    }
}



class Employee implements Comparable<Employee>{
    private String month;
    private String no;
    private String name;
    private double proj1;
    private double proj2;
    private double proj3;

    public Employee(String month, String no, String name, double proj1, double proj2, double proj3) {
        this.month = month;
        this.no = no;
        this.name = name;
        this.proj1 = proj1;
        this.proj2 = proj2;
        this.proj3 = proj3;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProj1() {
        return proj1;
    }

    public void setProj1(double proj1) {
        this.proj1 = proj1;
    }

    public double getProj2() {
        return proj2;
    }

    public void setProj2(double proj2) {
        this.proj2 = proj2;
    }

    public double getProj3() {
        return proj3;
    }

    public void setProj3(double proj3) {
        this.proj3 = proj3;
    }

    @Override
    public int compareTo(Employee o) {
        return this.no.compareTo(o.no);
    }
}

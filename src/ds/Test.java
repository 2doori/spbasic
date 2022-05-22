package ds;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    private static class Foo {
        public int id, targetCost, actualCost;
        public String ref;

        public Foo(int id, String ref, int targetCost, int actualCost) {
            this.id = id;
            this.targetCost = targetCost;
            this.actualCost = actualCost;
            this.ref = ref;
        }

        @Override
        public String toString() {
            return String.format("Foo(%d,%s,%d,%d)",id,ref,targetCost,actualCost);
        }
    }

    public static void main(String[] args) {
        List<Foo> list = Arrays.asList(
                new Foo(1, "P1", 300, 400),
                new Foo(2, "P2", 600, 400),
                new Foo(3, "P3", 30, 20),
                new Foo(3, "P3", 70, 20),
                new Foo(1, "P1", 360, 40),
                new Foo(4, "P4", 320, 200),
                new Foo(4, "P4", 500, 900));

        List<Foo> transform = list.stream()
                .collect(Collectors.groupingBy(foo -> foo.id))
                .entrySet().stream()
                .map(e -> e.getValue().stream()
                        .reduce((f1,f2) -> new Foo(f1.id, f1.ref,f1.targetCost + f2.targetCost,f1.actualCost + f2.actualCost)))
                .map(f -> f.get())
                .collect(Collectors.toList());
        System.out.println(transform);
    }
}
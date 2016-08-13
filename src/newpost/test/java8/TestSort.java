package newpost.test.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

/**
 * Created by serhii on 06.08.16.
 */
public class TestSort {

    public static void main(String[] args) {
        // java8 stream lambdas
        int[] mas = {1,1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,3,4,5,6,7,8,9,10};

        List<String> names = Arrays.asList("Kolia","Andriy","Ivan","Serhii","Oleg");

        for (String name : names) {
            System.out.println(name);
        }

        for (int i = 0; i < mas.length; i++) {
            System.out.println(mas[i]);
        }


        // function
        IntPredicate intPredicate = (val) -> val <= 5;

        List<String> list = Arrays.stream(mas).filter(value -> value % 2 == 0).distinct()
                .mapToObj(operand -> "[" + operand + "]").limit(4)
                .collect(Collectors.toList());


        // Arrays.stream(mas).collect(() -> 0,(integer, value) -> {}, (integer1, integer2) -> integer1 * integer2);



        ///Arrays.sort();
    }
}

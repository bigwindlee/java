package hello;

import java.util.List;
import java.util.function.Consumer;

public class Greeter {
    public String sayHello() {
        return "Hello world!";
    }

    public void printSquare_1(List<Integer> numbers) {
        numbers.forEach(n -> System.out.println(n * n));
    }

    public void printSquare_2(List<Integer> numbers) {
        Consumer<Integer> method = n -> System.out.println(n * n);
        numbers.forEach(method);
    }
}
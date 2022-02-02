package hello;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);

        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            numbers.add(i);
        }

        System.out.println("\n#1 Print squares of numbers:");
        greeter.printSquare_1(numbers);

        System.out.println("\n#2 Print squares of numbers:");
        greeter.printSquare_2(numbers);

        System.out.println("\n#3 A method which takes a lambda expression as a parameter:");
        /* 如果interface只定义了一个method，用lambda表达式实现这个method，就得到了一个实现了这个interface的object */
        StringFunction exclaim = s -> s + "!";
        StringFunction ask = s -> s + "?";
        printFormatted("Hello", exclaim);
        printFormatted("Hello", ask);
    }

    public static void printFormatted(String str, StringFunction format) {
        String result = format.run(str);
        System.out.println(result);
    }
}
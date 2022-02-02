package hello;

import java.util.List;

public class RosterTest {
    interface CheckPerson {
        boolean test(Person p);
    }

    // Approach 1: Create Methods that Search for Persons that Match One Characteristic
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

}

package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Person> people = samplePeople();

        PersonRule isActive = person -> person.isActive();
        PersonRule isAdult = person -> person.getAge() >= 18;
        PersonRule livesInStockholm = person -> "Stockholm".equals(person.getCity());

        PersonRule activeAndAdult = person -> isActive.test(person) && isAdult.test(person);
        PersonRule adultOrLivesInStockholm = person -> isAdult.test(person) || livesInStockholm.test(person);
        PersonRule notActive = person -> !isActive.test(person);

        PersonAction printName = person -> System.out.println(person.getName());
        PersonAction sendEmail = person -> System.out.println("Send email to " + person.getName());

        PersonProcessor processor = new PersonProcessor();

        System.out.println("Functional programming without streams:");
        System.out.println("Active people: " + filterPeople(people, isActive));
        System.out.println("Adults: " + filterPeople(people, isAdult));
        System.out.println("People in Stockholm: " + filterPeople(people, livesInStockholm));
        System.out.println("Active and adult: " + filterPeople(people, activeAndAdult));
        System.out.println("Adult or in Stockholm: " + filterPeople(people, adultOrLivesInStockholm));
        System.out.println("Not active: " + filterPeople(people, notActive));

        System.out.println("Print names:");
        processor.applyToMatching(people, isActive, printName);

        System.out.println("Send emails to adults:");
        processor.applyToMatching(people, isAdult, sendEmail);

        System.out.println("Reusable processor result: " + processor.findPeople(people, activeAndAdult));

        Predicate<Person> activePredicate = Person::isActive;
        Consumer<Person> emailConsumer = person -> System.out.println("Send email to " + person.getName());
        System.out.println("Using Predicate<Person>: " + people.stream().filter(activePredicate).toList());
        people.stream().filter(activePredicate).forEach(emailConsumer);

        System.out.println();
        System.out.println("Stream API:");
        System.out.println("All active people: " + findActivePeople(people));
        System.out.println("All names: " + mapNames(people));
        System.out.println("Adult count: " + countAdults(people));
        System.out.println("Sorted by age: " + sortByAge(people));
        System.out.println("First active person in Stockholm: " + findFirstActiveInStockholm(people).orElse(null));
        System.out.println("Active adults: " + findActiveAdults(people));
        System.out.println("Active people in Stockholm: " + findActivePeopleInStockholm(people));
        System.out.println("Inactive people older than 30: " + findInactivePeopleOlderThan30(people));
        System.out.println("Unique sorted cities: " + sortedUniqueCities(people));
        System.out.println("Unique sorted first letters: " + sortedUniqueFirstLetters(people));
        System.out.println("Formatted people: " + formatPeople(people));
        System.out.println("Emails: " + createEmails(people));
    }

    public static List<Person> samplePeople() {
        return List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );
    }

    public static List<Person> filterPeople(List<Person> people, PersonRule rule) {
        List<Person> matches = new ArrayList<>();
        for (Person person : people) {
            if (rule.test(person)) {
                matches.add(person);
            }
        }
        return matches;
    }

    public static List<Person> findActivePeople(List<Person> people) {
        return people.stream()
                .filter(Person::isActive)
                .toList();
    }

    public static List<String> mapNames(List<Person> people) {
        return people.stream()
                .map(Person::getName)
                .toList();
    }

    public static long countAdults(List<Person> people) {
        return people.stream()
                .filter(person -> person.getAge() >= 18)
                .count();
    }

    public static List<Person> sortByAge(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .toList();
    }

    public static Optional<Person> findFirstActiveInStockholm(List<Person> people) {
        return people.stream()
                .filter(Person::isActive)
                .filter(person -> "Stockholm".equals(person.getCity()))
                .findFirst();
    }

    public static List<Person> findActiveAdults(List<Person> people) {
        return people.stream()
                .filter(Person::isActive)
                .filter(person -> person.getAge() >= 18)
                .toList();
    }

    public static List<Person> findActivePeopleInStockholm(List<Person> people) {
        return people.stream()
                .filter(Person::isActive)
                .filter(person -> "Stockholm".equals(person.getCity()))
                .toList();
    }

    public static List<Person> findInactivePeopleOlderThan30(List<Person> people) {
        return people.stream()
                .filter(person -> !person.isActive())
                .filter(person -> person.getAge() > 30)
                .toList();
    }

    public static List<String> sortedUniqueCities(List<Person> people) {
        return people.stream()
                .map(Person::getCity)
                .distinct()
                .sorted()
                .toList();
    }

    public static List<String> sortedUniqueFirstLetters(List<Person> people) {
        return people.stream()
                .map(person -> person.getName().substring(0, 1))
                .distinct()
                .sorted()
                .toList();
    }

    public static List<String> formatPeople(List<Person> people) {
        return people.stream()
                .map(person -> person.getName() + " (" + person.getAge() + ") - " + person.getCity())
                .toList();
    }

    public static List<String> createEmails(List<Person> people) {
        return people.stream()
                .map(person -> person.getName().toLowerCase() + "@example.com")
                .collect(Collectors.toList());
    }
}

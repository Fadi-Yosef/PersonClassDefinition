package org.example;

import java.util.ArrayList;
import java.util.List;

public class PersonProcessor {

    public List<Person> findPeople(List<Person> people, PersonRule rule) {
        List<Person> matches = new ArrayList<>();
        for (Person person : people) {
            if (rule.test(person)) {
                matches.add(person);
            }
        }
        return matches;
    }

    public void applyToMatching(List<Person> people, PersonRule rule, PersonAction action) {
        for (Person person : people) {
            if (rule.test(person)) {
                action.execute(person);
            }
        }
    }
}

package org.example;

@FunctionalInterface
public interface PersonRule {
    boolean test(Person person);
}

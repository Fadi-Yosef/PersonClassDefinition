package org.example;

import junit.framework.TestCase;

import java.util.List;

public class AppTest extends TestCase {

    public void testFilterPeopleWithAdultRule() {
        List<Person> adults = App.filterPeople(App.samplePeople(), person -> person.getAge() >= 18);

        assertEquals(5, adults.size());
        assertEquals("Amina", adults.get(0).getName());
        assertEquals("Omar", adults.get(4).getName());
    }

    public void testFindFirstActiveInStockholm() {
        Person person = App.findFirstActiveInStockholm(App.samplePeople()).orElse(null);

        assertNotNull(person);
        assertEquals("Amina", person.getName());
    }

    public void testSortedUniqueCities() {
        List<String> cities = App.sortedUniqueCities(App.samplePeople());

        assertEquals(List.of("Gothenburg", "Malmö", "Stockholm", "Uppsala"), cities);
    }

    public void testCreateEmails() {
        List<String> emails = App.createEmails(App.samplePeople());

        assertEquals("amina@example.com", emails.get(0));
        assertEquals("omar@example.com", emails.get(5));
    }
}

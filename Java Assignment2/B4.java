
import java.util.*;
import java.util.stream.Collectors;
import java.io.IO;

class B4 {

    /*
     * Problem Statement:
     * Demonstrate the Map interface using HashMap, LinkedHashMap
     * and TreeMap. Perform map operations, count word frequency,
     * sort student marks in descending order and use Student
     * objects as keys in a HashMap.
     */

    void main() {

        // Store student roll numbers and marks.
        HashMap<String, Integer> marks = new HashMap<>();

        marks.put("S102", 92);
        marks.put("S104", 88);
        marks.put("S101", 76);
        marks.put("S103", 85);

        IO.println("HASH MAP");
        IO.println("Original marks: " + marks);

        // get() retrieves the marks of a student.
        IO.println("Marks of S102: " + marks.get("S102"));

        // containsKey() checks whether a roll number exists.
        IO.println("Contains S104: " + marks.containsKey("S104"));

        // size() returns the number of entries.
        IO.println("Number of students: " + marks.size());

        // remove() deletes the specified entry.
        marks.remove("S101");
        IO.println("After removing S101: " + marks);

        // keySet() displays all roll numbers.
        IO.println("\nRoll numbers: " + marks.keySet());

        // values() displays all marks.
        IO.println("Marks: " + marks.values());

        // entrySet() with Iterator displays each entry.
        IO.println("Entries using Iterator:");

        Iterator<Map.Entry<String, Integer>> iterator =
                marks.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();

            IO.println(entry.getKey() + " : " + entry.getValue());
        }

        // Word frequency counter using HashMap.
        String sentence = "java is easy and java is useful";

        HashMap<String, Integer> wordFrequency = new HashMap<>();

        String[] words = sentence.toLowerCase().split("\\s+");

        for (String word : words) {
            wordFrequency.put(
                    word,
                    wordFrequency.getOrDefault(word, 0) + 1);
        }

        IO.println("\nWORD FREQUENCY");
        IO.println("Sentence: " + sentence);

        for (Map.Entry<String, Integer> entry
                : wordFrequency.entrySet()) {

            if (entry.getValue() > 1) {
                IO.println(entry.getKey() + " = " + entry.getValue());
            }
        }

        // Sort entries by marks in descending order.
        Map<String, Integer> meritList = marks.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (first, second) -> first,
                        LinkedHashMap::new));

        IO.println("\nMERIT LIST (DESCENDING)");

        for (Map.Entry<String, Integer> entry
                : meritList.entrySet()) {

            IO.println(entry.getKey() + " : " + entry.getValue());
        }

        // LinkedHashMap preserves insertion order.
        LinkedHashMap<String, Integer> linkedMarks =
                new LinkedHashMap<>();

        linkedMarks.put("S102", 92);
        linkedMarks.put("S104", 88);
        linkedMarks.put("S101", 76);
        linkedMarks.put("S103", 85);

        IO.println("\nLINKED HASH MAP (INSERTION ORDER)");
        IO.println(linkedMarks);

        // TreeMap sorts keys in natural order.
        TreeMap<String, Integer> sortedMarks =
                new TreeMap<>(linkedMarks);

        IO.println("\nTREE MAP (ROLL NUMBER ORDER)");
        IO.println(sortedMarks);

        IO.println(
                "Choice: TreeMap is suitable for roll-number-wise reports "
                + "because it sorts the keys automatically.");

        // Student objects used as HashMap keys.
        HashMap<Student, String> studentDetails = new HashMap<>();

        Student firstStudent = new Student(101, "Ravi");
        Student sameStudent = new Student(101, "Ravi");

        studentDetails.put(firstStudent, "CSE");

        IO.println("\nSTUDENT OBJECT AS KEY");
        IO.println("Same student key exists: "
                + studentDetails.containsKey(sameStudent));

        IO.println("Department: " + studentDetails.get(sameStudent));
    }

    /*
     * Sample Input:
     * Marks: S102=92, S104=88, S101=76, S103=85
     * Sentence: java is easy and java is useful
     *
     * Sample Output:
     * Word frequency: java=2, is=2
     * Merit list: S102=92, S104=88, S103=85, S101=76
     * TreeMap: roll numbers in sorted order
     *
     * API Note:
     * HashMap stores key-value pairs without guaranteeing iteration
     * order. LinkedHashMap preserves insertion order. TreeMap sorts
     * keys. Stream API sorts entries by value. Iterator traverses
     * entries one at a time.
     */

    static class Student {

        private int rollNumber;
        private String name;

        Student(int rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object object) {

            if (this == object) {
                return true;
            }

            if (!(object instanceof Student)) {
                return false;
            }

            Student other = (Student) object;

            return rollNumber == other.rollNumber
                    && Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rollNumber, name);
        }

        /*
         * equals() compares two Student objects for logical equality.
         * hashCode() produces the hash value used by HashMap.
         * Both must be overridden consistently: equal objects must
         * have the same hash code, or HashMap lookups may fail.
         */
    }
}


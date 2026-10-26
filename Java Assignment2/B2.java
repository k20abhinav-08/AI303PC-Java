
import java.util.*;
import java.io.IO;

class B2 {

    void main() {

        /*
         * Problem Statement:
         * Simulate a ticket counter using ArrayDeque and LinkedList.
         * Use PriorityQueue to serve patients according to emergency
         * level instead of their arrival order.
         */

        // ArrayDeque follows FIFO order for the ticket counter.
        Queue<String> customerQueue = new ArrayDeque<>();

        customerQueue.offer("Ravi");
        customerQueue.offer("Sita");
        customerQueue.offer("Anil");
        customerQueue.offer("Meena");

        IO.println("TICKET COUNTER");
        IO.println("Initial Queue: " + customerQueue);

        // peek() shows the next customer without removing them.
        IO.println("Next Customer: " + customerQueue.peek());

        // Serve customers until the queue becomes empty.
        while (!customerQueue.isEmpty()) {

            try {
                String servedCustomer = customerQueue.poll();

                if (servedCustomer != null) {
                    IO.println("Serving: " + servedCustomer);
                }

                IO.println("Queue after service: " + customerQueue);

            } catch (Exception exception) {
                IO.println("Queue service error: "
                        + exception.getMessage());
            }
        }

        // poll() on an empty queue returns null.
        String emptyCustomer = customerQueue.poll();

        if (emptyCustomer == null) {
            IO.println("Queue is empty. No customer to serve.");
        }

        IO.println("Final ArrayDeque size: " + customerQueue.size());

        /*
         * PriorityQueue serves patients by emergency level.
         * A higher emergency level receives higher priority.
         * Level 3 = Critical, Level 2 = Serious, Level 1 = Normal.
         */

        Comparator<Patient> emergencyComparator =
                Comparator.comparingInt(Patient::getEmergencyLevel)
                        .reversed();

        Queue<Patient> patientQueue =
                new PriorityQueue<>(emergencyComparator);

        patientQueue.offer(new Patient("Charan", 1));
        patientQueue.offer(new Patient("Bala", 2));
        patientQueue.offer(new Patient("Amit", 3));
        patientQueue.offer(new Patient("Divya", 2));

        IO.println("\nPATIENT PRIORITY QUEUE");
        IO.println("Patients are served by emergency level:");

        while (!patientQueue.isEmpty()) {

            Patient patient = patientQueue.poll();

            IO.println("Serving: " + patient);
        }

        IO.println("Final PriorityQueue size: " + patientQueue.size());

        /*
         * LinkedList also implements Queue and supports FIFO behaviour.
         * It additionally implements Deque, which provides operations
         * at both the front and rear.
         */

        Queue<String> linkedQueue = new LinkedList<>();

        linkedQueue.offer("Kiran");
        linkedQueue.offer("Priya");
        linkedQueue.offer("Rahul");

        IO.println("\nLINKEDLIST QUEUE");
        IO.println("Initial Queue: " + linkedQueue);

        while (!linkedQueue.isEmpty()) {

            String servedCustomer = linkedQueue.poll();

            IO.println("Serving: " + servedCustomer);
            IO.println("Queue after service: " + linkedQueue);
        }

        IO.println("Final LinkedList queue size: "
                + linkedQueue.size());

        /*
         * Sample Input:
         * Customers: Ravi, Sita, Anil, Meena
         * Patients: Charan(1), Bala(2), Amit(3), Divya(2)
         *
         * Sample Output:
         * Serving: Ravi
         * Serving: Sita
         * Serving: Anil
         * Serving: Meena
         * Queue is empty. No customer to serve.
         * Final ArrayDeque size: 0
         * Priority order begins with Amit (Critical).
         * Final PriorityQueue size: 0
         * Final LinkedList queue size: 0
         *
         * API Note:
         * Queue defines offer(), poll(), peek() and size().
         * ArrayDeque provides efficient operations at both ends.
         * PriorityQueue serves elements according to a comparator.
         * LinkedList implements both Queue and Deque.
         *
         * ArrayDeque and LinkedList can be used for FIFO service.
         * PriorityQueue uses priority ordering rather than FIFO.
         */

    }

    static class Patient {

        private String name;
        private int emergencyLevel;

        Patient(String name, int emergencyLevel) {
            this.name = name;
            this.emergencyLevel = emergencyLevel;
        }

        int getEmergencyLevel() {
            return emergencyLevel;
        }

        @Override
        public String toString() {

            String emergencyName;

            switch (emergencyLevel) {
                case 3:
                    emergencyName = "Critical";
                    break;

                case 2:
                    emergencyName = "Serious";
                    break;

                default:
                    emergencyName = "Normal";
            }

            return emergencyName + "-" + name;
        }
    }
}

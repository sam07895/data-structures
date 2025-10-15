package assn05;

public class Main {

    /**
     * Write a series of tests to check the functionality of each task
     *
     * @param args
     */
    public static void main(String[] args) {
        testT1();
        testT2();
        testT3();
        testT4();
        double[] runtimeResults = compareRuntimes();
        for (int i = 0; i < runtimeResults.length; i++) {
            System.out.println("Runtime result " + i + ": " + runtimeResults[i]);
        }
    }

    /**
     * Test Task 1 - Write some tests to convince yourself that your code for Task 1 is working
     */
    public static void testT1() {
        SimpleEmergencyRoom er = new SimpleEmergencyRoom();

        // Test empty emergency room
        System.out.println("Dequeue from empty ER: " + (er.dequeue() == null ? "Passed" : "Failed"));

        // Adding patients with varying priorities
        er.addPatient("Patient A", 300); // Lower priority
        er.addPatient("Patient B", 600); // Highest priority
        er.addPatient("Patient C", 500); // Medium priority

        // Test if the patient with the highest priority is dequeued first
        String highPriorityPatient = er.dequeue().getValue().toString();
        System.out.println("Dequeue highest priority (Expected Patient B, got " + highPriorityPatient + "): " +
                (highPriorityPatient.equals("Patient B") ? "Passed" : "Failed"));

        // Test if the patient with the next highest priority is dequeued next
        String nextHighPriorityPatient = er.dequeue().getValue().toString();
        System.out.println("Dequeue next highest priority (Expected Patient C, got " + nextHighPriorityPatient + "): " +
                (nextHighPriorityPatient.equals("Patient C") ? "Passed" : "Failed"));

        // Test remaining patient is the one with the lowest priority
        String remainingPatient = er.dequeue().getValue().toString();
        System.out.println("Dequeue remaining patient (Expected Patient A, got " + remainingPatient + "): " +
                (remainingPatient.equals("Patient A") ? "Passed" : "Failed"));

        // Ensure ER is now empty
        System.out.println("Dequeue from now empty ER: " + (er.dequeue() == null ? "Passed" : "Failed"));
    }

    /**
     * Test Task 2 - Write some tests to convince yourself that your code for Task 2 (A & B) is working
     */
    public static void testT2() {
        MaxBinHeapER<String, Integer> heap = new MaxBinHeapER<>();

        // Test adding elements
        heap.enqueue("Patient A", 5);
        heap.enqueue("Patient B", 3);
        heap.enqueue("Patient C", 9);
        heap.enqueue("Patient D", 1);

        // Safely Test Enqueue & GetMax:
        String max = heap.getMax();
        if (max != null) {
            System.out.println("Test Enqueue & GetMax: " + (max.equals("Patient C") ? "Passed" : "Failed"));
        } else {
            System.out.println("Test Enqueue & GetMax: Failed - heap is empty or getMax() returned null unexpectedly.");
        }

        // Safely Test getMax again for consistency
        max = heap.getMax();
        if (max != null) {
            System.out.println("Test GetMax: Expected Patient C, got " + max + " - " + (max.equals("Patient C") ? "Passed" : "Failed"));
        } else {
            System.out.println("Test GetMax: Failed - heap is empty or getMax() returned null unexpectedly.");
        }

        // Safely Test dequeue
        String highestPriority = heap.dequeue();
        if (highestPriority != null) {
            System.out.println("Test Dequeue: Expected Patient C, got " + highestPriority + " - " + (highestPriority.equals("Patient C") ? "Passed" : "Failed"));
        } else {
            System.out.println("Test Dequeue: Failed - heap is empty or dequeue() returned null unexpectedly.");
        }

        // Check getMax after dequeue safely
        max = heap.getMax();
        if (max != null) {
            System.out.println("Test GetMax After Dequeue: Expected Patient A, got " + max + " - " + (max.equals("Patient A") ? "Passed" : "Failed"));
        } else {
            System.out.println("Test GetMax After Dequeue: Failed - heap is empty or getMax() returned null unexpectedly.");
        }

        // Test dequeue to empty
        heap.dequeue();  // Patient A
        heap.dequeue();  // Patient B
        heap.dequeue();  // Patient D, should now be empty

        String emptyDequeue = heap.dequeue();
        System.out.println("Test Dequeue on Empty Heap: " + (emptyDequeue == null ? "Passed" : "Failed"));

        String emptyGetMax = heap.getMax();
        System.out.println("Test GetMax on Empty Heap: " + (emptyGetMax == null ? "Passed" : "Failed"));

        // Add Patient D back for testing priority updates
        heap.enqueue("Patient D", 2);

        // Increase Priority
        heap.updatePriority("Patient D", 10);
        System.out.println("Test Increase Priority: " + (heap.getMax().equals("Patient D") ? "Passed" : "Failed"));

        // Ensuring the heap is well-formed before decreasing "Patient D" priority
        heap.enqueue("Patient E", 20);
        heap.enqueue("Patient F", 18);
        heap.enqueue("Patient G", 15);

        System.out.println("Heap before decreasing priority of 'Patient D':");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println("Node index " + i + ": " + heap.getAsArray()[i].getValue() + " - Priority: " + heap.getAsArray()[i].getPriority());
        }

// Decrease the priority
        heap.updatePriority("Patient D", 2);

        System.out.println("Heap after decreasing priority of 'Patient D':");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println("Node index " + i + ": " + heap.getAsArray()[i].getValue() + " - Priority: " + heap.getAsArray()[i].getPriority());
        }

        System.out.println("Test Decrease Priority: " + (heap.getMax().equals("Patient E") ? "Passed" : "Failed"));

// Test removing with negative priority
        heap.updatePriority("Patient D", -1);
        System.out.println("Test Remove with Negative Priority: Size should decrease, actual size: " + heap.size());

// Test non-existent patient update
        heap.updatePriority("Patient Z", 5);  // This patient does not exist
        System.out.println("Test Non-existent Patient: Size should remain constant, actual size: " + heap.size());
    }

    /**
     * Test Task 3 - This part can be used to test for task 3.
     */
    public static void testT3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /**
     * Test Task 4 - Write some tests to convince yourself that your code for Task 4 is working
     * You can use some of the helper methods already given below
     */
    public static void testT4() {
        double[] runtimeResults = compareRuntimes();

        System.out.println("SimpleEmergencyRoom - Total time to dequeue 100,000 patients: " + runtimeResults[0] + " nanoseconds");
        System.out.println("SimpleEmergencyRoom - Average time per dequeue: " + runtimeResults[1] + " nanoseconds");

        System.out.println("MaxBinHeapER - Total time to dequeue 100,000 patients: " + runtimeResults[2] + " nanoseconds");
        System.out.println("MaxBinHeapER - Average time per dequeue: " + runtimeResults[3] + " nanoseconds");
    }

    /**
     * fills up an Emergency Room based on a MaxBinHeapER
     * @param complexER an initially empty MaxBinHeapER
     */
    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    /**
     * fills up an Emergency Room based on a SimpleEmergencyRoom (overloaded)
     * @param simpleER an initially empty SimpleEmergencyRoom
     */
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    /**
     * Creates an array of patients
     * @return returns this array of patients
     */
    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    /**
     * TODO (Task 4): compareRuntimes
     * Compares the Run Times of the SimpleEmergencyRoom vs MaxBinHeapER
     * @return an array of results as follows:
     * index 0: total nanosec for simpleER
     * index 1: average nanosec for simpleER
     * index 2: total nanosec for maxHeapER
     * index 3: average nanosec for maxHeapER
     */
    public static double[] compareRuntimes() {
        // Array which you will populate as part of Task 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        long startTime = System.nanoTime();

        while (simplePQ.size() > 0) {
            simplePQ.dequeue();
        }

        long endTime = System.nanoTime();

        long simpleTotalTime = endTime - startTime;
        results[0] = simpleTotalTime;
        results[1] = (double) simpleTotalTime / 100000;

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        startTime = System.nanoTime();

        while (binHeap.size() > 0) {
            binHeap.dequeue();
        }

        endTime = System.nanoTime();

        long maxHeapTotalTime = endTime - startTime;
        results[2] = maxHeapTotalTime;
        results[3] = (double) maxHeapTotalTime / 100000;

        return results;
    }

}

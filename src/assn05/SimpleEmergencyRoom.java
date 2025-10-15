package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> _patients;

    public SimpleEmergencyRoom() {
        _patients = new ArrayList<>();
    }

    /**
     * TODO (Task 1): dequeue
     * @return return patient with the highest priority
     */
    public Patient dequeue() {
    	if (_patients.isEmpty()) {
            return null;
    	}

        int highestPriorityIndex = 0;
        for (int i = 1; i < _patients.size(); i++) {
            if (_patients.get(i).getPriority().compareTo(_patients.get(highestPriorityIndex).getPriority()) > 0) {
                highestPriorityIndex = i;
            }
        }

        return _patients.remove(highestPriorityIndex);
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        _patients.add(patient);
    }

    public List getPatients() {
        return _patients;
    }

    public int size() {
        return _patients.size();
    }

}

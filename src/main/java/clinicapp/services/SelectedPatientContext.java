package clinicapp.services;

import clinicapp.domain.Patient;

import java.util.Optional;

public class SelectedPatientContext {
    private Patient selected;

    public Optional<Patient> getSelected() {
        return Optional.ofNullable(selected);
    }

    public void setSelected(Patient patient) {
        this.selected = patient;
    }

    public void clear() {
        this.selected = null;
    }

    public boolean hasSelected() {
        return selected != null;
    }
}

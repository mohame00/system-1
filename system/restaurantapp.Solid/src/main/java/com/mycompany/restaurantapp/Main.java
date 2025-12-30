public class ClinicOOP {

    // ========= Abstraction + Encapsulation =========
    static abstract class Person {
        private String name;
        private int id;

        public Person(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public abstract void displayInfo();
    }

    static class Doctor extends Person {
        private String specialization;

        public Doctor(String name, int id, String specialization) {
            super(name, id);
            this.specialization = specialization;
        }

        @Override
        public void displayInfo() {
            System.out.println("Doctor Name: " + getName());
            System.out.println("Specialization: " + specialization);
        }
    }

    static class Patient extends Person {
        private int age;

        public Patient(String name, int id, int age) {
            super(name, id);
            this.age = age;
        }

        @Override
        public void displayInfo() {
            System.out.println("Patient Name: " + getName());
            System.out.println("Age: " + age);
        }
    }

    static class Appointment {
        private Doctor doctor;
        private Patient patient;
        private String date;

        public Appointment(Doctor doctor, Patient patient, String date) {
            this.doctor = doctor;
            this.patient = patient;
            this.date = date;
        }

        public void showDetails() {
            System.out.println("Appointment Date: " + date);
            doctor.displayInfo();
            patient.displayInfo();
        }
    }

    public static void main(String[] args) {

        Person doctor = new Doctor("Dr. Ahmed", 1, "Cardiology");
        Person patient = new Patient("Ali", 101, 30);

        Appointment appointment = new Appointment(
                (Doctor) doctor,
                (Patient) patient,
                "10-01-2025"
        );

        appointment.showDetails();
    }
}

package model;

import javax.print.DocFlavor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends User{
    // Atributo
    private String speciality;
    private ArrayList<AvaiableAppointment> avaiableAppointments = new ArrayList<>();

    // metodo constructor
    public Doctor(String name, String email){
        super(name, email); // hace referencia al constructor del padre
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    // Comportamientos: Métodos


    public void addAvaiableAppointment(String date, String time){
        avaiableAppointments.add(new Doctor.AvaiableAppointment(date,time)); // incrementa la lista y crea nuevas citas

    }

    public ArrayList<AvaiableAppointment> getAvaiableAppointments(){
        return avaiableAppointments; // lista completa de citas
    }

    @Override
    public String toString() {
        return super.toString() + "\nSpeciality: " + speciality + "\nAvaiable: " + avaiableAppointments.toString();
        // super.toString() me trae la herencia del padre de model.User en este caso
    }

    @Override
    public void showDataUser() {
        System.out.println("Empleado del Hospital: Cruz Roja");
        System.out.println("Departamento: Cancerologia");
    }

    public static class AvaiableAppointment{
        private int id;
        private Date date;
        private String time;

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

        public AvaiableAppointment(String date, String time) {
            try {
                this.date = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate(String DATE) { // fuente de datos
            return date;
        }

        public String getDate(){
            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Avaiable Appointments \nDate: " + date + "\nTime: " + time;
        }
    }

}

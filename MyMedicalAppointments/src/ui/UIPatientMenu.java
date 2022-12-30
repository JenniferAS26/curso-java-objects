package ui;

import model.Doctor;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UIPatientMenu {

    public static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome: " + UIMenu.patientLogged.getName());
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointments");
            System.out.println("0. Logout");

            Scanner sc =  new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmenMenu();
                    break;
                case 2:
                    showPatientMyAppointmens();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }

        }while (response != 0);
    }
    private static void showBookAppointmenMenu(){
        int response = 0;
        do {
            System.out.println("::Book an appointment ");
            System.out.println(":: Select date: ");
            // mostrar las fechas de los doctores que dejaron agendadas fechas
            // Numeracion de la lista de fechas: Integer (indice)
            // indice fecha seleccionada
            // [doctors]
            // 1. -doctor1
                // - 1 fecha1
                // - 2 fecha2
            // 2. -doctor2
            // 3. -doctor2

            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            int k = 0; // para capturar 1. 2. 3.
            for (int i = 0; i < UIDoctorMenu.doctorsAvailableAppointments.size(); i++) {
                ArrayList<Doctor.AvaiableAppointment> avaiableAppointments
                        = UIDoctorMenu.doctorsAvailableAppointments.get(i).getAvaiableAppointments();

                Map<Integer, Doctor> doctorAppointments = new TreeMap<>();

                for (int j = 0; j < avaiableAppointments.size(); j++) {
                    k++;
                    System.out.println(k + ". " + avaiableAppointments.get(j).getDate());
                    doctorAppointments.put(Integer.valueOf(j), UIDoctorMenu.doctorsAvailableAppointments.get(i)); //put para insertar en un map
                    doctors.put(Integer.valueOf(k), doctorAppointments);
                }
            }

            Scanner sc =  new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());

            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0; //indice de la fecha
            Doctor doctorSelected =  new Doctor("", "");
            for (Map.Entry<Integer,Doctor> doc :doctorAvailableSelected.entrySet()) { // doctorAvailableSelected.entrySet() acceso a como recorrer un Map
                indexDate = doc.getKey(); // obtener el index
                doctorSelected = doc.getValue();
            }
            System.out.println(doctorSelected.getName() +
                    ". Date: " +
                    doctorSelected.getAvaiableAppointments().get(indexDate).getDate() +
                    ". Time: " +
                    doctorSelected.getAvaiableAppointments().get(indexDate).getTime());

            System.out.println("Confirme your appointment: \n1. Yes. \n2. Change Data");
            response = Integer.valueOf(sc.nextLine());
            if (response == 1){
                UIMenu.patientLogged.addAppointmentDoctors(doctorSelected,
                        doctorSelected.getAvaiableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getAvaiableAppointments().get(indexDate).getTime());
                showPatientMenu();
            }

        }while (response != 0);
    }

    private static void showPatientMyAppointmens(){
        int response = 0;
        do {
            System.out.println("::My Appointments");
            if (UIMenu.patientLogged.getAppointmentDoctors().size() == 0){
                System.out.println("Don't have appointments");
                break;
            }

            for (int i = 0; i < UIMenu.patientLogged.getAppointmentDoctors().size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " +
                        "Date: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getDate() +
                        "Time: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getTime() +
                        "\n Doctor: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getDoctor().getName()
                        );
            }
            System.out.println("0. Return");
        }while (response != 0);

    }
}

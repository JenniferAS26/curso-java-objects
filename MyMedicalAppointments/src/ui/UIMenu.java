package ui;

import model.Doctor;
import model.Patient;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;

public class UIMenu {
    // Como los meses nunca van a cambiar, months deberia ser una constante
    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static Doctor doctorLogged;
    public static Patient patientLogged;

    public static void showMenu(){
        System.out.println("Welcome to My Appointments");
        System.out.println("Selecciona la opción deseada");

        int response = 0;
        do {
            System.out.println("1. Doctor"); // imprime el menu
            System.out.println("2. Patient");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in); // metodo para entrada de datos por teclado
            response = Integer.valueOf(sc.nextLine()); // guarda lo que recibe por teclado en una variable

            switch (response){ // para validar cada entrada por menu
                case 1:
                    System.out.println("Doctor");
                    response = 0;
                    authUSer(1);
                    break;
                case 2:
                    response = 0;
                    authUSer(2);
                    break;
                case 0:
                    System.out.println("Thank you for you visit");
                    break;
                default:
                    System.out.println("Please select a correct answer");
            }
        }while (response != 0);
    }

    private static void authUSer(int usetType){
        // userType =  1: Doctor
        // userType =  2: Patient

        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Alejandro Martinez", "alejandro@mail.com"));
        doctors.add(new Doctor("Karen Sosa", "karen@mail.com"));
        doctors.add(new Doctor("Rocio Gomez", "rocio@mail.com"));

        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Anahi Salgado", "anahi@mail.com"));
        patients.add(new Patient("Maria Hernandez", "maria@mail.com"));
        patients.add(new Patient("Roberto Rodriguez", "carlos@mail.com"));

        boolean emailCorrect = false;
        do {
            System.out.println("Insert your email: [a@a.com]");
            Scanner sc = new Scanner(System.in);
            String email = sc.nextLine();
            if (usetType == 1){
                for (Doctor d: doctors) {
                    if (d.getEmail().equals(email)){
                        emailCorrect = true;
                        // Obtener el usuario logueado
                        doctorLogged = d;
                        // showDoctorMenu
                        UIDoctorMenu.showDoctorMenu();
                    }
                }
            }
            if (usetType == 2){
                for (Patient p: patients) {
                    if (p.getEmail().equals(email)){
                        emailCorrect = true;
                        // Obtener el usuario logueado
                        patientLogged = p;
                        UIPatientMenu.showPatientMenu();
                        // showPatientMenu
                    }
                }
            }
        }while (!emailCorrect);


    }


    static void showPatientMenu(){
        int response = 0;
        do{
            System.out.println("\n\n");
            System.out.println("model.Patient");
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointment");
            System.out.println("0. Return");

            Scanner sc =  new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1: // Deberia mostrar la lista de meses disponibles
                    System.out.println("::Book an appointment");
                    for (int i = 1; i < 4; i++) {
                        System.out.println(i + ". " + MONTHS[i]); // mostrar la lista de los tres primeros meses
                    }
                    break;
                case 2:
                    System.out.println("::My appointment");
                    break;
                case 0:
                    showMenu();
                    break;
            }
        }while (response != 0);
    }
}

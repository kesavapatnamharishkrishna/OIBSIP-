import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Reservation {
    String pnr;
    String name;
    String trainNo;
    String trainName;
    String classType;
    String from;
    String to;
    String date;

    public Reservation(String pnr, String name, String trainNo, String trainName,
                       String classType, String from, String to, String date) {
        this.pnr = pnr;
        this.name = name;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.classType = classType;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public void display() {
        System.out.println("PNR: " + pnr);
        System.out.println("Name: " + name);
        System.out.println("Train No: " + trainNo);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class: " + classType);
        System.out.println("From: " + from + "  To: " + to);
        System.out.println("Date: " + date);
    }
}

public class OnlineReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Reservation> reservations = new HashMap<>();
    static final String USERNAME = "user";
    static final String PASSWORD = "1234";

    public static void main(String[] args) {
        if (login()) {
            int choice;
            do {
                System.out.println("\n=== Online Reservation System ===");
                System.out.println("1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: makeReservation(); break;
                    case 2: cancelReservation(); break;
                    case 3: System.out.println("Thank you! Exiting..."); break;
                    default: System.out.println("Invalid choice!");
                }
            } while (choice != 3);
        } else {
            System.out.println("Invalid login. Access Denied!");
        }
    }

    // --- Login Form ---
    static boolean login() {
        System.out.println("=== Login Form ===");
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        return uname.equals(USERNAME) && pass.equals(PASSWORD);
    }

    // --- Reservation Form ---
    static void makeReservation() {
        System.out.println("\n=== Reservation Form ===");
        System.out.print("Enter PNR Number: ");
        String pnr = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();

        String trainName = getTrainName(trainNo);

        System.out.print("Enter Class Type (Sleeper/AC/General): ");
        String classType = sc.nextLine();

        System.out.print("Enter From: ");
        String from = sc.nextLine();

        System.out.print("Enter To: ");
        String to = sc.nextLine();

        System.out.print("Enter Date of Journey (dd-mm-yyyy): ");
        String date = sc.nextLine();

        Reservation r = new Reservation(pnr, name, trainNo, trainName, classType, from, to, date);
        reservations.put(pnr, r);

        System.out.println("Reservation successful!");
        r.display();
    }

    // Mock Train Names (Normally comes from DB)
    static String getTrainName(String trainNo) {
        switch (trainNo) {
            case "101": return "Express A";
            case "102": return "Superfast B";
            case "103": return "Local C";
            default: return "Unknown Train";
        }
    }

    // --- Cancellation Form ---
    static void cancelReservation() {
        System.out.println("\n=== Cancellation Form ===");
        System.out.print("Enter PNR Number: ");
        String pnr = sc.nextLine();

        if (reservations.containsKey(pnr)) {
            Reservation r = reservations.get(pnr);
            System.out.println("Reservation Found:");
            r.display();

            System.out.print("Do you really want to cancel? (yes/no): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation Cancelled Successfully!");
            } else {
                System.out.println("Cancellation Aborted!");
            }
        } else {
            System.out.println("No reservation found with given PNR!");
        }
    }
}

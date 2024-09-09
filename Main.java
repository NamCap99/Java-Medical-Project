import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MedicalRecordManager manager = new MedicalRecordManager();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println("1. Add new medical record");
            System.out.println("2. Delete medical record");
            System.out.println("3. View all medical records");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Choose the type of medical record:");
                        System.out.println("1. Normal medical record");
                        System.out.println("2. VIP medical record");
                        int type = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        System.out.print("Enter medical record ID (format BA-XXX): ");
                        String medicalRecordId = scanner.nextLine();

                        System.out.print("Enter patient ID (format BN-XXX): ");
                        String patientId = scanner.nextLine();

                        System.out.print("Enter patient name: ");
                        String patientName = scanner.nextLine();

                        System.out.print("Enter admission date (dd/MM/yyyy): ");
                        String admissionDateStr = scanner.nextLine();
                        LocalDate admissionDate = LocalDate.parse(admissionDateStr, formatter);

                        System.out.print("Enter discharge date (dd/MM/yyyy): ");
                        String dischargeDateStr = scanner.nextLine();
                        LocalDate dischargeDate = LocalDate.parse(dischargeDateStr, formatter);

                        System.out.print("Enter reason for admission: ");
                        String reasonForAdmission = scanner.nextLine();

                        if (dischargeDate.isBefore(admissionDate)) {
                            System.out.println("Discharge date must be after or equal to the admission date.");
                            break;
                        }

                        if (type == 1) {
                            System.out.print("Enter hospital fee: ");
                            double hospitalFee = scanner.nextDouble();
                            scanner.nextLine(); // consume newline

                            NormalMedicalRecord normalRecord = new NormalMedicalRecord(
                                    manager.getNextRecordNumber(), medicalRecordId, patientId, patientName,
                                    admissionDate, dischargeDate, reasonForAdmission, hospitalFee);
                            manager.addMedicalRecord(normalRecord);
                        } else if (type == 2) {
                            System.out.print("Choose VIP package (VIP I, VIP II, VIP III): ");
                            String vipPackage = scanner.nextLine();

                            if (!vipPackage.equals("VIP I") && !vipPackage.equals("VIP II") && !vipPackage.equals("VIP III")) {
                                System.out.println("Invalid VIP package. Please choose again.");
                                break;
                            }

                            System.out.print("Enter VIP expiration date (dd/MM/yyyy): ");
                            String vipExpiryStr = scanner.nextLine();
                            LocalDate vipExpiryDate = LocalDate.parse(vipExpiryStr, formatter);

                            VIPMedicalRecord vipRecord = new VIPMedicalRecord(
                                    manager.getNextRecordNumber(), medicalRecordId, patientId, patientName,
                                    admissionDate, dischargeDate, reasonForAdmission, vipPackage, vipExpiryDate);
                            manager.addMedicalRecord(vipRecord);
                        }

                        System.out.println("Medical record added successfully!");

                    } catch (DuplicateMedicalRecordException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the medical record ID to delete: ");
                    String medicalRecordIdToDelete = scanner.nextLine();

                    MedicalRecord recordToDelete = manager.getRecordById(medicalRecordIdToDelete);
                    if (recordToDelete == null) {
                        System.out.println("Medical record ID does not exist.");
                    } else {
                        System.out.println("Are you sure you want to delete this record? (Yes/No)");
                        String confirmation = scanner.nextLine();

                        if (confirmation.equalsIgnoreCase("Yes")) {
                            manager.deleteMedicalRecord(medicalRecordIdToDelete);
                            System.out.println("Medical record deleted successfully!");
                        }
                    }
                    break;

                case 3:
                    System.out.println("List of medical records:");
                    manager.displayRecords();
                    break;

                case 0:
                    System.out.println("Exiting the program.");
                    return;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}

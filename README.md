# Medical Record Management System

This is a Java-based Medical Record Management System designed to manage patient medical records for both normal and VIP patients. The system allows you to add, view, and delete medical records, and store the data in a CSV file.

## Features

- **Add Medical Records**:
    - Add both normal and VIP medical records with details like patient ID, name, admission date, discharge date, and reason for admission.
    - VIP records include additional information such as VIP package (VIP I, VIP II, VIP III) and VIP expiration date.

- **View Medical Records**:
    - Display all stored medical records from the CSV file.

- **Delete Medical Records**:
    - Delete records based on the medical record ID with confirmation.

## Project Structure

- **Main.java**: The main entry point for the application. It provides a simple text-based menu to interact with the system.
- **MedicalRecordManager.java**: Manages all operations related to medical records, including loading data from a CSV file, saving new records, and deleting records.
- **NormalMedicalRecord.java**: Class for normal patient records, includes hospital fee information.
- **VIPMedicalRecord.java**: Class for VIP patient records, includes VIP package and expiration date information.
- **MedicalRecord.java**: An abstract class that represents common properties for all medical records.
- **DuplicateMedicalRecordException.java**: Custom exception class used to handle duplicate medical record IDs.

## Installation

1. Clone the repository or download the project files.
2. Ensure that Java (JDK 8 or higher) is installed on your machine.
3. Open the project in IntelliJ IDEA or another Java IDE.
4. Make sure to have the file `medical_records.csv` in the `data/` directory of the project root.

## How to Run

1. Compile and run the `Main.java` class from IntelliJ IDEA or using the command line.
2. The program will display a menu with the following options:
    - Add a new medical record
    - Delete a medical record
    - View all medical records
    - Exit the program

## CSV File Structure

The medical records are stored in a CSV file with the following format:

- For **normal patients**:
  record_number,medical_record_id,patient_id,patient_name,admission_date,discharge_date,reason_for_admission,hospital_fee

- For **VIP patients**:
  record_number,medical_record_id,patient_id,patient_name,admission_date,discharge_date,reason_for_admission,vip_package,vip_expiry_date


## Requirements

- Java JDK 8 or higher
- IntelliJ IDEA (or any other Java IDE)

## Future Enhancements

- Implement search functionality to find records by patient name or ID.
- Add more detailed validation and error handling.
- Create a GUI interface for better user experience.

## License

This project is open-source and available under the [MIT License](LICENSE).


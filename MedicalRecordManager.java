import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordManager {
    private static final String FILE_PATH = "/data/medical_records.csv";
    private List<MedicalRecord> records = new ArrayList<>();
    private int nextRecordNumber = 1; // Khởi tạo số thứ tự bệnh án bắt đầu từ 1
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MedicalRecordManager() {
        loadRecordsFromCSV();
    }

    // Đọc dữ liệu từ file CSV và tải vào danh sách records
    private void loadRecordsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");  // Tách các giá trị từ dòng CSV

                int recordNumber = Integer.parseInt(values[0]);
                String medicalRecordId = values[1];
                String patientId = values[2];
                String patientName = values[3];
                LocalDate admissionDate = LocalDate.parse(values[4], formatter);
                LocalDate dischargeDate = LocalDate.parse(values[5], formatter);
                String reasonForAdmission = values[6];

                // Phân biệt bệnh án thường và VIP dựa vào số lượng cột
                if (values.length == 8) {  // Bệnh án thường
                    double hospitalFee = Double.parseDouble(values[7]);
                    records.add(new NormalMedicalRecord(recordNumber, medicalRecordId, patientId, patientName,
                            admissionDate, dischargeDate, reasonForAdmission, hospitalFee));
                } else if (values.length == 9) {  // Bệnh án VIP
                    String vipPackage = values[7];
                    LocalDate vipExpiryDate = LocalDate.parse(values[8], formatter);
                    records.add(new VIPMedicalRecord(recordNumber, medicalRecordId, patientId, patientName,
                            admissionDate, dischargeDate, reasonForAdmission, vipPackage, vipExpiryDate));
                }

                nextRecordNumber = recordNumber + 1;  // Cập nhật số thứ tự bệnh án tiếp theo
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }

    // Phương thức lấy số thứ tự bệnh án tiếp theo
    public int getNextRecordNumber() {
        return nextRecordNumber;
    }

    // Thêm bệnh án mới và lưu vào file CSV
    public void addMedicalRecord(MedicalRecord record) throws DuplicateMedicalRecordException {
        for (MedicalRecord r : records) {
            if (r.getMedicalRecordId().equals(record.getMedicalRecordId())) {
                throw new DuplicateMedicalRecordException("Duplicate Medical Record");
            }
        }
        records.add(record);
        nextRecordNumber++; // Tăng số thứ tự bệnh án sau khi thêm mới
        saveRecordsToCSV();
    }

    // Lưu toàn bộ danh sách bệnh án vào file CSV
    private void saveRecordsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (MedicalRecord record : records) {
                bw.write(record.toCSV());
                bw.newLine();
            }
            System.out.println("Data saved into file CSV.");
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    // Xóa bệnh án và lưu lại file CSV
    public void deleteMedicalRecord(String medicalRecordId) {
        records.removeIf(record -> record.getMedicalRecordId().equals(medicalRecordId));
        saveRecordsToCSV();
    }

    // Hiển thị danh sách bệnh án
    public void displayRecords() {
        for (MedicalRecord record : records) {
            System.out.println(record.toCSV());
        }
    }

    // Tìm kiếm bệnh án theo mã
    public MedicalRecord getRecordById(String medicalRecordId) {
        for (MedicalRecord record : records) {
            if (record.getMedicalRecordId().equals(medicalRecordId)) {
                return record;
            }
        }
        return null;
    }
}

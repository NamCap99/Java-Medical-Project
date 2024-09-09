import java.time.LocalDate;
import java.util.Date;

public class VIPMedicalRecord extends MedicalRecord {
    private String vipPackage;
    private LocalDate vipExpiryDate;

    public VIPMedicalRecord(int recordNumber, String medicalRecordId, String patientId, String patientName,
                            LocalDate admissionDate, LocalDate dischargeDate, String reasonForAdmission,
                            String vipPackage, LocalDate vipExpiryDate) {
        super(recordNumber, medicalRecordId, patientId, patientName, admissionDate, dischargeDate, reasonForAdmission);
        this.vipPackage = vipPackage;
        this.vipExpiryDate = vipExpiryDate;
    }

    @Override
    public String toCSV() {
        return recordNumber + "," + medicalRecordId + "," + patientId + "," + patientName + "," + admissionDate + "," +
                dischargeDate + "," + reasonForAdmission + "," + vipPackage + "," + vipExpiryDate;
    }
}


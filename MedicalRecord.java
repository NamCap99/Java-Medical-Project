import java.time.LocalDate;

public abstract class MedicalRecord {
    protected int recordNumber;
    protected String medicalRecordId;
    protected String patientId;
    protected String patientName;
    protected LocalDate admissionDate;
    protected LocalDate dischargeDate;
    protected String reasonForAdmission;

    public MedicalRecord(int recordNumber, String medicalRecordId, String patientId, String patientName,
                         LocalDate admissionDate, LocalDate dischargeDate, String reasonForAdmission) {
        this.recordNumber = recordNumber;
        this.medicalRecordId = medicalRecordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.reasonForAdmission = reasonForAdmission;
    }

    public abstract String toCSV();

    public int getRecordNumber() {
        return recordNumber;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }
}

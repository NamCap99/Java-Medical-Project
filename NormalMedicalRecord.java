import java.time.LocalDate;

public class NormalMedicalRecord extends MedicalRecord {
    private double hospitalFee;

    public NormalMedicalRecord(int recordNumber, String medicalRecordId, String patientId, String patientName,
                               LocalDate admissionDate, LocalDate dischargeDate, String reasonForAdmission, double hospitalFee) {
        super(recordNumber, medicalRecordId, patientId, patientName, admissionDate, dischargeDate, reasonForAdmission);
        this.hospitalFee = hospitalFee;
    }

    @Override
    public String toCSV() {
        return recordNumber + "," + medicalRecordId + "," + patientId + "," + patientName + "," + admissionDate + "," +
                dischargeDate + "," + reasonForAdmission + "," + hospitalFee;
    }
}

package by.epam.training.task06.entity;

import java.util.List;


public class User {
    private int id;
    private String status;
    private String email;
    private String password;
    private String name;
    private double schoolCertificateScore;
    private List<Certificate> certificates;
    private int facultyId;

    public User() {

    }

    public User(String status, String email, String password, String name) {
        this.status = status;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(int id, String status, String email, String password, String name, double schoolCertificateScore,
                List<Certificate> certificates, int facultyId) {
        this.id = id;
        this.status = status;
        this.email = email;
        this.password = password;
        this.name = name;
        this.schoolCertificateScore = schoolCertificateScore;
        this.certificates = certificates;
        this.facultyId = facultyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSchoolCertificateScore() {
        return schoolCertificateScore;
    }

    public void setSchoolCertificateScore(double schoolCertificateScore) {
        this.schoolCertificateScore = schoolCertificateScore;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (facultyId != user.facultyId) return false;
        if (id != user.id) return false;
        if (Double.compare(user.schoolCertificateScore, schoolCertificateScore) != 0) return false;
        if (certificates != null ? !certificates.equals(user.certificates) : user.certificates != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (status != null ? !status.equals(user.status) : user.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(schoolCertificateScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (certificates != null ? certificates.hashCode() : 0);
        result = 31 * result + facultyId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", status='").append(status).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", schoolCertificateScore=").append(schoolCertificateScore);
        sb.append(", certificates=").append(certificates);
        sb.append(", facultyId=").append(facultyId);
        sb.append('}');
        return sb.toString();
    }
}

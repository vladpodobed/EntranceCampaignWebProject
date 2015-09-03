package by.epam.training.task06.entity;


public class Faculty {
    private int id;
    private String name;
    private int enrollment;

    public Faculty() {
    }

    public Faculty(int id, String name, int enrollment) {
        this.id = id;
        this.name = name;
        this.enrollment = enrollment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        if (enrollment != faculty.enrollment) return false;
        if (id != faculty.id) return false;
        if (name != null ? !name.equals(faculty.name) : faculty.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + enrollment;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Faculty{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", enrollment=").append(enrollment);
        sb.append('}');
        return sb.toString();
    }
}

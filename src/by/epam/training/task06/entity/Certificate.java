package by.epam.training.task06.entity;


public class Certificate {
    private int id;
    private int userId;
    private int disciplineId;
    private int result;

    public Certificate() {

    }

    public Certificate(int id, int userId, int disciplineId, int result) {
        this.id = id;
        this.userId = userId;
        this.disciplineId = disciplineId;
        this.result = result;
    }

    public Certificate(int userId, int disciplineId, int result) {
        this.userId = userId;
        this.disciplineId = disciplineId;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Certificate that = (Certificate) o;

        if (disciplineId != that.disciplineId) return false;
        if (id != that.id) return false;
        if (result != that.result) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + userId;
        result1 = 31 * result1 + disciplineId;
        result1 = 31 * result1 + result;
        return result1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Certificate{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", disciplineId=").append(disciplineId);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}

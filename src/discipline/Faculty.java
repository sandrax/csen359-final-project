package discipline;

public interface Faculty {
    public void setSuperior(Faculty superior);
    public void handleReport(DisciplinaryReport report);
}

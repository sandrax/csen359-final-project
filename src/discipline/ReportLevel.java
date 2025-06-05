package discipline;

public enum ReportLevel {
    MINOR(1),
    MAJOR(2),
    SEVERE(3),
    ILLEGAL(4),
    UNCATEGORIZED(5);

    private final int levelNum;

    ReportLevel(int num) {
        this.levelNum = num;
    }

    public boolean isWorseThan(ReportLevel other) {
        return this.levelNum > other.levelNum;
    }

    public boolean isBetterThan(ReportLevel other) {
        return this.levelNum < other.levelNum;
    }
}

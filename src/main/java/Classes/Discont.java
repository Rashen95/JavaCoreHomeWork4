package Classes;

public enum Discont {
    NONE(0), BRONZE(5), SILVER(10), GOLD(15), PLATINUM(20);
    private final int code;

    Discont(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
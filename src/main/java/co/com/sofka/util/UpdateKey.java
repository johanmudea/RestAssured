package co.com.sofka.util;

public enum UpdateKey {
    NAME("[name]"),
    JOB("[job]");

    private final String value;

    UpdateKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

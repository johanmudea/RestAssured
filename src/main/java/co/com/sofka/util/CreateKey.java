package co.com.sofka.util;

public enum CreateKey {
    NAME("[name]"),
    JOB("[job]");

    private final String value;

    CreateKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

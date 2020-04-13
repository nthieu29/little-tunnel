package little.tunnel.ui;

public enum ServerStatus {
    RUNNING("Running"),
    NOT_RUNNING("Not running");
    private String value;

    ServerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package ro.shiftleft.apiTestingDemo.apiClient;

public enum Endpoints {
    USERS("/users"),
    POSTS("/posts"),
    COMMENTS("/comments");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
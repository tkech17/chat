package ge.edu.freeuni.chat.server.model.user;

public final class ChatRequest {

    private String currentUsername;
    private String dstUsername;


    public ChatRequest() {
    }

    public ChatRequest(String currentUsername, String dstUsername) {
        this.currentUsername = currentUsername;
        this.dstUsername = dstUsername;
    }

    public String getDstUsername() {
        return dstUsername;
    }

    public void setDstUsername(String dstUsername) {
        this.dstUsername = dstUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class user {
    private String username;
    private String password;
    private List<Integer> quizScores;
    private LocalDateTime lastLogin;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
        this.quizScores = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addQuizScore(int score) {
        quizScores.add(score);
    }

    public List<Integer> getQuizScores() {
        return quizScores;
    }

    public void setLastLogin() {
        lastLogin = LocalDateTime.now();
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
}
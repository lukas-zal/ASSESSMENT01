import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, user> users = new HashMap<>();
    private static final String API_URL = "https://opentdb.com/api.php?amount=10";

//    QUIZZ MENU

    public static void main(String[] args) throws IOException, JSONException {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Play again");
            System.out.println("4. Exit");
            System.out.print("Choose an option(PLEASE SELECT A NUMBER FROM 1-4): ");
            int choice = Integer.parseInt(reader.readLine());

            if (choice == 1) {
                loginUser();
                //                loadHistory();
            } else if (choice == 2) {
                registerUser();
            } else if (choice == 3) {
                loginUser();
                //                loadHistory();
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//    LOGIN
    private static void loginUser() throws IOException, JSONException {
        System.out.print("Enter username: ");
        String username = reader.readLine();
        System.out.print("Enter password: ");
        String password = reader.readLine();

        if (users.containsKey(username)) {
            user user = users.get(username);
            if (user.checkPassword(password)) {
                System.out.println("Welcome back, " + username + "!");
                user.setLastLogin();
                playQuiz(user);
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("User not found. Please register first.");
        }
    }

//    REGISTER
    private static void registerUser() throws IOException, JSONException {
        System.out.print("Enter username: ");
        String username = reader.readLine();
        System.out.print("Enter password: ");
        String password = reader.readLine();

        user newUser = new user(username, password);
        users.put(username, newUser);
        System.out.println("Welcome, " + username + "! You registered successfully.");
        playQuiz(newUser);
    }


//    Quizz game code (den ksero pos na paro ta correct kai ta incorrect answers kai na ta kano randomize gia
//    na mhn bgainei panta correct answer to 4)

    private static void playQuiz(user user) throws IOException, JSONException {
        JSONArray questions = getQuestionsFromAPI();
        int score = 0;
        System.out.println("Let's start the quiz!");
        for (int i = 0; i < questions.length(); i++) {
            JSONObject questionObject = questions.getJSONObject(i);
            String question = questionObject.getString("question");
            String correctAnswer = questionObject.getString("correct_answer");
            JSONArray incorrectAnswers = questionObject.getJSONArray("incorrect_answers");
            String[] options = new String[incorrectAnswers.length() + 1];
            for (int j = 0; j < incorrectAnswers.length(); j++) {
                options[j] = incorrectAnswers.getString(j);
            }
            options[options.length - 1] = correctAnswer;

            QuizQuestion quizQuestion = new QuizQuestion(question, options, correctAnswer);

            System.out.println("Question " + (i + 1) + ": " + quizQuestion.getQuestion());
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            System.out.print("Your answer (PLEASE WRITE THE CORRECT ANSWER'S NUMBER): ");
            int userAnswer = Integer.parseInt(reader.readLine()) - 1;
            if (options[userAnswer].equals(correctAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer is: " + correctAnswer);
            }
        }

        user.addQuizScore(score);
        System.out.println("End of Quizz. Your score: " + score + "/" + questions.length());
    }

    private static JSONArray getQuestionsFromAPI() throws IOException, JSONException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONArray("results");
        } else {
            System.out.println("Failed to retrieve questions");
            return null;
        }
    }
}
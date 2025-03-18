import java.util.*;

class Question {
    String question, optionA, optionB, optionC, optionD, correctAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

public class QuizApplication {
    private List<Question> questions;
    private int score;

    public QuizApplication() {
        questions = new ArrayList<>();
        score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", "A. London", "B. Berlin", "C. Paris", "D. Rome", "C"));
        questions.add(new Question("Which data type is used to store decimal numbers in Java?", "A. int", "B. double", "C. char", "D. boolean", "B"));
        questions.add(new Question("What keyword is used to define a class in Java?", "A. class", "B. struct", "C. function", "D. object", "A"));
    }

    public void startQuiz() {
        Scanner sc = new Scanner(System.in);

        for (Question q : questions) {
            System.out.println("\n" + q.question);
            System.out.println(q.optionA);
            System.out.println(q.optionB);
            System.out.println(q.optionC);
            System.out.println(q.optionD);
            System.out.print("Your Answer: ");
            String userAnswer = sc.nextLine().trim();

            if (q.checkAnswer(userAnswer)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct Answer: " + q.correctAnswer);
            }
        }

        System.out.println("\nQuiz Over! Your Score: " + score + "/" + questions.size());
        sc.close();
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}

import java.util.Scanner;

public class Trivia {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Question[] questions = new Question[10];

        questions[0] = new Question("How many colors are there in a rainbow?", "5", "6", "7", "8", "C");
        questions[1] = new Question("Area 51 is located in which US state?", "Texas", "Nevada", "New Mexico", "Arizona", "B");
        questions[2] = new Question("What geometric shape is generally used for stop signs?", "Hexagon", "Pentagon", "Octagon", "Square", "C");
        questions[3] = new Question("What colour is the 'Ex' in FedEx Ground?", "Blue", "Purple", "Orange", "Green", "D");
        questions[4] = new Question("Who is the author of Jurassic Park?", "Stephen King", "Michael Crichton", "J.R.R. Tolkien", "George R.R. Martin", "B");
        questions[5] = new Question("What is the world's most expensive spice by weight?", "Saffron", "Vanilla", "Cardamom", "Cinnamon", "A");
        questions[6] = new Question("What alcoholic drink is mainly made from juniper berries?", "Vodka", "Rum", "Gin", "Tequila", "C");
        questions[7] = new Question("What is the currency of Poland?", "Euro", "Złoty", "Krona", "Ruble", "B");
        questions[8] = new Question("What is Cynophobia the fear of?", "Cats", "Spiders", "Heights", "Dogs", "D");
        questions[9] = new Question("In what year was McDonald's founded?", "1945", "1955", "1965", "1975", "B");

        Player p1 = new Player("Player A");
        Player p2 = new Player("Player B");

        System.out.println("=== RACE TO 10 POINTS ===");
        System.out.println("Correct = +1 | Incorrect = -1");

        int qIndex = 0;

        while (p1.score < 10 && p2.score < 10) {
            Question currentQ = questions[qIndex];

            System.out.println("\n--- Current Score -> " + p1.name + ": " + p1.score + " | " + p2.name + ": " + p2.score + " ---");
            System.out.println(currentQ.prompt);
            System.out.println("A) " + currentQ.choiceA);
            System.out.println("B) " + currentQ.choiceB);
            System.out.println("C) " + currentQ.choiceC);
            System.out.println("D) " + currentQ.choiceD);

            String p1Answer = getValidAnswer(p1, scanner);
            String p2Answer = getValidAnswer(p2, scanner);

            System.out.println("\n--- Round Results ---");

            if (p1Answer.equals(currentQ.correctLetter)) {
                p1.addPoint();
                System.out.println(p1.name + " is Correct! (+1 point)");
            } else {
                p1.losePoint();
                System.out.println(p1.name + " is Incorrect. (-1 point)");
            }

            if (p2Answer.equals(currentQ.correctLetter)) {
                p2.addPoint();
                System.out.println(p2.name + " is Correct! (+1 point)");
            } else {
                p2.losePoint();
                System.out.println(p2.name + " is Incorrect. (-1 point)");
            }

            System.out.println("The correct answer was " + currentQ.correctLetter + ".");

            qIndex++;

            if (qIndex >= questions.length) {
                qIndex = 0;
            }
        }

        System.out.println("\n=== GAME OVER ===");

        p1.displayScore();
        p2.displayScore();

        if (p1.score >= 10 && p2.score >= 10 && p1.score == p2.score) {
            System.out.println("It's a tie!");
        } else if (p1.score >= 10) {
            System.out.println(p1.name + " Wins!");
        } else {
            System.out.println(p2.name + " Wins!");
        }

        scanner.close();
    }

    public static String getValidAnswer(Player player, Scanner scanner) {
        System.out.print(player.name + ", your answer (A/B/C/D): ");

        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("A") || input.equals("B") ||
                input.equals("C") || input.equals("D")) {
                return input;
            }

            System.out.print("Don't be stupid, please enter A, B, C, or D: ");
        }
    }
}

class Question {
    String prompt;
    String choiceA;
    String choiceB;
    String choiceC;
    String choiceD;
    String correctLetter;

    public Question(String p, String a, String b, String c, String d, String answer) {
        this.prompt = p;
        this.choiceA = a;
        this.choiceB = b;
        this.choiceC = c;
        this.choiceD = d;
        this.correctLetter = answer;
    }
}

class Player {
    String name;
    int score;

    public Player(String n) {
        this.name = n;
        this.score = 0;
    }

    public void addPoint() {
        score++;
    }

    public void losePoint() {
        score--;
    }

    public void displayScore() {
        System.out.println(name + ": " + score);
    }
}

import java.util.Scanner;

public class BasicTriviaGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Standard Array - no ArrayList or Collections needed
        Question[] questions = new Question[10];
        
        // Questions from "Untitled document.pdf"
        questions[0] = new Question("How many colors are there in a rainbow?", "5", "6", "7", "8", "C"); //[cite: 1]
        questions[1] = new Question("Area 51 is located in which US state?", "Texas", "Nevada", "New Mexico", "Arizona", "B"); //[cite: 1]
        questions[2] = new Question("What geometric shape is generally used for stop signs?", "Hexagon", "Pentagon", "Octagon", "Square", "C"); //[cite: 1]
        questions[3] = new Question("What colour is the 'Ex' in FedEx Ground?", "Blue", "Purple", "Orange", "Green", "D"); //[cite: 1]
        questions[4] = new Question("Who is the author of Jurrasic Park?", "Stephen King", "Michael Crichton", "J.R.R. Tolkien", "George R.R. Martin", "B"); //[cite: 1]
        questions[5] = new Question("What is the world's most expensive spice by weight?", "Saffron", "Vanilla", "Cardamom", "Cinnamon", "A"); //[cite: 1]
        questions[6] = new Question("What alcoholic drink is mainly made from juniper berries?", "Vodka", "Rum", "Gin", "Tequila", "C"); //[cite: 1]
        questions[7] = new Question("What is the currency of Poland?", "Euro", "Złoty", "Krona", "Ruble", "B"); //[cite: 1]
        questions[8] = new Question("What is Cynophobia the fear of?", "Cats", "Spiders", "Heights", "Dogs", "D"); //[cite: 1]
        questions[9] = new Question("In what year was McDonald's founded?", "1945", "1955", "1965", "1975", "B"); //[cite: 1]

        Player p1 = new Player("Player A");
        Player p2 = new Player("Player B");

        System.out.println("=== RACE TO 10 POINTS ===");
        System.out.println("Correct = +1 | Incorrect = -1");

        int qIndex = 0; // Keeps track of which question we are on

        // Loop until someone reaches 10 points
        while (p1.score < 10 && p2.score < 10) {
            
            // Player A's turn
            askQuestion(p1, questions[qIndex], scanner);
            qIndex++;
            if (qIndex >= questions.length) qIndex = 0; // Restart array if we run out
            
            if (p1.score >= 10) break; // Stop immediately if Player A wins

            // Player B's turn
            askQuestion(p2, questions[qIndex], scanner);
            qIndex++;
            if (qIndex >= questions.length) qIndex = 0; // Restart array if we run out
        }

        // Announce Winner
        System.out.println("\n=== GAME OVER ===");
        System.out.println(p1.name + ": " + p1.score);
        System.out.println(p2.name + ": " + p2.score);

        if (p1.score >= 10) {
            System.out.println(p1.name + " Wins!");
        } else {
            System.out.println(p2.name + " Wins!");
        }

        scanner.close();
    }

    // A simple method to handle asking the question and updating the score
    public static void askQuestion(Player player, Question q, Scanner scanner) {
        System.out.println("\n" + player.name + "'s Turn (Score: " + player.score + ")");
        System.out.println(q.prompt);
        System.out.println("A) " + q.choiceA);
        System.out.println("B) " + q.choiceB);
        System.out.println("C) " + q.choiceC);
        System.out.println("D) " + q.choiceD);
        System.out.print("Your answer (A/B/C/D): ");

        String input = scanner.nextLine().trim().toUpperCase();

        if (input.equals(q.correctLetter)) {
            player.score++;
            System.out.println("Correct! +1 point.");
        } else {
            player.score--;
            System.out.println("Incorrect! The answer was " + q.correctLetter + ". -1 point.");
        }
    }
}

// Basic class to hold question data
class Question {
    String prompt, choiceA, choiceB, choiceC, choiceD, correctLetter;

    public Question(String p, String a, String b, String c, String d, String answer) {
        this.prompt = p;
        this.choiceA = a;
        this.choiceB = b;
        this.choiceC = c;
        this.choiceD = d;
        this.correctLetter = answer;
    }
}

// Basic class to hold player data
class Player {
    String name;
    int score;

    public Player(String n) {
        this.name = n;
        this.score = 0; // Everyone starts at 0
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class  StudentGradeCalculator {
	public static double calculateAverage(ArrayList<Double> scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }
	
    public static String[] determineGrade(double average) {
        String grade;
        String feedback;
        
        if (average >= 90) {
            grade = "A";
            feedback = "Excellent work!";
        } else if (average >= 80) {
            grade = "B";
            feedback = "Good job!";
        } else if (average >= 70) {
            grade = "C";
            feedback = "You passed.";
        } else if (average >= 60) {
            grade = "D";
            feedback = "You need to improve.";
        } else {
            grade = "F";
            feedback = "Failed. Better luck next time.";
        }
        
        return new String[]{grade, feedback};
    }
	
	 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         try {
            System.out.print("Enter the number of students: ");
            int numStudents = Integer.parseInt(scanner.nextLine());
           
            ArrayList<Double> classScores = new ArrayList<>();
			
			for (int i = 0; i < numStudents; i++) {
                System.out.print("\nEnter the name of student " + (i + 1) + ": ");
                String studentName = scanner.nextLine();
                
                System.out.print("How many assignments for " + studentName + "? ");
                int numAssignments = Integer.parseInt(scanner.nextLine());	
			
				 ArrayList<Double> scores = new ArrayList<>();
             for (int j = 0; j < numAssignments; j++) {
                    System.out.print("Enter score for assignment " + (j + 1) + ": ");
                    double score = Double.parseDouble(scanner.nextLine());
                    scores.add(score);
                }
				
				double average = calculateAverage(scores);
                String[] gradeFeedback = determineGrade(average);
				
				classScores.add(average);
				
				System.out.println("\nStudent: " + studentName);
                System.out.printf("Average score: %.2f%n", average);
                System.out.println("Grade: " + gradeFeedback[0]);
                System.out.println("Feedback: " + gradeFeedback[1]);
				}
				double classAverage = calculateAverage(classScores);
				System.out.printf("\nClass average score: %.2f%n", classAverage);
            
			    double highestScore = classScores.stream().mapToDouble(v -> v).max().orElse(0);
				double lowestScore = classScores.stream().mapToDouble(v -> v).min().orElse(0);
				System.out.printf("Highest score in class: %.2f%n", highestScore);
				System.out.printf("Lowest score in class: %.2f%n", lowestScore);
            
			} catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values for scores and number of students.");
			}
        
			scanner.close();
		}
                
                
}
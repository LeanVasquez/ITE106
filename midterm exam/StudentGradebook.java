import java.util.Scanner;
import java.util.Arrays;

public class StudentGradebook {

    public static double calculateAverage(double[] scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

    public static String determineGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void displayGradebook(String[] studentNames, double[][] grades, double[] averages, String[] letterGrades) {
        System.out.printf("%-15s %-20s %-15s %-10s%n", "Student", "Scores", "Average", "Grade");
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < studentNames.length; i++) {
            System.out.printf("%-15s ", studentNames[i]);
            System.out.print("[ ");
            for (int j = 0; j < grades[i].length; j++) {
                System.out.printf("%.2f ", grades[i][j]);
            }
            System.out.print("] ");
            System.out.printf("%-15.2f %-10s%n", averages[i], letterGrades[i]);
        }
    }

    public static void sortStudentsByAverage(String[] studentNames, double[][] grades, double[] averages, String[] letterGrades) {
        for (int i = 0; i < averages.length - 1; i++) {
            for (int j = 0; j < averages.length - i - 1; j++) {
                if (averages[j] < averages[j + 1]) {

                    double tempAvg = averages[j];
                    averages[j] = averages[j + 1];
                    averages[j + 1] = tempAvg;

                    String tempName = studentNames[j];
                    studentNames[j] = studentNames[j + 1];
                    studentNames[j + 1] = tempName;

                    String tempGrade = letterGrades[j];
                    letterGrades[j] = letterGrades[j + 1];
                    letterGrades[j + 1] = tempGrade;

                    double[] tempScores = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = tempScores;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = sc.nextInt();

        System.out.print("Enter the number of assignments: ");
        int numAssignments = sc.nextInt();

        String[] studentNames = new String[numStudents];
        double[][] grades = new double[numStudents][numAssignments];
        double[] averages = new double[numStudents];
        String[] letterGrades = new String[numStudents];

        for (int i = 0; i < numStudents; i++) {
            sc.nextLine(); 
            System.out.print("Enter the name of student " + (i + 1) + ": ");
            studentNames[i] = sc.nextLine();

            for (int j = 0; j < numAssignments; j++) {
                System.out.print("Enter score for " + studentNames[i] + " for assignment " + (j + 1) + ": ");
                grades[i][j] = sc.nextDouble();
            }

            averages[i] = calculateAverage(grades[i]);
            letterGrades[i] = determineGrade(averages[i]);
        }

        System.out.println("\nGradebook before sorting by average score:");
        displayGradebook(studentNames, grades, averages, letterGrades);

        sortStudentsByAverage(studentNames, grades, averages, letterGrades);
      
        System.out.println("\nGradebook after sorting by average score:");
        displayGradebook(studentNames, grades, averages, letterGrades);

        double highestScore = Arrays.stream(averages).max().orElse(0);
        double lowestScore = Arrays.stream(averages).min().orElse(0);
        System.out.printf("\nHighest average score in class: %.2f%n", highestScore);
        System.out.printf("Lowest average score in class:  %.2f%n", lowestScore);

        sc.close();
    }
}

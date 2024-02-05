
/* GROUP MEMBERS NAME AND ID
SELEHADIN ABDU RU 0596/13
KEDIR SEID RU 0602/13
SEID GENIYU RU 2584/13
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DormManagementSystem {

  private static final String FILE_NAME = "dorm_data.txt";
  private static File file = new File(FILE_NAME);

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int choice = 0;

    while (choice != 4) {
      System.out.println("\nDorm Management System");
      System.out.println("1. Add Student");
      System.out.println("2. Display Students");
      System.out.println("3. Search Student");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");
      choice = scan.nextInt();

      switch (choice) {
        case 1:
          addStudent();
          break;
        case 2:
          displayStudents();
          break;
        case 3:
          searchStudent();
          break;
        case 4:
          System.out.println("Exiting the system...");
          break;
        default:
          System.out.println("Invalid choice. Try again.");
          break;
      }
    }
  }

  private static void addStudent() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the student name: ");
    String name = scan.nextLine();
    System.out.print("Enter the student ID: ");
    String id = scan.nextLine();
    while (studentExists(id)) {
      System.out.println("A student with the same ID already exists. Please enter a different ID.");
      System.out.print("Enter the student ID: ");
      id = scan.nextLine();
    }
    System.out.print("Enter the student dorm room number: ");
    String room = scan.nextLine();

    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      PrintWriter writer = new PrintWriter(new FileWriter(file, true));
      writer.println(name + "," + id + "," + room);
      writer.close();
      System.out.println("Student added successfully.");
    } catch (IOException e) {
      System.out.println("An error occurred while adding the student.");
    }
  }

  private static boolean studentExists(String id) {
    try {
      Scanner scan = new Scanner(file);
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        if (line.split(",")[1].equals(id)) {
          scan.close();
          return true;
        }
      }
      scan.close();
    } catch (FileNotFoundException e) {
      // No students found, so the student does not exist
    }
    return false;
  }

  private static void displayStudents() {
    try {

      Scanner scan = new Scanner(file);
      System.out.println("\nStudent List");
      System.out.println("Name\t\tID\t\tRoom");
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        String[] parts = line.split(",");
        System.out.println(parts[0] + "\t\t" + parts[1] + "\t\t" + parts[2]);
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("No students found.");
    }
  }

  private static void searchStudent() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the student ID: ");
    String id = scan.nextLine();
    try {
      Scanner fileScan = new Scanner(file);
      boolean found = false;
      while (fileScan.hasNextLine()) {
        String line = fileScan.nextLine();
        String[] parts = line.split(",");
        if (parts[1].equals(id)) {
          System.out.println("\nStudent Found");
          System.out.println("Name: " + parts[0]);
          System.out.println("ID: " + parts[1]);
          System.out.println("Room: " + parts[2]);
          found = true;
          break;
        }
      }
      fileScan.close();
      if (!found) {
        System.out.println("No student found with the given ID.");
      }
    } catch (FileNotFoundException e) {
      System.out.println("No students found.");
    }
  }
}
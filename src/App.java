import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;     
import java.io.FileWriter; 
import java.io.BufferedWriter;  
import java.io.FileReader;   
import java.io.BufferedReader;  
import java.io.IOException;



public class App {
    private static final String FILENAME = "tasks.txt";
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        ArrayList<String> al=new ArrayList<>();
        
        loadtasks(al);

        boolean cont=true;
        do{
           System.out.println("\nEnter Your Choice:");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Show tasks");
            System.out.println("4. Mark task as completed");
            System.out.println("5. Exit");
            
            String choice=sc.next();
            sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter a task to add");
                    String task=sc.nextLine();
                    al.add(task);
                    saveTasks(al);
                    break;
                case "2":
                 if (al.isEmpty()) {
                 System.out.println("List is empty, nothing to delete.");
                   } else {
                       try { 
                           System.out.println("Enter the task number to delete (e.g., 1, 2...):");
                           int taskNumber = sc.nextInt();
                           sc.nextLine(); 

                           if (taskNumber > 0 && taskNumber <= al.size()) {
                               String removedTask = al.remove(taskNumber - 1);
                               System.out.println("'" + removedTask + "' deleted successfully.");
                               saveTasks(al);
                           } else {
                               System.out.println("Invalid task number.");
                           }
                       } catch (java.util.InputMismatchException e) { 
                           System.out.println("Error: Please enter a valid number.");
                           sc.nextLine(); 
                       }
                   }
                    break;
               case "3": 
                    if (al.isEmpty()) {
                        System.out.println("Your to-do list is empty.");
                    } else {
                        System.out.println("Your To-Do List:");
                        for (int i = 0; i < al.size(); i++) {
                            System.out.println((i + 1) + ". " + al.get(i));
                        }
                    }
                    break;
                   
                case "4":
                  if (al.isEmpty()) {
                      System.out.println("No tasks available to mark.");
                  } else {
                      System.out.println("Enter the task number to mark as completed:");
                      int taskNumber = sc.nextInt();
                      sc.nextLine();

                      if (taskNumber > 0 && taskNumber <= al.size()) {
                          String currentTask = al.get(taskNumber - 1);

                          if (currentTask.startsWith("[✔]")) {
                              System.out.println("Task is already marked as completed.");
                          } else {
                              al.set(taskNumber - 1, "[✔] " + currentTask);
                              System.out.println(" Task marked as completed!");
                              saveTasks(al);
                          }
                      } else {
                          System.out.println("Invalid task number.");
                      }
                  }
                  break;
                  case "5":
                    saveTasks(al);
                    cont = false;
                    System.out.println(" Tasks saved. Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }


        }while(cont);

        sc.close();
    }
    private static void saveTasks(ArrayList<String> al){
        try (FileWriter fw = new FileWriter(FILENAME);
             BufferedWriter bw=new BufferedWriter(fw)){
                 for(String tasks:al){
                    bw.write(tasks);
                    bw.newLine();
                 }
             } catch (IOException e) {
                System.out.println("Error "+ e.getMessage());
             }

    }
    private static void loadtasks(ArrayList<String> al){
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("No saved tasks found. Starting a new list.");
            return; 
        }       
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            
            String line;
            while ((line = br.readLine()) != null) {
                al.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }

    }


}
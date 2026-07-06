import com.sun.security.jgss.GSSUtil;

import  java.util.Scanner;

public class VendingMachineFactory {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Variables
        boolean isDone = false;
        int choice;
        RegularVM machine = null;

        // Welcome Message
        System.out.println("Hello World!");

        // While Loop for program execution
        while (!isDone)
        {
            // The usual print stuff
            System.out.println("**************************************");
            System.out.println("Welcome to the Vending Machine Factory");
            System.out.println("**************************************");
            System.out.println("1. Create Vending Machine");
            System.out.println("2. Modify Vending Machine");
            System.out.println("3. Test Vending Machine");
            System.out.println("4. Exit Program");
            System.out.println("**************************************");

            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            // Switch statement for everything
            switch(choice)
            {
                case 1:
                    String name = CreateVendingMachine();
                    machine = new RegularVM(name);
                    System.out.println("Vending Machine created with the name: " + name);

                    System.out.println();
                    break;
                case 2:
                    if (machine != null)
                        ModifyVendingMachine(machine);
                    else
                        System.out.println("Vending Machine not created");

                    System.out.println();
                    break;
                case 4:
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
        System.out.println("Have a nice day!");
    }

    static String CreateVendingMachine()
    {
        String name = "";

        System.out.println("Create Vending Machine...");
        System.out.print("Enter Vending Machine Name: ");
        name = scanner.next();

        return name;
    }

    static void ModifyVendingMachine(RegularVM machine)
    {
        boolean modificationDone = false;
        int choice;

        while (!modificationDone)
        {
            System.out.println("----------------------------");
            System.out.println("Vending Machine: " + machine.getName());
            System.out.println("----------------------------");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Modify Item");
            System.out.println("4. Return to Menu");
            System.out.println("----------------------------");
            System.out.print("Enter your choice (1-4): ");

            choice = scanner.nextInt();
        }
    }
}

import  java.util.Scanner;

public class VendingMachineFactory {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Variables
        boolean isDone = false;
        boolean isTesting = false;
        int choice;
        RegularVM machine = null;

        // Welcome Message
        System.out.println("Hello World!");

        // While Loop for program execution
        while (!isDone)
        {
            // The usual print stuff
            System.out.println("********************************************");
            System.out.println("Welcome to the Pizza Vending Machine Factory");
            if (machine != null) { System.out.println("Current Machine: " + machine.getName()); }
            System.out.println("********************************************");
            System.out.println("1. Create a Vending Machine");
            System.out.println("2. Test a Vending Machine");
            System.out.println("3. Exit Program");
            System.out.println("********************************************");

            System.out.print("Enter your choice (1-3): ");
            choice = scanner.nextInt();

            scanner.nextLine();

            // Switch statement for everything
            switch(choice)
            {
                // region case 1 - Create a Vending Machine
                case 1:
                    System.out.println();
                    System.out.println("-------------------------------------------");
                    System.out.println("What vending machine do you want to create?");
                    System.out.println("1. Regular Vending Machine");
                    System.out.println("2. Special Vending Machine");
                    System.out.println("-------------------------------------------");
                    System.out.print("Enter your choice (1-2): ");
                    choice = scanner.nextInt();

                    scanner.nextLine();

                    switch(choice)
                    {
                        case 1:
                            if (machine != null)
                            {
                                System.out.println("Deleted Vending Machine with name: " + machine.getName());
                            }
                            System.out.print("Name your Vending Machine: ");
                            String name = scanner.nextLine();
                            machine = new RegularVM(name);
                            System.out.println("Created Vending Machine with name: " + name);
                            break;

                        case 2:
                            System.out.println("This item is currently unavailable.");
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                    System.out.println();
                    break;
                    // endregion

                // region case 2 - Test a Vending Machine
                case 2:
                    if (machine != null)
                    {
                        System.out.println("Going to testing menu...");
                        isTesting = true;

                        while (isTesting)
                        {
                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println("What do you want to test?");
                            System.out.println("1. Vending Features of " + machine.getName());
                            System.out.println("2. Maintenance Features of " + machine.getName());
                            System.out.println("3. Exit Program");
                            System.out.println("-------------------------------------------");
                            System.out.print("Enter your choice (1-3): ");
                            choice = scanner.nextInt();

                            switch(choice)
                            {
                                case 1:
                                    System.out.println();
                                    RunVendingMachine(machine);
                                    break;
                                case 2:
                                    System.out.println("Maintenance");
                                    break;
                                case 3:
                                    System.out.println("Returning to main menu...");
                                    isTesting = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                                    break;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Machine to test does not exist.");
                    }

                    System.out.println();
                    break;
                    // endregion

                // region case 3 - End Program
                case 3:
                    isDone = true;
                    break;
                    // endregion

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
        System.out.println("Have a nice day!");
    }

    /**
     * Tests and runs the Vending Functions of RegularVM machine.
     * This method simulates how a vending machine would normally work.
     *
     * @param machine the machine to be run vending functions on
     */
    static void RunVendingMachine(RegularVM machine)
    {
        // Variables
        int choice = -1;
        double inputtedMoney;
        double lowestPrice = Double.POSITIVE_INFINITY;
        String input;

        // highest price set up
        for (MachineItem item : machine.getItems())
        {
            if (item.getPrice() < lowestPrice)
            {
                System.out.println(item.getPrice());
                System.out.println(lowestPrice);

                lowestPrice = item.getPrice();
            }
        }

        System.out.println(lowestPrice);

        // Money Input
        scanner.nextLine();
        machine.displayVendingMachine();
        Helper.MoneyInstructions();
        System.out.print("Input: ");
        input = scanner.nextLine();

        inputtedMoney = Helper.parseMoney(input, machine);
        System.out.println("Money: " + inputtedMoney);
        System.out.println();

        if (inputtedMoney < lowestPrice)
        {
            System.out.println("Inputted money is too low for item with lowest price");
            System.out.printf("Here's your Php %.2f back\n", inputtedMoney);
            return;
        }

        // Item Index Input
        System.out.println("0 to cancel current transaction");
        while (choice == -1)
        {
            System.out.print("Index of item order: Item ");
            choice = scanner.nextInt();
            if (choice > machine.getItems().size() || choice <= -1)
            {
                choice = -1;
                System.out.println("Invalid choice.");
            }
        }

        if (choice == 0)
        {
            System.out.println();
            System.out.println("Transaction cancelled.");
            System.out.printf("Here's your Php %.2f back\n", inputtedMoney);
            return;
        }

        // Transaction stuff
        machine.dispenseItem(choice - 1);

        // put finished register function here
    }

    static void ConductMaintenance(RegularVM machine)
    {

    }
}

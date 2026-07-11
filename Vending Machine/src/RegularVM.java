import java.util.ArrayList;

/**
 * Represents the main controller for a Regular Vending Machine system.
 * This class orchestrates operations between inventory management (MachineItem),
 * internal cash reserves (CashRegister), and structural ledger logging (History).
 */
public class RegularVM {

    /** The descriptive display name of this vending machine instance */
    private final String name;

    /** The central inventory engine storing all item slots available for purchase */
    private final ArrayList<MachineItem> items = new ArrayList<>();

    /** The total count of active item slots configured in this machine */
    private int itemCount;

    /** Enforces composition: The core physical cash handling register for the machine */
    private CashRegister register = new CashRegister();

    /** Enforces composition: The transaction history ledger tracking state boundaries */
    private History history;

    /**
     * Constructs and initializes a Regular Vending Machine with factory preset items.
     * Establishes the baseline history tracker upon configuration.
     *
     * @param name the descriptive identity string for the machine instance
     */
    public RegularVM(String name)
    {
        this.name = name;
        GeneratePresetItems();
        history = new History(getItemCount());
        history.restockPeriod(items);
    }

    /**
     * Retrieves the display identity name of the vending machine.
     *
     * @return the machine name string
     */
    public String getName() {
        return name;
    }

    /**
     * Exposes the internal register subsystem for currency manipulation.
     *
     * @return the CashRegister instance managed by this machine
     */
    public CashRegister getRegister() {return register;}

    /**
     * Retrieves the aggregate volume of slots allocated in the inventory system.
     *
     * @return the total count of item slots
     */
    public int getItemCount() {return itemCount;}

    /**
     * Retrieves the full structural container tracking all managed machine items.
     *
     * @return an ArrayList containing all active MachineItem profiles
     */
    public ArrayList<MachineItem> getItems()
    {
        return this.items;
    }

//    public void addItem(MachineItem item)
//    {
//        items.add(item);
//    }

    /**
     * Populates the internal slots with default initial toppings and quantities
     * to fulfill starting operational system baselines.
     */
    public void GeneratePresetItems()
    {
        items.add(new MachineItem(
                "Pepperoni",
                20,
                2));
        items.add(new MachineItem(
                "Mushroom",
                15,
                2));
        items.add(new MachineItem(
                "Pineapple",
                25,
                2));
        items.add(new MachineItem(
                "Ham",
                10,
                2));
        items.add(new MachineItem(
                "Mozzarella",
                15,
                2));
        items.add(new MachineItem(
                "Beef Mince",
                10,
                2));
        items.add(new MachineItem(
                "Basil Leaves",
                30,
                2));
        items.add(new MachineItem(
                "Sonion",
                100,
                2));

        this.itemCount = items.size();
    }

    /**
     * Restocks a specific item slot by matching its descriptive name identifier.
     * Refreshes historical snapshots if validation constraints are passed cleanly.
     *
     * @param itemName the case-sensitive string identifier of the product
     * @param quantity the positive integer volume to increment the slot stock by
     */
    public void RestockItem(String itemName, int quantity)
    {
        // Variables
        MachineItem itemToRestock = Helper.findItem(itemName, items);
        int index = items.indexOf(Helper.findItem(itemName, items));
        boolean successfulRestock;

        // Modify itemToRestock
        if (itemToRestock == null)
        {
            System.out.println("Item \"" + itemName + "\" not found");
            return;
        }

        successfulRestock = itemToRestock.restock(quantity);
        items.set(index, itemToRestock);

        // Add to Restock Log
        if (successfulRestock) history.restockPeriod(items);
    }

    /**
     * Performs an administrative collection sweep, accessing collected earnings
     * captured during completed user sales.
     */
    public void CollectMoney()
    {
        this.register.getMoneyGotten();
    }

    /**
     * Updates the retail pricing configuration of a managed product unit.
     *
     * @param itemName the string name of the item to modify
     * @param price    the new integer cost baseline to push to the item structure
     */
    public void SetItemPrice(String itemName, int price) // Incomplete
    {
        // Variables
        MachineItem itemToReprice = Helper.findItem(itemName, items);
        double previousPrice;
        int index = items.indexOf(Helper.findItem(itemName, items));

        // Modify itemToRestock
        if (itemToReprice == null)
        {
            System.out.println("Item " + itemName + " not found");
            return;
        }
        previousPrice = itemToReprice.getPrice();

        System.out.printf("Set price of %s from Php %.2f to Php %.2f\n",
                itemToReprice.getName(),
                previousPrice,
                (double)price);
        itemToReprice.setPrice(price);
        items.set(index, itemToReprice);

        // Add to Restock Log
    }

    /**
     * Evaluates payment metrics, processes product allocation decreases,
     * and triggers cash dispensing sequences for a product transaction.
     *
     * @param itemIndex  the numerical slot address targeted by the consumer
     * @param inputMoney the monetary raw payment inserted by the customer
     */
    public void dispenseItem(int itemIndex, double inputMoney)
    {
        // Variables
        double itemPrice = items.get(itemIndex).getPrice();
        double change = inputMoney - itemPrice;
        ArrayList<Integer> changeDenominations = register.calculateChange((int)change);
//        String changeToGive = changeDenominations.toString();

        // Check if vending machine has enough change to give
        if (register.changeIsPossible(change) && changeDenominations != null)
        {
            items.get(itemIndex).transact();
            System.out.println("Dispensed " + items.get(itemIndex).getName());
            generatePurchaseSummary(itemIndex);

            register.deductCash(changeDenominations);
            history.recordSale(itemIndex,
                    items.get(itemIndex).getName(),
                    itemPrice,
                    1);
        }
        else
        {
//            if (!register.changeIsPossible(change)) System.out.println("flag 1");
//            if (changeDenominations == null) System.out.println("flag 2");

            System.out.println("Vending machine cannot dispense enough change.");
            System.out.printf("Here's your Php %.2f back\n", inputMoney);
        }
    }

    /**
     * Internal formatting helper to isolate and print receipt breakdowns post-purchase.
     *
     * @param itemIndex the position of the product inside the array layout
     */
    private void generatePurchaseSummary(int itemIndex)
    {
        System.out.println("Generating purchase summary...");
        System.out.println();
        System.out.println("Item " + (itemIndex + 1) + ": " + items.get(itemIndex).getName());
        System.out.println("Quantity: 1");
        System.out.printf("Price: Php %.2f\n", items.get(itemIndex).getPrice());
        System.out.println("Have a nice day!");
    }

    /**
     * Formats and prints a structured, high-visibility storefront grid display
     * representing the item inventory status to consumers.
     */
    public void displayVendingMachine()
    {
        int i;
        int maxSpace = 33;
        int leftPad = name.length() + (maxSpace - name.length()) / 2;
        String nameFormat = String.format("%" + leftPad + "s", name);


        System.out.println("* * * * * * * * * * * * * * * * * * *");
        System.out.printf("* %-33s *\n", nameFormat);
        System.out.println("* * * * * * * * * * * * * * * * * * *");
        for (i = 1; i <= items.size(); i += 2)
        {
            System.out.printf("* %s %-10d * %s %-10d *\n", "Item", i, "Item", i + 1);
            System.out.printf("* %-15s * %-15s *\n", items.get(i - 1).getName(), items.get(i).getName());
            System.out.printf("* %s %-11.2f * %s %-11.2f *\n",
                    "Php",
                    items.get(i - 1).getPrice(),
                    "Php",
                    items.get(i).getPrice());
            System.out.printf("* %s %-5d * %s %-5d *\n",
                    "In Stock:",
                    items.get(i - 1).getStock(),
                    "In Stock:",
                    items.get(i).getStock());
            System.out.println("* * * * * * * * * * * * * * * * * * *");
        }
        System.out.println("*       ■■■■■■■■■■■■■■■■■■■■■       *");
        System.out.println("* * * * * * * * * * * * * * * * * * *");
    }

    /**
     * Administrative print utility showing raw underlying class properties
     * for tracking data consistency during system validation routines.
     */
    public void displayDebugInfo()
    {
        // Print Current Items with their stock and price
        System.out.println("+-----+-----+-----+-----+");
        System.out.println("Current items in Machine");
        System.out.println("+-----+-----+-----+-----+");

        int i = 0;
        for (MachineItem item : items)
        {
            i++;
            System.out.printf("%d. %-15s : Php%-10.2f : %d in stock%n", i, item.getName(), (float)item.getPrice(), item.getStock());
        }

        // Print current denominations in change pool

    }
}

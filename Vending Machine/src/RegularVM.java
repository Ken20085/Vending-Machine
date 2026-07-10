import java.util.ArrayList;

public class RegularVM {

    private final String name;
    private ArrayList<MachineItem> items = new ArrayList<>();
    private int itemCount;
    CashRegister register = new CashRegister();

    public RegularVM(String name)
    {
        this.name = name;
        GeneratePresetItems();
    }

    public String getName() {
        return name;
    }
    public CashRegister getRegister() {return register;}
    public int getItemCount() {return itemCount;}

    public ArrayList<MachineItem> getItems()
    {
        return this.items;
    }

//    public void addItem(MachineItem item)
//    {
//        items.add(item);
//    }

    public void GeneratePresetItems()
    {
        items.add(new MachineItem(
                "Pepperoni",
                20,
                50));
        items.add(new MachineItem(
                "Mushroom",
                15,
                50));
        items.add(new MachineItem(
                "Pineapple",
                25,
                50));
        items.add(new MachineItem(
                "Ham",
                10,
                50));
        items.add(new MachineItem(
                "Mozzarella",
                15,
                50));
        items.add(new MachineItem(
                "Beef Mince",
                10,
                50));
        items.add(new MachineItem(
                "Basil Leaves",
                30,
                50));
        items.add(new MachineItem(
                "Sonion",
                100,
                50));

        this.itemCount = items.size();

        // Generate Restock Log Here
        GenerateNewRestockLog();
    }

    public void GenerateNewRestockLog()
    {
        System.out.println("Generating restock log...");
    }

    public void RestockItem(String itemName, int quantity) // Incomplete
    {
        // Variables
        MachineItem itemToRestock = Helper.findItem(itemName, items);
        int previousQuantity = 0;
        int amountChanged = 0;
        int index = items.indexOf(Helper.findItem(itemName, items));

        // Modify itemToRestock
        if (itemToRestock == null)
        {
            System.out.println("Item " + itemName + " not found");
            return;
        }
        previousQuantity = itemToRestock.getStock();
        amountChanged = quantity - itemToRestock.getStock();

        itemToRestock.setStock(quantity);
        items.set(index, itemToRestock);

        // Add to Restock Log
    }

    public void CollectPayment()
    {
        // Add all denominations
    }

    public void SetItemPrice(String itemName, double price) // Incomplete
    {
        // Variables
        MachineItem itemToReprice = Helper.findItem(itemName, items);
        double previousPrice = 0;
        int index = items.indexOf(Helper.findItem(itemName, items));

        // Modify itemToRestock
        if (itemToReprice == null)
        {
            System.out.println("Item " + itemName + " not found");
            return;
        }
        previousPrice = itemToReprice.getPrice();

        itemToReprice.setPrice(price);
        items.set(index, itemToReprice);

        // Add to Restock Log
    }

    public void ReplenishMoney(String input)
    {

    }

    public void dispenseItem(int itemIndex)
    {
        items.get(itemIndex).transact();
        System.out.println("Dispensed " + items.get(itemIndex).getName());
        generatePurchaseSummary(itemIndex);
    }

    private void generatePurchaseSummary(int itemIndex)
    {
        System.out.println("Generating purchase summary...");
        System.out.println();
        System.out.println("Item " + (itemIndex + 1) + ": " + items.get(itemIndex).getName());
        System.out.println("Quantity: 1");
        System.out.printf("Price: Php %.2f\n", items.get(itemIndex).getPrice());
        System.out.println("Have a nice day!");
    }

    public void displayVendingMachine()
    {
        int i = 0;
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

/**
 * Represents an individual inventory slot or item position within the vending machine.
 * Tracks the properties of the item by its name, price, and current stock.
 */

public class MachineItem {
    /** Descriptive name of the Item */
    private final String name;

    /** The unit cost of the item */
    private double price;

    /** The amount available for this item */
    private int stock;

    /** The maximum amount inventory capacity of a single item */
    private final int maxStock = 50;

//    public MachineItem(String name) {
//        this.name = name;
//    }

    /**
     * Constructs a new machine item with a valid stock allocation.
     *
     * @param name  name of the item entered
     * @param price unit price of the item
     * @param stock starting quantity of the item
     */
    public MachineItem(String name, double price, int stock) {
        this.name = name;
        this.price = price;

        if (stock > maxStock)
        {
            System.out.println("You cannot put more than 50 stock.");
            System.out.println("Setting stock to 50...");
            this.stock = 50;
        }
        else
        {
            this.stock = stock;
        }
    }

    // region getters

    /**
     * Gets the item's descriptive name
     *
     * @return String name of the item
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the unit price of the item
     *
     * @return double monetary price value
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Gets the remaining available quantity of the item
     *
     * @return int of remaining items
     */
    public int getStock()
    {
        return stock;
    }
    // endregion
    // region setters
//    public void setName(String name)
//    {
//        this.name = name;
//    }

    /**
     * Updates the retail price of an item
     *
     * @param price the new price to assign to the product
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
//    public void setStock(int stock)
//    {
//        this.stock = stock;
//    }
    // endregion

    /**
     * Processes a single transactional action, decrementing the stock of the item bought
     */
    public void transact()
    {
        stock -= 1;
    }

    /**
     * Scales up the inventory quantitu of the item by a provided int amount.
     * Safeguarded against negative values and over allocation
     *
     * @param quantity amount to restock the item by
     * @return true if the inventory was successfully incremented; false if validation constraints fail
     *
     */
    public boolean restock(int quantity)
    {
        if (this.stock == maxStock)
        {
            System.out.println("You cannot put more than 50 stock.");
            return false;
        }
        else if (this.stock + quantity > maxStock)
        {
            System.out.println("Item restock quantity exceeds maximum stock: "
                    + this.stock
                    + " + "
                    + quantity
                    + " > "
                    + this.maxStock);
            return false;
        }
        else if (quantity < 0)
        {
            System.out.println("Invalid quantity: " + quantity);
            return false;
        }

        System.out.println("Restocking " + quantity + " to " + getName());
        this.stock += quantity;
        return true;
    }
}

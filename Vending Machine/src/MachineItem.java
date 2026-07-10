public class MachineItem {
    private String name;
    private double price;
    private int stock;

    public MachineItem(String name) {
        this.name = name;
    }

    public MachineItem(String name, double price, int stock) {
        this.name = name;
        this.price = price;

        int maxStock = 50;
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
    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public int getStock()
    {
        return stock;
    }
    // endregion
    // region setters
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setStock(int stock)
    {
        this.stock = stock;
    }
    // endregion\

    public void transact()
    {
        stock -= 1;
    }
}

import java.util.ArrayList;

public class CashRegister {
    ArrayList<Denomination> denominations = new ArrayList<>();

    public CashRegister() {
        InitializeDenominations();
    }

    public void InitializeDenominations()
    {
        this.denominations.add(new Denomination("1000", 1000));
        this.denominations.add(new Denomination("500", 500));
        this.denominations.add(new Denomination("100", 100));
        this.denominations.add(new Denomination("50", 50));
        this.denominations.add(new Denomination("20", 20));
        this.denominations.add(new Denomination("10", 10));
        this.denominations.add(new Denomination("5", 5));
        this.denominations.add(new Denomination("1", 1));
    }
}

import java.util.ArrayList;

public class RegularVM {

    private String name;
    ArrayList<MachineItem> items =  new ArrayList<>();
    CashRegister register = new CashRegister();
    private double totalSales;

    public RegularVM(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

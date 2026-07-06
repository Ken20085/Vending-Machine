public class Denomination {
    private final String denominationName;
    private int amount;
    private final int value;


    public Denomination(String denominationName, int value) {
        this.denominationName = denominationName;
        this.value = value;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() { return this.amount; }
    public String getDenominationName() { return this.denominationName; }
    public int getValue() { return this.value; }
}

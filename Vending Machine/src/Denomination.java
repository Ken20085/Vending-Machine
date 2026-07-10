public class Denomination {
    private final String denominationName;
    private int amount;
    private final int value;
    private static final int[] VALID_VALUES = {1000, 500, 200, 100, 50, 20, 10, 5, 1};  //valid bills

    public Denomination(int value) {
        this.value = value;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getDenominationName() {
        return this.denominationName;
    }

    public int getValue() {
        return this.value;
    }

    public static int[] getValidValues() {
        return VALID_VALUES;
    }

    //checks if value exists within the denominations
    public static boolean isValid(int value) {
        //loop through all valid denominations
        for (int valid : VALID_VALUES){
            if (valid == value) {
                return true;       //returns true if its valid
            }
        }
        return false;              //denomination doesnt exist
    }
}
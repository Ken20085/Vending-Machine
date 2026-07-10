/**
 * Represents the internal cash register of the vending machine. This tracks
 * the quantity of each denomination available and uses a greedy algorthm to
 * calculate the exact change for transactions.
 */
public class CashRegister {
    /** int array tracking how much of the denomination/bills are held */
    private int[] quantity;

    /**
     * Constructs an empty CashRegister. This array scales to match the number
     * of accepted denominations.
     */
    public CashRegister() {
        this.quantity = new int[Denomination.getValidValues().length];
    }

    /**
     * Determines the matching index for a inputted value.
     * Used internally to locate where denomination bills are stored in the
     * arrays.
     *
     * @param value the numberic value of the denomination (e.g. 50, 20, 10, ..., etc)
     * @return index of array if found ; -1 if value is an invalid denomination
     */
    private int findDenominationIndex(int value){
        int[] validValues = Denomination.getValidValues();
        int i;

        //loops through denominations to find the index of denomination given
        for (i = 0; i < validValues.length; i++){
            if (validValues[i] == value){
                return i;   //index of the denomination
            }
        }
        return -1;          //invalid denomination
    }

    /**
     * Adds incoming money to the cash register. This method is utilized when processing
     * customer payments or when the cash register is restocked.
     *
     * @param value the numeric value of the denomination to be added inside
     * @param count the number of bills and coins of this denomination to add
     */
    public void addCash(int value, int count){
        int index = findDenominationIndex(value);

        if (index != -1){
            this.quantity[index] += count;      //increments the count of denomination in that index
        }
        else{
            System.out.println(value + " is not a valid denomination!");
        }
    }

    /**
     * Computes the required bills and coins to needed to return change to a customer.
     * Uses a greedy algorithm to check if an exact combination is available without
     * modifying the actual balances yet.
     *
     * @param amountRequired is the total monetary amount owed to the customer
     * @return int array indicating the amount of each denomination needed to form the
     * exact change; null if exact change cannot be generated from current balance
     */
    public int[] calculateChange(int amountRequired){
        int i;
        int[] validValues = Denomination.getValidValues();
        int[] changeToGive = new int[validValues.length];   //array to track how many of each denomination to output
        int remainingAmount = amountRequired;

        //loop through all denominations
        for (i = 0; i < validValues.length; i++){
            int currentDenominationValue = validValues[i];

            //if statemtnt to check if denomination will fit into remaining change
            if (remainingAmount >= currentDenominationValue){
                int neededDenomination = remainingAmount / currentDenominationValue;     //int to track how  many of this denomination is needed
                int actualChangeToGive = Math.min(neededDenomination, this.quantity[i]); //compare how many we need vs how many do we have

                changeToGive[i] = actualChangeToGive;
                remainingAmount -= actualChangeToGive * currentDenominationValue;
            }
        }

        //check to see if target change is zero/change is dispensable
        if (remainingAmount == 0){
            return changeToGive;    //return what denominations to give
        }
        else{
            return null;            //change inside is not enough
        }
    }

    /**
     * Deducts the denominations from the cash register and prints the
     * currency dispensing log.
     *
     * @param change an int array listing the count of pieces to deduct per
     * denomination
     */
    public void deductCash(int[] change){
        int[] validValues = Denomination.getValidValues();
        int i;

        for (i = 0; i < quantity.length; i++){
            //updates actual changes to inventory
            this.quantity[i] -= change[i];

            //if successful print out a log
            if (change[i] > 0){
                System.out.println("Dispensing: " + change[i] + "x PHP " + validValues[i]);
            }
        }
    }

    /**
     * Computes for the total sum of all denominations currently inside the cash
     * register.
     *
     * @return total gross value
     */
    public int getTotalValue(){
        int total = 0;
        int i;
        int[] validValues = Denomination.getValidValues();

        //loop to multiply denomination by the count of denomination
        for (i = 0; i < validValues.length; i++){
            total += validValues[i] * quantity[i];
        }
        return total;
    }
}
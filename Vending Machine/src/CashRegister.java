public class CashRegister {
    private int[] quantity;     //how much of the denomination/bills are held

    public CashRegister() {
        this.quantity = new int[Denomination.getValidValues().length];
    }

    //retuns index of denomination entered
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

    //adds incoming currency to inventory count
    public void addCash(int value, int count){
        int index = findDenominationIndex(value);

        if (index != -1){
            this.quantity[index] += count;      //increments the count fo denomination in that index
        }
        else{
            System.out.println("Not a valid denomination!");
        }
    }

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
                int neededDenomination = remainingAmount / currentDenominationValue;    //int to track how  many of this denomination is needed
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
            return null;            //change inside is not enough (i think conditional statement in VM.java to print out error message if(variable == null) ts)
        }
    }

    //deducts the denominations and logs the change to give
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

    //retuns the total sum of money currently inside the VM
    public int getTotalValue(){
        int total = 0;
        int i;
        int[] validValues = Denomination.getValidValues();

        //loop to multiply denomination by the count of denomination
        for (int i = 0; i < validValues.length; i++){
            total += validValues[i] * quantity[i];
        }
        return total;
    }
}

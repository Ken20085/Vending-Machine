import java.util.ArrayList;
import java.util.Objects;

public class Helper {

    public static void DisplayAllItems(RegularVM machine)
    {
        int i = 0;

        System.out.println("Items");
        for (MachineItem item : machine.getItems())
        {
            i++;
            System.out.println(i + ". " + item.getName());
        }
    }
    public static void MachineDebugInfo(RegularVM machine)
    {

    }

    public static double parseMoney(String input, RegularVM machine)
    {
        double result = 0;
        String[] tokens = input.split(" ");

        for (String token : tokens)
        {
            String[] values = token.split("-");
            int value = Integer.parseInt(values[1]);
            int count = Integer.parseInt(values[0]);

            if (Denomination.isValid(value))
            {
                result += value * count;
            }
            machine.getRegister().addCash(value, count);
        }

        return result;
    }

    public static void MoneyInstructions()
    {
        System.out.println();
        System.out.println("To input money, please follow this format: <amount> <denomination>");
        System.out.println("<amount>-<denomination> <amount2>-<denomination2> (and so on)");
        System.out.println("Example: 1-1000 5-50 25-10");
        System.out.println();
        System.out.println("Note that valid denominations are: ");
        System.out.println("1000, 500, 200, 100, 50, 20, 10, 5, 1");
        System.out.println();
    }

    public static MachineItem findItem(String itemName, ArrayList<MachineItem> items)
    {
        if (items == null || items.isEmpty())
            return null;

        for (MachineItem item : items)
        {
            if (item != null && Objects.equals(item.getName(), itemName))
            {
                return item;
            }
        }

        return null;
    }
}

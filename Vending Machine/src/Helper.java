import java.util.ArrayList;

public class Helper {

    public static void DisplayAllItems(RegularVM machine)
    {
        int i = 0;

        System.out.println("Items");
        for (MachineItem item : machine.items)
        {
            i++;
            System.out.println(i + ". " + item.getName());
        }
    }
}

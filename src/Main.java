import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<StaffMember> lib = new ArrayList<>();
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.left);
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        String data;
        do {

                table.setColumnWidth(0, 30, 50);
                table.addCell("STAFF MANAGEMENT SYSTEM", style);
                table.addCell("1. Insert Employee", style);
                table.addCell("2. Update Employee", style);
                table.addCell("3. Display Employee", style);
                table.addCell("4. Remove Employee", style);
                table.addCell("5. Exit", style);
                System.out.println(table.render());
                System.out.println();
                System.out.println("--------------------------------");
            while (true){
                System.out.println("-> Enter your choice: ");
                data = sc.nextLine();
                if(data.matches("^[1-5]$")){
                    break;
                }else{
                    System.out.println(RED + "Enter a valid choice !!!"+RESET);
                }
            }

            switch (data) {
                case "1": {
                    Library.insert();
                    break;
                }
                case "2": {
                    Library.updateEmployee();
                    break;
                }
                case "3": {
                    Library.display();
                    break;
                }
                case "4": {
                    Library.remove();
                    break;
                }
                case "0": {
                    break;
                }
            }
        } while (data.equals("0"));
    }
}
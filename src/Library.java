import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    static Scanner sc = new Scanner(System.in);
    static String name;
    static String address;
    static double salary;
    static double rate;
    static double bonus;
    static int hours;
    static String type;
    static int updateType;
    static ArrayList<StaffMember> staff = new ArrayList<>();
    static ArrayList<Integer> removedIds = new ArrayList<>();

    static {
        staff.add(new Volunteer(1, "Fanau", "Pnhom Penh", 250));
        staff.add(new SalariesEmployee(2, "Che Minh", "Seam Reap", 200, 10));
        staff.add(new HourlySalaryEmployee(3, "Hour", "Koh Kong", 200, 10));
    }

    static int currentId = 3;

    static ArrayList<StaffMember> insert() {
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table1 = new Table(4, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        table1.setColumnWidth(0,20,30);
        table1.setColumnWidth(1,30,50);
        table1.setColumnWidth(2,30,50);
        table1.setColumnWidth(3,10,15);
        table1.addCell("1. Volunteer",style);
        table1.addCell("2. Salaries Employee",style);
        table1.addCell("3. HourlySalary Employee",style);
        table1.addCell("0. Exit",style);
        outerloop:
        while (true) {
            System.out.println(table1.render());
            while (true) {
                System.out.print("Enter your type: ");
                type = sc.nextLine();
                if (type.matches("^[0-3]$")) {
                    if(type.equals("0") )
                        break outerloop;
                    break;
                }else {
                    System.out.println(RED + "Invalid input. Try again." + RESET);
                }

            }

            if (type.equals("1") || type.equals("2")  || type.equals("3")) {
                System.out.println("ID :" + (currentId + 1));
                while (true){
                    System.out.print("Enter name: ");
                    name = sc.nextLine();
                    if(name.matches("^[a-zA-Z ]*$")){
                        break;
                    }else {
                        System.out.println(RED + "Name most be only letter !!!"+RESET);
                    }
                }
                while (true){
                    System.out.print("Enter address: ");
                    address = sc.nextLine();
                    if(address.matches("^[a-zA-Z ]*$")){
                        break;
                    }else {
                        System.out.println(RED + "Address most be only letter !!!"+RESET);
                    }
                }

                while (true){
                    System.out.print("Enter salary: ");
                    if (sc.hasNextDouble()) {
                        salary = sc.nextDouble();
                        if(salary > 0){
                            break;
                        }else{
                            System.out.println(RED + "Salary should be greater zero !!!"+RESET);
                        }
                    }else{
                        System.out.println(RED + "Salary should be number. Try again. !!!"+RESET);
                        sc.nextLine();
                    }
                }
                if (type.equals("1")) {
                    Volunteer volunteer = new Volunteer(currentId + 1, name, address, salary);
                    staff.add(volunteer);
                    currentId = currentId + 1;
                } else if (type.equals("2")) {
                    while (true){
                        System.out.print("Enter bonus: ");
                        if (sc.hasNextDouble()) {
                            bonus = sc.nextDouble();
                            if(bonus > 0){
                                break;
                            }else{
                                System.out.println(RED + "Bonus should be greater zero !!!"+RESET);
                            }
                        }else{
                            System.out.println(RED + "Bonus should be number. Try again. !!!"+RESET);
                            sc.nextLine();
                        }
                    }

                    SalariesEmployee salariesEmployee = new SalariesEmployee(currentId + 1, name, address, salary, bonus);
                    staff.add(salariesEmployee);
                    currentId = currentId + 1;
                }else {
                    while (true){
                        System.out.print("Enter hours: ");
                        if (sc.hasNextDouble()) {
                            hours = sc.nextInt();
                            if(hours > 0){
                                break;
                            }else {
                                System.out.println(RED + "Hours should be greater zero !!!"+RESET);
                            }
                        }else{
                            System.out.println(RED + "Hours should be number. Try again. !!!"+RESET);
                            sc.nextLine();
                        }
                    }
//                    System.out.print("Enter hours: ");
//                    hours = sc.nextInt();
                    while (true){
                        System.out.print("Enter rate: ");
                        if (sc.hasNextDouble()) {
                            rate = sc.nextDouble();
                            if(rate > 0){
                                break;
                            }else{
                                System.out.println(RED + "Rate should be greater zero !!!"+RESET);
                            }
                        }else {
                            System.out.println(RED + "Rate should be number. Try again. !!!"+RESET);
                            sc.nextLine();
                        }
                    }
//                    System.out.println("Enter rate: ");
//                    rate = sc.nextDouble();
                    HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(currentId + 1,name,address,hours,rate);
                    staff.add(hourlySalaryEmployee);
                    currentId = currentId + 1;
                }
                sc.nextLine();
                System.out.println(BLUE + "* You're adding successfully."+RESET);
                System.out.println();
            }

        }

        return staff;
    }



    static void display() {
        System.out.println("=======* Display Staff Information *=======");
        System.out.println();
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(9, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
            table.setColumnWidth(0, 30, 50);
            table.setColumnWidth(1, 10, 20);
            table.setColumnWidth(2, 20, 30);
            table.setColumnWidth(3, 20, 30);
            table.setColumnWidth(4, 10, 10);
            table.setColumnWidth(5, 10, 10);
            table.setColumnWidth(6, 10, 10);
            table.setColumnWidth(7, 10, 10);
            table.setColumnWidth(8, 10, 10);
            table.addCell("Type", style);
            table.addCell("ID", style);
            table.addCell("Name", style);
            table.addCell("Address", style);
            table.addCell("Salary", style);
            table.addCell("Bonus", style);
            table.addCell("Hours", style);
            table.addCell("Rate", style);
            table.addCell("Pay", style);

            staff.forEach(dis -> {
                if (dis instanceof Volunteer volunteer) {
                    table.addCell("Volunteer", style);
                    table.addCell(" " + volunteer.getId(), style);
                    table.addCell(" " + volunteer.getName(), style);
                    table.addCell(" " + volunteer.getAddress(), style);
                    table.addCell(" " + volunteer.getSalary(), style);
                    table.addCell("---", style);
                    table.addCell("---", style);
                    table.addCell("---", style);
                    table.addCell("$" + volunteer.pay(), style);
                } else if (dis instanceof SalariesEmployee salariesEmployee) {
                    table.addCell("Salaries Employee", style);
                    table.addCell(" " + salariesEmployee.getId(), style);
                    table.addCell(" " + salariesEmployee.getName(), style);
                    table.addCell(" " + salariesEmployee.getAddress(), style);
                    table.addCell(" " + salariesEmployee.getSalary(), style);
                    table.addCell(" " + salariesEmployee.getBonus(), style);
                    table.addCell("---", style);
                    table.addCell("---", style);
                    table.addCell("$" + salariesEmployee.pay(), style);
                } else {
                    HourlySalaryEmployee hourlySalaryEmployee = (HourlySalaryEmployee) dis;
                    table.addCell("Hourly Employee", style);
                    table.addCell(" " + hourlySalaryEmployee.getId(), style);
                    table.addCell(" " + hourlySalaryEmployee.getName(), style);
                    table.addCell(" " + hourlySalaryEmployee.getAddress(), style);
                    table.addCell("---", style);
                    table.addCell("---", style);
                    table.addCell(" " + hourlySalaryEmployee.getHourWorked(), style);
                    table.addCell(" " + hourlySalaryEmployee.getRate(), style);
                    table.addCell("$" + hourlySalaryEmployee.pay(), style);
                }

            });
        System.out.println(table.render());
        System.out.println();
        System.out.println();
        System.out.println("Press any key to continue");
        System.console().readLine();
    }

    static void hoursEmployeeTable(StaffMember he){
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
        Table tableHe = new Table(7, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        tableHe.setColumnWidth(0, 30, 50);
        tableHe.setColumnWidth(1, 10, 20);
        tableHe.setColumnWidth(2, 20, 30);
        tableHe.setColumnWidth(3, 10, 10);
        tableHe.setColumnWidth(4, 10, 10);
        tableHe.setColumnWidth(5, 10, 10);
        tableHe.setColumnWidth(6, 10, 10);


        tableHe.addCell("Type Employee", style);
        tableHe.addCell("ID", style);
        tableHe.addCell("Name", style);
        tableHe.addCell("Address", style);
        tableHe.addCell("Hours", style);
        tableHe.addCell("Rate", style);
        tableHe.addCell("Pay", style);

        tableHe.addCell("Hourly Employee", style);
        tableHe.addCell(" "+ he.id,style);
        tableHe.addCell(" " + he.name, style);
        tableHe.addCell(" " + he.address, style);
        tableHe.addCell(" "+ ((HourlySalaryEmployee) he).getHourWorked(), style);
        tableHe.addCell(" " + ((HourlySalaryEmployee) he).getRate() , style);
        tableHe.addCell(" "+ he.pay(), style);
        System.out.println(tableHe.render());
    }
    static void salaryEmployeeTable(StaffMember se) {
        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
        Table tableSe = new Table(7, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        tableSe.setColumnWidth(0, 30, 50);
        tableSe.setColumnWidth(1, 10, 20);
        tableSe.setColumnWidth(2, 20, 30);
        tableSe.setColumnWidth(3, 20, 30);
        tableSe.setColumnWidth(4, 10, 10);
        tableSe.setColumnWidth(5, 10, 10);
        tableSe.setColumnWidth(6, 10, 10);
        tableSe.addCell("Type Employee", style);
        tableSe.addCell("ID", style);
        tableSe.addCell("Name", style);
        tableSe.addCell("Address", style);
        tableSe.addCell("Salary", style);
        tableSe.addCell("Bonus", style);
        tableSe.addCell("Pay", style);


        tableSe.addCell("Salaries Employee", style);
        tableSe.addCell(" "+ se.id,style);
        tableSe.addCell(" " + se.name, style);
        tableSe.addCell(" " + se.address, style);
        tableSe.addCell(" "+ ((SalariesEmployee) se).getSalary(), style);
        tableSe.addCell(" " + ((SalariesEmployee) se).getBonus() , style);
        tableSe.addCell(" "+ se.pay(), style);
        System.out.println(tableSe.render());
    }
    static void volunteer(StaffMember up){

        CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
        Table tableUp = new Table(6, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        tableUp.setColumnWidth(0, 30, 50);
        tableUp.setColumnWidth(1, 10, 20);
        tableUp.setColumnWidth(2, 20, 30);
        tableUp.setColumnWidth(3, 20, 30);
        tableUp.setColumnWidth(4, 10, 10);
        tableUp.setColumnWidth(5, 10, 10);
        tableUp.addCell("Type Employee", style);
        tableUp.addCell("ID", style);
        tableUp.addCell("Name", style);
        tableUp.addCell("Address", style);
        tableUp.addCell("Salary", style);
        tableUp.addCell("Pay", style);


        tableUp.addCell("Volunteer", style);
        tableUp.addCell(" "+ up.id,style);
        tableUp.addCell(" " + up.name, style);
        tableUp.addCell(" " + up.address, style);
        tableUp.addCell(" "+ ((Volunteer) up).getSalary(), style);
        tableUp.addCell(" "+ up.pay(), style);
        System.out.println(tableUp.render());
    }
        static void updateEmployee () {
            int idInput;
            while (true) {
                System.out.println("Enter or Search ID to Update: ");
                String input = sc.next();

                if (input.matches("\\d+")) { // Ensures only digits (positive numbers)
                    idInput = Integer.parseInt(input);
                    if (idInput > 0) {
                        break;
                    }
                }
                System.out.println(RED + "Invalid input! Please enter a valid numeric ID." +RESET);
            }
            int finalIdInput = idInput;
            //

            staff.stream()
                    .filter(up -> up.id == finalIdInput)
                    .findFirst()
                    .ifPresent(
                        up -> {
                            boolean updated = false; // Flag to check if an update was made

                            if (up instanceof Volunteer) {
                                volunteer(up);
                                System.out.println("1. Name\t2. Address\t3. Salary\t0. Cancel");
                            }
                            if (up instanceof SalariesEmployee) {
                                salaryEmployeeTable(up);
                                System.out.println("1. Name\t2. Address\t3. Salary\t4. Bonus\t0. Cancel");
                            }
                            if (up instanceof HourlySalaryEmployee) {
                                hoursEmployeeTable(up);
                                System.out.println("1. Name\t2. Address\t3. Hours\t4. Rate\t0. Cancel");
                            }
                            while (true){
                                System.out.print("Choose column to update: ");
                                if (sc.hasNextInt()) {  // Check if input is an integer
                                    updateType = sc.nextInt();
                                    sc.nextLine(); // Consume newline character

                                    // Validate allowed input range (0-4)
                                    if (updateType >= 0 && updateType <= 4) {
                                        break; // Exit loop if valid
                                    } else {
                                        System.out.println(RED + "Invalid option! Please enter a number between 0 and 4." + RESET);
                                    }
                                }else {
                                    System.out.println(RED + "Invalid input! Please enter a number only." + RESET);
                                    sc.nextLine();
                                }
                            }

                            // Consume the newline character

                            if (updateType == 0) return;

                            switch (updateType) {
                                case 1:
                                    while (true){
                                        System.out.print("Enter new name: ");
                                        String name = sc.nextLine();
                                        if(name.matches("^[a-zA-Z ]*$")){
                                            System.out.println("Update Successfully");
                                            up.setName(name);
                                            break;
                                        }else {
                                            System.out.println(RED + "Name most be only letter !!!"+RESET);
                                        }

                                    }
                                    updated = true;
                                    break;
                                case 2:
                                    while (true){
                                        System.out.print("Enter new address: ");
                                        String address = sc.nextLine();
                                        if(address.matches("^[a-zA-Z ]*$")){
                                            System.out.println("Update Successfully");
                                            up.setAddress(address);
                                            break;
                                        }else {
                                            System.out.println(RED + "Address most be only letter !!!"+RESET);
                                        }


                                    }
                                    updated = true;
                                    break;
                                case 3:
                                    if (up instanceof Volunteer) {
                                        while (true){
                                            System.out.print("Enter new salary: ");

                                            if (sc.hasNextDouble()) {
                                                double salary = sc.nextDouble();
                                                if(salary > 0){
                                                    ((Volunteer) up).setSalary(salary);
                                                    break;
                                                }else{
                                                    System.out.println(RED + "Salary should be greater zero !!!"+RESET);
                                                }
                                            }else{
                                                System.out.println(RED + "Salary should be number. Try again. !!!"+RESET);

                                            }
                                        }

                                    } else if (up instanceof SalariesEmployee) {
                                        while (true){
                                            System.out.print("Enter new salary: ");
                                            if(sc.hasNextDouble()){
                                                double salary = sc.nextDouble();
                                                if(salary > 0){
                                                    ((SalariesEmployee) up).setSalary(salary);
                                                    System.out.println("Update Successfully");
                                                    break;
                                                }else {
                                                    System.out.println(RED + "Salary should be greater zero !!!"+RESET);
                                                }
                                            }
                                            ((SalariesEmployee) up).setSalary(salary);
                                        }
//                                        System.out.println("Enter new salary: ");
//                                        double salary = sc.nextDouble();
//                                        ((SalariesEmployee) up).setSalary(salary);

                                    } else if (up instanceof HourlySalaryEmployee) {
                                        while (true){
                                            System.out.print("Enter new hours: ");
                                            if (sc.hasNextInt()) {
                                                int hours = sc.nextInt();
                                                sc.nextLine();
                                                if(hours > 0){
                                                    ((HourlySalaryEmployee)up).setHourWorked(hours);
                                                    System.out.println("Update Successfully");
//                                                    updated = true;
                                                    break;
                                                }else {
                                                    System.out.println(RED + "Hours should be greater zero !!!"+RESET);
                                                }
                                            }else {
                                                System.out.println(RED + "Invalid input! Please enter a valid number for hours." + RESET);
                                                sc.nextLine();
                                            }
//                                            ((HourlySalaryEmployee) up).setHourWorked(hours);
//                                            updated = true;
                                        }
//                                        System.out.println("Enter new hours: ");
//                                        int hours = sc.nextInt();
//                                        ((HourlySalaryEmployee) up).setHourWorked(hours);
                                    }
                                    updated = true;
                                    break;
                                case 4:
                                    if (up instanceof SalariesEmployee) {
                                        while (true){
                                            System.out.println("Enter new bonus: ");
                                            if(sc.hasNextDouble()){
                                                double bonus = sc.nextDouble();
                                                sc.nextLine();
                                                if(bonus > 0){
                                                    ((SalariesEmployee) up).setBonus(bonus);
//                                                    updated = true;
                                                    break;
                                                }
                                            }else {
                                                System.out.println(RED + "Invalid input! Please enter a valid number for bonus." + RESET);
                                                sc.nextLine();
                                            }
                                        }
//                                        System.out.println("Enter new bonus: ");
//                                        double bonus = sc.nextDouble();
//                                        ((SalariesEmployee) up).setBonus(bonus);
                                    } else if (up instanceof HourlySalaryEmployee) {
                                        while (true){
                                            System.out.println("Enter new rate: ");
                                            if(sc.hasNextDouble()){
                                                double rate = sc.nextDouble();
                                                if(rate > 0){
                                                    ((HourlySalaryEmployee) up).setRate(rate);
                                                    break;
                                                }
                                            }else {
                                                System.out.println(RED + "Invalid input! Please enter a valid number for hours." + RESET);
                                                sc.nextLine();
                                            }
                                        }
//                                        System.out.println("Enter new rate: ");
//                                        double rate = sc.nextDouble();
//                                        ((HourlySalaryEmployee) up).setRate(rate);
                                    }
                                    updated = true;
                                    break;
                                default:
                                    System.out.println("Invalid option! Please choose a valid option.");
                                    return;
                            }

                            // Print the updated details only if an update was made
                            if (updated) {
                                System.out.println(BLUE+ "\n* After Update "+RESET);
                                if (up instanceof Volunteer) {
                                    volunteer(up);
                                } else if (up instanceof SalariesEmployee) {
                                    salaryEmployeeTable(up);
                                } else if (up instanceof HourlySalaryEmployee) {
                                    hoursEmployeeTable(up);
                                }
                            }
                        }
                    );
        }

        static void remove () {
            int idInput;
            while (true) {
                System.out.println("Enter or Search ID to Remove: ");
                String input = sc.next();
                if (input.matches("\\d+")) { // Ensures only digits (positive numbers)
                    idInput = Integer.parseInt(input);
                    if (idInput > 0) {
                        break;
                    }
                }
                System.out.println(RED + "Invalid input! Please enter a valid numeric ID." +RESET);
            }
            int finalIdInput = idInput;
            boolean isRemove = staff.removeIf(up -> up.id == finalIdInput);
            if (isRemove) {
                System.out.println(BLUE + "* Removed successfully" + RESET);

            } else {
                System.out.println(RED + "This ID " + idInput + " not found" +RESET);
            }
            System.out.println();
            System.out.println();
        }
}


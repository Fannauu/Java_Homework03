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

    static {
        staff.add(new Volunteer(1, "Fanau", "Pnhom Penh", 250));
        staff.add(new SalariesEmployee(2, "koko", "pp", 200, 10));
        staff.add(new HourlySalaryEmployee(3, "koko", "pp", 200, 10));
    }

    static int currentId = 3;

    static ArrayList<StaffMember> insert() {

//        outerloop:
//        while (true) {
//            System.out.println("1. Volunteer");
//            System.out.println("2. Salaries Employee");
//            System.out.println("3. HourlySalary Employee");
//            System.out.println("0. Exit");
//
//            while (true) {
//                System.out.print("Enter your type: ");
//                type = sc.nextLine();
//                if (type.matches("^[0-3]$")) {
//                    if(type.equals("0") )
//                        break outerloop;
//                    break;
//                }
//                System.out.print("Invalid input. Enter your type: ");
//            }
//        }
        outerloop:
        while (true) {
            System.out.println("1. Volunteer");
            System.out.println("2. Salaries Employee");
            System.out.println("3. HourlySalary Employee");
            System.out.println("0. Exit");
            System.out.print("Enter your type: ");
            type = sc.nextLine();
            while (true) {
                System.out.print("Enter your type: ");
                type = sc.nextLine();
                if (type.matches("^[0-3]$")) {
                    if(type.equals("0") )
                        break outerloop;
                    break;
                }
                System.out.print("Invalid input. Enter your type: ");
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

                    }
                }
                sc.nextLine();

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
                        }
                    }
                    SalariesEmployee salariesEmployee = new SalariesEmployee(currentId + 1, name, address, salary, bonus);
                    staff.add(salariesEmployee);
                    currentId = currentId + 1;
                }else {
                    System.out.print("Enter hours: ");
                    hours = sc.nextInt();
                    System.out.println("Enter rate: ");
                    rate = sc.nextDouble();
                    HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(currentId + 1,name,address,hours,rate);
                    staff.add(hourlySalaryEmployee);
                    currentId = currentId + 1;
                }

                System.out.println(BLUE + "* You're adding successfully."+RESET);
                System.out.println();
            }

//            if(type.equals("0")){
//                break;
//            }
//
        }

        return staff;
    }

    static void display() {
       CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
//        table.setColumnWidth(,);
        staff.forEach(dis -> {
            if (dis instanceof Volunteer volunteer) {
                System.out.println("===* Volunteer *===");
                System.out.println(volunteer);
//                table.addCell();
//                System.out.println("ID: " + volunteer.getId());
//                System.out.println("Name: " + volunteer.getName());
//                System.out.println("Address: " + volunteer.getAddress());
//                System.out.println("Salary: " + volunteer.getSalary());
            } else if (dis instanceof SalariesEmployee salariesEmployee) {
                System.out.println("===* Salaries Employee *===");
                System.out.println(salariesEmployee);


//                System.out.println("ID: " + salariesEmployee.getId());
//                System.out.println("Name: " + salariesEmployee.getName());
//                System.out.println("Address: " + salariesEmployee.getAddress());
//                System.out.println("Salary: " + salariesEmployee.getSalary());
//                System.out.println("Bonus: " + salariesEmployee.getBonus());
            } else {
                HourlySalaryEmployee hourlySalaryEmployee = (HourlySalaryEmployee) dis;
                System.out.println(hourlySalaryEmployee);


            }
        });
    }

    static void updateEmployee() {
        System.out.println("Enter or Search ID to Update: ");
        int idInput = sc.nextInt();
        staff.stream()
                .filter(up -> up.id == idInput)
                .findFirst()
                .ifPresent(

                        up -> {
                            System.out.println(up);
                            if (up instanceof Volunteer) {
                                System.out.println("1. Name\t2. Address\t3. Salary\t0. Cancel");

                            }
                            if (up instanceof SalariesEmployee) {
                                System.out.println("1. Name\t2. Address\t3. Salary\t4.Bonus \t0.Cancel");
                            }
                            if (up instanceof HourlySalaryEmployee) {
                                System.out.println("1. Name\t2. Address\t3. Hour\t4.Rate \t0.Cancel");
                            }
                            System.out.println("Choose column to update: ");
                            updateType = sc.nextInt();
                            sc.nextLine();

                            if (updateType == 0) return;
                            if (updateType == 1) {
                                System.out.println("Enter new name: ");
                                String name = sc.nextLine();
                                up.setName(name);

                            } else if (updateType == 2) {
                                System.out.println("Enter new address: ");
                                String address = sc.nextLine();
                                up.setAddress(address);
                            } else if (updateType == 3) {
                                if (up instanceof Volunteer) {
                                    System.out.println("Enter new salary: ");
                                    double salary = sc.nextDouble();
                                    ((Volunteer) up).setSalary(salary);
                                } else if (up instanceof SalariesEmployee) {
                                    System.out.println("Enter new salary: ");
                                    double salary = sc.nextDouble();
                                    ((SalariesEmployee) up).setSalary(salary);
                                }
                                if (up instanceof HourlySalaryEmployee) {
                                    System.out.println("Enter new hours: ");
                                    int hours = sc.nextInt();
                                    ((HourlySalaryEmployee) up).setHourWorked(hours);
                                }
                            } else if (updateType == 4) {
                                if (up instanceof SalariesEmployee) {
                                    System.out.println("Enter new bonus:");
                                    double bonus = sc.nextDouble();
                                    ((SalariesEmployee) up).setBonus(bonus);
                                } else if (up instanceof HourlySalaryEmployee) {
                                    System.out.println("Enter new rate: ");
                                    double rate = sc.nextDouble();
                                    ((HourlySalaryEmployee) up).setRate(rate);
                                }
                            }
                            System.out.println(up);
                        }
                );


    }

    static void remove() {
        System.out.println("Enter or Search ID to Remove: ");
        int idInput = sc.nextInt();
        boolean isRemove = staff.removeIf(up -> up.id == idInput);
        if (isRemove) {
            System.out.println("Removed successfully");

        } else {
            System.out.println("Not found" + idInput);
        }
    }

}


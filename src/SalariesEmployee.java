public class SalariesEmployee extends StaffMember{
    private double salary;
    private double bonus;

    public SalariesEmployee(int id, String name, String address, double salary, double bonus) {
        super( id, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double pay(){
        return salary + bonus;
    }

    @Override
    public String toString() {
        return "id : " + id + "\n" + "name : " + name + "\n" + "salary : " + salary + "\n" + "bonus : " + bonus + "\n";
    }
}

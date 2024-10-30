import java.util.Scanner;

class Employee {
    String name;
    int id;
    double basic = 0, da = 0, grossSal = 0, netSal = 0, it = 0;

    Employee(String name, double basic, int id) {
        this.name = name;
        this.basic = basic;
        this.id = id;
    }

    void display() {
        System.out.println("The net salary of " + name + " with ID " + id + " is " + netSal);
    }

    void computeNetSal() {
        da = 0.52 * basic;
        it = 0.3 * basic;
        grossSal = basic + da;
        netSal = grossSal - it;
    }
}

class Fulltime extends Employee {
    double bonus, deductions;

    Fulltime(String name, double basic, int id, double bonus, double deductions) {
        super(name, basic, id);
        this.bonus = bonus;
        this.deductions = deductions;
    }

    @Override
    void computeNetSal() {
        da = 0.52 * basic;
        it = 0.3 * basic;
        grossSal = basic + da + bonus - deductions;
        netSal = grossSal - it;
    }
}

class Parttime extends Employee {
    int hours;
    static final double HOURLY_RATE = 1000;

    Parttime(String name, int hours, int id) {
        super(name, 0, id); // Basic is not used for part-time
        this.hours = hours;
    }

    @Override
    void computeNetSal() {
        netSal = HOURLY_RATE * hours;
    }
}

public class EmpTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of employees: ");
        int numOfEmp = sc.nextInt();
        sc.nextLine();
        Employee[] employees = new Employee[numOfEmp];

        for (int i = 0; i < numOfEmp; i++) {
            System.out.print("Enter the Employee ID: ");
            int employeeId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter the name of the Employee: ");
            String name = sc.nextLine();
            System.out.print("Enter 1 for full-time and 2 for part-time: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter the basic salary: ");
                    double basicSal = sc.nextDouble();
                    System.out.print("Enter the bonus: ");
                    double bonus = sc.nextDouble();
                    System.out.print("Enter deductions: ");
                    double deductions = sc.nextDouble();
                    employees[i] = new Fulltime(name, basicSal, employeeId, bonus, deductions);
                    break;
                case 2:
                    System.out.print("Enter the hours worked: ");
                    int hours = sc.nextInt();
                    employees[i] = new Parttime(name, hours, employeeId);
                    break;
                default:
                    System.out.println("Invalid option");
                    i--; // Repeat the iteration for invalid option
                    break;
            }
            System.out.println();
        }

        for (Employee emp : employees) {
            emp.computeNetSal();
            emp.display();
        }
    }
}
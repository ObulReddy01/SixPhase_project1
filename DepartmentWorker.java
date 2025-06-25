import java.util.List;

public class DepartmentWorker extends Thread {
    private List<Employee> employees;

    public DepartmentWorker(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void run() {
        try {
            EmployeeDAO dao = new EmployeeDAO();
            for (Employee emp : employees) {
                try {
                    dao.insertEmployee(emp);
                } catch (Exception e) {
                    System.err.println("Insert failed for: " + emp.name);
                }
            }
            dao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

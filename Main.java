import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String errorLog = "error.log";

        Map<String, List<Employee>> deptMap = CSVReader.readCSV(filePath, errorLog);
        List<Thread> threads = new ArrayList<>();

        for (List<Employee> deptEmployees : deptMap.values()) {
            DepartmentWorker worker = new DepartmentWorker(deptEmployees);
            threads.add(worker);
            worker.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Data import complete.");
    }
}

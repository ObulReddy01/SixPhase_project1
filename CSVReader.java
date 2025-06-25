import java.io.*;
import java.util.*;

public class CSVReader {
    public static Map<String, List<Employee>> readCSV(String filePath, String errorLog) {
        Map<String, List<Employee>> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorLog))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String dept = parts[2].trim();
                    double salary = Double.parseDouble(parts[3].trim());

                    Employee emp = new Employee(id, name, dept, salary);
                    map.computeIfAbsent(dept, k -> new ArrayList<>()).add(emp);
                } catch (Exception e) {
                    errorWriter.write("Error processing line: " + line + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}

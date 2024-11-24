
import java.util.*;
import java.util.stream.Collectors;
public class TestClass {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee(1, "Prisha", 20000),
                new Employee(2, "Jitesh", 30000),
                new Employee(3, "Poonam", 40000),
                new Employee(4, "Mike", 30000),
                new Employee(5, "Sike", 20000)
        );
        Map<Integer, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e -> e.getSalary()));
        Set<Map.Entry<Integer, List<Employee>>> entrySet = newData.entrySet();
        Iterator<Map.Entry<Integer, List<Employee>>> iterator = entrySet.iterator();
        while(iterator.hasNext())
        {
            Map.Entry<Integer, List<Employee>> entry = iterator.next();
            System.out.println(entry);
        }

        System.out.println("================");
        for(Map.Entry<Integer,List<Employee>> entry : newData.entrySet()) {
            List<Employee> val = entry.getValue();
            for (Employee emp : val)
            {
                System.out.println(emp.getName()+"------->");
                System.out.println(entry.getKey());
            }
        }
    }}
package telran.employees.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.SalaryDistribution;

public class CompanyImpl implements Company {
	HashMap<Long, Employee> employees = new HashMap<>(); // most effective structure for the interface methods
	TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	private Map<String, List<Employee>> employeesDepartment = new HashMap<>();

	@Override
	public boolean addEmployee(Employee empl) {
		boolean res = employees.putIfAbsent(empl.id(), empl) == null;
		if (res) {
			addEmployeesAge(empl);
			employeesDepartment.computeIfAbsent(empl.department(), k -> new ArrayList<>()).add(empl);
		}

		return res;
	}

	private void addEmployeesAge(Employee empl) {
		int age = getAge(empl.birthDate());
		employeesAge.computeIfAbsent(age, k -> new LinkedList<>()).add(empl);

	}

	@Override
	public Employee removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl != null) {
			removeEmployeeAge(empl);
			List<Employee> departmentEmployees = employeesDepartment.get(empl.department());
			departmentEmployees.remove(empl);
			if (departmentEmployees.isEmpty()) {
				employeesDepartment.remove(empl.department());
			}
		}

		return empl;
	}

	private void removeEmployeeAge(Employee empl) {
		int age = getAge(empl.birthDate());
		List<Employee> list = employeesAge.get(age);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesAge.remove(age);
		}

	}

	@Override
	public Employee getEmployee(long id) {

		return employees.get(id);
	}

	@Override
	public List<Employee> getEmployees() {
		return new ArrayList<>(employees.values());
	}

	@Override
	public List<DepartmentSalary> getDepartmentSalaryDistribution() {
		// TODO Auto-generated method stub
		return employees.values().stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)))
				.entrySet().stream().map(e -> new DepartmentSalary(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
	}

	@Override
	public List<SalaryDistribution> getSalaryDistribution(int interval) {
		Map<Integer, Long> mapIntervalNumbers = employees.values().stream()
				.collect(Collectors.groupingBy(e -> e.salary() / interval, Collectors.counting()));
		return mapIntervalNumbers
				.entrySet().stream().map(e -> new SalaryDistribution(e.getKey() * interval,
						e.getKey() * interval + interval, e.getValue().intValue()))
				.sorted((sd1, sd2) -> Integer.compare(sd1.min(), sd2.min())).toList();
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		// TODO Auto-generated method stub
		return employeesDepartment.getOrDefault(department, new ArrayList<>());
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		// TODO Auto-generated method stub
		return employees.values().stream().filter(e -> e.salary() >= salaryFrom && e.salary() <= salaryTo)
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> getEmployeesByAge(int ageFrom, int ageTo) {

		return employeesAge.subMap(ageFrom, ageTo + 1).values().stream().flatMap(List::stream).toList();
	}

	public int getAge(LocalDate birthDate) {

		return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
	}

	@Override
	public Employee updateSalary(long id, int newSalary) {
		// TODO Auto-generated method stub
		Employee employee = employees.get(id);
		if (employee != null) {
			Employee updatedEmployee = new Employee(id, employee.name(), employee.department(), newSalary,
					employee.birthDate());
			employees.put(id, updatedEmployee);
			return updatedEmployee;
		}
		return null;
	}

	@Override
	public Employee updateDepartment(long id, String department) {
		// TODO Auto-generated method stub
		Employee employee = employees.get(id);
		if (employee != null) {
			Employee updatedEmployee = new Employee(id, employee.name(), department, employee.salary(),
					employee.birthDate());
			employees.put(id, updatedEmployee);
			return updatedEmployee;
		}
		return null;
	}

}

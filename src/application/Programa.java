package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Employee;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

			String linha = bf.readLine();
			while (linha != null) {
				
				String[] linhaCsv = linha.split(",");
				list.add(new Employee(linhaCsv[0], linhaCsv[1], Double.parseDouble(linhaCsv[2])));
				linha = bf.readLine();
			}
		} catch (IOException e) {
			e.getStackTrace();
		}

		System.out.print("Enter salary: ");
		Double salaryBase = sc.nextDouble();
		
		Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		
		List<String> emails = list.stream()
				.filter(e -> e.getSalary() > salaryBase)
				.map(e -> e.getEmail())
				.sorted(comp)
				.collect(Collectors.toList());
		
		emails.forEach(System.out::println);
		
		double sum = list.stream()
				.filter(e -> e.getName().charAt(0) == 'M')
				.map(e -> e.getSalary())
				.reduce(0.0, (x,y) -> x + y);
		
		System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
		
		sc.close();

	}

}

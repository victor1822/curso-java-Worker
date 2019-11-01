package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program { //programa principal 

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US); //utilizar o padrao internacional de numeros em ponto flutuante
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //definir o formato de leitura da data para este padrao
		
		//Pegando os dados do colaborador e passando para a classe em si

		System.out.print("Digite o nome do departamento: ");
		String departmentName = sc.nextLine();
		System.out.println("Digite os dados do colaborador: ");
		System.out.print("Nome: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.print("Salario-base: ");
		double baseSalary = sc.nextDouble();

		//passando o valor das variaveis para um objeto da classe Worker.
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary,
				new Department(departmentName)); //instanciando um novo objeto utilizando o construtor da classe Worker, o mesmo para a classe Department
		System.out.print("Quantos contratos para este colaborador?");
		//le o numero de contratos que se deseja digitar
		int n = sc.nextInt();
		
		//passa os dados de cada contrato para a classe principal
		for (int i = 0; i < n; i++) {
			System.out.println("Digite os dados do contrato #" + i + ": ");
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(sc.next());
			System.out.print("Valor por hora: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duracao(horas): ");
			int hours = sc.nextInt();
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract);
		}

		//printa na tela o resultado: Quanto o colaborador ganha, no mes que deu de entrada, de acordo com os contratos
		System.out.print("Digite mes e ano para calcular o salario do mes (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		System.out.println("Name: " + worker.getName());
		System.out.println("Departamento: " + worker.getDepartment().getName());
		System.out.println("Salario no mes " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
		sc.close();
	}

}

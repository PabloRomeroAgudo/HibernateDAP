package view;

import java.util.List;

import controller.GestionController;
import dao.EmpleadoDAOImpl;
import io.IO;
import models.Departamento;
import models.Empleado;

public class ViewEmpleado {
	
	static GestionController controller = GestionController.builder().empleadoDAO(new EmpleadoDAOImpl()).build();
	
	public static void menu() {		
		List<String> opciones = List.of(
				"0. Salir",
				"\n1. Añadir empleado",  
				"\n2. Añadir departamento al empleado",  
				"\n3. Añadir proyecto al empleado",  
				"\n4. Mostrar todo", 
				"\n5. Buscar por nombre", 
				"\n6. Buscar por ID", 
				"\n7. Modificar salario",
				"\n8. Borrar"); 
				
		while (true) {
			IO.println(opciones);
			switch (IO.readInt()) {
				case 0:
					return;
				case 1:
					add();
					break;
				case 2:
					addDepartToEmp();
					break;
				case 3:
					addProjectToEmp();
					break;
				case 4:
					findAll();
					break;
				case 5:
					findByName();
					break;
				case 6:
					findById();
					break;
				case 7:
					updateSalary();
					break;
				case 8:
					delete();
					break;
				default:
			}
		}	
	}
	
	public static void add() {
		IO.print("Introduce el nombre del empleado");
		String nombre = IO.readString();
		IO.print("Introduce el salario del empleado");
		Double salario = IO.readDouble();
		
		Boolean anyadido = controller.addEmpleado(Empleado.builder()
				.nombre(nombre)
				.salario(salario)
				.build());			
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void addDepartToEmp() {
		IO.print("Introduce el id del empleado");
		Integer id = IO.readInt();
		IO.print("Introduce el id del departamento");
		Integer departamento = IO.readInt();
		
		Boolean anyadido = controller.addDepToEmp(id, departamento);
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void addProjectToEmp() {
		IO.print("Introduce el id del empleado");
		Integer id = IO.readInt();
		IO.print("Introduce el id del proyecto");
		Integer project = IO.readInt();
		
		Boolean anyadido = controller.addProjectToEmp(id, project);
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void findAll() {
		controller.getEmpleados().forEach(System.out::println);
	}
	
	public static void findByName() {
		IO.print("Introduce el inico del nombre");
		String init = IO.readString();
		
		controller.getEmpleadoByName(init).forEach(System.out::println);
	}
	
	public static void findById() {
		IO.print("Introduce el id del empleado");
		Integer id = IO.readInt();
		
		System.out.println(controller.getEmpleadoById(id));
	}
	
	public static void updateSalary() {
		IO.print("Introduce el id del empleado a modificar");
		Integer id = IO.readInt();
		
		IO.print("Introduce el nuevo salario del empleado");
		Double salario = IO.readDouble();
		
		Boolean anyadido = controller.updateSalary(Empleado.builder()
					.id(id)
					.salario(salario)
					.build());
	
		IO.print(anyadido ? "Modificado correctamente" : "No se ha podido modificar");
	}
	
	public static void delete() {
		IO.print("Introduce el id del empleado");
		Integer id = IO.readInt();
		
		Boolean anyadido = controller.deleteEmpleado(Empleado.builder().id(id).build());
		
		IO.print(anyadido ? "Borrado correctamente" : "No se ha podido borrar");
	}
}

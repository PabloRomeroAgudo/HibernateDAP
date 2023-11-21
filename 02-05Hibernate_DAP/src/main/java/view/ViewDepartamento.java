package view;

import java.util.List;

import controller.GestionController;
import dao.DepartamentoDAOImpl;
import io.IO;
import models.Departamento;
import models.Empleado;

public class ViewDepartamento {
	static GestionController controller = GestionController.builder().departamentoDAO(new DepartamentoDAOImpl()).build();
	
	public static void menu() {		
		List<String> opciones = List.of(
				"0. Salir",
				"\n1. Añadir departamento",  
				"\n2. Añadir empleado al departamento",
				"\n4. Mostrar todo", 
				"\n5. Buscar por nombre", 
				"\n6. Buscar por ID", 
				"\n7. Modificar jefe",
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
					addEmpToDep();
					break;
				case 3:
					findAll();
					break;
				case 4:
					findByName();
					break;
				case 5:
					findById();
					break;
				case 6:
					updateBoss();
					break;
				case 7:
					delete();
					break;
				default:
			}
		}	
	}
	
	public static void add() {
		IO.print("Introduce el nombre del departamento");
		String nombre = IO.readString();
		
		Boolean anyadido = controller.addDepartamento(Departamento.builder()
				.nombre(nombre)
				.build());
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void addEmpToDep() {
		IO.print("Introduce el id del departamento");
		Integer id = IO.readInt();
		IO.print("Introduce el id del empleado");
		Integer empleado = IO.readInt();
		
		Boolean anyadido = controller.addEmpToDep(id, empleado);
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void findAll() {
		controller.getDepartamentos().forEach(System.out::println);
	}
	
	public static void findByName() {
		IO.print("Introduce el inico del nombre");
		String init = IO.readString();
		
		controller.getDepartamentoByName(init).forEach(System.out::println);
	}
	
	public static void findById() {
		IO.print("Introduce el id del departamento");
		Integer id = IO.readInt();
		
		System.out.println(controller.getDepartamentoById(id));
	}
	
	public static void updateBoss() {
		IO.print("Introduce el id del departamento");
		Integer id = IO.readInt();
		IO.print("Introduce el id del jefe");
		Integer jefe = IO.readInt();
		
		Boolean anyadido = controller.updateBoss(Departamento.builder()
				.id(id)
				.jefe(Empleado.builder()
						.id(jefe).build()).build());
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void delete() {
		IO.print("Introduce el id del departamento");
		Integer id = IO.readInt();
		
		Boolean anyadido = controller.deleteDepartamento(Departamento.builder().id(id).build());
		
		IO.print(anyadido ? "Borrado correctamente" : "No se ha podido borrar");
	}
}

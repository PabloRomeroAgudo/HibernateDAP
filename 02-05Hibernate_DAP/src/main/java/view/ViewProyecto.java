package view;

import java.util.List;

import controller.GestionController;
import dao.ProyectoDAOImpl;
import io.IO;
import models.Proyecto;

public class ViewProyecto {
	
	static GestionController controller = GestionController.builder().proyectoDAO(new ProyectoDAOImpl()).build();
	
	public static void menu() {		
		List<String> opciones = List.of(
				"0. Salir",
				"\n1. Añadir proyecto",  
				"\n2. Mostrar todo", 
				"\n3. Buscar por nombre", 
				"\n4. Buscar por ID", 
				"\n5. Modificar empleados",
				"\n6. Borrar"); 
				
		while (true) {
			IO.println(opciones);
			switch (IO.readInt()) {
				case 0:
					return;
				case 1:
					add();
					break;
				case 2:
					findAll();
					break;
				case 3:
					findByName();
					break;
				case 4:
					findById();
					break;
				case 5:
					updateEmps();
					break;
				case 6:
					delete();
					break;
				default:
			}
		}	
	}
	
	public static void add() {
		IO.print("Introduce el nombre del proyecto");
		String nombre = IO.readString();
		
		Boolean anyadido = controller.createProyecto(Proyecto.builder()
				.nombre(nombre)
				.build());
		IO.print(anyadido ? "Añadido correctamente" : "No se ha podido añadir");
	}
	
	public static void findAll() {
		controller.getProyectos().forEach(System.out::println);
	}
	
	public static void findByName() {
		IO.print("Introduce el inico del nombre");
		String init = IO.readString();
		
		controller.getProyectoByName(init).forEach(System.out::println);
	}
	
	public static void findById() {
		IO.print("Introduce el id del proyecto");
		Integer id = IO.readInt();
		
		System.out.println(controller.getProyectoById(id));
	}
	
	public static void updateEmps() {
		IO.print("Introduce el id del proyecto");
		Integer id = IO.readInt();
		IO.print("Introduce el id del empleado");
		Integer empleado = IO.readInt();
		
		Boolean anyadido = controller.updateEmps(id, empleado);
		IO.print(anyadido ? "Modificado correctamente" : "No se ha podido modificar");
	}
	
	public static void delete() {
		IO.print("Introduce el id del Proyecto");
		Integer id = IO.readInt();
		
		Boolean anyadido = controller.deleteProyecto(Proyecto.builder().id(id).build());
		
		IO.print(anyadido ? "Borrado correctamente" : "No se ha podido borrar");
	}
}

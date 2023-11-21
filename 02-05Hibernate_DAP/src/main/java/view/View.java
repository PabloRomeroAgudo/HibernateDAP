package view;

import java.util.List;

import io.IO;

public class View {
	
	
	public static void main(String[] args) 
	{
		List<String> opciones = List.of(
				"0. Salir",
				"1. Empleados", 
				"2. Departamentos", 
				"3. Proyectos");
		
		while (true) {
			IO.println(opciones);
			switch (IO.readInt()) {
				case 0:
					return;
				case 1:
					ViewEmpleado.menu();
					break;
				case 2:
					ViewDepartamento.menu();
					break;
				case 3:
					ViewProyecto.menu();
					break;
				default:
			}
		}		

	}

}

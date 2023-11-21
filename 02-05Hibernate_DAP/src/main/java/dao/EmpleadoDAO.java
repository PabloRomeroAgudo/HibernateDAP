package dao;

import models.Empleado;

public interface EmpleadoDAO extends CrudDAO<Empleado> {
	Boolean addDepToEmp(Integer id, Integer departamento);
	
	Boolean addProjectToEmp(Integer id, Integer project);
}

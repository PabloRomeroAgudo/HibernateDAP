package dao;

import models.Departamento;

public interface DepartamentoDAO extends CrudDAO<Departamento> {
	Boolean addEmpToDep(Integer id, Integer empleado);
}

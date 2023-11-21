package dao;

import models.Proyecto;

public interface ProyectoDAO extends CrudDAO<Proyecto> {
	Boolean updateAux(Integer id, Integer empleado);
}

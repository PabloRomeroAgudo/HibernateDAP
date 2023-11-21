package dao;

import java.util.List;
import java.util.Set;

import models.Departamento;
import models.Empleado;
import models.Proyecto;

public class EmpleadoDAOImpl implements EmpleadoDAO {
	
	@Override
	public Boolean add(Empleado entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		
		return true;
	}

	@Override
	public Boolean addDepToEmp(Integer id, Integer departamento) {
		manager.getTransaction().begin();
		Empleado emp = manager.find(Empleado.class, id);
		Departamento depToAdd = manager.find(Departamento.class, departamento);
		emp.setDepartamento(depToAdd);
		manager.merge(emp);
		manager.getTransaction().commit();
		
		return depToAdd != null;
	}
	
	@Override
	public Boolean addProjectToEmp(Integer id, Integer project) {
		manager.getTransaction().begin();
		Empleado emp = manager.find(Empleado.class, id);
		Set<Proyecto> proyectitos = emp.getProyectos();
		Proyecto projectToAdd = manager.find(Proyecto.class, project);
		proyectitos.add(projectToAdd);
		emp.setProyectos(proyectitos);
		manager.merge(emp);
		manager.getTransaction().commit();
		
		return projectToAdd != null;
	}
	
	@Override
	public List<Empleado> findAll() {
		List<Empleado> emps = manager.createQuery("FROM Empleado").getResultList();
		
		return emps;
	}

	@Override
	public List<Empleado> findByName(String init) {
		List<Empleado> emps = manager.createQuery("FROM Empleado WHERE nombre LIKE :init")
				.setParameter("init", init + "%")
				.getResultList();
		
		return emps;
	}

	@Override
	public Empleado findById(Integer id) {
		Empleado emp = manager.find(Empleado.class, id);
		
		return emp;
	}

	@Override
	public Boolean update(Empleado entity) {
		
		manager.getTransaction().begin();
		Empleado emp = manager.find(Empleado.class, entity.getId());
		emp.setSalario(entity.getSalario());
		manager.merge(emp);
		manager.getTransaction().commit();
		
		return true;
	}

	@Override
	public Boolean delete(Empleado entity) {
		manager.getTransaction().begin();
		Empleado emp = manager.find(Empleado.class, entity.getId());
		manager.remove(emp);
		manager.getTransaction().commit();
		
		return emp != null;
	}

}

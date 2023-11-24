package dao;

import java.util.HashSet;
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
		Set<Empleado> emps = depToAdd.getEmpleados() != null ? depToAdd.getEmpleados() : new HashSet<>();
		emps.add(emp);
		//emps.stream().map(empleado -> empleado = Empleado.builder().id(empleado.getId()).build());
		depToAdd.setEmpleados(emps);
		emp.setDepartamento(depToAdd);
		
		manager.merge(emp);
		manager.merge(depToAdd);
		manager.getTransaction().commit();
		
		return depToAdd != null;
	}
	
	@Override
	public Boolean addProjectToEmp(Integer id, Integer project) {
		manager.getTransaction().begin();
		Empleado emp = manager.find(Empleado.class, id);
		Set<Proyecto> proyectitos = emp.getProyectos() != null ? emp.getProyectos() : new HashSet<>();
		Proyecto projectToAdd = manager.find(Proyecto.class, project);
		proyectitos.add(projectToAdd);
		emp.setProyectos(proyectitos);
		
		Set<Empleado> empleaditos = projectToAdd.getEmpleados() != null ? projectToAdd.getEmpleados() : new HashSet<>();
		empleaditos.add(emp);
		projectToAdd.setEmpleados(empleaditos);
		
		manager.merge(emp);
		manager.merge(projectToAdd);
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
		Departamento dep = manager.find(Departamento.class, emp.getDepJefe().getId());
		dep.setJefe(null);
		manager.remove(emp);
		manager.merge(dep);
		manager.getTransaction().commit();
		
		return emp != null;
	}

}

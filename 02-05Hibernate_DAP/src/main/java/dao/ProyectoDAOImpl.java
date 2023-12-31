package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Empleado;
import models.Proyecto;

public class ProyectoDAOImpl implements ProyectoDAO {

	@Override
	public Boolean add(Proyecto entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		
		return true;
	}

	@Override
	public List<Proyecto> findAll() {
		List<Proyecto> projects = manager.createQuery("FROM Proyecto").getResultList();
		
		return projects;
	}

	@Override
	public List<Proyecto> findByName(String init) {
		List<Proyecto> projects = manager.createQuery("FROM Proyecto WHERE nombre LIKE :init")
				.setParameter("init", init + "%")
				.getResultList();
		
		return projects;
	}

	@Override
	public Proyecto findById(Integer id) {
		Proyecto project = manager.find(Proyecto.class, id);
		
		return project;
	}

	@Override
	public Boolean updateAux(Integer id, Integer empleado) {
		manager.getTransaction().begin();
		Proyecto project = manager.find(Proyecto.class, id);
		Set<Empleado> empleaditos = project.getEmpleados() != null ? project.getEmpleados() : new HashSet<>();
		Empleado empToAdd = manager.find(Empleado.class, empleado);
		empleaditos.add(empToAdd);
		project.setEmpleados(empleaditos);
		Boolean actualizado = update(project);
		Set<Proyecto> proyectitos = empToAdd.getProyectos() != null ? empToAdd.getProyectos() : new HashSet<>();
		proyectitos.add(project);
		empToAdd.setProyectos(proyectitos);
		
		manager.merge(empToAdd);
		manager.getTransaction().commit();
		
		return actualizado;
	}
	
	@Override
	public Boolean update(Proyecto entity) {
		
		manager.merge(entity);
		
		return entity != null;
	}

	@Override
	public Boolean delete(Proyecto entity) {
		manager.getTransaction().begin();
		Proyecto project = manager.find(Proyecto.class, entity.getId());
		Set<Empleado> empleaditos = project.getEmpleados();
		if (empleaditos != null) {
			for (Empleado empleado : empleaditos) {
				Set<Proyecto> projects_emp = empleado.getProyectos();
				projects_emp.remove(project);
				empleado.setProyectos(projects_emp);
				manager.merge(empleado);
			}
		}
		manager.remove(project);
		manager.getTransaction().commit();
		
		return project != null;
	}

}

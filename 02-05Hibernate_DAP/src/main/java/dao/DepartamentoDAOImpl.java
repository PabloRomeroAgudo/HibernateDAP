package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Departamento;
import models.Empleado;

public class DepartamentoDAOImpl implements DepartamentoDAO{
	
	@Override
	public Boolean add(Departamento entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		
		return true;
	}
	
	@Override
	public Boolean addEmpToDep(Integer id, Integer empleado) {
		manager.getTransaction().begin();
		Departamento dep = manager.find(Departamento.class, id);
		Set<Empleado> empleaditos = dep.getEmpleados() != null ? dep.getEmpleados() : new HashSet<>();
		Empleado empToAdd = manager.find(Empleado.class, empleado);
		empleaditos.add(empToAdd);
		dep.setEmpleados(empleaditos);
		//empToAdd.setDepartamento(dep);
		manager.merge(dep);
		//manager.merge(empToAdd);
		manager.getTransaction().commit();
		
		return empToAdd != null;
	}

	@Override
	public List<Departamento> findAll() {
		List<Departamento> departs = manager.createQuery("FROM Departamento").getResultList();
		
		return departs;
	}

	@Override
	public List<Departamento> findByName(String init) {
		List<Departamento> departs = manager.createQuery("FROM Departamento WHERE nombre LIKE :init")
				.setParameter("init", init + "%")
				.getResultList();
		
		return departs;
	}

	@Override
	public Departamento findById(Integer id) {
		Departamento dep = manager.find(Departamento.class, id);
		
		return dep;
	}

	@Override
	public Boolean update(Departamento entity) {
		manager.getTransaction().begin();
		Departamento dep = manager.find(Departamento.class, entity.getId());
		Empleado bossToAdd = manager.find(Empleado.class, entity.getJefe().getId());
		dep.setJefe(bossToAdd);
		bossToAdd.setDepJefe(dep);
		manager.merge(dep);
		manager.merge(bossToAdd);
		manager.getTransaction().commit();
		
		return true;
	}

	@Override
	public Boolean delete(Departamento entity) {
		manager.getTransaction().begin();
		Departamento dep = manager.find(Departamento.class, entity.getId());
		manager.remove(dep);
		
		List<Integer> emps = manager.createQuery("SELECT id FROM Empleado WHERE departamento=:id_depart").setParameter("id_depart", dep.getId()).getResultList();
		for (Integer id : emps) {
			Empleado e = manager.find(Empleado.class, id);
			e.setDepartamento(null);
			manager.merge(e);
		}
		
		manager.getTransaction().commit();
		
		return dep != null;
	}

}

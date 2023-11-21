package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public interface CrudDAO<T> {	
	
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-empresarial");
	
	public static final EntityManager manager = emf.createEntityManager();
	
	Boolean add(T entity);
	
	List<T> findAll();
	
	List<T> findByName(String init);
	
	T findById(Integer id);
	
	Boolean update(T entity);
	
	Boolean delete(T entity);
}

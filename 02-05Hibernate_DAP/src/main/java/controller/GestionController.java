package controller;

import java.util.List;
import java.util.logging.Logger;

import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
import dao.ProyectoDAO;
import lombok.Builder;
import models.Departamento;
import models.Empleado;
import models.Proyecto;

@Builder
public class GestionController {
	private final Logger logger = Logger.getLogger(GestionController.class.getName());

    // Mis dependencias
    private DepartamentoDAO departamentoDAO;
    private EmpleadoDAO empleadoDAO;
    private ProyectoDAO proyectoDAO;

    // Empleados
    public List<Empleado> getEmpleados() {
        logger.info("Obteniendo Empleados");
        return empleadoDAO.findAll();
    }

    public Boolean addEmpleado(Empleado emp) {
        logger.info("Creando Empleado");
        return empleadoDAO.add(emp);
    }
    
    public Boolean addDepToEmp(Integer id, Integer departamento) {
    	logger.info("Añadiendo departamento a empleado");
    	return empleadoDAO.addDepToEmp(id, departamento);
    }
    
    public Boolean addProjectToEmp(Integer id, Integer project) {
    	logger.info("Añadiendo proyecto a empleado");
    	return empleadoDAO.addDepToEmp(id, project);
    }
    
    public List<Empleado> getEmpleadoByName(String init) {
    	logger.info("Obteniendo Empleado con inicio de nombre: " + init);
        return empleadoDAO.findByName(init);
    }

    public Empleado getEmpleadoById(Integer id) {
        logger.info("Obteniendo Empleado con id: " + id);
        return empleadoDAO.findById(id);
    }

    public Boolean updateSalary(Empleado emp) {
        logger.info("Actualizando salario con id: " + emp.getId());
        return empleadoDAO.update(emp);
    }

    public Boolean deleteEmpleado(Empleado emp) {
        logger.info("Eliminando Empleado con id: " + emp.getId());
        return empleadoDAO.delete(emp);
    }

    // Departamentos
    public List<Departamento> getDepartamentos() {
        logger.info("Obteniendo Departamentos");
        return departamentoDAO.findAll();
    }

    public Boolean addDepartamento(Departamento dep) {
        logger.info("Creando Departamento");
        return departamentoDAO.add(dep);
    }
    
    public Boolean addEmpToDep(Integer id, Integer empleado) {
        logger.info("Añadiendo empleado con id " + empleado + " al departamento con id " + id);
        return departamentoDAO.addEmpToDep(id, empleado);
    }
    
    public List<Departamento> getDepartamentoByName(String init) {
    	logger.info("Obteniendo Departamentos con inicio de nombre: " + init);
        return departamentoDAO.findByName(init);
    }

    public Departamento getDepartamentoById(Integer id) {
        logger.info("Obteniendo Departamento con id: " + id);
        return departamentoDAO.findById(id);
    }

    public Boolean updateBoss(Departamento dep) {
        logger.info("Actualizando jefe del Departamento con id: " + dep.getId());
        return departamentoDAO.update(dep);
    }

    public Boolean deleteDepartamento(Departamento dep) {
        logger.info("Eliminando Departamento con id: " + dep.getId());
        return departamentoDAO.delete(dep);
    }
    
    // Proyectos
    public List<Proyecto> getProyectos() {
        logger.info("Obteniendo Proyectos");
        return proyectoDAO.findAll();
    }

    public Boolean createProyecto(Proyecto project) {
        logger.info("Creando Proyecto");
        return proyectoDAO.add(project);
    }

    public List<Proyecto> getProyectoByName(String init) {
    	logger.info("Obteniendo Proyectos con inicio de nombre: " + init);
        return proyectoDAO.findByName(init);
    }
    
    public Proyecto getProyectoById(Integer id) {
        logger.info("Obteniendo Proyecto con id: " + id);
        return proyectoDAO.findById(id);
    }

    public Boolean updateEmps(Integer id, Integer empleado) {
        logger.info("Actualizando Proyecto con id: " + id);
        return proyectoDAO.updateAux(id, empleado);
    }

    public Boolean deleteProyecto(Proyecto project) {
        logger.info("Eliminando Proyecto con id: " + project.getId());
        return proyectoDAO.delete(project);
    }

}

package models;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String nombre;
	@Column
	private Double salario;
	
	@OneToOne
	@JoinColumn(name = "depJefe")
	private Departamento depJefe;
	
	@ManyToOne
	@JoinColumn(name = "departamento")
	private Departamento departamento;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Proyecto> proyectos;


	@Override
	public String toString() {
		Integer cont = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Empleado [id=" + this.getId() + ", nombre=" + this.getNombre() + ", salario=" + this.getSalario() 
				+ ", depJefe=" + (this.getDepJefe() != null ? this.getDepJefe().getId() : "null")
				+ ", departamento=" + (this.getDepartamento() != null ? this.getDepartamento().getId() : "null")
				+ ", proyectos[");
		if (this.getProyectos() != null) {
			for (Proyecto proyecto : proyectos) {
				sb.append(proyecto.getId());
				if (cont != proyectos.size()) {
					sb.append(", ");
				}
				cont++;
			}			
		}
		sb.append("]]");
		
		return sb.toString();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Empleado))
			return false;
		Empleado empleado = (Empleado) o;
		return id.equals(empleado.id) && nombre.equals(empleado.nombre) && salario.equals(empleado.salario);
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, salario);
	}
	
	
}

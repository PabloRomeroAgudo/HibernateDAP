package models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	
	@ManyToMany
	private Set<Proyecto> proyectos;


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Empleado [id=" + this.getId() + ", nombre=" + this.getNombre() + ", salario=" + this.getSalario() 
				+ ", depJefe=" + (this.getDepJefe() != null ? this.getDepJefe().getId() : "null")
				+ ", departamento=" + (this.getDepartamento() != null ? this.getDepartamento().getId() : "null")
				+ ", proyectos[");
		if (this.getProyectos() != null) {
			for (Proyecto proyecto : proyectos) {
				sb.append(proyecto.getId() + ", ");
			}			
		}
		sb.append("]]");
		
		return sb.toString();
	}
}

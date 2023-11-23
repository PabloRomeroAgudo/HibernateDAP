package models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "proyecto")
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String nombre;
	
	@ManyToMany(mappedBy = "proyectos")
	private Set<Empleado> empleados;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Proyecto [id=" + this.getId() + ", nombre=" + this.getNombre() + ", id_empleados=[");
		if(this.getEmpleados() != null) {
			for (Empleado empleado : empleados) {
				sb.append(empleado.getId() + ", ");
			}
		}
		sb.append("]]");
		
		return sb.toString();
	}
}

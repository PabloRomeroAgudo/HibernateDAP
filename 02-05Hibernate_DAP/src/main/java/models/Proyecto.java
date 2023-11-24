package models;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@ManyToMany(mappedBy = "proyectos", fetch = FetchType.EAGER)
	private Set<Empleado> empleados;

	@Override
	public String toString() {
		Integer cont = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Proyecto [id=" + this.getId() + ", nombre=" + this.getNombre() + ", id_empleados=[");
		if(this.getEmpleados() != null) {
			for (Empleado empleado : empleados) {
				sb.append(empleado.getId());
				if (cont != empleados.size()) {
					sb.append(", ");
				}
				cont ++;
			}
		}
		sb.append("]]");
		
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Proyecto))
			return false;
		Proyecto proyectos = (Proyecto) o;
		return id.equals(proyectos.id) && nombre.equals(proyectos.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	
}

package models;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "departamento")
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String nombre;
	
	@OneToOne(mappedBy = "depJefe")
	private Empleado jefe;
	
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
	private Set<Empleado> empleados;

	
	
	@Override
	public String toString() {
		Integer cont = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Departamento [id=" + this.getId() + ", nombre=" + this.getNombre() 
		+ ", jefe=" + (this.getJefe() != null ? this.getJefe().getId() : "null") 
		+ ", empleados=[");
		if (this.getEmpleados() != null) {
			for (Empleado empleado : empleados) {
				sb.append(empleado.getId());
				if (cont != empleados.size()) {
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
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento departamento = (Departamento) o;
        return id.equals(departamento.id) && nombre.equals(departamento.nombre) && jefe.equals(departamento.jefe);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, jefe);
	}

	
}

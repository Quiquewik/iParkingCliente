package cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {

	@Id
	private String id;
	private String nombreUsuario;
	private String password;
	@Indexed(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String correo;
	private int	tipoUsuario;
	//Hay 3 tipos, Básica, Trabajador y Premium
	private String membresia;
	private Date inicioMembresia;
	private Date finMembresia;
	private boolean isMembresiaActiva;
	private Vehiculo[] listaVehiculos;
	public Usuario(String nombreUsuario, String password, String dni, String nombre, String apellidos, String direccion, String correo,Vehiculo[] listaVehiculos, int tipoUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.correo = correo;
		this.listaVehiculos = listaVehiculos;
		this.tipoUsuario = tipoUsuario;
	}

	//Default constructor para SingIn para usuario común
	public Usuario(String dni, String pass, String membresia) {
		this.dni = dni;
		this.password = pass;
		this.tipoUsuario = 2;
		this.listaVehiculos = new Vehiculo[0];
		this.membresia = membresia;
	}

}

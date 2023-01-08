package cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

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
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String correo;
	private Vehiculo[] listaVehiculos;
	private int	tipoUsuario;

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
	public Usuario(String dni, String pass) {
		this.dni = dni;
		this.password = pass;
		this.tipoUsuario = 2;
		this.listaVehiculos = new Vehiculo[0];
	}

	//Metodo check para comprobar si usuario tien todos los datos.
	private boolean checkUsuario(Usuario usuario){
		boolean result;

		result = usuario.id != null && usuario.nombreUsuario != null && usuario.password != null && usuario.dni != null && usuario.dni.length() == 9 &&
				usuario.nombre != null && usuario.apellidos != null && usuario.direccion != null && usuario.correo != null && usuario.tipoUsuario > 0 &&
				usuario.listaVehiculos.length > 0;

		return result;
	}

}

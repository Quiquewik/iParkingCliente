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
	@Indexed(unique = true)
	private String nombreUsuario;
	private String password;
	@Indexed(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	@Indexed(unique = true)
	private String correo;
	private int	tipoUsuario;

	public Usuario(String nombreUsuario, String password, String dni, String nombre, String apellidos, String direccion, String correo, int tipoUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.correo = correo;
		this.tipoUsuario = tipoUsuario;
	}

	private boolean checkUsuario(Usuario usuario){
		boolean result;

		result = usuario.id != null && usuario.nombreUsuario != null && usuario.password != null && usuario.dni != null && usuario.dni.length() == 9 &&
				usuario.nombre != null && usuario.apellidos != null && usuario.direccion != null && usuario.correo != null && usuario.tipoUsuario > 0;

		return result;
	}

}

package cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private int	tipoUsuario;
	

}

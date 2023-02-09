package cliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservas")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reserva {

    @Id
    private String idReserva;
    @Indexed(unique = true)
    private String matriculaVehiculo;
    private String dniUsuario;
    private String idPlaza;
    private String idParking;
    private Date horaReserva;
    private Date finReserva;


}

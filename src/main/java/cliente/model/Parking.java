package cliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parkings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parking {

    @Id
    private String id;
    private String nombre;
    private int codigoPostal;
    private String provincia;
    private String localidad;
    private Plaza[] plazas;

}

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
    private String nombreParking;
    private int codigoPostal;
    private String provincia;
    private String localidad;
    private Plaza[] plazas;

    public Parking(String nombreParking, int codigoPostal, String provincia, String localidad, Plaza[] plazas) {
        this.nombreParking = nombreParking;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.localidad = localidad;
        this.plazas = plazas;
    }
}

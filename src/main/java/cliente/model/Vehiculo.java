package cliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehiculo {

    @Id
    private String id;
    @Indexed(unique = true)
    private String matricula;
    private String marca;
    private String modelo;
    private String id_plaza;
    private int tipoVehiculo;
    private boolean estacionado;

    public Vehiculo(String matricula, String marca, String modelo, String id_plaza, int tipoVehiculo, boolean estacionado) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.id_plaza = id_plaza;
        this.tipoVehiculo = tipoVehiculo;
        this.estacionado = estacionado;
    }
}

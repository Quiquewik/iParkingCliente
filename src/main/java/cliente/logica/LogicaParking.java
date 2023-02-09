package cliente.logica;

import cliente.controller.ParkingController;
import cliente.model.Parking;
import cliente.model.Plaza;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class LogicaParking {
    private static final ParkingController controller = new ParkingController();

    public static DefaultTableModel getDefaultTableModel(String nombreParking) {

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"Numero", "Tipo", "Ocupada", "Reservada"});
        Plaza[] listaPlazas = controller.callGetParkingByName(nombreParking).getPlazas();
        for (Plaza p : listaPlazas) {
            ArrayList<String> listaAtributos = new ArrayList<>();
            listaAtributos.add(p.getNumeroPlaza() + "");
            listaAtributos.add(p.getTipoPlaza() == 1 ? "coche" : "moto");
            listaAtributos.add(p.isOcupado() ? "Ocupada" : "Libre");
            listaAtributos.add(p.isReservada() || p.isOcupado() ? "No disponible" : "Reservable");
            model.addRow(listaAtributos.toArray());
        }
        return model;
    }

    public static String[] getNombres() {
        Parking[] listaParkings = controller.callGetParkings();
        String[] listaNombres = new String[listaParkings.length];

        for (int i = 0; i < listaParkings.length; i++) {
            listaNombres[i] = listaParkings[i].getNombre();
        }

        return listaNombres;
    }

    public static Parking getParking(String nombre){
        return controller.callGetParkingByName(nombre);
    }

}

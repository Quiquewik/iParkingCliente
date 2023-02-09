package cliente.logica;

import cliente.controller.ParkingController;
import cliente.controller.ReservaController;
import cliente.model.Parking;
import cliente.model.Plaza;
import cliente.model.Reserva;
import cliente.model.Usuario;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class LogicaReserva {

    private static final ReservaController controller = new ReservaController();
    private static final ParkingController parkingController = new ParkingController();

    public static boolean addReserva(Reserva reservaIn, Usuario userIn, Parking parkingIn, int indexPlaza, int indexVehiculo) {

        int tiempoReservado = (int) reservaIn.getHoraReserva().toInstant().until(reservaIn.getFinReserva().toInstant(), ChronoUnit.HOURS);
        boolean checkReserva = false;
        boolean bExito = false;
        Reserva updatedreserva;
        //Check si la matricula ya tiene alguna reserva
        if (null == controller.callGetReservaByMatricula(userIn.getListaVehiculos()[indexVehiculo].getMatricula())) {
            //check usuario segun membresia
            if (userIn.getMembresia().equalsIgnoreCase("basica")) {
                checkReserva = tiempoReservado <= 3;
            } else if (userIn.getMembresia().equalsIgnoreCase("trabajador")) {
                checkReserva = tiempoReservado <= 10;
            } else if (userIn.getMembresia().equalsIgnoreCase("premium")) {
                checkReserva = true;
            }
            if (checkReserva) {
                reservaIn.setIdParking(parkingIn.getId());
                reservaIn.setDniUsuario(userIn.getDni());
                reservaIn.setIdPlaza(String.format("%03d", indexPlaza + 1));
                reservaIn.setMatriculaVehiculo(userIn.getListaVehiculos()[indexVehiculo].getMatricula());
                updatedreserva = controller.callAddReserva(reservaIn);
                //actualizamos parking
                parkingIn.getPlazas()[indexPlaza].setReservada(true);
                parkingController.callUpdateParkingById(parkingIn);

                if (null != updatedreserva.getIdReserva()) {
                    bExito = true;
                }
            }
        }
        return bExito;
    }

    public static DefaultTableModel getDefaultTableModel(String dni) {

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"Matricula", "Plaza", "Hora Inicio", "Hora Fin"});
        Reserva[] listaPlazas = controller.callGetReservaByDni(dni);
        String pattern = "dd-M-yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (Reserva r : listaPlazas) {
            ArrayList<String> listaAtributos = new ArrayList<>();
            listaAtributos.add(r.getMatriculaVehiculo());
            listaAtributos.add(r.getIdPlaza());
            listaAtributos.add(simpleDateFormat.format(r.getHoraReserva()));
            listaAtributos.add(simpleDateFormat.format(r.getFinReserva()));
            model.addRow(listaAtributos.toArray());
        }
        return model;
    }

    public static void eliminarReserva(int selectedRow, Usuario user, Parking parking) {
        Reserva[] listaReservas = controller.callGetReservaByDni(user.getDni());
        Reserva deleteReserva = listaReservas[selectedRow];
        int numeroPlaza = Integer.parseInt(deleteReserva.getIdPlaza())-1;

        controller.callDeleteReservaById(deleteReserva.getIdReserva());
        Plaza plaza = parking.getPlazas()[numeroPlaza];
        plaza.setReservada(false);
        parking.getPlazas()[numeroPlaza] = plaza;
        parkingController.callUpdateParkingById(parking);

    }
}

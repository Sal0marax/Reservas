package gestionreservas;

import java.util.LinkedList;

public class ListaReservas {
    private LinkedList<Reserva> reservas;

    public ListaReservas() {
        reservas = new LinkedList<>();
    }

    public void añadirReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public String listarReservas() {
        StringBuilder sb = new StringBuilder();
        for (Reserva reserva : reservas) {
            sb.append(reserva.toString()).append("\n");
        }
        return sb.toString();
    }

    public Reserva buscarReservaPorRut(String rut) {
        for (Reserva reserva : reservas) {
            if (reserva.getRut().equals(rut)) {
                return reserva;
            }
        }
        return null; // No se encontró ninguna reserva con el RUT especificado
    }
}

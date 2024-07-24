
package gestionreservas;



public class Nodo {
    Reserva reserva;
    Nodo siguiente;

    public Nodo(Reserva reserva) {
        this.reserva = reserva;
        this.siguiente = null;
    }
}

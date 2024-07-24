
package gestionreservas;


import java.util.Stack;

public class PilaDeshacer {
    private Stack<Reserva> pila;

    public PilaDeshacer() {
        this.pila = new Stack<>();
    }

    public void apilar(Reserva reserva) {
        pila.push(reserva);
    }

    public Reserva desapilar() {
        if (!pila.isEmpty()) {
            return pila.pop();
        }
        return null;
    }
}

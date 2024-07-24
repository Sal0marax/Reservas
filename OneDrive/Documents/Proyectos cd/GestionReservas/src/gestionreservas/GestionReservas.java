package gestionreservas;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class GestionReservas {
    private static boolean reservasCargadas = false;
    private static ListaReservas listaAceptadas = new ListaReservas();

    private static Reserva reservaActual; // Para almacenar la reserva actual

    public static void main(String[] args) {
        ListaReservas listaReservas = new ListaReservas();
        PilaDeshacer pilaDeshacer = new PilaDeshacer();

        String[] opciones = {
            "Reservar Sector de Videojuegos",
            "Reservar Mesa de Ludoteca",
            "Reservar Sillones de Descanso",
            "Reservar Cabinas Telefónicas",
            "Gestionar Reservas",
            "Salir"
        };

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del Trabajador:");
        String rut = JOptionPane.showInputDialog("Ingrese el RUT del trabajador:");

        if (!ValidacionRut.validarRut(rut)) {
            JOptionPane.showMessageDialog(null, "RUT inválido. Intente nuevamente.");
            return;
        }

        reservaActual = new Reserva(nombre, rut, "", "", ""); // Inicialmente sin área, fecha ni horario
        listaReservas.añadirReserva(reservaActual);

        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción",
                    "Gestión de Reservas",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0: // Reservar sector de videojuegos
                    reservarArea("Sector de Videojuegos", listaReservas);
                    break;
                case 1: // Reservar mesa de ludoteca
                    reservarArea("Mesa de Ludoteca", listaReservas);
                    break;
                case 2: // Reservar sillones de descanso
                    reservarArea("Sillones de Descanso", listaReservas);
                    break;
                case 3: // Reservar cabinas telefónicas
                    reservarArea("Cabinas Telefónicas", listaReservas);
                    break;
                case 4: // Gestionar reservas
                    gestionarReservas(listaReservas, listaAceptadas);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }

    public static void reservarArea(String area, ListaReservas listaReservas) {
        JDialog dialog = new JDialog((Frame) null, "Seleccionar Fecha y Hora", true);
        dialog.setSize(400, 250); // Ajustado el tamaño para incluir todos los componentes
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // Ajustado el número de filas

        JCalendar calendario = new JCalendar();
        calendario.setMinSelectableDate(new Date()); // Fecha mínima: hoy
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30); // Fecha máxima: hoy + 30 días
        calendario.setMaxSelectableDate(cal.getTime());
        panel.add(calendario);

        String[] horarios = new String[6];
        for (int i = 8; i <= 18; i += 2) {
            horarios[(i / 2) - 4] = String.format("%02d:00", i);
        }
        JComboBox<String> horarioComboBox = new JComboBox<>(horarios);
        panel.add(horarioComboBox);

        JButton okButton = new JButton("OK");
        panel.add(okButton);

        okButton.addActionListener(e -> {
            Date selectedDate = calendario.getDate();
            String selectedHora = (String) horarioComboBox.getSelectedItem();
            String fechaHora = String.format("%1$tY-%1$tm-%1$td %2$s", selectedDate, selectedHora);

            if (reservaActual != null) {
                reservaActual.setArea(area);
                reservaActual.setFecha(fechaHora);
                reservaActual.setHorario(selectedHora);
                JOptionPane.showMessageDialog(dialog, "Reserva actualizada exitosamente.\n" + reservaActual.toString());
            } else {
                JOptionPane.showMessageDialog(dialog, "No hay reserva actual para actualizar.");
            }

            dialog.dispose();
        });

        dialog.add(panel);
        dialog.setVisible(true);
    }

    public static void gestionarReservas(ListaReservas listaReservas, ListaReservas listaAceptadas) {
        if (!reservasCargadas) {
            listaAceptadas.añadirReserva(new Reserva("Michael Jackson", "936532888", "Cabinas", "2024-08-01", "08:00"));
            listaAceptadas.añadirReserva(new Reserva("Axel Rose", "963528741", "Sillones", "2024-08-02", "10:00"));
            listaAceptadas.añadirReserva(new Reserva("Tracy Chapman", "953628264", "Mesa", "2024-08-03", "12:00"));
            reservasCargadas = true;
        }

        JOptionPane.showMessageDialog(null, "Reservas realizadas:\n" + listaReservas.listarReservas());
    }
}

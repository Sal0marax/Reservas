package gestionreservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class DateSelector {

    public static Date showDatePicker() {
        final Date[] selectedDate = {null};

        // Crear el frame
        JFrame frame = new JFrame("Seleccionar Fecha");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear el modelo del Spinner para las fechas
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 30); // Fecha máxima: hoy + 30 días
        Date maxDate = calendar.getTime();

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(today, today, maxDate, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        // Limitar la selección a días de semana
        dateSpinner.addChangeListener(e -> {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.setTime((Date) dateSpinner.getValue());
            int dayOfWeek = selectedCalendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                JOptionPane.showMessageDialog(frame, "No se puede seleccionar fines de semana.");
                dateSpinner.setValue(today);
            }
        });

        panel.add(dateSpinner, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        panel.add(okButton, BorderLayout.SOUTH);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDate[0] = (Date) dateSpinner.getValue();
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);

        // Esperar a que el usuario seleccione una fecha
        while (selectedDate[0] == null) {
            try {
                Thread.sleep(100); // Espera activa
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return selectedDate[0];
    }

    public static void main(String[] args) {
        // Ejemplo de uso del selector de fecha
        Date selectedDate = showDatePicker();
        System.out.println("Fecha seleccionada: " + selectedDate);
    }
}

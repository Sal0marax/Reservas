package gestionreservas;

public class ValidacionRut {

    public static boolean validarRut(String rut) {
        // Eliminar espacios en blanco y caracteres no deseados
        rut = rut.replace("-", "").trim();
        
        // Verificar la longitud del RUT
        if (rut.length() < 8 || rut.length() > 9) {
            return false;
        }
        
        // Separar el dígito verificador y el número del RUT
        String rutBase = rut.length() == 9 ? rut.substring(0, 8) : rut;
        char digitoVerificador = rut.length() == 9 ? rut.charAt(8) : '0'; // Asumimos '0' si no hay dígito verificador

        // Convertir el número del RUT en un array de dígitos
        int[] digits = new int[8];
        for (int i = 0; i < 8; i++) {
            digits[i] = Character.getNumericValue(rutBase.charAt(7 - i));
        }

        // Calcular la suma ponderada
        int suma = (2 * digits[0]) + (3 * digits[1]) + (4 * digits[2]) + 
                   (5 * digits[3]) + (6 * digits[4]) + (7 * digits[5]) + 
                   (2 * digits[6]) + (3 * digits[7]);

        // Calcular el dígito verificador correcto
        int parteEntera = suma / 11;
        int multiEntero = parteEntera * 11;
        int solucion = suma - multiEntero;
        int digitoCorrecto = 11 - solucion;
        if (digitoCorrecto == 11) {
            digitoCorrecto = 0;
        } else if (digitoCorrecto == 10) {
            digitoCorrecto = 'K';
        }

        // Comparar el dígito verificador ingresado con el calculado
        return (digitoCorrecto == digitoVerificador || 
                (digitoVerificador == 'K' && digitoCorrecto == 'K') ||
                (Character.getNumericValue(digitoVerificador) == digitoCorrecto));
    }
}

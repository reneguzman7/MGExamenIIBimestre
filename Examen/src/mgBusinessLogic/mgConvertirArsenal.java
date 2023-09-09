public class mgConvertirArsenal {
    
    public static String reemplazarLetrasConPalabras(String input) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char letra = input.charAt(i);
            String palabraReemplazo = obtenerPalabraParaLetra(letra);
            if (palabraReemplazo != null) {
                resultado.append(palabraReemplazo);
                if (i < input.length() - 1) {
                    resultado.append(",");
                }
            } else {
                // Si la letra no tiene una palabra correspondiente, la dejamos sin cambios.
                resultado.append(letra);
            }
        }
        return resultado.toString();
    }

    private static String obtenerPalabraParaLetra(char letra) {
        switch (Character.toLowerCase(letra)) {
            case 'a':
                return "avion";
            case 'b':
                return "barco";
            case 'c':
                return "convoy";
            case 'd':
                return "dron";
            case 't':
                return "tanque";
            default:
                return null; // Devuelve null si la letra no tiene un reemplazo definido.
        }
    

    }
}

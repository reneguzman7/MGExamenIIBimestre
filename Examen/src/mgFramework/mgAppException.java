package mgFramework;

/**
 * La clase mgAppException es una excepción personalizada que se utiliza para manejar errores en la aplicación.
 * Extiende de la clase Exception de Java y proporciona funcionalidad adicional para registrar información de depuración.
 */
public class mgAppException extends Exception {

    /**
     * Constructor que acepta una causa, una clase y un mensaje de error.
     *
     * @param cause     La causa original del error.
     * @param className El nombre de la clase en la que se produjo el error.
     * @param message   El mensaje de error detallado.
     */
    public mgAppException(Throwable cause, Object className, String message) {
        super(className + ":" + message, cause);
        setDebuggingLog();
    }

    /**
     * Constructor que acepta una causa y una clase, con un mensaje de error por defecto.
     *
     * @param cause     La causa original del error.
     * @param className El nombre de la clase en la que se produjo el error.
     */
    public mgAppException(Throwable cause, Object className) {
        super(className + ":" + "Error no especificado", cause);
        setDebuggingLog();
    }

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message El mensaje de error detallado.
     */
    public mgAppException(String message) {
        super("Error en clase no especificada " + ":" + message);
        setDebuggingLog();
    }

    /**
     * Este método se llama internamente para imprimir un registro de depuración cuando se lanza la excepción.
     * Muestra información sobre la excepción, incluido el mensaje y la causa.
     */
    void setDebuggingLog() {
        System.out.println("-------------------------");
        System.out.println("{AppException}");
        System.out.println(getMessage());
        System.out.println(getCause());
        System.out.println("-------------------------");
    }
}

package LN;

/**
 * Excepción a aplicar cuando ya haya algún usuario registrado con el mismo nickname.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsClienteRepetido extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private final String mensaje = "Ya existe un cliente con ese DNI. Tendrá que introducir una nueva opción.";

	public String getMessage()
	{	
		return mensaje;
	}	
}
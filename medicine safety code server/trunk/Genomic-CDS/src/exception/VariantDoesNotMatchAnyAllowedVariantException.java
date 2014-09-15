package exception;


/**
 * This Exception will capture any problem when matching criteria syntax strings to retrieve genetic variants.
 * 
 * @author Jose Antonio Mi�arro Gim�nez
 * @version 2.0
 * @date 15/09/2014
 */
public class VariantDoesNotMatchAnyAllowedVariantException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public VariantDoesNotMatchAnyAllowedVariantException(String message) {
		super(message);
	}
}

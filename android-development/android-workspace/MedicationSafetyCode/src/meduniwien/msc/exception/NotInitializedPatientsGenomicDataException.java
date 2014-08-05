package meduniwien.msc.exception;

/**
 * Represents an error when trying to analyze patient's genotype that was not initialized.
 * 
 * @author Jose Antonio Mi�arro Gim�nez
 * */
public class NotInitializedPatientsGenomicDataException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NotInitializedPatientsGenomicDataException(String message){
		super(message);
	}
}

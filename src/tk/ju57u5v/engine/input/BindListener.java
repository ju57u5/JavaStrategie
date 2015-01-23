package tk.ju57u5v.engine.input;

public interface BindListener {

	/**
	 * Wird aufgerufen, wenn ein Bind aktiv wird
	 * 
	 * @param bindQuery
	 *            Query des Binds
	 */
	public void bindActivated(String bindQuery);

}

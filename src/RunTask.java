import javax.swing.SwingUtilities;

/**
 * Code to run implementation of the task described in
 *Johnson, D. A. (2013). 
 *A component analysis of the impact of evaluative and objective feedback on performance. 
 *Journal of Organizational Behavior Management, 33(2), 89-103.
 */


/**.
 * @author Joshua Bensemann
 *
 */
public class RunTask implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunTask());
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {		
		@SuppressWarnings("unused")
		CheckingTask t = new CheckingTask();
		
	}

}
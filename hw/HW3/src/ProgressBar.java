
/*
Java Swing, 2nd Edition
By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
ISBN: 0-596-00408-7
Publisher: O'Reilly 
*/
// SwingProgressBarExample.java
// A demonstration of the JProgressBar component. The component tracks the
// progress of a for loop.
//

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressBar extends JPanel {

	JProgressBar pbar;
	JFrame frame;
	String name;

	static final int MY_MINIMUM = 0;

	static final int MY_MAXIMUM = 100;

	public ProgressBar(String name) {
		this.name = name;
		pbar = new JProgressBar();
		pbar.setMinimum(MY_MINIMUM);
		pbar.setMaximum(MY_MAXIMUM);
		pbar.setSize(500, 360);
		pbar.setMinimumSize(new Dimension(500, 360));
		add(pbar);
	}

	public void updateBar(int newValue) {
		pbar.setValue(newValue);
	}

	public void stop() {
		frame.dispose();
	}
	
	public void start() {
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this.pbar);
//		frame.setSize(new Dimension(500, 360));
		frame.setLocation(200, 200);
		frame.pack();
		frame.setVisible(true);

//		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
//			final int percent = i;
//			try {
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						it.updateBar(percent);
//					}
//				});
//				java.lang.Thread.sleep(100);
//			} catch (InterruptedException e) {
//				;
//			}
//		}
	}
}

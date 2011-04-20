package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.PopUpJFrame;
import view.RichRailJFrame;
import controller.output.GraphicDisplay;
import controller.output.TextLog;

public class GuiController implements ActionListener {
	private RichRailJFrame jframe;
	private TrainController tc;

	/**
	 * GuiController controller.
	 */
	public GuiController() {
		// Create new jframe
		jframe = new RichRailJFrame(this);
		// Create the traincontroller
		tc = new TrainController();

		// Create the default display for the gui
		GraphicDisplay graphicDisplay = new GraphicDisplay();
		TextLog textlog = new TextLog();

		// Add the differtent displays to the observerable.
		tc.addObserver(graphicDisplay);
		tc.addObserver(textlog);

		// Start the GUI
		jframe.initGUI();

		// Set the outputs
		jframe.setGraphicDisplay(graphicDisplay);
		jframe.setTextOutput(textlog);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jframe.jButtonExecute) {
			System.out.println("GuiController.actionPerformed() - Execute command");
			
			String cmd = jframe.getjTextFieldCmd().getText();
			try {
				String resultMessage = tc.parseCommand(cmd);

				StringBuffer sb = new StringBuffer(jframe.getjTextAreaLog().getText());
				sb.append(resultMessage + "\n");
				jframe.getjTextAreaLog().setText(sb.toString());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(jframe, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (arg0.getSource() == jframe.jButtonDuplicate) {
			System.out.println("GuiController.actionPerformed() - Duplicate view");

			// Create new jframe
			PopUpJFrame popup = new PopUpJFrame();
			// Create new display
			GraphicDisplay newDisplay = new GraphicDisplay();
			// Register display at traincontroller
			tc.addObserver(newDisplay);

			// Start the GUI
			popup.initGUI();
			// Set the display in the new jframe
			popup.setDisplay(newDisplay);

			// Perform an update from observerable
			tc.stateChanged();
		}
	}
}

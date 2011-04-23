package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.PopUpJFrame;
import view.RichRailJFrame;
import controller.output.GraphicDisplay;
import controller.output.TextLog;

public class UiController implements ActionListener {
	private RichRailJFrame jframe;
	private CommandController tc;

	/**
	 * GuiController controller.
	 */
	public UiController() {
		// Create new jframe
		jframe = new RichRailJFrame(this);
		// Create the traincontroller
		tc = new CommandController();

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

		// TODO weghalen, dit is ene test:
		try {
			tc.parseCommand("new train tr1");
			tc.parseCommand("new train tr2");
			tc.parseCommand("new wagon wg1");
			tc.parseCommand("new wagon wg2");
			tc.parseCommand("new wagon wg3");

			tc.parseCommand("add wg1 to tr1");
			tc.parseCommand("add wg2 to tr1");
			tc.parseCommand("add wg3 to tr1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jframe.jButtonExecute || arg0.getSource() == jframe.jTextFieldCmd) {
			System.out.println("GuiController.actionPerformed() - Execute command");

			String cmd = jframe.getjTextFieldCmd().getText();
			try {
				String resultMessage = tc.parseCommand(cmd);
				appendToOutputLog(resultMessage);

				jframe.getjTextFieldCmd().setText("");
			} catch (Exception e) {
				appendToOutputLog(e.getMessage());
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

	public void appendToOutputLog(String message) {
		StringBuffer sb = new StringBuffer(jframe.getjTextAreaLog().getText());
		sb.append(message + "\n");
		jframe.getjTextAreaLog().setText(sb.toString());
	}
}

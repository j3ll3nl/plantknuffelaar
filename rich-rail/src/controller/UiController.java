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
	private CommandController cc;

	/**
	 * GuiController controller.
	 */
	public UiController() {
		// Create new jframe
		jframe = new RichRailJFrame(this);
		// Create the traincontroller
		cc = new CommandController();

		// Create the default display for the gui
		GraphicDisplay graphicDisplay = new GraphicDisplay();
		TextLog textlog = new TextLog();

		// Add the differtent displays to the observerable.
		cc.addObserver(graphicDisplay);
		cc.addObserver(textlog);

		// Start the GUI
		jframe.initGUI();

		// Set the outputs
		jframe.setGraphicDisplay(graphicDisplay);
		jframe.setTextOutput(textlog);

		// TODO weghalen, dit is ene test:
		try {
			cc.parseCommand("new train tr1");
			cc.parseCommand("new train tr2");
			cc.parseCommand("new wagon wg1");
			cc.parseCommand("new wagon wg2");
			cc.parseCommand("new wagon wg3");

			cc.parseCommand("add wg1 to tr1");
			cc.parseCommand("add wg2 to tr1");
			cc.parseCommand("add wg3 to tr1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void appendToOutputLog(String message) {
		StringBuffer sb = new StringBuffer(jframe.getjTextAreaLog().getText());
		sb.append(message + "\n");
		jframe.getjTextAreaLog().setText(sb.toString());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jframe.jButtonExecute || arg0.getSource() == jframe.jTextFieldCmd) {
			System.out.println("GuiController.actionPerformed() - Execute command");

			String cmd = jframe.getjTextFieldCmd().getText();
			try {
				String resultMessage = cc.parseCommand(cmd);
				appendToOutputLog(resultMessage);

				jframe.getjTextFieldCmd().setText("");
			} catch (Exception e) {
				appendToOutputLog(e.getMessage());
				JOptionPane.showMessageDialog(jframe, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (arg0.getSource() == jframe.jButtonDuplicate) {
			System.out.println("GuiController.actionPerformed() - Duplicate view");
			try {
			cc.parseCommand("display graphicdisplay");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

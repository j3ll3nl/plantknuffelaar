package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JOptionPane;

import view.PopUpJFrame;
import view.RichRailJFrame;
import controller.output.Display;

public class GuiController extends Observable implements ActionListener {
	private RichRailJFrame jframe;
	private TrainController tc;

	public GuiController() {
		jframe = new RichRailJFrame(this);
		tc = new TrainController();

		// Create the default display for the gui
		Display defaultDisplay = new Display();
		addObserver(defaultDisplay);

		// Start the GUI
		jframe.initGUI();
		jframe.setDefaultDisplay(defaultDisplay);
	}

	public void setCmdHistory(String cmd) {
		StringBuffer sb = new StringBuffer(jframe.getjTextAreaCmdHistory().getText());
		sb.append(cmd + "\n");
		jframe.getjTextAreaCmdHistory().setText(sb.toString());
		// jframe.getjTextFieldCmd().setText("");
	}

	public void setLog(String type, String log) {
		StringBuffer sb = new StringBuffer(jframe.getjTextAreaLog().getText());
		sb.append(type + "\t" + log + "\n");
		jframe.getjTextAreaLog().setText(sb.toString());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jframe.jButtonExecute) {
			System.out.println("MainController.actionPerformed() - Execute");

			String cmd = jframe.getjTextFieldCmd().getText();

			if (cmd.equals("")) {
				JOptionPane.showMessageDialog(jframe, "No command is given!", "Error", JOptionPane.ERROR_MESSAGE);
				setLog("error", "no command is given");
			} else {
				try {
					// Split the string from white spaces
					String[] b = cmd.split(" ");
					if (b[0].equals("new")) {
						// New command
						if (b[1].equals("train")) {
							// New train
							tc.newTrain(b[2]);
							setLog("info", "train " + b[2] + " created");
						} else if (b[1].equals("wagon")) {
							// Check if there is an parameter for numseats
							if (b.length == 3) {
								// Create standard wagon with 20 seats
								tc.newWagon(b[2]);
								setLog("info", "wagon " + b[2] + " created with 20 seats");
							} else {
								if (b[3].equals("numseats")) {
									throw new Exception();
								} else {
									// Create wagon with custom seats
									tc.newWagon(b[2], Integer.parseInt(b[4]));
									setLog("info", "wagon " + b[2] + " created with " + b[4] + " seats");
								}
							}
						}
					} else if (b[0].equals("add")) {
						// Add command
					} else if (b[0].equals("getnumseats")) {
						// Get command
					} else if (b[0].equals("delete")) {
						// Delete command
					} else if (b[0].equals("remove")) {
						// Remove command
					} else {
						throw new Exception();
					}
					setCmdHistory(cmd);

					newUpdate();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(jframe, "Invalid command!", "Error", JOptionPane.WARNING_MESSAGE);
					setLog("error", "invalid command");
				}
			}
		} else if (arg0.getSource() == jframe.jButtonDuplicate) {
			System.out.println("MainController.actionPerformed() - Duplicate view");

			PopUpJFrame popup = new PopUpJFrame();
			Display newDisplay = new Display();
			addObserver(newDisplay);

			// Start the GUI
			popup.initGUI();
			popup.setDisplay(newDisplay);

			newUpdate();
		}
	}

	private void newUpdate() {
		setChanged();
		notifyObservers(tc.getTrains());
	}
}

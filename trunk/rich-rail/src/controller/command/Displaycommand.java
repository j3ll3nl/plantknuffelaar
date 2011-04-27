package controller.command;

import view.PopUpJFrame;
import controller.output.GraphicDisplay;
import controller.output.TextLog;

public class Displaycommand implements CommandInterface{
	private CommandResult cresult = new CommandResult();
	
	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		if (cmd[1].equals("graphicdisplay")|| cmd[1].equals("textlog")) {
			display(cmd);
		}else {
			throw new Exception("unknown type given");
		}
		return cresult;
	}
	
	private void display(String[] cmd) throws Exception {
		System.out.println("Displaycommand.display(" + cmd + ")");
				
		// Create new jframe
		PopUpJFrame popup = new PopUpJFrame();
		// Create new display
		
		if(cmd[1].equals("graphicdisplay")){
			// new graphic display
			GraphicDisplay newDisplay = new GraphicDisplay();
			// Start the GUI
			popup.initGUI();
			// Set the display in the new jframe
			popup.setDisplay(newDisplay);
			cresult.setObject(newDisplay);
		}else{
			// new Textlog display
			TextLog newDisplay = new TextLog();
			// Start the GUI
			popup.initGUI();
			// Set the display in the new jframe
			popup.setDisplay(newDisplay);
			cresult.setObject(newDisplay);
		}

		cresult.setMessage("display \"" + cmd[1] + "\" created");
		

	}
}

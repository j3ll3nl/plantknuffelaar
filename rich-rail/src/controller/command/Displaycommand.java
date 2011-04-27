package controller.command;

import view.PopUpJFrame;
import controller.output.GraphicDisplay;
import controller.output.TextLog;

public class Displaycommand implements CommandInterface{
	private CommandResult cresult = new CommandResult();
	
	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		if (cmd[1].equals("graphicdisplay")) {
			display(cmd,new GraphicDisplay());
		}else if(cmd[1].equals("textlog")){
			display(cmd,new TextLog());
		}else {
			throw new Exception("unknown type given");
		}
		return cresult;
	}
	
	private void display(String[] cmd,Object newDisplay) throws Exception {
		System.out.println("Displaycommand.display(" + cmd + ")");
				
		// Create new frame
		PopUpJFrame popup = new PopUpJFrame();
		// Create new display
		popup.initGUI();
		// Set the display in the new frame
		popup.setDisplay(newDisplay);
		cresult.setObject(newDisplay);
		
		cresult.setMessage("display \"" + cmd[1] + "\" created");
		

	}
}

package command;

import controller.TrainController;

public class RichRailCommands implements Command {

	private TrainController tc;

	public RichRailCommands(TrainController trainController) {
		tc = trainController;
	}

	@Override
	public void execute(TrainController tc, String cmd) throws Exception {
		System.out.println("RichRailCommands.execute(" + cmd + ")");
		if(cmd.equals("")){
			throw new Exception("No command is given!");
		}else{
			String[] b = cmd.split(" ");
			if (b[0].equals("new")) {
				
			}
		}
	}
}

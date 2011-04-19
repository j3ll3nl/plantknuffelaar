package command;

import controller.TrainController;

public interface Command {
	public void execute(TrainController tc, String cmd) throws Exception;

}

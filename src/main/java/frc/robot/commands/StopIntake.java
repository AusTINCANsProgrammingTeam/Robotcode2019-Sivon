package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//import java.util.logging.Logger;

public class StopIntake extends Command {
  //private static final Logger LOGGER = Logger.getLogger(StopIntake.class.getName());

    /**
     * Instantiates the command
     */
    public StopIntake() {
        requires(Robot.getIntakeSubsystem());
    }

    /**
     * Runs the command
     */
    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().stopIntake();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

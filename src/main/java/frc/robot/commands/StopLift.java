package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//import java.util.logging.Logger;

public class StopLift extends Command {
  //private static final Logger LOGGER = Logger.getLogger(StopLift.class.getName());

    /**
     * Instantiates the command.
     */
    public StopLift() {
        requires(Robot.getLiftSubsystem());
    }
    /**
     * Runs the command.
     */
    @Override
    protected void initialize() {
        Robot.getLiftSubsystem().stopLift();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

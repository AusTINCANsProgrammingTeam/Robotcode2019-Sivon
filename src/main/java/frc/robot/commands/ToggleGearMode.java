package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import java.util.logging.Logger;

public class ToggleGearMode extends Command {
  private static final Logger LOGGER = Logger.getLogger(ToggleGearMode.class.getName());

    /**
     * Runs the command
     */
    @Override
    protected void initialize() {
        Robot.getDriveSubsystem().toggleGearMode();
        LOGGER.info(String.format("GearMode has been toggled to %s!", Robot.getDriveSubsystem().getGearMode()));
    }

    @Override
    protected boolean isFinished() { return true; }
}

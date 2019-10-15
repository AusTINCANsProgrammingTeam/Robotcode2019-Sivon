package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystem.drive.GearMode;
import frc.robot.subsystems.GearMode;

import java.util.logging.Logger;

public class SetGearMode extends Command {
  private static final Logger LOGGER = Logger.getLogger(SetGearMode.class.getName());

    private GearMode gearMode;
    
    public SetGearMode(GearMode gearMode) {
        this.gearMode = gearMode;
    }

    /**
     * Runs the command
     */
    @Override
    protected void initialize() {
        Robot.getDriveSubsystem().setGearMode(gearMode);
        LOGGER.info(String.format("Set the gear mode to %s.", gearMode));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

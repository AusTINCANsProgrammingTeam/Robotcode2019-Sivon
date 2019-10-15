package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

import java.util.logging.Logger;

public class ToggleHatchSolenoid extends Command {
  private static final Logger LOGGER = Logger.getLogger(ToggleHatchSolenoid.class.getName());
    private IntakeSubsystem intakeSubsystem;

    /**
     * Instantiates the command
     */
    public ToggleHatchSolenoid() {
        this.intakeSubsystem = Robot.getIntakeSubsystem();
    }

    /**
     * Runs the command
     */
    @Override
    protected void execute() {
        intakeSubsystem.toggleSolenoidState();
        LOGGER.info("Solenoid has been toggled!");
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

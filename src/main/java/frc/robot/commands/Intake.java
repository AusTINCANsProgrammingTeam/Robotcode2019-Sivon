package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

import java.util.logging.Logger;

public class Intake extends Command {
  private static final Logger LOGGER = Logger.getLogger(Intake.class.getName());

    /**
     * Instantiates the command.
     */
    public Intake() {
        requires(Robot.getIntakeSubsystem());
        LOGGER.info("Intake Command Initialized.");
    }

    /**
     * Runs the command.
     */
    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().runIntake(IntakeSubsystem.IntakeDirection.IN);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

import java.util.logging.Logger;

public class OuttakeHalfSpeed extends Command {
  private static final Logger LOGGER = Logger.getLogger(Outtake.class.getName());

    /**
     * Instantiates the command
     */
    public OuttakeHalfSpeed() {
        requires(Robot.getIntakeSubsystem());
    }

    /**
     * Runs the command
     */
    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().runIntakeHalfSpeed(IntakeSubsystem.IntakeDirection.OUT);
        LOGGER.info("Outtake is initializing!");

    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

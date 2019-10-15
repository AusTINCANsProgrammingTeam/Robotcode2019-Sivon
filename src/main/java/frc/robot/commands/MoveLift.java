package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.Arm;

//import java.util.logging.Logger;

public class MoveLift extends Command {
  //private static final Logger LOGGER = Logger.getLogger(MoveLift.class.getName());
    //private Arm.Direction direction;
    //private double speed;

    /**
     * Moves the lift by changing direction and speed
     * @param direction Either UP or DOWN
     * @param speed Speed of lift
     */
    public MoveLift() {
        requires(Robot.getLiftSubsystem());
    }

    /**
     * Runs the command
     */
    @Override
    protected void initialize() {
        //Robot.getLiftSubsystem().moveLift(direction, speed);
    }
    /**
     * Runs the command
     */
    @Override
    protected void execute() {
        Robot.getLiftSubsystem().moveLift();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}

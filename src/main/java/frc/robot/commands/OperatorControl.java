package frc.robot.commands;

//import java.lang.System.Logger;
//import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class OperatorControl extends Command {
  //motion code here
  
  private DriveMode driveMode;
  private ExampleSubsystem driveSubsystem;
  private Joystick joystick;

  /**
   * Instantiates the operator control
   * @param driveMode either ARCADE or TANK
   */
  public OperatorControl(DriveMode driveMode) {
      this.driveMode = driveMode;
      this.driveSubsystem = Robot.getDriveSubsystem();
      this.joystick = Robot.getOperatorInterface().getDriveController();
  }

  @Override
  public void execute() {
      //LOGGER.warning(Double.toString(joystick.getRawAxis(1))+ ", " + Double.toString(joystick.getRawAxis(4)));
      switch(driveMode) {
        //case TANK:
          //    driveSubsystem.tankDrive(joystick.getRawAxis(0), joystick.getRawAxis(1));
          //    break;
          case ARCADE:
        driveSubsystem.arcadeDrive(-joystick.getRawAxis(1), -joystick.getRawAxis(4));
            break;
        default:
            break;
      }
  }

  @Override
  protected boolean isFinished() {
      return false;
  }
}
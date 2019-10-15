package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int LEFT_MOTOR_1 = 5;
  public static final int LEFT_MOTOR_2 = 6;
  public static final int LEFT_MOTOR_3 = 7;

  public static final int RIGHT_MOTOR_1 = 2;
  public static final int RIGHT_MOTOR_2 = 3;
  public static final int RIGHT_MOTOR_3 = 4;

  // Pneumatic system.
  public static final int PCM_ADDRESS = 1;
  public static final int GEARBOX_FORWARD_CHANNEL = 0;
  public static final int GEARBOX_REVERSE_CHANNEL = 1;

  // Lift motors Sparks.
  public static final int LIFT_MOTOR_1 = 9;
  public static final int LIFT_MOTOR_2 = 1;
  public static final int LIFT_MOTOR_3 = 2;

  // Intake motors and pneumatics.
  public static final int LEFT_INTAKE_MOTOR = 3;
  public static final int RIGHT_INTAKE_MOTOR = 4;
  public static final int PIVOT_INTAKE_MOTOR = 5;
  public static final int INTAKE_SOLENOID_1 = 2;
  public static final int INTAKE_SOLENOID_2 = 3;

  // Port for the joystick.
  public static final int JOYSTICK_PORT = 0;

}

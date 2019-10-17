package frc.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.Intake;
import frc.robot.commands.Outtake;
import frc.robot.commands.ToggleGearMode;
import frc.robot.commands.IntakeHalfSpeed;
import frc.robot.commands.OuttakeHalfSpeed;
import frc.robot.commands.DriveMode;
import frc.robot.subsystems.GearMode;
import frc.robot.commands.OperatorControl;
import frc.robot.subsystems.Arm;
import frc.robot.commands.MoveLiftUp;
import frc.robot.commands.MoveLiftDown;
import frc.robot.subsystems.SparkMaxGroup;

//import frc.robot.LoggingSystem;
import java.util.logging.Logger;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private SendableChooser<Double> autoChooser;
    private CANSparkMax m_motor;
    private static final Logger LOGGER = Logger.getLogger(Robot.class.getName());
    //private static final LoggingSystem LOGGING_SYSTEM = LoggingSystem.getInstance();

    private static ExampleSubsystem driveSubsystem;
    private static Arm liftSubsystem;
    private static IntakeSubsystem intakeSubsystem;

    private static OI oi;
    private Spark blinkin = new Spark(6);
    //private static final int deviceID = 9;
    
    @Override
    public void disabledInit() {
        liftSubsystem.resetPos();
        SmartDashboard.putBoolean("UpLimit", liftSubsystem.getUpLimit());
        SmartDashboard.putBoolean("DownLimit", liftSubsystem.getDownLimit());
    }
    @Override
    public void disabledPeriodic(){
        SmartDashboard.putBoolean("UpLimit", liftSubsystem.getUpLimit());
        SmartDashboard.putBoolean("DownLimit", liftSubsystem.getDownLimit());
    }

    /**
     *Initializes the TalonSRX Groups, the Solenoid, Operator Interface, Lift Motors, and Intake Motors.
     */
    @Override
    public void robotInit() {
        // Initialize the auto chooser system
        autoChooser = new SendableChooser<>();
        autoChooser.addOption("0.0", 0.0);
        autoChooser.addOption("0.25", 0.25);
        autoChooser.addOption("0.50", 0.5);
        autoChooser.addOption("0.75", 0.75);
        autoChooser.setDefaultOption("1.0", 1.0);
        autoChooser.addOption("1.25", 1.25);
        autoChooser.addOption("1.50", 1.5);
        autoChooser.addOption("1.75", 1.75);
        autoChooser.addOption("2.0", 2.0);
        autoChooser.addOption("2.25", 2.25);
        autoChooser.addOption("2.50", 2.50);
        autoChooser.addOption("2.75", 2.75);
        autoChooser.addOption("3.0", 3.5);

        driveSubsystem = new ExampleSubsystem(
            new SparkMaxGroup(
                new CANSparkMax(RobotMap.LEFT_MOTOR_1, MotorType.kBrushless), // This motor is the master for the left side.
                new CANSparkMax(RobotMap.LEFT_MOTOR_2, MotorType.kBrushless),
                new CANSparkMax(RobotMap.LEFT_MOTOR_3, MotorType.kBrushless)
        ),
            new SparkMaxGroup(
                new CANSparkMax(RobotMap.RIGHT_MOTOR_1, MotorType.kBrushless), // This motor is the master for the right side.
                new CANSparkMax(RobotMap.RIGHT_MOTOR_2, MotorType.kBrushless),
                new CANSparkMax(RobotMap.RIGHT_MOTOR_3, MotorType.kBrushless)
        ),
                new DoubleSolenoid(RobotMap.PCM_ADDRESS, RobotMap.GEARBOX_FORWARD_CHANNEL,
                RobotMap.GEARBOX_REVERSE_CHANNEL),
        //dont change the pcm address 
            new DoubleSolenoid(RobotMap.PCM_ADDRESS, RobotMap.GEARBOX_2_FORWARD_CHANNEL, RobotMap.GEARBOX_2_REVERSE_CHANNEL)
);
    }

    public static Arm getLiftSubsystem() {
        if(liftSubsystem != null) {
            return liftSubsystem;
        }
        throw new RuntimeException("Lift subsystem has not yet been initialized!");
    }

    public static ExampleSubsystem getDriveSubsystem() {
        if(driveSubsystem != null) {
            return driveSubsystem;
        }
        throw new RuntimeException("Drive subsystem has not yet been initialized!");
    }
    /**
     * Returns the instance of the intake subsystem.
     * @return the instance of the intake subsystem.
     */
    public static IntakeSubsystem getIntakeSubsystem() {
        if(intakeSubsystem != null) {
            return intakeSubsystem;
        }
        throw new RuntimeException("Intake subsystem has not yet been initialized!");
    }

    /**
     * Returns the instance of the Operator Interface.
     * @return the instance of the Operator Interface.
     */
    public static OI getOperatorInterface() {
        return oi;
    }

    /**
     * Initializes the TeleOp Robot State and binds the buttons that will be used in the controller.
     */
    @Override
    public void teleopInit() {

        LOGGER.info("Teleop Init!");
        

        oi.bindButton("buttonRB", OI.ButtonMode.WHILE_HELD, new Intake(),1);
        oi.bindButton("buttonRT", OI.ButtonMode.WHILE_HELD, new Outtake(), 1);
        oi.bindButton("button2", OI.ButtonMode.WHEN_PRESSED, new ToggleGearMode(), 1);
        oi.bindButton("button1", OI.ButtonMode.WHEN_PRESSED, new MoveLiftUp(), 1);
        oi.bindButton("button3", OI.ButtonMode.WHEN_PRESSED, new MoveLiftDown(), 1);
        
        oi.bindButton("buttonLB", OI.ButtonMode.WHILE_HELD, new IntakeHalfSpeed(), 1);
        oi.bindButton("buttonLT", OI.ButtonMode.WHILE_HELD, new OuttakeHalfSpeed(), 1);
        oi.bindButton("buttonA", OI.ButtonMode.WHEN_PRESSED, new ToggleGearMode(), 0);
        
        
        Scheduler.getInstance().add(new OperatorControl(DriveMode.ARCADE));
    // Stretch Goal: Make the button bindings come from an xml/json config.
    //how would we implement such a system?
}
    /**
     * Runs the TeleOp Periodic code.
     */
    private static double solid_red = 0.61;
    private static double solid_blue = 0.87;
    //private static double light_chase_red = -0.31;
    //private static double light_chase_blue = -0.29;
    //private static double shot_red = -0.85;
    //private static double shot_blue = -0.83;
    //private static double fast_rainbow = -0.57;
    //private static double solid_yellow = 0.69;
    //private static double solid_orange = 0.63;
    //private static double color1_strobe = 0.15;
    //private static double color2_strobe = 0.35;
    //private static double strobe_red = -0.11;
    //private static double strobe_blue = -0.9;
    private static double breath_red = -0.17;
    private static double breath_blue = -0.15;

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        GearMode gearMode = getDriveSubsystem().getGearMode();

        switch(getIntakeSubsystem().getSolenoidState()) {
            case kForward: //intake is closed - dont strobe
                if(gearMode.equals(GearMode.HIGH)){
                    blinkin.set(solid_red);
                } else if(gearMode.equals(GearMode.LOW)) {
                    blinkin.set(solid_blue);
                } else {
                    blinkin.set(0.9);
                }
                break;
            case kReverse: //intake is open - strobe
                if(gearMode.equals(GearMode.HIGH)){
                    blinkin.set(breath_red);
                } else if(gearMode.equals(GearMode.LOW)) {
                    blinkin.set(breath_blue);
                } else {
                    blinkin.set(0.9);
                }
                break;
            case kOff:

                break;
        }
        SmartDashboard.putNumber("SetPoint", liftSubsystem.getRotations());
       SmartDashboard.putNumber("ProcessVariable", liftSubsystem.getPos());


    }
    //private static Timer timer = new Timer();
}
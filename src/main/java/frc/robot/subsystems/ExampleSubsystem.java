package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.util.logging.Logger;

public class ExampleSubsystem extends Subsystem {
    private static final Logger LOGGER = Logger.getLogger(ExampleSubsystem.class.getName());

    private DifferentialDrive differentialDrive;
    private GearMode gearMode;
    private DoubleSolenoid gearboxSolenoid;

    /**
     * This initializes the drive subsystem.
     * @param leftSpeedController the speedController on the left to be initialized.
     * @param rightSpeedController the speedController on the right to be initialized.
     * @param gearboxSolenoid Solenoid to be initialized.
     */
    public ExampleSubsystem(SpeedController leftSpeedController, SpeedController rightSpeedController,
                          DoubleSolenoid gearboxSolenoid) {
        this.differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);
        differentialDrive.setSafetyEnabled(false);
        this.gearboxSolenoid = gearboxSolenoid;
        setGearMode(GearMode.LOW); //todo maybe this is part of the "every/other" bug?
        LOGGER.info("Drive subsystem initialization complete!");
    }

    /**
     * Sets the speed of both sides of the tank drive.
     * @param leftSpeed speed
     * @param rightSpeed speed
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }
    /**
     * Sets the speed and heading of the arcade drive.
     * @param velocity velocity
     * @param heading heading
     */
    public void arcadeDrive(double velocity, double heading) {
        differentialDrive.arcadeDrive(velocity, heading * .75, true);
    }

    /**
     * Returns instance of gearMode
     * @return instance of gearMode
     */
    public GearMode getGearMode() {
        return gearMode;
    }

    /**
     * Sets the gear mode
     * @param gearMode mode to set the gear
     */
    public void setGearMode(GearMode gearMode) {
        this.gearMode = gearMode;
        updateGearMode();
    }

    /**
     * Easy way to change the gear mode after being set.
     */
    public void toggleGearMode() {
        switch(gearMode) {
            case HIGH:
                gearMode = GearMode.LOW;
                break;
            case LOW:
                gearMode = GearMode.HIGH;
                break;
                //default case? (in case of accidentally using it before setGearMode())
        }
        updateGearMode();
    }

    /**
     * Changes gear mode internally
     */
    private void updateGearMode() {
        switch(gearMode) {
            case HIGH:
                gearboxSolenoid.set(DoubleSolenoid.Value.kForward);
                break;
            case LOW:
                gearboxSolenoid.set(DoubleSolenoid.Value.kReverse);
                break;
        }
    }

    @Override
    protected void initDefaultCommand() {
    }
}
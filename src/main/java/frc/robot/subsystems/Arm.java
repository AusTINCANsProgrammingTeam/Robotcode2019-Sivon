package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.MoveLift;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;

import java.util.logging.Logger;


public class Arm extends Subsystem {
  private static final Logger LOGGER = Logger.getLogger(Arm.class.getName());

    public enum Direction {UP, DOWN}

    //private SpeedController liftSpeedController;
    public static double DEFAULT_LIFT_UP_SPEED = 1.0;
    public static double DEFAULT_LIFT_DOWN_SPEED = 0.75;
    private CANPIDController m_pidController;
    private CANEncoder m_encoder;
    private CANDigitalInput upLimit;
    private CANDigitalInput downLimit;
    private static double rotations;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    /**
     * Initializes our Lift subsystem.
     * @param controller controller to be initialized.
     * @param inverted If the lift is inverted or not
     */
    public Arm(CANSparkMax controller) {
        //this.liftSpeedController = controller;
        m_pidController = controller.getPIDController();
        upLimit = controller.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
        downLimit = controller.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
        // Encoder object created to display position values
        m_encoder = controller.getEncoder();
        rotations = m_encoder.getPosition();
        /*if (rotations < -0.45)
        {
            rotations = -.45;
        }
        m_pidController.setReference(rotations, ControlType.kPosition);*/
    
        // PID coefficients
        kP = 1; 
        kI = 1e-4;
        kD = 1; 
        kIz = 0; 
        kFF = 0; 
        kMaxOutput = 1; 
        kMinOutput = -1;
    
        // set PID coefficients
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);
        // double p = SmartDashboard.getNumber("P Gain", 0);
        // double i = SmartDashboard.getNumber("I Gain", 0);
        // double d = SmartDashboard.getNumber("D Gain", 0);
        // double iz = SmartDashboard.getNumber("I Zone", 0);
        // double ff = SmartDashboard.getNumber("Feed Forward", 0);
        // double max = SmartDashboard.getNumber("Max Output", 0);
        // double min = SmartDashboard.getNumber("Min Output", 0);
        // rotations = SmartDashboard.getNumber("Set Rotations", 0);
        // if((p != kP)) { m_pidController.setP(p); kP = p; }
        // if((i != kI)) { m_pidController.setI(i); kI = i; }
        // if((d != kD)) { m_pidController.setD(d); kD = d; }
        // if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
        // if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
        // //if((max != kMaxOutput) || (min != kMinOutput)) { 
          //m_pidController.setOutputRange(min, max); 
          //kMinOutput = min; kMaxOutput = max; 
        //}
        
       
    
       // liftSpeedController.setInverted(inverted);
        LOGGER.info("Lift subsystem initialization complete!");
    }
    /**
     * Moves the lift.
     * @param direction Direction specified.
     * @param speed Speed specified.
     */
    public void moveLift() {
                if(Robot.getOperatorInterface().getOperatorController().getRawAxis(3) < -.15){
                    if(rotations < 120 && upLimit.get() == false){
                         rotations = rotations + 1.05;
                    }
                    LOGGER.warning(Double.toString(Robot.getOperatorInterface().getOperatorController().getRawAxis(3)));
                    LOGGER.warning("moveLift stick Up");
                    m_pidController.setReference(rotations, ControlType.kPosition);
                }
                else if(Robot.getOperatorInterface().getOperatorController().getRawAxis(3) > .15){
                    if(rotations > 0 && downLimit.get() == false){
                        rotations = rotations - 1.05;
                    }
                    LOGGER.warning(Double.toString(Robot.getOperatorInterface().getOperatorController().getRawAxis(3)));
                    m_pidController.setReference(rotations, ControlType.kPosition);
                }
    }
    
    public void moveLiftPos(Direction direction){
        switch(direction){
            case UP:
                if(upLimit.get() == false){
                rotations = 120;
                m_pidController.setReference(rotations, ControlType.kPosition);
                LOGGER.warning("moveLiftPos Up");
            }
                break;
            case DOWN:
                if(downLimit.get() == false){
                rotations = 0;
                m_pidController.setReference(rotations, ControlType.kPosition);
                LOGGER.warning("moveLiftPos Down");
            }
                break;

        }
    }
    /**
     * Stops the lift by setting the speed to zero.
     */
    public void stopLift() {
       
    }

    public void resetPos(){
       m_pidController.setReference(0, ControlType.kPosition);
       rotations = 0;
       m_encoder.setPosition(0);
       LOGGER.warning("encoderPos: "+ Double.toString(m_encoder.getPosition()));
    }

    public double getPos(){
       return  m_encoder.getPosition();
    }

    public double getRotations(){
        return rotations;
    }
    
    @Override
    protected void initDefaultCommand() {
       setDefaultCommand(new MoveLift());
    }

    public boolean getUpLimit()
    {
        return upLimit.get();
    }

    public boolean getDownLimit()
    {
        return downLimit.get();
    }
}

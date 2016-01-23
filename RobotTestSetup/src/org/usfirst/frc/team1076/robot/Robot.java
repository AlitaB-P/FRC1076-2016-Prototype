
package org.usfirst.frc.team1076.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team1076.robot.Gamepad;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    final TankJoystick tankControl = new TankJoystick();
    final SingleJoystick stickControl = new SingleJoystick();
    final ClaytonJoystick claytonControl = new ClaytonJoystick();
 
	IDrivetrainJoystick drivetrainJoystick;
    String autoSelected;
    SendableChooser chooser;
    SendableChooser controlMethod;
    Gamepad gamepad;
    CANTalon leftMotor;
    CANTalon rightMotor;
    CANTalon leftSlave;
    CANTalon rightSlave;
    CANTalon intakeMotor;
    
    static final int LEFT_INDEX = 0;
    static final int RIGHT_INDEX = 2;
    static final int INTAKE_INDEX = 5;
    static final double MAX_SPEED = 1.0;
    static final double INTAKE_SPEED = 0.2;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        controlMethod = new SendableChooser();
        controlMethod.addDefault("Tank Control", tankControl);
        controlMethod.addObject("Stick Control", stickControl);
        controlMethod.addObject("Clayton Control", claytonControl);
        SmartDashboard.putData("Control method", controlMethod);
        
        rightMotor = new CANTalon(RIGHT_INDEX);
        rightSlave = new CANTalon(RIGHT_INDEX+1);
        rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightSlave.set(RIGHT_INDEX);
        rightMotor.setInverted(true);
        
        leftMotor = new CANTalon(LEFT_INDEX);
        leftSlave = new CANTalon(LEFT_INDEX+1);
        leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftSlave.set(LEFT_INDEX);
        
        intakeMotor = new CANTalon(INTAKE_INDEX);

        gamepad = new Gamepad(0);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

	public void teleopInit() {
		drivetrainJoystick = (IDrivetrainJoystick) controlMethod.getSelected();
		//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		//System.out.println("Control method selected selected: " + controlSelected);
    }
	
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	MotorOutput motorOutput = drivetrainJoystick.motionForGamepadInput(gamepad);
    	
    	leftMotor.set(motorOutput.left * MAX_SPEED);
    	rightMotor.set(motorOutput.right * MAX_SPEED);
    	
    	double in = gamepad.getLeftTrigger();
    	double out = gamepad.getRightTrigger();
    	intakeMotor.set((in - out) * INTAKE_SPEED);
    }
    

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

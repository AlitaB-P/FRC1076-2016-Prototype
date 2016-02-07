package org.usfirst.frc.team1076.robot;

public interface IRobotController {
	public void robotInit(IRobot robot);
	
	// Autonomous
	public void autonomousInit(IRobot robot);
	public void autonomousPeriodic(IRobot robot);
	
	// Teleop
	public void teleopInit(IRobot robot);
	public void teleopPeriodic(IRobot robot);
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final Spark m_leftMotor = new Spark(3);
  private final Spark m_rightMotor = new Spark(1);

  private final VictorSPX m_launchWheel = new VictorSPX(6);
  private final VictorSPX m_flyWheel = new VictorSPX(5);

  // Update firmware if needed
  // Set it to some Spark ID
  // private final SparkMAX m_noteWheel = new SparkMAX(); 

  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);
  
  private final Joystick m_stick = new Joystick(0);

  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor);
  }

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    // m_leftMotor.set(1);
    // m_robotDrive.arcadeDrive(0,1);
    m_robotDrive.arcadeDrive(-m_stick.getRawAxis(1)*0.8, -m_stick.getRawAxis(4)*0.8);
    m_launchWheel.follow(m_flyWheel);
    // m_flyWheel.set(VictorSPXControlMode.PercentOutput, m_stick.getRawAxis(3));
    // double intake = m_stick.getRawButton(6) ? -1: 0;
    m_flyWheel.set(VictorSPXControlMode.PercentOutput, m_stick.getRawAxis(3)-m_stick.getRawAxis(2));
    // m_noteWheel.set()
  }
}
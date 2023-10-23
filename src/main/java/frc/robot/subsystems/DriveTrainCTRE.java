// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainCTREConstants;

/**
 * Example DriveTrain subsystem for control the robot moviment
 * Example of a differential drive with CTRE motor controllers
 * The configuration of motors and their IDs will depend on your own robot
  */
public class DriveTrainCTRE extends SubsystemBase {

  /**
   * The master Talon SRX motor controller for the left side of the drivetrain.
   */
  WPI_TalonSRX leftMaster = new WPI_TalonSRX(DrivetrainCTREConstants.kLeftMasterID);

  /**
   * The slave Victor SPX motor controller for the left side of the drivetrain.
   */
  WPI_VictorSPX leftSlave = new WPI_VictorSPX(DrivetrainCTREConstants.kLeftSlaveID);

  /**
   * The master Talon SRX motor controller for the right side of the drivetrain.
   */
  WPI_TalonSRX rightMaster = new WPI_TalonSRX(DrivetrainCTREConstants.kRightMasterID);

  /**
   * The slave Victor SPX motor controller for the right side of the drivetrain.
   */
  WPI_VictorSPX rightSlave = new WPI_VictorSPX(DrivetrainCTREConstants.kRightSlaveID);

  /** 
   * Object used to control robots with two main motors, one on the left and one on the right.
   * It has several essential methods to control the robot’s locomotion
   */
  private DifferentialDrive diffDrive = new DifferentialDrive(leftMaster, rightMaster);

  /** Creates a new DriveTrain. */
  public DriveTrainCTRE() {
    // Configure motors and followers to factory default settings.
    rightMaster.configFactoryDefault();
    rightSlave.configFactoryDefault();
    leftMaster.configFactoryDefault();
    leftSlave.configFactoryDefault();
    
    // Set up followers for the left and right masters.
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    // Set motor inversion based on specific requirements.
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
    leftMaster.setInverted(false);
    leftSlave.setInverted(false);
  }

  /** 
   * Method used to control the robot. Uses the Y and X axis of a controller left and right anlogs respectively
   */
  public void drive(XboxController driveController) {
    double vel = driveController.getLeftY();
    double ang = driveController.getRightX();
    diffDrive.arcadeDrive(vel, ang);
  }

  // Method used in autonomous commands
  public void tankDrive(double leftVel, double rightVel) {
    diffDrive.tankDrive(leftVel, rightVel);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

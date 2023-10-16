// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainREVConstants;

/**
 * Example DriveTrain subsystem for control the robot moviment
 * Example of a differential drive with Rev motor controllers
 * The configuration of motors and their IDs will depend on your own robot
  */
public class DriveTrainREV extends SubsystemBase {
  
    /**
   * The master Talon SRX motor controller for the left side of the drivetrain.
   */
  CANSparkMax leftMaster = new CANSparkMax(DrivetrainREVConstants.kLeftMasterID, MotorType.kBrushless);

  /**
   * The slave Victor SPX motor controller for the left side of the drivetrain.
   */
  CANSparkMax leftSlave = new CANSparkMax(DrivetrainREVConstants.kLeftSlaveID, MotorType.kBrushless);

  /**
   * The master Talon SRX motor controller for the right side of the drivetrain.
   */
  CANSparkMax rightMaster = new CANSparkMax(DrivetrainREVConstants.kRightMasterID, MotorType.kBrushless);

  /**
   * The slave Victor SPX motor controller for the right side of the drivetrain.
   */
  CANSparkMax rightSlave = new CANSparkMax(DrivetrainREVConstants.kRightSlaveID, MotorType.kBrushless);

  /** 
   * Object used to control robots with two main motors, one on the left and one on the right.
   * It has several essential methods to control the robot’s locomotion
   */
  private DifferentialDrive diffDrive = new DifferentialDrive(leftMaster, rightMaster);
  
  /** Creates a new DriveTrainREV. */
  public DriveTrainREV() {
    // Set up followers for the left and right masters.
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    // Set motor inversion based on specific requirements.
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
    leftMaster.setInverted(false);
    leftSlave.setInverted(false);
  }

  public void drive(XboxController driveController) {
    double vel = driveController.getLeftY();
    double ang = driveController.getRightX();
    diffDrive.arcadeDrive(vel, ang);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

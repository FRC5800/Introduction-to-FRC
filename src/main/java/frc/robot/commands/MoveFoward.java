// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainCTRE;

/** 
 * Example command that uses the DriveTrain subsystem
  */
public class MoveFoward extends CommandBase {
  /** Creates a new MoveFoward. */
  DriveTrainCTRE driveTrain = new DriveTrainCTRE();

  PIDController pid = new PIDController(0, 0, 0);
  
  // Creates a timer using WPILib that will control the command
  Timer timer = new Timer();
  double runningTime = 0;
  
  public MoveFoward(DriveTrainCTRE driveTrain, double runningTime) {
    this.driveTrain = driveTrain;
    this.runningTime = runningTime;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Reset the timer if the stop() method don't reset it
    timer.reset();
    // Start the timer
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Set 50% of the total voltage on the DriveTrain motors
    driveTrain.tankDrive(0.5, 0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Set the motors voltage to 0 to stop the motors
    driveTrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Stop the command after 3 seconds
    return timer.get() >= 3;
  }
}

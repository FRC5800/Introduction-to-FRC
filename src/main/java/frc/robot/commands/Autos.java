// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainCTRE;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command autonomousRoutine1(DriveTrainCTRE driveTrain, double runningTime) {
    return new MoveFoward(driveTrain, runningTime);
  }

  // Its methods are statics, so objects are not created
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

}

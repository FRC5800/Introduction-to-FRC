// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveTrainCTRE;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveTrainCTRE driveTrain = new DriveTrainCTRE();

  // Controller used to control the robot moviment
  private final XboxController driverController =
    new XboxController(OperatorConstants.kDriverControllerPort);

  // Controller used to control the robot other subsystems than the DriveTrain
  private final XboxController coDriverController =
    new XboxController(OperatorConstants.kCoDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    /**
     * Add a default command to DriveTrain
     * default commands don't end and keep running all over the match
      */
    driveTrain.setDefaultCommand(
      new RunCommand(() -> driveTrain.drive(driverController), driveTrain)
    );

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Method where the commands will be linked to the controller buttons
   */
  private void configureBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}

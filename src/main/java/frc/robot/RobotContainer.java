// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.MoveFoward;
import frc.robot.subsystems.DriveTrainCTRE;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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

  // Controller used to control the other robot subsystems than the DriveTrain
  private final XboxController coDriverController =
    new XboxController(OperatorConstants.kCoDriverControllerPort);

    SendableChooser<Command> chooser = new SendableChooser<Command>();
    

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    /**
     * Add a default command to DriveTrain
     * default commands don't end and keep running all over the match
     * RunCommand is used because drive() is not a command, but a method
      */
    driveTrain.setDefaultCommand(
      new RunCommand(() -> driveTrain.drive(driverController), driveTrain)
    );

    // Configure the trigger bindings
    configureBindings();
    chooser.setDefaultOption("comando1", new MoveFoward(driveTrain, 2, 0.2));
    chooser.addOption("comando2", new MoveFoward(driveTrain, 5, 0.5));
    chooser.addOption("comando3", new MoveFoward(driveTrain, 8, 0.8));

    SmartDashboard.putData(chooser);
  }

  /**
   * Method where the commands will be linked to the controller buttons
   */
  private void configureBindings() {
    /* On JoystickButton() you will pass the controller that will be used, the button, and the command that will be runned */
    new JoystickButton(driverController, XboxController.Button.kA.value).onTrue(/*your command goes here. Example:*/new MoveFoward(driveTrain, 2, 0.5));
    // Same example but with a different controller
    new JoystickButton(coDriverController, XboxController.Button.kA.value).onTrue(/*your command goes here. Example:*/new MoveFoward(driveTrain, 2, 0.5));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return chooser.getSelected();
  }
}

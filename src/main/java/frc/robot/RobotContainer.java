// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final MotorSubsystem m_motorSubsystem = new MotorSubsystem();
  private final LEDSubsystem m_LEDSubsystem = new LEDSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  // private final CommandXboxController m_driverController = new
  // CommandXboxController(
  // Constants.kDriverControllerPort);

  // The driver's controller
  XboxController m_driverController = new XboxController(Constants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the {@link Trigger#Trigger(java.util.function.BooleanSupplier)}
   * constructor with an arbitrary predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_motorSubsystem::ifButtonPressed).onTrue(new
    // MotorCommand(m_motorSubsystem));

    // Schedule `startSparkMotor` when the Xbox controller's B button is
    // pressed, cancelling on release.
    // m_driverController.b().whileTrue(m_motorSubsystem.startSparkMotor());
    // m_driverController.b().onFalse(m_motorSubsystem.stopSparkMotor());

    new JoystickButton(m_driverController, Constants.OperatorConstants.kB)
        .whileTrue(m_motorSubsystem.startSparkMotor());
    new JoystickButton(m_driverController, Constants.OperatorConstants.kB)
        .onFalse(m_motorSubsystem.stopSparkMotor());

    new JoystickButton(m_driverController, Constants.OperatorConstants.kA)
        .whileTrue(m_motorSubsystem.startSparkMaxMotor());
    new JoystickButton(m_driverController, Constants.OperatorConstants.kA)
        .onFalse(m_motorSubsystem.stopSparkMaxMotor());

    // new JoystickButton(m_driverController,
    // Constants.OperatorConstants.kX).onTrue(m_LEDSubsystem.setPurple(0));
    new JoystickButton(m_driverController, Constants.OperatorConstants.kX)
        .whileTrue(m_LEDSubsystem.rainbow());

    new JoystickButton(m_driverController, Constants.OperatorConstants.kY)
        .onTrue(m_LEDSubsystem.setAllBlue());

    // TBD
    // make a button to start the rainbow command when pressed (whileTrue)
    // does releasing the button stop the command?

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}

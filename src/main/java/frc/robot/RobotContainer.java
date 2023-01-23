// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.*;
import frc.robot.commands.Autos;
import frc.robot.commands.Controller1;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
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
  private final PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
  private final Controller1 cont1Command = new Controller1(m_LEDSubsystem, m_PneumaticsSubsystem);
  public XboxController m_driverController = new XboxController(Constants.kDriverControllerPort);

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
    // One option for running commands based on buttons:
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_motorSubsystem::ifButtonPressed).onTrue(new
    // MotorCommand(m_motorSubsystem));

    // Another option for running commands basedon buttons:
    // Schedule `startSparkMotor` when the Xbox controller's B button is
    // pressed, cancelling on release.
    // m_driverController.b().whileTrue(m_motorSubsystem.startSparkMotor());
    // m_driverController.b().onFalse(m_motorSubsystem.stopSparkMotor());

    // While B is held, the motor will run. It'll stop when B is released
    new JoystickButton(m_driverController, Constants.OperatorConstants.kB)
        .whileTrue(m_motorSubsystem.startSparkMotor());
    new JoystickButton(m_driverController, Constants.OperatorConstants.kB)
        .onFalse(m_motorSubsystem.stopSparkMotor());

    // While A is held, the motor will run. It'll stop when A is released
    new JoystickButton(m_driverController, Constants.OperatorConstants.kA)
        .whileTrue(m_motorSubsystem.startSparkMaxMotor());
    new JoystickButton(m_driverController, Constants.OperatorConstants.kA)
        .onFalse(m_motorSubsystem.stopSparkMaxMotor());

    // While X is held, the LEDs will do a moving rainbow
    new JoystickButton(m_driverController, Constants.OperatorConstants.kX)
        .whileTrue(m_LEDSubsystem.rainbow());

    // When Y is pressed, change the LEDs to blue - once.
    new JoystickButton(m_driverController, Constants.OperatorConstants.kY)
        .onTrue(m_LEDSubsystem.setAllBlue());
  }

  /**
   * the TeleCommand will get scheduled in Robot.teleopInit.
   * Therefore the execute portion of this command will run as soon as Teleop starts
   */
  public Command getTeleCommand() {
    return cont1Command;
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

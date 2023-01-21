// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Controller1;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.TalonSRXSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.DIOSubsystem;

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
  private final LEDSubsystem m_ledSubsystem = new LEDSubsystem();
  private final MotorSubsystem m_motors = new MotorSubsystem();
  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();
  private final TalonSRXSubsystem m_talonSubsystem = new TalonSRXSubsystem();
  private final DIOSubsystem m_dioSubsystem = new DIOSubsystem();

  private final Controller1 cont1Command = new Controller1(m_ledSubsystem, m_motors, m_pneumaticsSubsystem,
      m_talonSubsystem, m_dioSubsystem);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  XboxController m_driverController = new XboxController(
      OperatorConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is
    // pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    /*
     * new JoystickButton(m_driverController,
     * Constants.OperatorConstants.kA).onTrue(m_ledSubsystem.setAllYellow());
     * new JoystickButton(m_driverController,
     * Constants.OperatorConstants.kB).onTrue(m_ledSubsystem.movingRainbow());
     * new JoystickButton(m_driverController,
     * Constants.OperatorConstants.kB).onFalse(m_ledSubsystem.commandRainbow());
     * new JoystickButton(m_driverController,
     * Constants.OperatorConstants.kX).onTrue(m_ledSubsystem.twoColorToggle());
     * new JoystickButton(m_driverController,
     * Constants.OperatorConstants.kY).onTrue(m_ledSubsystem.commandRainbow());
     */
  }

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

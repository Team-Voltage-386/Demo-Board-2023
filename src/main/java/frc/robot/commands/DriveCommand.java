// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.DriveMotors_Subsystem;

import static frc.robot.Constants.OperatorConstants.*;

public class DriveCommand extends CommandBase {

  private final DriveMotors_Subsystem myDriveMotorsSubsystem;
  private final CommandXboxController myDriverController = new CommandXboxController(
      Constants.OperatorConstants.kDriverControllerPort);

  /** Creates a new DriveCommand. */
  public DriveCommand(DriveMotors_Subsystem aDriveMotorsSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.

    myDriveMotorsSubsystem = aDriveMotorsSubsystem;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double powerValue = myDriverController.getLeftY();

    myDriveMotorsSubsystem.setPower(powerValue);
    DataLogManager.log("Motor set to " + powerValue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveMotors_Subsystem extends SubsystemBase {

  CANSparkMax mySparkMaxController;

  /** Creates a new DriveMotors_Subsystem. */
  public DriveMotors_Subsystem() {

    mySparkMaxController = new CANSparkMax(Constants.OperatorConstants.SparkMax, MotorType.kBrushless);

  }

  public void setPower(double aPower) {
    mySparkMaxController.set(aPower);
    DataLogManager.log("Motor Power: " + aPower + "Voltage: " + mySparkMaxController.getBusVoltage());
  }

  public CommandBase driveForwardMethodCommand() {
    return runOnce(
        () -> {
          mySparkMaxController.set(Constants.OperatorConstants.kForward);
        });
  }

  public CommandBase driveReverseMethodCommand() {
    return runOnce(
        () -> {
          mySparkMaxController.set(Constants.OperatorConstants.kReverse);
        });
  }

  public CommandBase driveStopMethodCommand() {
    return runOnce(
        () -> {
          mySparkMaxController.set(Constants.OperatorConstants.kStop);
        });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

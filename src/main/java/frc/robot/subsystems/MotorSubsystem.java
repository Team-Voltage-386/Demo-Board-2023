// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class MotorSubsystem extends SubsystemBase {

  private WPI_TalonSRX m_talonController;
  private CANSparkMax m_sparkMaxController;
  private Spark m_sparkController;

  /** Creates a new MotorSubsystem. */
  public MotorSubsystem() {
    m_talonController = new WPI_TalonSRX(Constants.OperatorConstants.CANTalonSRX);
    m_sparkMaxController = new CANSparkMax(Constants.OperatorConstants.SparkMax, MotorType.kBrushless);
    m_sparkController = new Spark(Constants.OperatorConstants.kOGSparkPort);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  
   public CommandBase startSparkMotor() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
          m_sparkController.set(0.5);
        });
  }
  public CommandBase stopSparkMotor() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
          m_sparkController.set(0);
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a
   * digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleMotorCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  /**
   * simple method to set motor to a speed based on a xbox controller input. Uses
   * a deadzone to ensure motor stops near zero.
   * 
   * @param speed
   */
  public void setSparkSpeed(double s) {
    if (Math.abs(s) > 0.1)
      m_sparkController.set(s);
    else
      m_sparkController.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

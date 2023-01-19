// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.LEDSubsystem;

public class rainbowLEDsCommand extends CommandBase {

  Subsystem m_ledsubsystem;
  int m_rainbowFirstPixelHue;

  /** Creates a new rainbowLEDs command. */
  public rainbowLEDsCommand(Subsystem subsystem) {
    m_ledsubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_ledsubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // set up our initial index/count
    m_rainbowFirstPixelHue = 0;
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * HSV stands for Hue, Saturation, and Value. Hue describes the color or tint,
   * saturation being the amount of gray, and value being the brightness. In 
   * WPILib, Hue is an integer from 0 - 180. Saturation and Value are integers from 0 - 255.
   */

  @Override
  public void execute() {
    
    //TBD!!!!!  How do I call the method in the LEDSubsystem.rainbow?????
    //m_ledsubsystem.run(m_ledsubsystem.)
    
    // Increase by to make the rainbow "move"
    m_rainbowFirstPixelHue += 3;
    // Check bounds
    m_rainbowFirstPixelHue %= 180;

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

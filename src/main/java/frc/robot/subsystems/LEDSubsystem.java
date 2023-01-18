// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
  /** Creates a new LEDSubsytem. */
  private static final int kLEDPort = 4;
  private static final int kLEDLength = 10;
  private String currentColor = "yellow";

  AddressableLED led = new AddressableLED(kLEDPort);
  
  AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(kLEDLength);

  public LEDSubsystem() {
    led.setLength(kLEDLength);
    led.setData(ledBuffer);
    led.start();
  }

  public void setAll(String color)
  {
    currentColor = color;
    if (color.equals("purple"))
    {
      for(int i = 0; i < kLEDLength; i++)
      {
        setPurple(i);
      }
    }

    if (color.equals("yellow"))
    {
      for(int i = 0; i < kLEDLength; i++)
      {
        setYellow(i);
      }
    }
  }
    
  public String getColor()
  {
    return currentColor;
  }

  public void setPurple(int index) {
    ledBuffer.setRGB(index, 128, 0, 128);
  }

  public void setYellow(int index) {
    ledBuffer.setRGB(index, 255, 255, 0);
  }
  
  public void toggleColor()
  {
    if(currentColor.equals("yellow"))
    {
      currentColor = "purple";
      for(int i = 0; i < kLEDLength; i++)
      {
        setPurple(i);
      }
    }
    else
    {
      currentColor = "yellow";
      for(int i = 0; i < kLEDLength; i++)
      {
        setYellow(i);
      }
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    led.setData(ledBuffer);
    
  }
}

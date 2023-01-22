// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDSubsystem extends SubsystemBase {
  /** Creates a new LEDSubsytem. */

  AddressableLED led = new AddressableLED(Constants.kLEDPort);
  AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(Constants.kLEDLength);
  private String currentColor = "yellow";
  int m_rainbowFirstPixelHue;

  public LEDSubsystem() {

    // Length is expensive to set, so only set it once, then just update data
    led.setLength(Constants.kLEDLength);

    setAllYellow();
    led.setData(ledBuffer);
    led.start(); // this starts the continuous updating of the LEDs to what's in the buffer
  }

  public void setAllYellow() {

    for (int i = 0; i < ledBuffer.getLength() - 1; i++) {
      ledBuffer.setRGB(i, 255, 255, 0);
    }
  }

  public CommandBase setAllBlue() {
    return runOnce(
        () -> {
          for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 0, 0, 255);
          }
        });
  }

  public CommandBase rainbow() {
    return run(
        () -> {

          // For every pixel
          for (var i = 0; i < ledBuffer.getLength(); i++) {
            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final var hue = (m_rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            // Set the value
            ledBuffer.setHSV(i, hue, 255, 128);
          }
          // Increase by to make the rainbow "move"
          m_rainbowFirstPixelHue += 3;
          // Check bounds
          m_rainbowFirstPixelHue %= 180;
          led.setData(ledBuffer);
          System.out.println(m_rainbowFirstPixelHue);
        });

  }
  // public CommandBase setAll(String color) {
  // return runOnce(
  // () -> {
  // if (color.equals("purple")) {
  // for (int i = 0; i < ledBuffer.getLength(); i++) {
  // setPurple(i);
  // }
  // System.out.println("purple");
  // }

  // if (color.equals("yellow")) {
  // for (int i = 0; i < kLEDLength; i++) {
  // setYellow(i);
  // }
  // System.out.println("yellow");
  // }
  // });
  // }

  // }

  public void setAll(String color) {
    currentColor = color;
    if (color.equals("purple")) {
      for (int i = 0; i < ledBuffer.getLength(); i++) {
        setPurple(i);
      }
    }
  }

  // if (color.equals("yellow")) {
  // for (int i = 0; i < kLEDLength; i++) {
  // setYellow(i);
  // }
  // }
  // }

  public String getColor() {
    return currentColor;
  }

  public void onePurple(int index) {
    ledBuffer.setRGB(index, 128, 0, 128);

  }

  public CommandBase setPurple(int index) {
    return runOnce(
        () -> {
          ledBuffer.setRGB(index, 128, 0, 128);
        });
  }

  public CommandBase setYellow(int index) {
    return runOnce(
        () -> {
          ledBuffer.setRGB(index, 255, 255, 0);
        });
  }

  public void toggleColor() {
    if (currentColor.equals("yellow")) {
      currentColor = "purple";
      for (int i = 0; i < ledBuffer.getLength(); i++) {
        setPurple(i);
      }
    } else {
      currentColor = "yellow";
      for (int i = 0; i < ledBuffer.getLength(); i++) {
        setYellow(i);
      }
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    led.setData(ledBuffer);
    SmartDashboard.putNumber("rainbowFirstPixelHue", m_rainbowFirstPixelHue);

  }

public void allOff() {
}

public void allPurple() {
}

public void allYellow() {
}
}

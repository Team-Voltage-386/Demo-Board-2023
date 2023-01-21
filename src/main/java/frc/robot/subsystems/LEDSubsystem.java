// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
  /** Creates a new LEDSubsytem. */
  private static final int kLEDPort = 4;
  private static final int kLEDLength = 60;
  private String currentColor = "yellow";
  private boolean isYellow = false;

  AddressableLED led = new AddressableLED(kLEDPort);

  AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(kLEDLength);

  public LEDSubsystem() {
    led.setLength(kLEDLength);
    led.setData(ledBuffer);
    allOff();
    led.start();
  }

  public CommandBase setAllPurple() {
    return runOnce(
        () -> {
          for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 119, 0, 200);
          }
        });
  }

  public void allPurple() {
    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 119, 0, 200);
    }
  }

  public void allWhite() {
    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 255, 255, 255);
    }
  }

  public void allOff() {
    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 0, 0, 0);
    }
  }

  public void allRed() {
    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 255, 0, 0);
    }
  }

  public CommandBase setAllYellow() {
    return runOnce(
        () -> {
          for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 255, 0);
          }
        });
  }

  public void allYellow() {
    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 255, 255, 0);
    }
  }

  public CommandBase twoColorToggle() {
    return runOnce(
        () -> {
          if (isYellow == true) {
            allPurple();
            System.out.println("Setting all purple");
            isYellow = false;
          } else {
            allYellow();
            System.out.println("Setting all yellow");
            isYellow = true;
          }
        });
  }

  private int rainbowFirstPixelHue = 1;

  public void rainbow() {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
      ledBuffer.setHSV(i, hue, 255, 128);
    }
    rainbowFirstPixelHue += 3;
    rainbowFirstPixelHue %= 180;
  }

  public CommandBase commandRainbow() {
    return runOnce(
        () -> {
          for (var i = 0; i < ledBuffer.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            ledBuffer.setHSV(i, hue, 255, 128);

          }
          rainbowFirstPixelHue += 3;
          rainbowFirstPixelHue %= 180;
        });
  }

  public CommandBase movingRainbow() {
    return runOnce(
        () -> {
          for (int i = 0; i < 60; i++) {
            Timer.delay(0.02);
            rainbow();
            System.out.println("Changing rainbow color");
            led.setData(ledBuffer);
          }
        });
  }

  public void periodic() {
    // This method will be called once per scheduler run
    led.setData(ledBuffer);

  }
}

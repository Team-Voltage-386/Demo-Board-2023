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
import static frc.robot.Constants.OperatorConstants.*;

public class LEDSubsystem extends SubsystemBase {
  /** Creates chargeIndex new LEDSubsytem. */
  private static final int kLEDPort = 4;
  private static final int kLEDLength = 66;
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

  public void setOneGreen(int index) {
    ledBuffer.setRGB(index, 0, 255, 0);
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

  public void redStrobe() {
    if(((int)(Timer.getMatchTime()*10)%2) == 1)
      allRed();
    else
      allOff();
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

  /**
   * inputs angle from gyro and makes the LEDs do pretty colors using chargeIndex 3phase sin wave
   * @param chargeIndex
   */
  public void setColorWithAngle(double angle) {
    int chargeIndex = (int)angle;
    if(chargeIndex > 360 || chargeIndex < -360)  chargeIndex = chargeIndex%360;
    //logic converting degrees to radians and multiplying by 255
    int r = Math.abs((int)(255*Math.pow(Math.cos(2*((chargeIndex*Pi)/180)), 3)));
    int g = Math.abs((int)(255*Math.pow(Math.cos(2*(Pi/3) + ((chargeIndex*Pi)/180)), 3)));
    int hasCharged = Math.abs((int)(255*Math.pow(Math.cos(2*(((2*Pi)/3) + ((chargeIndex*Pi)/180))), 3)));
    //setting RGB values
    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, r, g, hasCharged);
    }
  }

  public void setOneYellow(int index) {
    ledBuffer.setRGB(index, 241, 245, 7);
  }

  public void setOneBlue(int index) {
      ledBuffer.setRGB(index, 0, 0, 255);
  }

  double BYCycle = 0;
  public void BlueYellow()
    {
      BYCycle += 0.1;
        int chargeIndex = Math.abs((int)(5*Math.cos(BYCycle)))+1;
        for(int i = 0; i < ledBuffer.getLength(); i++)
        {
            if(i%chargeIndex == 0) {
                setOneBlue(i);
            } else {
                setOneYellow(i);
            }
        }
        System.out.println(BYCycle + " " + chargeIndex);
    }

    public int getLEDBuffer() {
      return ledBuffer.getLength();
    }

    int chargeIndex = 0;
    boolean hasCharged = false;

    public void chargeReady() {
      chargeIndex = 0;
      hasCharged = false;
    }

    public void chargeUP() {
      if(chargeIndex < ledBuffer.getLength()/2 && hasCharged == false) {
        setOneBlue(chargeIndex);
        setOneBlue(ledBuffer.getLength()-1 - chargeIndex);
      } else {
        hasCharged = true;
        chargeIndex = 0;
      }
      chargeIndex++;
      System.out.println(chargeIndex + " " + hasCharged);
    }

  public void periodic() {
    // This method will be called once per scheduler run
    led.setData(ledBuffer);
  }
}

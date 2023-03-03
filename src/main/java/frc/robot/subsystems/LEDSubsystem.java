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
  /** Creates a new LEDSubsytem. */
  private static final int kLEDPort1 = 4;
  private static final int kLEDLength1 = 66;
  private static final int kLEDPort2 = 5;
  private static final int kLEDLength2 = 23;
  private String currentColor = "yellow";
  private boolean isYellow = false;
  private boolean TimeToCharge = false;

  AddressableLED led1 = new AddressableLED(kLEDPort1);
  AddressableLED led2 = new AddressableLED(kLEDPort2);
  AddressableLED[] LEDList = {led1, led2};
  

  AddressableLEDBuffer ledBuffer1 = new AddressableLEDBuffer(kLEDLength1);
  AddressableLEDBuffer ledBuffer2 = new AddressableLEDBuffer(kLEDLength2);
  AddressableLEDBuffer[] LEDBufferList = {ledBuffer1, ledBuffer2};

  public LEDSubsystem() {
    led1.setLength(kLEDLength1);
    led1.setData(ledBuffer1);
    led2.setLength(kLEDLength2);
    led2.setData(ledBuffer2);
    allOff();
    led1.start();
    led2.start();
  }

  public void setOneGreen(int index, int LED) {
    LEDBufferList[LED].setRGB(index, 0, 255, 0);
  }

  public CommandBase setAllPurple() {
    return runOnce(
        () -> {
          for (int i = 0; i < ledBuffer1.getLength(); i++) {
            ledBuffer1.setRGB(i, 119, 0, 200);
          }
        });
  }

  public void allPurple(int LED) {
    for (int i = 0; i < LEDBufferList[LED].getLength(); i++) {
      LEDBufferList[LED].setRGB(i, 119, 0, 200);
    }
  }

  public void allWhite(int LED) {
    for (int i = 0; i < LEDBufferList[LED].getLength(); i++) {
      LEDBufferList[LED].setRGB(i, 255, 255, 255);
    }
  }

  public void allOff() {
    for(int k = 0; k < LEDBufferList.length; k++)
      for (int i = 0; i < LEDBufferList[k].getLength(); i++) {
        LEDBufferList[k].setRGB(i, 0, 0, 0);
      }
  }

  public void allOff(int LED) {
    for (int i = 0; i < LEDBufferList[LED].getLength(); i++) {
      LEDBufferList[LED].setRGB(i, 0, 0, 0);
    }
  }

  public void allRed(int LED) {
    for (int i = 0; i < LEDBufferList[LED].getLength(); i++) {
      LEDBufferList[LED].setRGB(i, 255, 0, 0);
    }
  }

  public void redStrobe() {
    if(((int)(Timer.getMatchTime()*10)%2) == 1)
      allRed(0);
    else
      allOff(0);
  }

  public CommandBase setAllYellow() {
    return runOnce(
        () -> {
          for (int i = 0; i < ledBuffer1.getLength(); i++) {
            ledBuffer1.setRGB(i, 255, 255, 0);
          }
        });
  }

  public void allYellow(int LED) {
    for (int i = 0; i < LEDBufferList[LED].getLength(); i++) {
      LEDBufferList[LED].setRGB(i, 255, 255, 0);
    }
  }

  public CommandBase twoColorToggle() {
    return runOnce(
        () -> {
          if (isYellow == true) {
            allPurple(0);
            System.out.println("Setting all purple");
            isYellow = false;
          } else {
            allYellow(0);
            System.out.println("Setting all yellow");
            isYellow = true;
          }
        });
  }

  private int rainbowFirstPixelHue = 1;

  public void rainbow() {
    for (var i = 0; i < ledBuffer1.getLength(); i++) {
      final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer1.getLength())) % 180;
      ledBuffer1.setHSV(i, hue, 255, 128);
    }
    rainbowFirstPixelHue += 3;
    rainbowFirstPixelHue %= 180;
  }

  public CommandBase commandRainbow() {
    return runOnce(
        () -> {
          for (var i = 0; i < ledBuffer1.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer1.getLength())) % 180;
            ledBuffer1.setHSV(i, hue, 255, 128);

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
            led1.setData(ledBuffer1);
          }
        });
  }

  /**
   * inputs angle from gyro and makes the LEDs do pretty colors using a 3phase sin wave
   * @param a
   */
  public void setColorWithAngle(double angle, int LED) {
    int a = (int)angle;
    if(a > 360 || a < -360)  a = a%360;
    //logic converting degrees to radians and multiplying by 255
    int r = Math.abs((int)(255*Math.pow(Math.cos(2*((a*Pi)/180)), 3)));
    int g = Math.abs((int)(255*Math.pow(Math.cos(2*(Pi/3) + ((a*Pi)/180)), 3)));
    int b = Math.abs((int)(255*Math.pow(Math.cos(2*(((2*Pi)/3) + ((a*Pi)/180))), 3)));
    //setting RGB values
    for (int i = 0; i < ledBuffer1.getLength(); i++) {
      ledBuffer1.setRGB(i, r, g, b);
    }
  }

  public void setOneYellow(int index, int LED) {
    LEDBufferList[LED].setRGB(index, 241, 245, 7);
  }

  public void setOneBlue(int index, int LED) {
      LEDBufferList[LED].setRGB(index, 0, 0, 255);
  }

  double BYCycle = 0;
  public void BlueYellow(int LED)
    {
      BYCycle += 0.1;
        int a = Math.abs((int)(5*Math.cos(BYCycle)))+1;
        for(int i = 0; i < LEDBufferList[LED].getLength(); i++)
        {
            if(i%a == 0) {
                setOneBlue(i, LED);
            } else {
                setOneYellow(i, LED);
            }
        }
        System.out.println(BYCycle + " " + a);
    }

    public int getLEDBuffer(int LED) {
      return LEDBufferList[LED].getLength();
    }

    int ChargeIndex = 0;
    boolean b = false;
    public void chargeReady() {
      ChargeIndex = 0;
      b = false;
    }

    // public void chargeUP() {
    //   if(a < ledBuffer1.getLength()/2 && b == false) {
    //     setOneBlue(a);
    //     setOneBlue(ledBuffer1.getLength()-1 - a);
    //   } else {
    //     b = true;
    //     a = 0;
    //   }
    //   a++;
    //   System.out.println(a + " " + b);
    // }
    
  public void chargeUP() {
    TimeToCharge = true;
  }

  private int PeriodicRuns = 0;
  public void periodic() {
    // This method will be called once per scheduler run
    for(int i = 0; i < LEDList.length; i++)
      LEDList[i].setData(LEDBufferList[i]);

    if(TimeToCharge && PeriodicRuns < LEDBufferList[0].getLength()) {
      setOneYellow(PeriodicRuns, 0);
      PeriodicRuns++;
    }
    
  }
}

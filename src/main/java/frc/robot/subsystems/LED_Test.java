package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LED_Test 
{
    private static final int kLEDLength = 60;
    private String currentColor = "Off";
    private boolean isOff;

    AddressableLED led = new AddressableLED(Constants.OperatorConstants.kLEDPort);
    AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(kLEDLength);

    public LED_Test ()
    {
        led.setLength(kLEDLength);
        led.setData(ledBuffer);
        allOff();
        led.start(); 
    }

    public void allOff() 
    {
        isOff=true;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 0,0,0);
        }
    }

    public void allBlue() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 0,0,255);
        }
    }

    public void allGreen() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 0,255,0);
        }
    }

    public void allRed() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 255,0,0);
        }
    }

    public void allYellow() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 224,231,34);
        }
    }

    public void allPurple() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 128,0,128);
        }
    }

    public void allOrange
    () 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 255,165,0);
        }
    }

    public void allGold() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i, 255,215,0);
        }
    }

    public void allViolet() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i,155,38,182);
        }
    }

    public void allGrey() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i,151,153,155);
        }
    }

    public void allWhite() 
    {
        isOff=false;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            ledBuffer.setRGB(i,255,255,255);
        }
    }

    public void allMulti() 
    {
        isOff=false;
        int z=0;
        for (int i = 0; i < ledBuffer.getLength(); i++) 
        {
            if (z==10)
            {
                z=0;
            }
            if (z==0)
            {
                ledBuffer.setRGB(i,255,255,255);
            }
            if (z==1)
            {
                ledBuffer.setRGB(i,255,0,0);
            }
            if (z==2)
            {
                ledBuffer.setRGB(i,0,255,0);
            }
            if (z==3)
            {
                ledBuffer.setRGB(i,0,0,255);
            }
            if (z==4)
            {
                ledBuffer.setRGB(i,224,231,34);
            }
            if (z==5)
            {
                ledBuffer.setRGB(i,128,0,128);
            }
            if (z==6)
            {
                ledBuffer.setRGB(i,255,165,0);
            }
            if (z==7)
            {
                ledBuffer.setRGB(i,155,38,182);
            }
            if (z==8)
            {
                ledBuffer.setRGB(i,255,215,0);
            }
            if (z==9)
            {
                ledBuffer.setRGB(i,151,153,155);
            }
            z++;
        }
    }

    public void periodic() {
        // This method will be called once per scheduler run
        led.setData(ledBuffer);
    
    }
}

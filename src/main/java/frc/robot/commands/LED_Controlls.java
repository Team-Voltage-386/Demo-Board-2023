package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LED_Test;

import static frc.robot.Constants.OperatorConstants.*;

public class LED_Controlls extends CommandBase 
{
    private int NumButtonsPressed = 0;
    private int LEDMethod=0;
    private double LastButtonPress=-2.0;
    private double LastSwitchPress=-2.0;
    private DigitalInput button= new DigitalInput(2);
    private DigitalInput Switch = new DigitalInput(0);
    private final LED_Test led;
    private boolean isOff=true;

    public boolean getButtonState() 
    {
        return button.get();
    }

    public boolean getLimitSwitchState() 
    {
        return Switch.get();
    }

    public LED_Controlls(LED_Test LED) 
    {
        led=LED;
    }

    @Override
    public void execute() 
    {
        if(!getLimitSwitchState()&&(Timer.getFPGATimestamp()-LastSwitchPress)>0.3)
        {
            LEDMethod++;
            LEDMethod=LEDMethod%10;
            LastSwitchPress=Timer.getFPGATimestamp();
        }
        if(!getButtonState()&&(Timer.getFPGATimestamp()-LastButtonPress)>0.3)
        {
            NumButtonsPressed++;
            NumButtonsPressed = NumButtonsPressed % 3;
            LastButtonPress=Timer.getFPGATimestamp();
        }
        if(NumButtonsPressed==0)
        {
            led.allOff();
            isOff=true;
        }
        if(NumButtonsPressed==2)
        {
            isOff=false;
        }
        if(NumButtonsPressed==3)
        {
            if (((int) Timer.getFPGATimestamp()%2)==1)
            {
                led.allOff();
                isOff=true;
            }
            else
            {
                isOff=false;
            }
        }
        if (!isOff&&LEDMethod==0)
        {
            led.allGreen();
        }
        if (!isOff&&LEDMethod==1)
        {
            led.allRed();
        }
        if (!isOff&&LEDMethod==2)
        {
            led.allBlue();
        }
        if (!isOff&&LEDMethod==4)
        {
            led.allPurple();
        }
        if (!isOff&&LEDMethod==5)
        {
            led.allOrange();
        }
        if (!isOff&&LEDMethod==6)
        {
            led.allGrey();
        }
        if (!isOff&&LEDMethod==7)
        {
            led.allWhite();
        }
        if (!isOff&&LEDMethod==8)
        {
            led.allMulti();
        }
        if (!isOff&&LEDMethod==9)
        {
            led.allSiezure();
        }
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}
}

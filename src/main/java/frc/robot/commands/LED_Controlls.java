package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LED_Test;

import static frc.robot.Constants.OperatorConstants.*;

public class LED_Controlls extends CommandBase 
{
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
        int LEDMethod=0;
        double LastButtonPress=-2.0;
        double LastSwitchPress=-2.0;
        if(!getLimitSwitchState()&&(Timer.getFPGATimestamp()-LastSwitchPress)>0.5)
        {
            LEDMethod++;
            LEDMethod=LEDMethod%10;
        }
        if(!getButtonState()&&(Timer.getFPGATimestamp()-LastButtonPress)>0.5&&isOff==true)
        {
            LastButtonPress=Timer.getFPGATimestamp();
            isOff=false;
        }
        if(!getButtonState()&&(Timer.getFPGATimestamp()-LastButtonPress)>0.5&&isOff==false)
        {
            LastButtonPress=Timer.getFPGATimestamp();
            led.allOff();
            isOff=true;
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
            led.allGold();
        }
        if (!isOff&&LEDMethod==9)
        {
            led.allMulti();
        }
        if (!isOff&&LEDMethod==10)
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

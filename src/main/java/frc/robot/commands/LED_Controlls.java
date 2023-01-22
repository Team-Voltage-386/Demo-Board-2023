package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LED_Test;

import static frc.robot.Constants.OperatorConstants.*;

public class LED_Controlls extends CommandBase 
{
    private DigitalInput button= new DigitalInput(2);
    private DigitalInput Switch = new DigitalInput(0);
    private boolean isOff=true;

    public boolean getButtonState() 
    {
        return button.get();
    }

    public boolean getLimitSwitchState() 
    {
        return Switch.get();
    }

    public LED_Controlls() 
    {}


    @Override
    public void execute() 
    {
        if ((!getLimitSwitchState())&&isOff==false)
        {
            LED_Test.allOff();
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

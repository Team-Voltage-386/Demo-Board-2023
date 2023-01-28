package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IODigitalSubsystem extends SubsystemBase
{
    private DigitalInput button= new DigitalInput(Constants.OperatorConstants.kPushButton);
    private DigitalInput Switch = new DigitalInput(Constants.OperatorConstants.kLimitSwitchPort);
    public boolean buttonState;
    public boolean limitState;
    public IODigitalSubsystem()
    {}
    public boolean getButtonState() 
    {
        return buttonState;
    }
    public boolean getLimitSwitchState() 
    {
        return limitState;
    }
    
    public void periodic() 
    {
        // This method will be called once per scheduler run
        buttonState=button.get();
        limitState=Switch.get();
    }
}

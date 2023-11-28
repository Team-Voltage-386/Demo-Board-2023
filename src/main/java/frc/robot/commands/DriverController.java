package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorTest;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.robot.Constants.OperatorConstants.*;

public class DriverController extends CommandBase 
{
    private final MotorTest motors;

    private boolean isOff = true, buttonPressed;
    private int NumButtonsPressed = 0, LEDMethod=0, LEDMode=0;
    private double LastSwitchPress=-2.0, lastX=-2.0;

    public DriverController(MotorTest MOTORS) 
    {
        motors = MOTORS;
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }

    
    @Override
    public void execute() 
    {
        Motor_Controlls();
    }

    @Override
    public void end(boolean interrupted) {}
    
    private void Motor_Controlls ()
    {
        if (kcont1.getRawButton(kA))
        {
            motors.setRotation();
        }
        else
        {
            motors.setRotation(0);
        }
    }
}

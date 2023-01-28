package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorTest;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IODigitalSubsystem;
import frc.robot.subsystems.LED_Test;
import static frc.robot.Constants.OperatorConstants.*;

public class DriverController extends CommandBase 
{
    private final LED_Test led;
    private final IODigitalSubsystem IO;
    private final MotorTest motors;

    private boolean isOff = true;
    private int NumButtonsPressed = 0, LEDMethod=0;
    private double LastButtonPress=-2.0, LastSwitchPress=-2.0;

    public DriverController(MotorTest MOTORS, LED_Test LED, IODigitalSubsystem InOut) 
    {
        motors = MOTORS;
        led=LED;
        IO=InOut;
    }


    @Override
    public void execute() 
    {
        LED_Controlls();
        Motor_Controlls();
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}

    private void Motor_Controlls ()
    {
        motors.setDrivePower1(kcont1.getRawAxis(kRightVertical));
        motors.setDrivePower2(kcont1.getRawAxis(kLeftVertical));
    }

    private void LED_Controlls()
    {
        double curentTime = Timer.getFPGATimestamp();
        boolean switchState = (!IO.getLimitSwitchState()), buttonState = (!IO.getButtonState());

        if(switchState && (curentTime-LastSwitchPress)>0.3)
        {
            LEDMethod++;
            LEDMethod=LEDMethod%10;
            LastSwitchPress=Timer.getFPGATimestamp();
        }
        if(buttonState && (curentTime-LastButtonPress)>0.3)
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
}

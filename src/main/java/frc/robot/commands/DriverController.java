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

    private boolean isOff = true, LEDMode=true;
    private int NumButtonsPressed = 0, LEDMethod=0;
    private double LastButtonPress=-2.0, LastSwitchPress=-2.0, lastX=-2.0;

    public DriverController(MotorTest MOTORS, LED_Test LED, IODigitalSubsystem InOut) 
    {
        motors = MOTORS;
        led=LED;
        IO=InOut;
    }


    @Override
    public void execute() 
    {
        double currentTime = Timer.getFPGATimestamp();
        if (kcont1.getRawButton(kX) && (currentTime-lastX)>0.3)
        {   
            LEDMode=(!LEDMode);
            lastX=currentTime;
        }
        if (LEDMode)
        {
            LED_Controlls();
        }
        else
        {
            DistanceLED();
        }
        Motor_Controlls();
    }

    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}

    private void DistanceLED()
    {
        int start = ((int) IO.getGyroPitch())%60;
        int end = start+5;
        if (end>60)
        {
            end=60;
        }
        if (start>60)
        {
            start=60;
        }
        led.setRed(start, end);
    }

    private void Motor_Controlls ()
    {
        motors.setDrivePower1(kcont1.getRawAxis(kRightVertical));
        motors.setDrivePower2(kcont1.getRawAxis(kLeftVertical));
        motors.setDrivePower3(kcont1.getRawAxis(kLeftHorizontal));
        if (kcont1.getRawButton(kA))
        {
            motors.setDrivePower4(1);
        }
        else
        {
            motors.setDrivePower4(0);
        }
    }

    private void LED_Controlls()
    {
        double currentTime = Timer.getFPGATimestamp();
        boolean switchState = (!IO.getLimitSwitchState()), buttonState = (!IO.getButtonState());

        if(switchState && (currentTime-LastSwitchPress)>0.3)
        {
            LEDMethod++;
            LEDMethod=LEDMethod%10;
            LastSwitchPress=Timer.getFPGATimestamp();
        }
        if(buttonState && (currentTime-LastButtonPress)>0.3)
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

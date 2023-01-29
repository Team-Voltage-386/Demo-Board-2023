package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorTest;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IODigitalSubsystem;
import frc.robot.subsystems.LED_Test;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.robot.Constants.OperatorConstants.*;

public class DriverController extends CommandBase 
{
    private final LED_Test led;
    private final IODigitalSubsystem IO;
    private final MotorTest motors;

    private boolean isOff = true, buttonPressed;
    private int NumButtonsPressed = 0, LEDMethod=0, LEDMode=0;
    private double LastSwitchPress=-2.0, lastX=-2.0;

    public DriverController(MotorTest MOTORS, LED_Test LED, IODigitalSubsystem InOut) 
    {
        motors = MOTORS;
        led=LED;
        IO=InOut;
        buttonPressed = IO.getButtonState();
    }


    @Override
    public void execute() 
    {
        double currentTime = Timer.getFPGATimestamp();
        if (kcont1.getRawButton(kX) && (currentTime-lastX)>0.3)
        {   
            LEDMode++;
            lastX=currentTime;
            LEDMode=LEDMode%5;
        }
        if (LEDMode==0)
        {
            LED_Controlls();
        }
        if (LEDMode==1)
        {
            DistanceLED();
        }
        if (LEDMode==2)
        {
            RollLED();
        }
        if (LEDMode==3)
        {
            PitchLED();
        }
        if (LEDMode==4)
        {
            RPLED();
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

    private void DistanceLED()
    {
        int start = (int) (IO.getUltraDistance());
        int end = start+5;
        if (end>=60)
        {
            end=59;
        }
        if (start>=60)
        {
            start=59;
        }
        led.setRed(start,end);
    }

    private void RollLED()
    {
        int start = (int) -(IO.getGyroRoll()-4.5) + 27;
        int end = start+5;
        if (end>=60)
        {
            end=59;
        }
        if (start>=60)
        {
            start=59;
        }
        if (start==27)
        {
            led.setGreen(start, end);
        }
        else
        {
            led.setRed(start, end);
        }
    }

    private void PitchLED()
    {
        int start = (int) -(IO.getGyroPitch()+1.5) + 27;
        int end = start+5;
        if (end>=60)
        {
            end=59;
        }
        if (start>=60)
        {
            start=59;
        }
        if (start==27)
        {
            led.setGreen(start, end);
        }
        else
        {
            led.setRed(start, end);
        }
    }

    private void RPLED()
    {
        int roll = (int) (IO.getGyroRoll()-4.5);
        int pitch = (int) (IO.getGyroPitch()+1.5);
        if (roll<0)
        {
            roll=-roll;
        }
        if (pitch<0)
        {
            pitch=-pitch;
        }
        led.setAllColor(roll%45*255, pitch%45*255, 0);
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
        if(buttonState != buttonPressed)
        {
            buttonPressed = !buttonPressed;
            NumButtonsPressed++;
            NumButtonsPressed = NumButtonsPressed % 3;
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
        if(NumButtonsPressed==1)
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

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.annotation.Target;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;

public class PID extends SubsystemBase
{
    @Override
    public void periodic() 
    {
        //Periodically checks SHuffleBoard to see if the PID values need to be updated
        updatePID();
    }

    //The ShuffleBoard tab that the PID values will be retrieved from
    private ShuffleboardTab pidTab;

    //PID values that are currently programmed into the motor
    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    //PID values that are on ShuffleBoard
    private SimpleWidget PValue, IValue, DValue, IZone, FeedForward, MaxOutput, MinOutput, MotorPos, MotorVel, MotorTarget;

    //The PID controller for the CanSparkMax motor
    private SparkPIDController SparkPIDcontrols;

    //Motor's encoder
    private RelativeEncoder MotorEncoder;

    public PID(String SuffleBoardTabName, CANSparkMax Motor)
    {
        //Creates a tab in ShuffleBoard for the PID values of the specified CanSparkMax motor
        pidTab = Shuffleboard.getTab(SuffleBoardTabName);

        //Saves the PID controller that is built into the motor and its encoder
        SparkPIDcontrols = Motor.getPIDController();

        //Gets the motor's encoders
        MotorEncoder = Motor.getEncoder();

        //Initializes the PID variables
        kP = 1e-10; 
        kI = 0.1;
        kD = 0.0001; 
        kIz = 1; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;

        //Creates the widget for target position 
        MotorTarget = pidTab.add("Target", 1000);

        //Creates the Widgets for the motor's encoder values
        MotorPos = pidTab.add("Encoder Position", MotorEncoder.getPosition());
        MotorVel = pidTab.add("Encoder Velocity", MotorEncoder.getVelocity());

        //Creates the Widgets that will be used in the shuffleboard tab
        PValue = pidTab.add("P Value", kP);
        IValue = pidTab.add("I Value", kI);
        DValue = pidTab.add("D Value", kD);
        IZone= pidTab.add("I Zone", kIz);
        FeedForward = pidTab.add("Feed Forward", kFF);
        MaxOutput = pidTab.add("Max Output", kMaxOutput);
        MinOutput = pidTab.add("Min Output", kMinOutput);

        //Burns the initialized PID values into the CanSparkMax motor
        SparkPIDcontrols.setP(kP);
        SparkPIDcontrols.setI(kI);
        SparkPIDcontrols.setD(kD);
        SparkPIDcontrols.setIZone(kIz);
        SparkPIDcontrols.setFF(kFF);
        SparkPIDcontrols.setOutputRange(kMinOutput, kMaxOutput);
    }

    private void updatePID()
    {
        //Updates encoder values
        MotorPos.getEntry().setDouble(MotorEncoder.getPosition());
        MotorVel.getEntry().setDouble(MotorEncoder.getVelocity());

        //Retrieves the values from ShuffleBoard
        double p = PValue.getEntry().getDouble(0);
        double i = IValue.getEntry().getDouble(0);
        double d = DValue.getEntry().getDouble(0);
        double iz = IZone.getEntry().getDouble(0);
        double ff = FeedForward.getEntry().getDouble(0);
        double max = MaxOutput.getEntry().getDouble(0);
        double min = MinOutput.getEntry().getDouble(0);

        //Checks for changes in the P value
        if (kP != p)
        {
            kP = p;
            SparkPIDcontrols.setP(kP);
        }

        //Checks for changes in the I value
        if (kI != i)
        {
            kI = i;
            SparkPIDcontrols.setI(kI);
        }

        //Checks for changes in the D value
        if (kD != d)
        {
            kD = d;
            SparkPIDcontrols.setD(kD);
        }

        //Checks for changes in the I zone value
        if (kIz != iz)
        {
            kIz = iz;
            SparkPIDcontrols.setIZone(kIz);
        }

        //Checks for changes in the feed forward value
        if (kFF != ff)
        {
            kFF = ff;
            SparkPIDcontrols.setFF(kFF);
        }

        //Checks for changes in the maximum input and output values
        if (kMaxOutput != max || kMinOutput != min)
        {
            kMaxOutput = max;
            kMinOutput = min;
            SparkPIDcontrols.setOutputRange(kMinOutput, kMaxOutput);
        }    
    }

    //Controls the motor with PID
    public void setReference(double refrenceValue, ControlType refrenceControlType)
    {
        SparkPIDcontrols.setReference(refrenceValue, refrenceControlType);
    }

    public void setReference(ControlType refrenceControlType)
    {
        SparkPIDcontrols.setReference(MotorTarget.getEntry().getDouble(0), refrenceControlType);
    }

    // public double calculate(double targetValue, double currentValue) {
        
    // }
}

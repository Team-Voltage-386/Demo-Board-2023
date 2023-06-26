package frc.robot.subsystems;


import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase{
    /*Motor1 = CanSparkMax
    * Motor2 = Spark
    * Motor3 = TalonSRX
    * Motor4 = Servo
    */
    private WPI_TalonSRX motor3 = new WPI_TalonSRX(Constants.OperatorConstants.CANTalonSRX);
    private CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);
    private Spark motor2 = new Spark(Constants.OperatorConstants.kOGSparkPort);
    private Servo motor4 = new Servo(Constants.OperatorConstants.Servo);
    private SparkMaxPIDController SparkPIDcontrols = motor1.getPIDController();
    private RelativeEncoder m1_encoder = motor1.getEncoder();
    
    
    private double maxRPM = 5700;

    private double kP; 
    private double kI;
    private double kD; 
    private double kIz; 
    private double kFF; 
    private double kMaxOutput; 
    private double kMinOutput;

    private double setPoint;

    public MotorTest ()
    {
        kP = 1e-10; 
        kI = 0.1;
        kD = 0.0001; 
        kIz = 1; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;

        SmartDashboard.putNumber("P Value", kP);
        SmartDashboard.putNumber("I Value", kI);
        SmartDashboard.putNumber("D Value", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);
        SmartDashboard.putNumber("Set Point", setPoint);

        SmartDashboard.putNumber("Velocity", m1_encoder.getVelocity());

        SparkPIDcontrols.setP(kP);
        SparkPIDcontrols.setI(kI);
        SparkPIDcontrols.setD(kD);
        SparkPIDcontrols.setIZone(kIz);
        SparkPIDcontrols.setFF(kFF);
        SparkPIDcontrols.setOutputRange(kMinOutput, kMaxOutput);
    }

    

    public void driveMotorStop1()
    {
        motor1.set(0);
    }

    public void driveMotorStop2()
    {
        motor2.set(0);
    }

    public void driveMotorStop3()
    {
        motor3.set(0);
    }

    public void driveMotorStop4()
    {
        motor4.set(0);
    }

    public void setDrivePower1(double v)
    {
        setPoint=v*maxRPM;
        //System.out.println(SparkPIDcontrols.getP());
        if(v>0.1||v<-0.1)
        {
            SparkPIDcontrols.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        }
        else
        {
            //driveMotorStop1();
            SparkPIDcontrols.setReference(0, CANSparkMax.ControlType.kVelocity);
        }
    }

    public void setDrivePower2(double v)
    {
        if(v>0.1||v<-0.1)
        {
            motor2.set(v);
        }
        else
        {
            driveMotorStop2();
        }
    }

    public void setDrivePower3(double v)
    {
        if(v>0.1||v<-0.1)
        {
            motor3.set(v);
        }
        else
        {
            driveMotorStop3();
        }
    }

    public void setDrivePower4(double v)
    {
        if(v>0)
        {
            motor4.set(v);
        }
        else
        {
            driveMotorStop4();
        }
    }

    @Override
    public void periodic() 
    {
        updateWidgets();
        updateTuning();
    }




    private final GenericEntry mot1Enc = Shuffleboard.getTab("main").add("mot 1 enc", 0).withPosition(1,1).withSize(1,1).getEntry();

    private void updateWidgets() 
    {
       mot1Enc.setDouble(motor1.getEncoder().getPosition());
    }

    private void updateTuning() 
    {
        SmartDashboard.putNumber("Set Point", setPoint);

        SmartDashboard.putNumber("Velocity", m1_encoder.getVelocity());

        if (kP != SmartDashboard.getNumber("P Value", 0))
        {
            kP = SmartDashboard.getNumber("P Value", 0);
            SparkPIDcontrols.setP(kP);
        }
        if (kI != SmartDashboard.getNumber("I Value", 0))
        {
            kI = SmartDashboard.getNumber("I Value", 0);
            SparkPIDcontrols.setI(kI);
        }
        if (kD != SmartDashboard.getNumber("D Value", 0))
        {
            kD = SmartDashboard.getNumber("D Value", 0);
            SparkPIDcontrols.setD(kD);
        }
        if (kIz != SmartDashboard.getNumber("I Zone", 0))
        {
            kIz = SmartDashboard.getNumber("I Zone", 0);
            SparkPIDcontrols.setIZone(kIz);
        }
        if (kFF != SmartDashboard.getNumber("Feed Forward", 0))
        {
            kFF = SmartDashboard.getNumber("Feed Forward", 0);
            SparkPIDcontrols.setFF(kFF);
        }
        if (kMaxOutput != SmartDashboard.getNumber("Max Output", 0) || kMinOutput != SmartDashboard.getNumber("Min Output", 0))
        {
            kMaxOutput = SmartDashboard.getNumber("Max Output", 0);
            kMinOutput = SmartDashboard.getNumber("Min Output", 0);
            SparkPIDcontrols.setOutputRange(kMinOutput, kMaxOutput);
        }    
    }
} 
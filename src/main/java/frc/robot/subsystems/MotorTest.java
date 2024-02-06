package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase {
    private CANSparkMax m_motorA;
   
    private ShuffleboardTab motorTab;

    private SimpleWidget encoder;

    public MotorTest() {
        m_motorA = new CANSparkMax(11, MotorType.kBrushless);
        motorTab = Shuffleboard.getTab("Sensors");
        encoder = motorTab.add("Absolute Encoder", 0);
    }

    @Override
    public void periodic() {
        encoder.getEntry().setDouble(m_motorA.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle).getPosition()*360);
        }
}
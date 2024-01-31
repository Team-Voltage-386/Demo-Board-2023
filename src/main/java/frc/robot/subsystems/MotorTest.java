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
    private DigitalInput SensorUV;
   
    private ShuffleboardTab sensorTab;

    private SimpleWidget Detected;

    public MotorTest() {
        SensorUV = new DigitalInput(5);
        sensorTab = Shuffleboard.getTab("Sensors");
        Detected = sensorTab.add("Detected", false);
    }

    @Override
    public void periodic() {
        Detected.getEntry().setBoolean(SensorUV.get());
        }
}
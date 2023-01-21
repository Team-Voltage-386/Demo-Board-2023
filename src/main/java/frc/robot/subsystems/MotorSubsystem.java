package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class MotorSubsystem extends SubsystemBase {

    private CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);
    private Spark motor3 = new Spark(3);

    public MotorSubsystem() {
    }

    public void stopMotor1() {
        motor1.set(0);
    }

    public void setPower1(double v) {
        motor1.set(v);
    }

    public void stopMotor3() {
        motor3.set(0);
    }

    public void setPower3() {
        motor3.set(0.5);
    }

    @Override
    public void periodic() {
        updateWidgets();
    }

    private final GenericEntry mot1Enc = Shuffleboard.getTab("main").add("mot 1 enc", 0).withPosition(1, 1)
            .withSize(1, 1).getEntry();

    private void updateWidgets() {
        mot1Enc.setDouble(motor1.getEncoder().getPosition());
    }

}

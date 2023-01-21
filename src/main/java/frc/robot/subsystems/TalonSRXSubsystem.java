package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TalonSRXSubsystem extends SubsystemBase {
    private TalonSRX motor2 = new TalonSRX(2);

    public TalonSRXSubsystem() {
    }

    public void stopMotor2() {
        motor2.set(ControlMode.PercentOutput, 0);
    }

    public void setPower2(double v2) {
        motor2.set(ControlMode.PercentOutput, v2);
    }

    @Override
    public void periodic() {
    }

}

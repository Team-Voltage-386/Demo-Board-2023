package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TalonSRXSubsystem extends SubsystemBase {
    private TalonSRX motor2 = new TalonSRX(2);
    // Encoder plgEncoder = new Encoder(4, 5);

    public TalonSRXSubsystem() {
        // plgEncoder.reset();
        // plgEncoder.setDistancePerPulse(1 / 44.4);
    }

    public void stopMotor2() {
        motor2.set(ControlMode.PercentOutput, 0);
    }

    public void setPower2(double v2) {
        motor2.set(ControlMode.PercentOutput, v2);
    }

    public void resetPLGEncoder() {
        // plgEncoder.reset();
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Encoder Distance", plgEncoder.getDistance());
        // SmartDashboard.putNumber("Encoder Rate", plgEncoder.getRate());
        // SmartDashboard.putBoolean("Encoder is Stopped", plgEncoder.getStopped());
        // SmartDashboard.putBoolean("Encoder Direction", plgEncoder.getDirection());
        // SmartDashboard.putNumber("Encoder Raw Count", plgEncoder.getRaw());
    }

}

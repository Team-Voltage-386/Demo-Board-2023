package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTest extends SubsystemBase {

    private CANSparkMax motorA = new CANSparkMax(12, MotorType.kBrushless);
    private CANSparkMax motorB = new CANSparkMax(11, MotorType.kBrushless);

    private double oldA = 0, oldB = 0;
    private boolean oldIsOn = false;
    private double oldBothMotor = 0;
    private boolean oldUseBothMotor;
    private boolean oldInvertBothMotor;
    private ShuffleboardTab motorTab;

    private SimpleWidget motorOnOff;
    private SimpleWidget motorAShuffable, motorBShuffable;
    private SimpleWidget bothMotorShuffable;
    private SimpleWidget useBothMotor;
    private SimpleWidget invertBothMotor;
    private SimpleWidget motorARPM;
    private SimpleWidget motorBRPM;

    public MotorTest() {
        motorTab = Shuffleboard.getTab("Motor Controls");
        motorOnOff = motorTab.add("Are Motors On", false);
        motorAShuffable = motorTab.add("Motor A Percent", 0);
        motorBShuffable = motorTab.add("Motor B Percent", 0);
        bothMotorShuffable = motorTab.add("Set Both Motors", 0);
        useBothMotor = motorTab.add("Use Both Motor Setting", false);
        invertBothMotor = motorTab.add("Invert Both Motor Setting", false);
        motorARPM = motorTab.add("Motor A RPM", 0.0);
        motorBRPM = motorTab.add("Motor B RPM", 0.0);

        motorA.enableVoltageCompensation(12.16);
        motorB.enableVoltageCompensation(12.16);

        System.out.printf("Nominal voltage A: %f\n", motorA.getVoltageCompensationNominalVoltage());
        System.out.printf("Nominal voltage B: %f\n", motorB.getVoltageCompensationNominalVoltage());
    }

    @Override
    public void periodic() {
        oldUseBothMotor = useBothMotor.getEntry().getBoolean(false);
        oldInvertBothMotor = invertBothMotor.getEntry().getBoolean(false);

        oldBothMotor = bothMotorShuffable.getEntry().getDouble(0);
        if (oldBothMotor > 100) {
            oldBothMotor = 100;
        } else if (oldBothMotor < 0) {
            oldBothMotor = 0;
        }

        double A, B;
        if (oldUseBothMotor) {
            if (oldInvertBothMotor) {
                A = oldBothMotor;
                B = -1 * oldBothMotor;
            } else {
                A = -1 * oldBothMotor;
                B = oldBothMotor;
            }
        } else {
            A = motorAShuffable.getEntry().getDouble(0);
            B = motorBShuffable.getEntry().getDouble(0);
        }

        if (A > 100) {
            A = 100;
        } else if (A < -100) {
            A = -100;
        }

        if (B > 100) {
            B = 100;
        } else if (B < -100) {
            B = -100;
        }

        oldA = A;
        oldB = B;

        boolean isOn = motorOnOff.getEntry().getBoolean(false);

        if (isOn) {
            if (!oldIsOn) {
                // Asked to turn on
                oldIsOn = true;
                motorA.set(oldA / 100);
                motorB.set(oldB / 100);
            }
        } else {
            if (oldIsOn) {
                // Asked to turn off
                oldIsOn = false;
                motorA.set(0);
                motorB.set(0);
            }
        }

        RelativeEncoder absEncA = motorA.getEncoder();
        this.motorARPM.getEntry().setDouble(Math.abs(absEncA.getVelocity()));

        RelativeEncoder absEncB = motorB.getEncoder();
        this.motorBRPM.getEntry().setDouble(Math.abs(absEncB.getVelocity()));
    }
}
package frc.robot.commands;

import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.StringLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.TalonSRXSubsystem;
import frc.robot.subsystems.DIOSubsystem;

import static frc.robot.Constants.OperatorConstants.*;

public class Controller1 extends CommandBase {
    private final LEDSubsystem led;
    private final MotorSubsystem motors;
    private final PneumaticsSubsystem piston;
    private final TalonSRXSubsystem talonMotors;
    private final DIOSubsystem dio;
    public boolean isPurple = false;
    int ultraDistance;
    int gyroAngle;
    int gyroPitch;
    double[] gyroYPR;
    int gyroPitchLEDStart;
    BooleanLogEntry myBooleanLog;
    DoubleLogEntry myDoubleLog;
    StringLogEntry myStringLog;

    public Controller1(LEDSubsystem LED, MotorSubsystem MOTORS, PneumaticsSubsystem PISTON,
            TalonSRXSubsystem TALONMOTORS, DIOSubsystem DIO) {
        led = LED;
        motors = MOTORS;
        piston = PISTON;
        talonMotors = TALONMOTORS;
        dio = DIO;
        led.allOff();

    }

    @Override
    public void initialize() {
        DataLog log = DataLogManager.getLog();
        myBooleanLog = new BooleanLogEntry(log, "/my/boolean");
        myDoubleLog = new DoubleLogEntry(log, "/my/double");
        myStringLog = new StringLogEntry(log, "/my/string");
    }

    @Override
    public void execute() {
        double v = kcont1.getRawAxis(kLeftVertical);
        if (v > 0.4) {
            led.allPurple();
            myStringLog.append("set LEDs all purple");
        } else if (v < -0.4) {
            led.allYellow();
            myStringLog.append("set LEDs all yellow");
        } else {
            led.allOff();
        }

        if (kcont1.getRawAxis(kRightHorizontal) < -0.2 || kcont1.getRawAxis(kRightHorizontal) > 0.2) {
            motors.setPower1(kcont1.getRawAxis(kRightVertical));
            myStringLog.append("Power1 setting:");
            myDoubleLog.append(kcont1.getRawAxis(kRightVertical));
        } else
            motors.setPower1(0);

        if (kcont1.getRawAxis(kRightHorizontal) < -0.2 || kcont1.getRawAxis(kRightHorizontal) > 0.2) {
            talonMotors.setPower2(kcont1.getRawAxis(kRightHorizontal));
            myStringLog.append("Power2 setting:");
            myDoubleLog.append(kcont1.getRawAxis(kRightHorizontal));
        } else
            talonMotors.setPower2(0);

        motors.setServoPos(kcont1.getRawAxis(kRightTrigger));

        if (kcont1.getRawButtonPressed(kA))
            piston.pistonForward();
        if (kcont1.getRawButtonPressed(kB))
            piston.pistonReverse();
        if (kcont1.getRawButtonPressed(kY))
            piston.pistonToggle();

        if (kcont1.getRawButtonPressed(kX))
            piston.pistonButton(true);
        if (kcont1.getRawButtonReleased(kX))
            piston.pistonButton(false);

        if (dio.getButtonState()) {
            led.allRed();
        }
        if (dio.getLimitSwitchState()) {
            led.allRed();
            talonMotors.resetPLGEncoder();
        }
        if (kcont1.getRawButtonPressed(kRightBumper)) {
            motors.setPower3();
        }
        if (kcont1.getRawButtonPressed(kLeftBumper)) {
            motors.stopMotor3();
        }

        // if (dio.getUltraDistanceInch() > 60) {
        // ultraDistance = 60;
        // } else {
        // ultraDistance = dio.getUltraDistanceInch();
        // }
        // for (int i = 0; i < (60 - ultraDistance); i++) {
        // led.setOneGreen(i);
        // }

        gyroYPR = dio.getGyroYPR();
        gyroPitch = (int) gyroYPR[2];

        int gyroPitchLEDStart = 29 + gyroPitch;
        if (gyroPitchLEDStart > 59)
            gyroPitchLEDStart = 30;
        if (gyroPitchLEDStart < 0)
            gyroPitchLEDStart = 0;

        if (gyroPitchLEDStart <= 29) {
            for (int i = 0; i < 8; i++) {
                led.setOneGreen(gyroPitchLEDStart + 4 - i);
            }
        } else {
            for (int i = 0; i < 8; i++) {
                led.setOneGreen(gyroPitchLEDStart - 4 + i);
            }
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }

}
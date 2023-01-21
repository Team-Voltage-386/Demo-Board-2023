package frc.robot.commands;

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
    public void execute() {
        double v = kcont1.getRawAxis(kLeftVertical);
        if (v > 0.4)
            led.allPurple();
        else if (v < -0.4)
            led.allYellow();
        else
            led.allOff();

        motors.setPower1(kcont1.getRawAxis(kRightVertical));

        talonMotors.setPower2(kcont1.getRawAxis(kRightHorizontal));

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
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.MOTORSubsystem;

import static frc.robot.Constants.OperatorConstants.*;

public class Controller1 extends CommandBase {

    private final LEDSubsystem led;
    private final MOTORSubsystem motors;

    public boolean isPurple = false;

    public Controller1(LEDSubsystem LED, MOTORSubsystem MOTORS) {
        led = LED;
        motors = MOTORS;
        // init
        led.allOff();
    }


    @Override
    public void execute() {
        double v = kcont1.getRawAxis(kLeftVertical);
        if (v > 0.4) led.allPurple();
        else if (v < -0.4) led.allYellow();
        else led.allOff();

        motors.setPower1(kcont1.getRawAxis(kRightVertical));
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}

}
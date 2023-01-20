package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import static frc.robot.Constants.OperatorConstants.*;

public class Controller1 extends CommandBase {

    private final LEDSubsystem led;

    public boolean isPurple = false;

    public Controller1(LEDSubsystem LED) {
        led = LED;

        // init
        led.allYellow();
    }


    @Override
    public void execute() {
        handleLEDColorToggle();
    }


    /** determines led color through toggle on A button */
    private void handleLEDColorToggle() {

        if (kcont1.getRawButtonPressed(kA)) {
            isPurple = !isPurple;
            if (isPurple) led.allPurple();
            else led.allYellow();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}

}
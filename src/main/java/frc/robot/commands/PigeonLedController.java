package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.DIOSubsystem;

public class PigeonLedController extends Command {
    private final LEDSubsystem led;
    private final DIOSubsystem pigeon;

    public PigeonLedController(LEDSubsystem LED,
            DIOSubsystem DIO) {
        led = LED;
        pigeon = DIO;

        led.allOff();
        led.chargeReady();
    }

    @Override
    public void execute() {
        double percentHeading = pigeon.gyroHeading() % 360.0 / 360.0;

        int numLeds = (int) Math.ceil(percentHeading * (double) led.ledStripLength());

        if (numLeds == led.ledStripLength()) {
            led.setAllPurple();
        } else {
            led.allOff();
            for (int i = 0; i < numLeds; i++) {
                led.setOneGreen(i, 0);
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

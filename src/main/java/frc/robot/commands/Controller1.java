package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

import frc.robot.subsystems.PneumaticsSubsystem;

import static frc.robot.Constants.OperatorConstants.*;
import static frc.robot.Constants.*;
/**
 * This command reads buttons on the controller and activates the LEDs and/or pneumatic piston.
 * The execute method continuously polls the state of the buttons.
 */
public class Controller1 extends CommandBase {

    private final LEDSubsystem led;
    private final PneumaticsSubsystem piston;

    public boolean isPurple = false;

    public Controller1(LEDSubsystem LED, PneumaticsSubsystem PISTON) {
        led = LED;
        piston = PISTON;
        // initialize all LEDs to off
        led.allOff();
    }

    /**
     * the execute method is called at a 50hz rate.
     * 
     */
    @Override
    public void execute() {
        // TBD - doesn't seem to work right now.
        double v = kcont1.getRawAxis(kLeftVertical);
        if (v > 0.4)
            led.allPurple();
        else if (v < -0.4)
            led.setAllYellow();
        else
            led.allOff();

        // motors.setPower1(kcont1.getRawAxis(kRightVertical));

        if (kcont1.getRawButtonPressed(kA))
            piston.pistonForward(); // extend piston
        if (kcont1.getRawButtonPressed(kB))
            piston.pistonReverse();  // retract piston
        if (kcont1.getRawButtonPressed(kY))
            piston.pistonToggle();  // each button press toggles the state of the piston

        // pistonButton call - YOU MUST WRITE IT LIKE THIS:
        // While the X button is held, the piston will remain extended. Upon release the piston will retract
        if (kcont1.getRawButtonPressed(kX))
            piston.pistonButton(true);
        if (kcont1.getRawButtonReleased(kX))
            piston.pistonButton(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }

}
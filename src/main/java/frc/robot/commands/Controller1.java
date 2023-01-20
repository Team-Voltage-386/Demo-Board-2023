package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.MOTORSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;

import static frc.robot.Constants.OperatorConstants.*;

public class Controller1 extends CommandBase {

    private final LEDSubsystem led;
    private final MOTORSubsystem motors;
    private final PneumaticsSubsystem piston;

    public boolean isPurple = false;

    public Controller1(LEDSubsystem LED, MOTORSubsystem MOTORS, PneumaticsSubsystem PISTON) {
        led = LED;
        motors = MOTORS;
        piston = PISTON;
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

        if(kcont1.getRawButtonPressed(kA)) piston.pistonForward();
        if(kcont1.getRawButtonPressed(kB)) piston.pistonReverse();
        if(kcont1.getRawButtonPressed(kY)) piston.pistonToggle();

        //pistonButton call - YOU MUST WRITE IT LIKE THIS:
        if(kcont1.getRawButtonPressed(kX)) piston.pistonButton(true);
        if(kcont1.getRawButtonReleased(kX)) piston.pistonButton(false);
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}

}

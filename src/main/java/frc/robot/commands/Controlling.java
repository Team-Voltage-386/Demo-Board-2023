package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorTest;

import static frc.robot.Constants.OperatorConstants.*;

public class Controlling extends CommandBase {

    private final MotorTest motors;

    public Controlling(MotorTest MOTORS) {
        motors = MOTORS;
    }


    @Override
    public void execute() {
        motors.setDrivePower1(kcont1.getRawAxis(kRightVertical));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}
}

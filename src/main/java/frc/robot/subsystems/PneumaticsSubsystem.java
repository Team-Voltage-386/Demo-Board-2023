package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsSubsystem extends SubsystemBase {
    private boolean pistonExtended = false;
    private PneumaticsControlModule pcm = new PneumaticsControlModule();
    private DoubleSolenoid testSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 3);
    private Compressor pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);

    public PneumaticsSubsystem() {
        System.out.println(pcmCompressor.isEnabled());
        testSolenoid.set(Value.kOff);
    }

    public void pistonForward() {
        testSolenoid.set(Value.kForward);
        pistonExtended = true;
    }

    public void pistonReverse() {
        testSolenoid.set(Value.kReverse);
        pistonExtended = false;
    }

    public void pistonToggle() {
        testSolenoid.toggle();
        pistonExtended = !pistonExtended;
    }

    public void pistonButton(boolean b) {
        if (b)
            pistonForward();
        else
            pistonReverse();
    }

}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsSubsystem extends SubsystemBase {
    private PneumaticsControlModule pcm = new PneumaticsControlModule();
    private DoubleSolenoid testSolinoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,4,3);
    private Compressor pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);

    public PneumaticsSubsystem() {}
}

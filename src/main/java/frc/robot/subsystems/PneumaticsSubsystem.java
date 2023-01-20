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

public class PneumaticsSubsystem extends SubsystemBase{

    private PneumaticsControlModule pcm = new PneumaticsControlModule();

    private DoubleSolenoid testSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 3);

    private Compressor pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);


/*
    private pcmCompressor.enableDigital();

    private pcmCompressor.disable();

    private boolean enabled = pcmCompressor.enabled();
    private boolean pressureSwitch = pcmCompressor.getPressureSwitchValue();
    private double current = pcmCompressor.getCompressorCurrent();
*/
    public PneumaticsSubsystem() {
        System.out.println(pcmCompressor.isEnabled());
        testSolenoid.set(Value.kOff);
        
    }  

    public void pistonForward(boolean b){
        if(b) testSolenoid.set(Value.kForward);
    }

    public void pistonReverse(boolean b){
        if(b) testSolenoid.set(Value.kReverse);
    }
}
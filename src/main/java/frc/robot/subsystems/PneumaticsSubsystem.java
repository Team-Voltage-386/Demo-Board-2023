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

    private boolean pistonExtended = false;

    private PneumaticsControlModule pcm = new PneumaticsControlModule();

    private DoubleSolenoid testSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 3);

    private Compressor pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);


    public PneumaticsSubsystem() {
        System.out.println(pcmCompressor.isEnabled());
        testSolenoid.set(Value.kOff);
    }  

    public void pistonForward(){
        testSolenoid.set(Value.kForward);
        pistonExtended = true;
    }

    public void pistonReverse(){
        testSolenoid.set(Value.kReverse);
        pistonExtended = false;
    }

    public void pistonToggle(){
        //if(pistonExtended) testSolenoid.set(Value.kReverse); 
        //else testSolenoid.set(Value.kForward);
        testSolenoid.toggle();
        pistonExtended = !pistonExtended;
    }

    /**pistonButton(boolean b) makes the piston extend and stay extended while the button is held down. when the button is released, the piston will retract.
     * this method must be called twice.
     *<hr>
     * the first call must be:
     * if(kController.getRawButtonPressed(kButton)) piston.pistonButton(true);
     *<hr>
     * the second must be:
     * if(kController.getRawButtonReleased(kButton)) piston.pistonButton(false);
     *<hr>
     * pistonButton is written this way (instead of just using a while loop) to stop the piston from running pistonForward() 50 times a second.
     * @param b
     */
    public void pistonButton(boolean b){
         if(b) pistonForward();
         else pistonReverse();
    }
}
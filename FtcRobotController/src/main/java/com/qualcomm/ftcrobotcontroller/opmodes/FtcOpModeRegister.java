package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    manager.register("Teleop", meccanumLaunchControl.class);
    //manager.register("Color Display Ambient",ColorDisplaytest.class);
    manager.register("Ball shoot center",ballShootAuto3D_Print_Tube.class);
    manager.register("Cap Ball", mecanumCapBallStateMachine10_31_16.class);
    manager.register("beacon blue center",beaconAutonomousBLUELinearSlideShorterRevA2_7.class);
    manager.register("beacon red center",beaconAutonomousREDLinearSlideShorterRevA2_7.class);
    //manager.register("slide with gyro test",slideSidewayGyroTesting.class);
  }
}

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

    manager.register("Teleop", meccanumLaunchControl.class);//
    manager.register("Shoot Straight",ballShootAuto3D_Print_Tube.class);//
    manager.register("Shoot from side BLUE",ballShootAuto3D_Print_Tube_Block_Blue.class);//
    manager.register("Shoot from side RED",ballShootAuto3D_Print_Tube_Block_Red.class);
    manager.register("beacon blue center",beaconAutonomousBLUELinearSlideShorterRevA2_7.class);//
    manager.register("beacon red center",beaconAutonomousREDLinearSlideShorterRevA2_7.class);//
    manager.register("beacon blue auto shoot",beaconAutonomousBLUELinearSlideShorterRevA2_7_shootFirst.class);//
    manager.register("beacon blue auto block",beaconAutonomousBLUELinearSlideShorterRevA2_7_BLOCKS_AFTER.class);//
    manager.register("beacon red auto block",beaconAutonomousREDLinearSlideShorterRevA2_7_BLOCKS_AFTER.class);//
    manager.register("beacon red auto shoot",beaconAutonomousREDLinearSlideShorterRevA2_7_shootFirst.class);//
  }
}

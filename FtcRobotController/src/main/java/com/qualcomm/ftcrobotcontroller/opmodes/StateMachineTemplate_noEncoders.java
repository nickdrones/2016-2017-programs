package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;

public class StateMachineTemplate_noEncoders extends OpMode
{
  private int state = 0;
  public StateMachineTemplate_noEncoders()
  {
  }
  protected DcMotor leftFront;
  protected DcMotor rightFront;
  @Override public void init() {
      leftFront = hardwareMap.dcMotor.get("leftFront");
      rightFront = hardwareMap.dcMotor.get("rightFront");
    leftFront.setDirection(DcMotor.Direction.FORWARD);
    rightFront.setDirection(DcMotor.Direction.REVERSE);
  }
  public void loop ()
  {
    switch (state)
    {
      case 0:
        leftFront.setMode(RunMode.RESET_ENCODERS);
        rightFront.setMode(RunMode.RESET_ENCODERS);
        while ((leftFront.getCurrentPosition()!=0)&&(rightFront.getCurrentPosition()!=0)){
        }
        state++;
        break;
      case 1:
        leftFront.setMode(RunMode.RUN_USING_ENCODERS);
        rightFront.setMode(RunMode.RUN_USING_ENCODERS);
        leftFront.setPower(1);
        rightFront.setPower(1);
        if(leftFront.getCurrentPosition()>2000)
        {
          leftFront.setPower(0);
          rightFront.setPower(0);
          state++;
        }
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
        break;
      default:
        break;
    }
  }
}

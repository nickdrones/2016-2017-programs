package com.qualcomm.ftcrobotcontroller.opmodes;

  //      import com.qualcomm.ftccommon.DbgLog;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
  //      import com.qualcomm.robotcore.hardware.Servo;
  //      import com.qualcomm.robotcore.util.Range;


public class Test_Board_stateMachine_3July extends OpMode

{
    private int v_state = 0;
    private int counter =0;
    int start_position=0;

    public Test_Board_stateMachine_3July()

   {
   }

    @Override public void init (){
        // Initialization steps
        DcMotor m_left;
        DcMotor m_right;
        m_right = hardwareMap.dcMotor.get("m_right");
        m_left = hardwareMap.dcMotor.get("m_left");

        int counter = 0;
        m_left.setMode(RunMode.RESET_ENCODERS);
        m_right.setMode(RunMode.RESET_ENCODERS);

        while(m_right.getCurrentPosition()!=0) {   //wait for reset and count while waiting.

            telemetry.addData("'Resetting encoder Counter=", counter);
            counter++; }

         telemetry.addData("Current Position", m_right.getCurrentPosition()); //should show zero if reset finished
        telemetry.addData("Target Position", m_right.getTargetPosition());//should be zero  at this point
        telemetry.addData("Current Mode", m_right.getMode());//default is run using encoders
        telemetry.addData("Current power", m_right.getPower());//default is zero
        telemetry.addData("Current Direction", m_right.getDirection());//default is forward

       } //init
//--------------------------------------------------------------------------

    @Override public void start ()

    {


    } // start

    //--------------------------------------------------------------------------

    @Override public void loop ()

    {

       // int start_position;   //to keep track of motor current position before run to position
        DcMotor m_left;
        DcMotor m_right;
        m_right = hardwareMap.dcMotor.get("m_right");
        m_left = hardwareMap.dcMotor.get("m_left");


      switch (v_state)
        {case 0:

            m_left.setMode(RunMode.RESET_ENCODERS);
            m_right.setMode(RunMode.RESET_ENCODERS);

            v_state++; //transition to next state for next loop
           break;

        case 1:         //Drive forward

            m_left.setDirection(DcMotor.Direction.FORWARD);                  //setting the motors on the left to be reversed so positive power makes them turn backward.
            m_right.setDirection(DcMotor.Direction.REVERSE);

            m_left.setPower(.25);
            m_right.setPower(.25);

            m_right.setMode(RunMode.RUN_TO_POSITION);
            m_left.setMode(RunMode.RUN_TO_POSITION);

            m_left.setTargetPosition(5280);
            m_right.setTargetPosition(5280);
            counter++;

            if (m_right.getCurrentPosition()> 5265)
            {
               m_left.setPower(0);
               m_right.setPower(0);
               v_state++;
            }
            break;

         case 2:   //turn
             m_left.setDirection(DcMotor.Direction.FORWARD);                  //setting the motors on the left to be reversed so positive power makes them turn backward.
             m_right.setDirection(DcMotor.Direction.FORWARD);

             m_left.setPower(.25);
             m_right.setPower(.25);

             m_right.setMode(RunMode.RUN_TO_POSITION);
             m_left.setMode(RunMode.RUN_TO_POSITION);

             start_position = m_right.getCurrentPosition();
             m_left.setTargetPosition(start_position+3200);
             m_right.setTargetPosition(start_position+3200);

             if (m_right.getCurrentPosition()> 3185)
             {
                 m_left.setPower(0);
                 m_right.setPower(0);
                 v_state++;
            }
            break;

         case 3:  //straight
             m_left.setDirection(DcMotor.Direction.FORWARD);                  //setting the motors on the left to be reversed so positive power makes them turn backward.
             m_right.setDirection(DcMotor.Direction.REVERSE);

             m_left.setPower(.25);
             m_right.setPower(.25);

             m_right.setMode(RunMode.RUN_TO_POSITION);
             m_left.setMode(RunMode.RUN_TO_POSITION);

             start_position = m_right.getCurrentPosition();
             m_left.setTargetPosition(start_position+8200);
             m_right.setTargetPosition(start_position+8200);

             if (m_right.getCurrentPosition()> 8185)
             {
                 m_left.setPower(0);
                 m_right.setPower(0);
              //   v_state++;
             }
            break;

        default:  //do nothing.  Auto is complete.

            break;
        }

        //
        // Send telemetry data to the driver station.
        //
      //  update_telemetry (); // Update common telemetry
        telemetry.addData ("18", "State: " + v_state);
        telemetry.addData("Current Position", m_right.getCurrentPosition()); //should show zero if reset finished
        telemetry.addData("Target Position", m_right.getTargetPosition());//should be zero  at this point
        telemetry.addData("Current Mode", m_right.getMode());//default is run using encoders
        telemetry.addData("Current power", m_right.getPower());//default is zero
        telemetry.addData("Current Direction", m_right.getDirection());//default is forward


    } // loop



} //

package org.firstinspires.ftc.teamcode.Programs;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Programs.Hardware69;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="6901 : Start Drive", group="Your MOM")
public class StartDrive extends OpMode{

    /* Declare OpMode members. */
    Hardware69 robot       = new Hardware69(); // use the class created to define a Pushbot's hardware
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.5 ;                 // sets rate to move servo

    double armPos = -0.62;
    double slow = 0.3;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Sup Nerd, ", "Get Ready");    //
    }


    //* Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY

    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    /*
    8
public void setPosition(double pos){
    robot.Arm.setTargetPosition((int) (robot.TICKS_PER_REV * pos));
}
    public double getPosition(){
        return robot.Arm.getCurrentPosition()/robot.TICKS_PER_REV;
    }
    */
    @Override
    public void loop() {
        if (gamepad1.right_trigger >= 0.3) {
            slow = 1;
        }
        else{
            slow = 0.3;
        }

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        double leftPower;
        double rightPower;



        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;




        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        robot.leftDrive.setPower(leftPower * slow);
        robot.backleftDrive.setPower(leftPower * slow);
        robot.backrightDrive.setPower(rightPower * slow);
        robot.rightDrive.setPower(rightPower * slow);

        double intakeSpeed = 0.8;

        //   intake.SetPower (intakeSpeed);
        // Use gamepad left & right trigger to open and close the intake
        // CLose
        if (gamepad1.right_bumper) {
        if (gamepad1.right_bumper) {
            robot.intake.setPower(1);
        }
        if (gamepad1.left_bumper) {
            robot.intake.setPower(-1);
        }
        if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
            robot.intake.setPower(0);
        }



        // Open

        // Move both servos to new position.  Assume servos are mirror image of each other.
    /*
    clawOffset = Range.clip(clawOffset, -1, 1);
    robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
    robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

     */

        // Use gamepad buttons to move the arm up (Y) and down (A)
    /*
    if (gamepad2.left_stick_y >= 0.1)
        robot.Arm.setPower(robot.ARM_UP_POWER);
    else if (gamepad2.left_stick_y < -0.1)
        robot.Arm.setPower(robot.ARM_DOWN_POWER);
    else
        robot.Arm.setPower(0.0);

     */

        // Carousel

        if (gamepad2.right_bumper) {
            robot.Carousel.setPower(0.3);
        }
        if (gamepad2.left_bumper) {
            robot.Carousel.setPower(-0.3);
        }
        if (!gamepad2.right_bumper && !gamepad2.left_bumper) {
            robot.Carousel.setPower(0);
        }

        //arm

           if (gamepad2.left_stick_y) {
            robot.Arm.setPower(-0.4);
        }
        if (-gamepad2.left_stick_y) {
            robot.Arm.setPower(0);
        }
        if (!gamepad2.left_stick_y && !-gamepad2.left_stick_y) {
            robot.Arm.setPower(-0.2);
        }








/*
    robot.Arm.setVelocity(gamepad2.left_stick_y);
    final double ARM_SPEED = 500;
    robot.Arm.setVelocity(gamepad2.left_stick_y * ARM_SPEED);
*/

        // arm postiion code
        // High
/*
    if (gamepad2.y) {
        robot.Arm.setPower(0.5);
        setPosition(-0.62);
        robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    // Mid
    if (gamepad2.b) {
        robot.Arm.setPower(0.5);
        setPosition(-0.26);
        robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    // Low
    if (gamepad2.a) {
        robot.Arm.setPower(0.5);
        setPosition(-0.2);
        robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    // Intake Position
    if (gamepad2.x) {
        setPosition(0);
        robot.Arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }
    if (gamepad2.dpad_up) {
        armPos = armPos - 0.005;
        robot.Arm.setPower(0.5);
        setPosition(armPos);
        robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    if (gamepad2.dpad_down) {
        armPos = armPos + 0.005;
        robot.Arm.setPower(0.5);
        setPosition(armPos);
        robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

*/

        // Send telemetry message to signify robot running;
        telemetry.addData("claw",  "Offset = %.2f", clawOffset);
        //telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("arm motor position divided by tick per rev", robot.Arm.getCurrentPosition()/383.6);
        telemetry.addData("Arm position: ", armPos);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}

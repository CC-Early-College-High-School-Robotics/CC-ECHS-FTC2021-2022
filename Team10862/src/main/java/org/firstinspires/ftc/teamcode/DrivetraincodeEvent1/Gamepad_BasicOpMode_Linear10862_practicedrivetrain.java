/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.DrivetraincodeEvent1;
//Package is telling us where to find this specific code

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="10862TeleOp", group="Linear Opmode")
public class Gamepad_BasicOpMode_Linear10862_practicedrivetrain extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    // Declare OpMode members.
    //null = no value
    private DcMotorEx leftFront = null;
    private DcMotorEx leftRear = null;
    private DcMotorEx rightFront = null;
    private DcMotorEx rightRear = null;
    private DcMotor carouselMotor = null;
    private DcMotor otherMotor = null;
    //Servos
    private Servo rightServo = null;
    private Servo leftServo = null;

    @Override
    @SuppressWarnings("FieldCanBeLocal")

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront  = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear  = hardwareMap.get(DcMotorEx.class, "rightRear");
        carouselMotor = hardwareMap.get(DcMotor.class, "carouselMotor");
        otherMotor = hardwareMap.get(DcMotor.class, "otherMotor");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
        leftServo = hardwareMap.get(Servo.class, "leftServo");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // Drivetrain Motor Directions
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftRear.setDirection(DcMotorEx.Direction.REVERSE);
        rightFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightRear.setDirection(DcMotorEx.Direction.FORWARD);
        carouselMotor.setDirection(DcMotorEx.Direction.REVERSE);
        otherMotor.setDirection(DcMotor.Direction.FORWARD);
        //Servo Directions
        rightServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);

        //rightServo Starting Position = 0
        //leftServo Starting Position = 0
        double posRight = 0;
        double posLeft = 0;



        // Adjusting the Zero Power Behavior changes how the motors behaved when a
        // Power of 0 is applied.

        //Drivetrain Zero Power Behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        carouselMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        otherMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //Do servos have something similar to ZeroPowerBehavior?

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            //double = 2 digits after the decimal
            double leftPower;
            double rightPower;

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.

            //Drivetrain
            double drive = -gamepad2.left_stick_y;
            double turn = -gamepad2.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);
            //Wouldn't the right power also be drive+turn?

            // Send calculated power to wheels
            leftFront.setPower(leftPower*0.8);
            leftRear.setPower(leftPower*0.8);
            rightFront.setPower(rightPower*0.8);
            rightRear.setPower(rightPower*0.8);

            //CarouselMotor
            if (gamepad2.right_bumper) {
                carouselMotor.setPower(0.5);
            }
            if (gamepad2.left_bumper) {
                carouselMotor.setPower(-0.4);
            }
            // ! means not
            if (!gamepad2.left_bumper && !gamepad2.right_bumper) {
                carouselMotor.setPower(0);
            }

            //100 Milliseconds = 1 Second
            //Servo

            posLeft = Math.min(Math.max(posLeft, 0), 0.45);
            leftServo.setPosition(Math.min(Math.max(posLeft, 0), 0.45));

            posRight = Math.min(Math.max(posRight, 0), 0.45);
            rightServo.setPosition(Math.min(Math.max(posRight, 0), 0.45));
            /* && means AND
            || means OR */

            if (gamepad1.right_trigger > 0) {
                posRight += gamepad1.right_trigger / 500;
                posLeft += gamepad1.right_trigger / 500;
            }
            if (gamepad1.left_trigger > 0) {
                posRight -= gamepad1.left_trigger / 500;
                posLeft -= gamepad1.left_trigger / 500;
            }

            if (gamepad1.dpad_up) {
                    posRight -= 0.0005;
                    posLeft -= 0.0005;
            }
            if (gamepad1.dpad_down) {
                posRight += 0.001;
                posLeft += 0.001;
            }

            if (gamepad1.y) {
                posRight = 0.0;
                posLeft = 0.0;
            }
            if (gamepad1.b) {
                posRight = 0.15;
                posLeft = 0.15;
            }
            if (gamepad1.x) {
                posRight = 0.28;
                posLeft = 0.28;
            }
            if (gamepad1.a) {
                posRight = 0.45;
                posLeft = 0.45;
            }

            //otherMotor (Intake/Outtake)
            //intake
            if (gamepad1.right_bumper) {
                otherMotor.setPower(1);
            }
            //outtake
            if (gamepad1.left_bumper) {
                otherMotor.setPower(-0.45);
            }

            // ! means not
            if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
                otherMotor.setPower(0);
            }
        }




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)");
            telemetry.update();
        }
    }


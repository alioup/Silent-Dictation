/******************************************************************************\
* Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
* Leap Motion proprietary and confidential. Not for distribution.              *
* Use subject to the terms of the Leap Motion SDK Agreement available at       *
* https://developer.leapmotion.com/sdk_agreement, or another agreement         *
* between Leap Motion and you, your company or other organization.             *
\******************************************************************************/

import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class SampleListener extends Listener {
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    public boolean thumbIn(Controller controller){
     	 Frame frame = controller.frame();
     	 Hand right= frame.hands().rightmost();
      	Finger thumb=right.fingers().get(0);
      	Bone tip=thumb.bone(Bone.Type.TYPE_DISTAL);
      	Vector tipDir=tip.direction();
      	//Vector palmDir=right.palmPosition();
     	 if (tipDir.getX()< .5 && tipDir.getX()>-.6 && tipDir.getX()!=0){
     		 //System.out.print("T"); 
     		 return true;
     	 }
     	 else{
     		 return false;
     	 }
      }
   public boolean indexOut(Controller controller){
    	 Frame frame = controller.frame();
    	 Hand right= frame.hands().rightmost();
     	Finger index=right.fingers().get(1);
     	Bone tip=index.bone(Bone.Type.TYPE_DISTAL);
     	Vector tipDir=tip.direction();
     	//Vector palmDir=right.palmPosition();
    	 if (tipDir.getX()<.7 && tipDir.getX()>.2 && tipDir.getX()!=0){
    		//System.out.print("I");
    		 return true;
    	 }
    		 else{ 
        		 return false;
        	 }
     }
   
   public boolean pinkyOut(Controller controller){
   	 Frame frame = controller.frame();
   	 Hand right= frame.hands().rightmost();
    	Finger pinky=right.fingers().get(4);
    	Bone tip=pinky.bone(Bone.Type.TYPE_DISTAL);
    	Vector tipDir=tip.direction();
    	//Vector palmDir=right.palmPosition();
   	 if (tipDir.getX()< .65 && tipDir.getX()>-.1 && tipDir.getX()!=0){
   		// System.out.print("P");
   		 return true;
   	 } 
   		 else{
       		 return false;
       	 }
    }
  
   public boolean middleOut(Controller controller){
   	 Frame frame = controller.frame();
   	 Hand right= frame.hands().rightmost();
    	Finger middle=right.fingers().get(2);
    	Bone tip=middle.bone(Bone.Type.TYPE_DISTAL);
    	Vector tipDir=tip.direction();
    	//Vector palmDir=right.palmPosition();
   	 if (tipDir.getX()< .8 && tipDir.getX()>.4 && tipDir.getX()!=0){
   		// System.out.print("M ");
   		 return true;
   	 }
   		 else{
       		 return false;
       	 }
    }
   public void checkLetter(Controller controller){
   	if (indexOut(controller) && !middleOut(controller)&&!pinkyOut(controller) && thumbIn(controller)){
   		countG++;
   		System.out.println("g");
   	}
   	if (pinkyOut(controller) && thumbIn(controller)&& !middleOut(controller)&& !indexOut(controller)){
   		countI++;
   		System.out.println("i");
   	}
   	if (indexOut(controller) && middleOut(controller)&& thumbIn(controller)&& !pinkyOut(controller)){
   		countR++;
   		System.out.println("r");
   	}
   	if (indexOut(controller) && !thumbIn(controller)&& !middleOut(controller) && !pinkyOut(controller)){
   		countL++;
   		System.out.println("l");
   	}
   	if (!indexOut(controller) && !middleOut(controller)&& !pinkyOut(controller) && thumbIn(controller)){
   		countS++;
   		System.out.println("s");
   	}
      if (indexOut(controller)&& middleOut(controller)&& pinkyOut(controller)&& !thumbIn(controller)){
   	   countX++;	
      }
      /*if (countX>3){
   	   lastLetter=' ';
   	   countG=0;
   	   countI=0;
   	   countR=0;
   	   countL=0;
   	   countS=0;
   	   countX=0;
      }
      if (countG>15 && lastLetter==' '){
   	   System.out.println("G");
   	   lastLetter='G';
   	   countG=0;
   	   countI=0;
   	   countR=0;
   	   countL=0;
   	   countS=0;
   	   countX=0;
      }
      if (countI>5 && lastLetter==' '){
   	   System.out.println("I");
   	   lastLetter='i';
   	   countG=0;
   	   countI=0;
   	   countR=0;
   	   countL=0;
   	   countS=0;
   	   countX=0;
      }
      if (countR>5 && lastLetter==' '){
   	   System.out.println("R");
   	   lastLetter='r';
   	   countG=0;
   	   countI=0;
   	   countR=0;
   	   countL=0;
   	   countS=0;
   	   countX=0;
      }
      if (countL>5 && lastLetter==' '){
   	   System.out.println("L");
   	   lastLetter='l';
   	   countG=0;
   	   countI=0;
   	   countR=0;
   	   countL=0;
   	   countS=0;
   	   countX=0;
      }
      if (countS>5 && lastLetter==' '){
   	   System.out.println("S");
   	   lastLetter='s';
   	   countG=0;
   	   countI=0;
   	   countR=0;
   	   countL=0;
   	   countS=0;
   	   countX=0;
      }*/
   }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        System.out.println("Frame id: " + frame.id()
                         + ", timestamp: " + frame.timestamp()
                         + ", hands: " + frame.hands().count()
                         + ", fingers: " + frame.fingers().count()
                         + ", tools: " + frame.tools().count()
                         + ", gestures " + frame.gestures().count());

        //Get hands
        for(Hand hand : frame.hands()) {
            String handType = hand.isLeft() ? "Left hand" : "Right hand";
            System.out.println("  " + handType + ", id: " + hand.id()
                             + ", palm position: " + hand.palmPosition());

            // Get the hand's normal vector and direction
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();

            // Calculate the hand's pitch, roll, and yaw angles
            System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
                             + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
                             + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");

            // Get arm bone
            Arm arm = hand.arm();
            System.out.println("  Arm direction: " + arm.direction()
                             + ", wrist position: " + arm.wristPosition()
                             + ", elbow position: " + arm.elbowPosition());

            // Get fingers
            for (Finger finger : hand.fingers()) {
                System.out.println("    " + finger.type() + ", id: " + finger.id()
                                 + ", length: " + finger.length()
                                 + "mm, width: " + finger.width() + "mm");

                //Get Bones
                for(Bone.Type boneType : Bone.Type.values()) {
                    Bone bone = finger.bone(boneType);
                    System.out.println("      " + bone.type()
                                     + " bone, start: " + bone.prevJoint()
                                     + ", end: " + bone.nextJoint()
                                     + ", direction: " + bone.direction());
                }
            }
        }

        // Get tools
        for(Tool tool : frame.tools()) {
            System.out.println("  Tool id: " + tool.id()
                             + ", position: " + tool.tipPosition()
                             + ", direction: " + tool.direction());
        }

        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);

            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    CircleGesture circle = new CircleGesture(gesture);

                    // Calculate clock direction using the angle between circle normal and pointable
                    String clockwiseness;
                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
                        // Clockwise if angle is less than 90 degrees
                        clockwiseness = "clockwise";
                    } else {
                        clockwiseness = "counterclockwise";
                    }

                    // Calculate angle swept since last frame
                    double sweptAngle = 0;
                    if (circle.state() != State.STATE_START) {
                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                    }

                    System.out.println("  Circle id: " + circle.id()
                               + ", " + circle.state()
                               + ", progress: " + circle.progress()
                               + ", radius: " + circle.radius()
                               + ", angle: " + Math.toDegrees(sweptAngle)
                               + ", " + clockwiseness);
                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    System.out.println("  Swipe id: " + swipe.id()
                               + ", " + swipe.state()
                               + ", position: " + swipe.position()
                               + ", direction: " + swipe.direction()
                               + ", speed: " + swipe.speed());
                    break;
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    System.out.println("  Screen Tap id: " + screenTap.id()
                               + ", " + screenTap.state()
                               + ", position: " + screenTap.position()
                               + ", direction: " + screenTap.direction());
                    break;
                case TYPE_KEY_TAP:
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    System.out.println("  Key Tap id: " + keyTap.id()
                               + ", " + keyTap.state()
                               + ", position: " + keyTap.position()
                               + ", direction: " + keyTap.direction());
                    break;
                default:
                    System.out.println("Unknown gesture type.");
                    break;
            }
        }

        if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
            System.out.println();
        }
    }
}

class Sample {
    public static void main(String[] args) {
        // Create a sample listener and controller
        SampleListener listener = new SampleListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }
}

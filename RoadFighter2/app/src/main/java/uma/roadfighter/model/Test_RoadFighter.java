package uma.roadfighter.model;

import java.util.Random;

import Util.Utilities;
import eventb_prelude.BOOL;
import eventb_prelude.BRelation;
import eventb_prelude.BSet;
import eventb_prelude.Enumerated;
import eventb_prelude.Pair;

public class Test_RoadFighter {

    public static Integer random_USER_LANE = 0;
    public static Integer random_USER_CAR = 1;

    static Random rnd = new Random();
    static Integer max_size_BSet = 10;
    Integer min_integer = Utilities.min_integer;
    Integer max_integer = Utilities.max_integer;

    public Integer GenerateRandomInteger() {
        BSet<Integer> S = new BSet<Integer>(
                new Enumerated(min_integer, max_integer)
        );
        /** User defined code that reflects axioms and theorems: Begin **/

        /** User defined code that reflects axioms and theorems: End **/

        return (Integer) S.toArray()[rnd.nextInt(S.size())];
    }

    public boolean GenerateRandomBoolean() {
        boolean res = (Boolean) BOOL.instance.toArray()[rnd.nextInt(2)];

        /** User defined code that reflects axioms and theorems: Begin **/

        /** User defined code that reflects axioms and theorems: End **/

        return res;
    }


    public BSet<Integer> GenerateRandomIntegerBSet() {
        int size = rnd.nextInt(max_size_BSet);
        BSet<Integer> S = new BSet<Integer>();
        while (S.size() != size) {
            S.add(GenerateRandomInteger());
        }

        /** User defined code that reflects axioms and theorems: Begin **/

        /** User defined code that reflects axioms and theorems: End **/

        return S;
    }

    public BSet<Boolean> GenerateRandomBooleanBSet() {
        int size = rnd.nextInt(2);
        BSet<Boolean> res = new BSet<Boolean>();
        if (size == 0) {
            res = new BSet<Boolean>(GenerateRandomBoolean());
        } else {
            res = new BSet<Boolean>(true, false);
        }

        /** User defined code that reflects axioms and theorems: Begin **/

        /** User defined code that reflects axioms and theorems: End **/

        return res;
    }

    public BRelation<Integer, Integer> GenerateRandomBRelation() {
        BRelation<Integer, Integer> res = new BRelation<Integer, Integer>();
        int size = rnd.nextInt(max_size_BSet);
        while (res.size() != size) {
            res.add(
                    new Pair<Integer, Integer>(GenerateRandomInteger(), GenerateRandomInteger()));
        }
        /** User defined code that reflects axioms and theorems: Begin **/

        /** User defined code that reflects axioms and theorems: End **/

        return res;
    }

    public static void main(String[] args) {
        Test_RoadFighter test = new Test_RoadFighter();

        /** User defined code that reflects axioms and theorems: Begin **/

        //random_USER_LANE = test.GenerateRandomInteger();
        //random_USER_CAR = test.GenerateRandomInteger();

        RoadFighter machine = new RoadFighter();

		/* ~~~~~ Adding a line ~~~~~ */
        Integer Finish_ADD_LANE = 2850;
        Integer PTex = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_ADD_LANE = random_USER_LANE;
        Integer Left_ADD_LANE = 25;
        Integer Right_ADD_LANE = 100;
        Integer F_ADD_LANE = 3;

        Integer H_ADD_OBJECT = 3072;
        Integer W_ADD_OBJECT = 128;
        Integer X_ADD_OBJECT = 0;
        Integer Y_ADD_OBJECT = 0;

        machine.evt_ADD_OBJECT.run_ADD_OBJECT(PTex, H_ADD_OBJECT, Lane_ADD_LANE, W_ADD_OBJECT, X_ADD_OBJECT, Y_ADD_OBJECT);
        machine.evt_ADD_LANE.run_ADD_LANE(Finish_ADD_LANE, Lane_ADD_LANE, Left_ADD_LANE, Right_ADD_LANE, F_ADD_LANE);
        /* ^^^^^ Finished adding a line ^^^^^ */


		/* ~~~~~ Adding a car ~~~~~ */
        Integer Car_ADD_CAR = random_USER_CAR;
        Integer Desc_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer H_ADD_CAR = 25;
        Integer W_ADD_CAR = 25;
        Integer X_ADD_CAR = 75;
        Integer Y_ADD_CAR = 40;
        Integer F_ADD_CAR = 3;
        Integer M_ADD_CAR = 38;

        machine.evt_ADD_CAR.run_ADD_CAR(Car_ADD_CAR, Desc_ADD_CAR, H_ADD_CAR, W_ADD_CAR, X_ADD_CAR, Y_ADD_CAR, F_ADD_CAR, M_ADD_CAR);
        /* ^^^^^ Finished adding a car ^^^^^ */


		/* ~~~~~ Adding two obstacles at the same positions ~~~~~ */

        Integer Desc_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer H_ADD_OBSTACLE = 20;
        Integer Obs_ADD_OBSTACLE = 3;
        Integer W_ADD_OBSTACLE = 20;
        Integer X_ADD_OBSTACLE = 70;
        Integer Y_ADD_OBSTACLE = 70;

        machine.evt_ADD_OBSTACLE.run_ADD_OBSTACLE(Desc_ADD_OBSTACLE, H_ADD_OBSTACLE, Obs_ADD_OBSTACLE, W_ADD_OBSTACLE, X_ADD_OBSTACLE, Y_ADD_OBSTACLE);

        Desc_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Obs_ADD_OBSTACLE = 4;

        machine.evt_ADD_OBSTACLE.run_ADD_OBSTACLE(Desc_ADD_OBSTACLE, H_ADD_OBSTACLE, Obs_ADD_OBSTACLE, W_ADD_OBSTACLE, X_ADD_OBSTACLE, Y_ADD_OBSTACLE);

        if (machine.get_obstacles().contains(4)) {
            throw new AssertionError("Error -- obstacles are added at same position");
        } else {
            System.out.println("Obstacles test is passed");
        }

		/* ^^^^^ Finished adding two obstacles at the same positions ^^^^^ */


		/* ~~~~~ Checking velocity to be less than maximum ~~~~~ */
        Integer startVelocity = 10;
        Integer maxVelocity = 50;
        Integer moreThanMaxVelocity = 100;

        machine.evt_SET_VEL.run_SET_VEL(random_USER_CAR, startVelocity);
        machine.evt_SET_MAXVEL.run_SET_MAXVEL(random_USER_CAR, maxVelocity);

        machine.evt_SET_VEL.run_SET_VEL(random_USER_CAR, moreThanMaxVelocity);

        if (machine.get_vel().apply(random_USER_CAR).equals(moreThanMaxVelocity)
                && !(machine.get_vel().apply(random_USER_CAR).equals(startVelocity))) {
            throw new AssertionError("Error -- velocity is more than maximum");
        } else {
            System.out.println("Velocity test is passed");
        }
        /* ^^^^^ Finished checking velocity to be less than maximum ^^^^^ */


		/* ~~~~~ Checking car to be deleted ~~~~~ */
        machine.evt_DELETE_CAR.run_DELETE_CAR(random_USER_CAR);

        if (machine.get_cars().contains(random_USER_CAR) || machine.get_objects().contains(random_USER_CAR)) {
            throw new AssertionError("Error -- car was not deleted");
        } else {
            System.out.println("Car deletion test is passed");
        }
        /* ^^^^^ Finished checking car to be deleted ^^^^^ */

        /** User defined code that reflects axioms and theorems: End **/


        int n = 24; //the number of events in the machine
        //Init values for parameters in event: SET_LEFT_BORDER
        Integer B_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_RIGHT_BORDER
        Integer B_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: DELETE_LANE
        Integer Lane_DELETE_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_FINISH_LINE
        Integer F_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_POS
        Integer Obj_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer X_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Y_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: ADD_LANE
        Finish_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Lane_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Left_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Right_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        F_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: ADD_OBJECT
        Integer Desc_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        H_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Obj_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        W_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        X_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Y_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: ADD_CAR
        Car_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Desc_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        H_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        W_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        X_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Y_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        F_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        M_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: DELETE_CAR
        Integer Car_DELETE_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: ADD_OBSTACLE
        Desc_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        H_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Obs_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        W_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        X_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Y_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: DELETE_OBSTACLE
        Integer Obs_DELETE_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_VEL
        Integer Car_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer V_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_ACC
        Integer A_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Car_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_MAXVEL
        Integer Car_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer M_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: UPDATE_VEL
        Integer Car_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Elapsed_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: APPLY_FRICTION
        Integer Elapsed_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Obj_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: CAR_RESET
        Integer Car_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: WALL_COLLISION
        Integer Car_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: OBJECT_COLLISION
        Integer MyCar_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Obj_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: FINISHED_LANE
        Integer Car_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Lane_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: UPDATE_POS
        Integer Elapsed_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer Obj_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: UPDATE_SCORE
        Integer Car_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer S_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_DRIFT
        Integer Car_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        Integer D_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        //Init values for parameters in event: SET_ZERO_VEL
        Integer Car_SET_ZERO_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));

        boolean leftBorderIsSet = false;
        boolean rightBorderIsSet = false;
        boolean lineWasDeleted = false;
        boolean finishWasSet = false;
        boolean positionWasSet = false;
        boolean laneWasAdded = false;
        boolean objectWasAdded = false;
        boolean carWasAdded = false;
        boolean carWasDeleted = false;
        boolean obstacleWasAdded = false;
        boolean obstacleWasDeleted = false;
        boolean velocityWasSet = false;
        boolean accelerationWasSet = false;
        boolean maxVelocityWasSet = false;
        boolean velocityWasUpdated = false;
        boolean frictionWasApplied = false;
        boolean carWasReset = false;
        boolean wallCollided = false;
        boolean objectCollided = false;
        boolean laneFinished = false;
        boolean positionUpdated = false;
        boolean scoreUpdated = false;
        boolean driftWasSet = false;
        boolean zeroVelocityWasSet = false;

        while (!(leftBorderIsSet && rightBorderIsSet && lineWasDeleted && finishWasSet
                && positionWasSet && laneWasAdded && objectWasAdded && carWasAdded
                && carWasDeleted && obstacleWasAdded && obstacleWasDeleted && velocityWasSet
                && accelerationWasSet && maxVelocityWasSet && velocityWasUpdated && frictionWasApplied
                && carWasReset && wallCollided && objectCollided && laneFinished && positionUpdated
                && scoreUpdated && driftWasSet && zeroVelocityWasSet)) {
            switch (rnd.nextInt(n)) {
                case 0:
                    if (!leftBorderIsSet && machine.evt_SET_LEFT_BORDER.guard_SET_LEFT_BORDER(B_SET_LEFT_BORDER, Lane_SET_LEFT_BORDER)) {
                        machine.evt_SET_LEFT_BORDER.run_SET_LEFT_BORDER(B_SET_LEFT_BORDER, Lane_SET_LEFT_BORDER);
                        if (machine.get_left_border().apply(Lane_SET_LEFT_BORDER).equals(B_SET_LEFT_BORDER)) {
                            leftBorderIsSet = true;
                        } else {
                            throw new AssertionError("Left border was not set");
                        }
                    }
                    break;

                case 1:
                    if (!rightBorderIsSet && machine.evt_SET_RIGHT_BORDER.guard_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER, Lane_SET_RIGHT_BORDER)) {
                        machine.evt_SET_RIGHT_BORDER.run_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER, Lane_SET_RIGHT_BORDER);
                        if (machine.get_right_border().apply(Lane_SET_RIGHT_BORDER).equals(B_SET_RIGHT_BORDER)) {
                            rightBorderIsSet = true;
                        } else {
                            throw new AssertionError("Right border was not set");
                        }
                    }
                    break;
                case 2:
                    if (!lineWasDeleted && machine.evt_DELETE_LANE.guard_DELETE_LANE(Lane_DELETE_LANE)) {
                        machine.evt_DELETE_LANE.run_DELETE_LANE(Lane_DELETE_LANE);
                        if (!machine.get_lanes().has(Lane_DELETE_LANE) || !machine.get_objects().has(Lane_DELETE_LANE)) {
                            lineWasDeleted = true;
                        } else {
                            throw new AssertionError("Line was not deleted");
                        }
                    }
                    break;
                case 3:
                    if (!finishWasSet && machine.evt_SET_FINISH_LINE.guard_SET_FINISH_LINE(F_SET_FINISH_LINE, Lane_SET_FINISH_LINE)) {
                        machine.evt_SET_FINISH_LINE.run_SET_FINISH_LINE(F_SET_FINISH_LINE, Lane_SET_FINISH_LINE);
                        if (machine.get_finish_line().apply(Lane_SET_FINISH_LINE).equals(F_SET_FINISH_LINE)) {
                            finishWasSet = true;
                        } else {
                            throw new AssertionError("Finish was not set");
                        }
                    }
                    break;
                case 4:
                    if (!positionWasSet && machine.evt_SET_POS.guard_SET_POS(Obj_SET_POS, X_SET_POS, Y_SET_POS)) {
                        machine.evt_SET_POS.run_SET_POS(Obj_SET_POS, X_SET_POS, Y_SET_POS);
                        if (machine.get_posX().apply(Obj_SET_POS).equals(X_SET_POS) && machine.get_posY().apply(Obj_SET_POS).equals(Y_SET_POS)) {
                            positionWasSet = true;
                        } else {
                            throw new AssertionError("Position was not set");
                        }
                    }
                    break;
                case 5:
                    if (!laneWasAdded && machine.evt_ADD_LANE.guard_ADD_LANE(Finish_ADD_LANE, Lane_ADD_LANE, Left_ADD_LANE, Right_ADD_LANE, F_ADD_LANE)) {
                        machine.evt_ADD_LANE.run_ADD_LANE(Finish_ADD_LANE, Lane_ADD_LANE, Left_ADD_LANE, Right_ADD_LANE, F_ADD_LANE);
                        if (machine.get_lanes().has(Lane_ADD_LANE)) {
                            laneWasAdded = true;
                        } else {
                            throw new AssertionError("Line was not added");
                        }
                    }
                    break;
                case 6:
                    if (!objectWasAdded && machine.evt_ADD_OBJECT.guard_ADD_OBJECT(Desc_ADD_OBJECT, H_ADD_OBJECT, Obj_ADD_OBJECT, W_ADD_OBJECT, X_ADD_OBJECT, Y_ADD_OBJECT)) {
                        machine.evt_ADD_OBJECT.run_ADD_OBJECT(Desc_ADD_OBJECT, H_ADD_OBJECT, Obj_ADD_OBJECT, W_ADD_OBJECT, X_ADD_OBJECT, Y_ADD_OBJECT);
                        if (machine.get_objects().has(Obj_ADD_OBJECT)) {
                            objectWasAdded = true;
                        } else {
                            throw new AssertionError("Object was not added");
                        }
                    }
                    break;
                case 7:
                    if (!carWasAdded && machine.evt_ADD_CAR.guard_ADD_CAR(Car_ADD_CAR, Desc_ADD_CAR, H_ADD_CAR, W_ADD_CAR, X_ADD_CAR, Y_ADD_CAR, F_ADD_CAR, M_ADD_CAR)) {
                        machine.evt_ADD_CAR.run_ADD_CAR(Car_ADD_CAR, Desc_ADD_CAR, H_ADD_CAR, W_ADD_CAR, X_ADD_CAR, Y_ADD_CAR, F_ADD_CAR, M_ADD_CAR);
                        if (machine.get_cars().has(Car_ADD_CAR)) {
                            carWasAdded = true;
                        } else {
                            throw new AssertionError("Car was not added");
                        }
                    }
                    break;
                case 8:
                    if (!carWasDeleted && machine.evt_DELETE_CAR.guard_DELETE_CAR(Car_DELETE_CAR)) {
                        machine.evt_DELETE_CAR.run_DELETE_CAR(Car_DELETE_CAR);
                        if (!machine.get_cars().has(Car_DELETE_CAR) || !machine.get_objects().has(Car_DELETE_CAR)) {
                            carWasDeleted = true;
                        } else {
                            throw new AssertionError("Car was not deleted");
                        }
                    }
                    break;
                case 9:
                    if (!obstacleWasAdded && machine.evt_ADD_OBSTACLE.guard_ADD_OBSTACLE(Desc_ADD_OBSTACLE, H_ADD_OBSTACLE, Obs_ADD_OBSTACLE, W_ADD_OBSTACLE, X_ADD_OBSTACLE, Y_ADD_OBSTACLE)) {
                        machine.evt_ADD_OBSTACLE.run_ADD_OBSTACLE(Desc_ADD_OBSTACLE, H_ADD_OBSTACLE, Obs_ADD_OBSTACLE, W_ADD_OBSTACLE, X_ADD_OBSTACLE, Y_ADD_OBSTACLE);
                        if (machine.get_obstacles().has(Obs_ADD_OBSTACLE)) {
                            obstacleWasAdded = true;
                        } else {
                            throw new AssertionError("Obstacle was not added");
                        }
                    }
                    break;
                case 10:
                    if (!obstacleWasDeleted && machine.evt_DELETE_OBSTACLE.guard_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE)) {
                        machine.evt_DELETE_OBSTACLE.run_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE);
                        if (!machine.get_obstacles().has(Obs_DELETE_OBSTACLE) || !machine.get_objects().has(Obs_DELETE_OBSTACLE)) {
                            obstacleWasDeleted = true;
                        } else {
                            throw new AssertionError("Obstacle was not deleted");
                        }
                    }
                    break;
                case 11:
                    if (!velocityWasSet && machine.evt_SET_VEL.guard_SET_VEL(Car_SET_VEL, V_SET_VEL)) {
                        machine.evt_SET_VEL.run_SET_VEL(Car_SET_VEL, V_SET_VEL);
                        if (machine.get_vel().apply(Car_SET_VEL).equals(V_SET_VEL)) {
                            velocityWasSet = true;
                        } else {
                            throw new AssertionError("Velocity was not set");
                        }
                    }
                    break;
                case 12:
                    if (!accelerationWasSet && machine.evt_SET_ACC.guard_SET_ACC(A_SET_ACC, Car_SET_ACC)) {
                        machine.evt_SET_ACC.run_SET_ACC(A_SET_ACC, Car_SET_ACC);
                        if (machine.get_acc().apply(Car_SET_ACC).equals(A_SET_ACC)) {
                            accelerationWasSet = true;
                        } else {
                            throw new AssertionError("Acceleration was not set");
                        }
                    }
                    break;
                case 13:
                    if (!maxVelocityWasSet && machine.evt_SET_MAXVEL.guard_SET_MAXVEL(Car_SET_MAXVEL, M_SET_MAXVEL)) {
                        machine.evt_SET_MAXVEL.run_SET_MAXVEL(Car_SET_MAXVEL, M_SET_MAXVEL);
                        if (machine.get_maxvel().apply(Car_SET_MAXVEL).equals(M_SET_MAXVEL)) {
                            maxVelocityWasSet = true;
                        } else {
                            throw new AssertionError("Maximum velocity was not set");
                        }
                    }
                    break;
                case 14:
                    if (!velocityWasUpdated && machine.evt_UPDATE_VEL.guard_UPDATE_VEL(Car_UPDATE_VEL, Elapsed_UPDATE_VEL)) {
                        BRelation<Integer, Integer> vel_tmp = machine.get_vel();
                        machine.evt_UPDATE_VEL.run_UPDATE_VEL(Car_UPDATE_VEL, Elapsed_UPDATE_VEL);
                        if (machine.get_vel().apply(Car_UPDATE_VEL).equals(new Integer(vel_tmp.apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / 1000)))) {
                            velocityWasUpdated = true;
                        } else {
                            throw new AssertionError("Velocity was not updated");
                        }
                    }
                    break;
                case 15:
                    if (!frictionWasApplied && machine.evt_APPLY_FRICTION.guard_APPLY_FRICTION(Elapsed_APPLY_FRICTION, Lane_APPLY_FRICTION, Obj_APPLY_FRICTION)) {
                        BRelation<Integer, Integer> vel_tmp = machine.get_vel();
                        machine.evt_APPLY_FRICTION.run_APPLY_FRICTION(Elapsed_APPLY_FRICTION, Lane_APPLY_FRICTION, Obj_APPLY_FRICTION);
                        if (machine.get_friction().apply(Obj_APPLY_FRICTION).equals(vel_tmp.apply(Obj_APPLY_FRICTION) - new Integer(machine.get_friction().apply(Lane_APPLY_FRICTION) * 5 * Elapsed_APPLY_FRICTION))) {
                            frictionWasApplied = true;
                        } else {
                            throw new AssertionError("Friction was not applied");
                        }
                    }
                    break;
                case 16:
                    if (!carWasReset && machine.evt_CAR_RESET.guard_CAR_RESET(Car_CAR_RESET, Lane_CAR_RESET)) {
                        machine.evt_CAR_RESET.run_CAR_RESET(Car_CAR_RESET, Lane_CAR_RESET);
                        if (machine.get_posX().apply(Car_CAR_RESET).equals(machine.get_left_border().apply(Lane_CAR_RESET) + machine.get_right_border().apply(Lane_CAR_RESET) - machine.get_left_border().apply(Lane_CAR_RESET) / 2) &&
                                machine.get_drift().apply(Car_CAR_RESET).equals(0) &&
                                machine.get_vel().apply(Car_CAR_RESET).equals(0) &&
                                machine.get_acc().apply(Car_CAR_RESET).equals(0) &&
                                machine.get_collision().apply(Car_CAR_RESET).equals(false)) {
                            carWasReset = true;
                        } else {
                            throw new AssertionError("Car was not reset");
                        }
                    }
                    break;
                case 17:
                    if (!wallCollided && machine.evt_WALL_COLLISION.guard_WALL_COLLISION(Car_WALL_COLLISION, Lane_WALL_COLLISION)) {
                        machine.evt_WALL_COLLISION.run_WALL_COLLISION(Car_WALL_COLLISION, Lane_WALL_COLLISION);
                        if (machine.get_collision().apply(Car_WALL_COLLISION).equals((!((machine.get_left_border().apply(Lane_WALL_COLLISION)).compareTo(new Integer(machine.get_posX().apply(Car_WALL_COLLISION) - new Integer(machine.get_width().apply(Car_WALL_COLLISION) / 2))) <= 0 && (machine.get_right_border().apply(Lane_WALL_COLLISION)).compareTo(new Integer(machine.get_posX().apply(Car_WALL_COLLISION) + new Integer(machine.get_width().apply(Car_WALL_COLLISION) / 2))) >= 0)))) {
                            wallCollided = true;
                        } else {
                            throw new AssertionError("Wall was not collided");
                        }
                    }
                    break;
                case 18:
                    if (!objectCollided && machine.evt_OBJECT_COLLISION.guard_OBJECT_COLLISION(MyCar_OBJECT_COLLISION, Obj_OBJECT_COLLISION)) {
                        machine.evt_OBJECT_COLLISION.run_OBJECT_COLLISION(MyCar_OBJECT_COLLISION, Obj_OBJECT_COLLISION);
                        if (machine.get_collision().apply(MyCar_OBJECT_COLLISION).equals(((new Integer(machine.get_posX().apply(MyCar_OBJECT_COLLISION) - machine.get_posX().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(MyCar_OBJECT_COLLISION) - machine.get_posY().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posX().apply(Obj_OBJECT_COLLISION) - machine.get_posX().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj_OBJECT_COLLISION) - machine.get_posY().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0))
                                &&
                                machine.get_active().apply(Obj_OBJECT_COLLISION).equals((!((new Integer(machine.get_posX().apply(MyCar_OBJECT_COLLISION) - machine.get_posX().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(MyCar_OBJECT_COLLISION) - machine.get_posY().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posX().apply(Obj_OBJECT_COLLISION) - machine.get_posX().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj_OBJECT_COLLISION) - machine.get_posY().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0)))) {
                            objectCollided = true;
                        } else {
                            throw new AssertionError("Object did not collide");
                        }
                    }
                    break;
                case 19:
                    if (!laneFinished && machine.evt_FINISHED_LANE.guard_FINISHED_LANE(Car_FINISHED_LANE, Lane_FINISHED_LANE)) {
                        machine.evt_FINISHED_LANE.run_FINISHED_LANE(Car_FINISHED_LANE, Lane_FINISHED_LANE);
                        if (machine.get_finished().apply(Car_FINISHED_LANE).equals(((machine.get_posY().apply(Car_FINISHED_LANE)).compareTo(machine.get_finish_line().apply(Lane_FINISHED_LANE)) > 0))) {
                            laneFinished = true;
                        } else {
                            throw new AssertionError("Lane is not finished");
                        }
                    }
                    break;
                case 20:
                    if (!positionUpdated && machine.evt_UPDATE_POS.guard_UPDATE_POS(Elapsed_UPDATE_POS, Obj_UPDATE_POS)) {

                        BRelation<Integer, Integer> posX_tmp = machine.get_posX();
                        BRelation<Integer, Integer> posY_tmp = machine.get_posY();

                        machine.evt_UPDATE_POS.run_UPDATE_POS(Elapsed_UPDATE_POS, Obj_UPDATE_POS);

                        if (machine.get_posX().apply(Obj_UPDATE_POS).equals(new Integer(posX_tmp.apply(Obj_UPDATE_POS) + new Integer(new Integer(machine.get_drift().apply(Obj_UPDATE_POS) * Elapsed_UPDATE_POS * 50) / 1000)))
                                &&
                                machine.get_posY().apply(Obj_UPDATE_POS).equals(new Integer(posY_tmp.apply(Obj_UPDATE_POS) + new Integer(new Integer(machine.get_vel().apply(Obj_UPDATE_POS) * Elapsed_UPDATE_POS) / 1000)))) {
                            positionUpdated = true;
                        } else {
                            throw new AssertionError("Position was not updated");
                        }
                    }
                    break;
                case 21:
                    if (!scoreUpdated && machine.evt_UPDATE_SCORE.guard_UPDATE_SCORE(Car_UPDATE_SCORE, S_UPDATE_SCORE)) {
                        BRelation<Integer, Integer> score_tmp = machine.get_score();
                        machine.evt_UPDATE_SCORE.run_UPDATE_SCORE(Car_UPDATE_SCORE, S_UPDATE_SCORE);
                        if (machine.get_score().apply(Car_UPDATE_SCORE).equals(new Integer(score_tmp.apply(Car_UPDATE_SCORE) + S_UPDATE_SCORE))) {
                            scoreUpdated = true;
                        } else {
                            throw new AssertionError("Score was not updated");
                        }
                    }
                    break;
                case 22:
                    if (!driftWasSet && machine.evt_SET_DRIFT.guard_SET_DRIFT(Car_SET_DRIFT, D_SET_DRIFT)) {
                        machine.evt_SET_DRIFT.run_SET_DRIFT(Car_SET_DRIFT, D_SET_DRIFT);
                        if (machine.get_drift().apply(Car_SET_DRIFT).equals(D_SET_DRIFT)) {
                            driftWasSet = true;
                        } else {
                            throw new AssertionError("Drift was not set");
                        }
                    }
                    break;
                case 23:
                    if (!zeroVelocityWasSet && machine.evt_SET_ZERO_VEL.guard_SET_ZERO_VEL(Car_SET_ZERO_VEL)) {
                        machine.evt_SET_ZERO_VEL.run_SET_ZERO_VEL(Car_SET_ZERO_VEL);
                        if (machine.get_vel().apply(Car_SET_ZERO_VEL).equals(0)) {
                            zeroVelocityWasSet = true;
                        } else {
                            throw new AssertionError("Zero velocity was not set");
                        }
                    }
                    break;
            }
            B_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            B_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_DELETE_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            F_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obj_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            X_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Y_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Finish_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Left_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Right_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            F_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Desc_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            H_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obj_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            W_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            X_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Y_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Desc_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            H_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            W_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            X_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Y_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            F_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            M_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_DELETE_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Desc_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            H_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obs_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            W_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            X_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Y_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obs_DELETE_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            V_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            A_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            M_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Elapsed_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Elapsed_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obj_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            MyCar_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obj_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Lane_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Elapsed_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Obj_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            S_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            D_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
            Car_SET_ZERO_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1, Utilities.max_integer))));
        }

        System.out.println("All tests passed");
    }

}

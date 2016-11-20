package RoadFighter_sequential;
import java.util.Random;
import Util.Utilities;
import eventb_prelude.*;

public class Test_RoadFighter{

	public static Integer random_USER_LANE;
	public static Integer random_USER_CAR;

	static Random rnd = new Random();
	static Integer max_size_BSet = 10;
	Integer min_integer = Utilities.min_integer;
	Integer max_integer = Utilities.max_integer;

	public Integer GenerateRandomInteger(){
		BSet<Integer> S =  new BSet<Integer>(
				new Enumerated(min_integer, max_integer)
				);
		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return (Integer) S.toArray()[rnd.nextInt(S.size())];
	}

	public boolean GenerateRandomBoolean(){
		boolean res = (Boolean) BOOL.instance.toArray()[rnd.nextInt(2)];

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}


	public BSet<Integer> GenerateRandomIntegerBSet(){
		int size = rnd.nextInt(max_size_BSet);
		BSet<Integer> S = new BSet<Integer>();
		while (S.size() != size){
			S.add(GenerateRandomInteger());
		}

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return S;
	}

	public BSet<Boolean> GenerateRandomBooleanBSet(){
		int size = rnd.nextInt(2);
		BSet<Boolean> res = new BSet<Boolean>();
		if (size == 0){
			res = new BSet<Boolean>(GenerateRandomBoolean());
		}else{
			res = new BSet<Boolean>(true,false);
		}

		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}

	public BRelation<Integer,Integer> GenerateRandomBRelation(){
		BRelation<Integer,Integer> res = new BRelation<Integer,Integer>();
		int size = rnd.nextInt(max_size_BSet);
		while (res.size() != size){
			res.add(
					new Pair<Integer, Integer>(GenerateRandomInteger(), GenerateRandomInteger()));
		}
		/** User defined code that reflects axioms and theorems: Begin **/

		/** User defined code that reflects axioms and theorems: End **/

		return res;
	}

	public static void main(String[] args){
		Test_RoadFighter test = new Test_RoadFighter();

		/** User defined code that reflects axioms and theorems: Begin **/
		test.random_USER_LANE = test.GenerateRandomInteger();
		test.random_USER_CAR = test.GenerateRandomInteger();
		/** User defined code that reflects axioms and theorems: End **/

		RoadFighter machine = new RoadFighter();
		int n = 24; //the number of events in the machine
		//Init values for parameters in event: SET_LEFT_BORDER
		Integer B_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_RIGHT_BORDER
		Integer B_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: DELETE_LANE
		Integer Lane_DELETE_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_FINISH_LINE
		Integer F_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_POS
		Integer Obj_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_LANE
		Integer Finish_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Left_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Right_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer F_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_OBJECT
		Integer Desc_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer H_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer W_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_CAR
		Integer Car_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Desc_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer H_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer W_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer F_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer M_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: DELETE_CAR
		Integer Car_DELETE_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: ADD_OBSTACLE
		Integer Desc_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer H_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obs_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer W_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer X_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Y_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: DELETE_OBSTACLE
		Integer Obs_DELETE_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_VEL
		Integer Car_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer V_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_ACC
		Integer A_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Car_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_MAXVEL
		Integer Car_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer M_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: UPDATE_VEL
		Integer Car_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Elapsed_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: APPLY_FRICTION
		Integer Elapsed_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: CAR_RESET
		Integer Car_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: WALL_COLLISION
		Integer Car_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: OBJECT_COLLISION
		Integer MyCar_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: FINISHED_LANE
		Integer Car_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Lane_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: UPDATE_POS
		Integer Elapsed_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer Obj_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: UPDATE_SCORE
		Integer Car_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer S_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_DRIFT
		Integer Car_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		Integer D_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		//Init values for parameters in event: SET_ZERO_VEL
		Integer Car_SET_ZERO_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));

		while (true){
			switch (rnd.nextInt(n)){
			case 0: if (machine.evt_SET_LEFT_BORDER.guard_SET_LEFT_BORDER(B_SET_LEFT_BORDER,Lane_SET_LEFT_BORDER))
				machine.evt_SET_LEFT_BORDER.run_SET_LEFT_BORDER(B_SET_LEFT_BORDER,Lane_SET_LEFT_BORDER); break;
			case 1: if (machine.evt_SET_RIGHT_BORDER.guard_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER,Lane_SET_RIGHT_BORDER))
				machine.evt_SET_RIGHT_BORDER.run_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER,Lane_SET_RIGHT_BORDER); break;
			case 2: if (machine.evt_DELETE_LANE.guard_DELETE_LANE(Lane_DELETE_LANE))
				machine.evt_DELETE_LANE.run_DELETE_LANE(Lane_DELETE_LANE); break;
			case 3: if (machine.evt_SET_FINISH_LINE.guard_SET_FINISH_LINE(F_SET_FINISH_LINE,Lane_SET_FINISH_LINE))
				machine.evt_SET_FINISH_LINE.run_SET_FINISH_LINE(F_SET_FINISH_LINE,Lane_SET_FINISH_LINE); break;
			case 4: if (machine.evt_SET_POS.guard_SET_POS(Obj_SET_POS,X_SET_POS,Y_SET_POS))
				machine.evt_SET_POS.run_SET_POS(Obj_SET_POS,X_SET_POS,Y_SET_POS); break;
			case 5: if (machine.evt_ADD_LANE.guard_ADD_LANE(Finish_ADD_LANE,Lane_ADD_LANE,Left_ADD_LANE,Right_ADD_LANE,F_ADD_LANE))
				machine.evt_ADD_LANE.run_ADD_LANE(Finish_ADD_LANE,Lane_ADD_LANE,Left_ADD_LANE,Right_ADD_LANE,F_ADD_LANE); break;
			case 6: if (machine.evt_ADD_OBJECT.guard_ADD_OBJECT(Desc_ADD_OBJECT,H_ADD_OBJECT,Obj_ADD_OBJECT,W_ADD_OBJECT,X_ADD_OBJECT,Y_ADD_OBJECT))
				machine.evt_ADD_OBJECT.run_ADD_OBJECT(Desc_ADD_OBJECT,H_ADD_OBJECT,Obj_ADD_OBJECT,W_ADD_OBJECT,X_ADD_OBJECT,Y_ADD_OBJECT); break;
			case 7: if (machine.evt_ADD_CAR.guard_ADD_CAR(Car_ADD_CAR,Desc_ADD_CAR,H_ADD_CAR,W_ADD_CAR,X_ADD_CAR,Y_ADD_CAR,F_ADD_CAR,M_ADD_CAR))
				machine.evt_ADD_CAR.run_ADD_CAR(Car_ADD_CAR,Desc_ADD_CAR,H_ADD_CAR,W_ADD_CAR,X_ADD_CAR,Y_ADD_CAR,F_ADD_CAR,M_ADD_CAR); break;
			case 8: if (machine.evt_DELETE_CAR.guard_DELETE_CAR(Car_DELETE_CAR))
				machine.evt_DELETE_CAR.run_DELETE_CAR(Car_DELETE_CAR); break;
			case 9: if (machine.evt_ADD_OBSTACLE.guard_ADD_OBSTACLE(Desc_ADD_OBSTACLE,H_ADD_OBSTACLE,Obs_ADD_OBSTACLE,W_ADD_OBSTACLE,X_ADD_OBSTACLE,Y_ADD_OBSTACLE))
				machine.evt_ADD_OBSTACLE.run_ADD_OBSTACLE(Desc_ADD_OBSTACLE,H_ADD_OBSTACLE,Obs_ADD_OBSTACLE,W_ADD_OBSTACLE,X_ADD_OBSTACLE,Y_ADD_OBSTACLE); break;
			case 10: if (machine.evt_DELETE_OBSTACLE.guard_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE))
				machine.evt_DELETE_OBSTACLE.run_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE); break;
			case 11: if (machine.evt_SET_VEL.guard_SET_VEL(Car_SET_VEL,V_SET_VEL))
				machine.evt_SET_VEL.run_SET_VEL(Car_SET_VEL,V_SET_VEL); break;
			case 12: if (machine.evt_SET_ACC.guard_SET_ACC(A_SET_ACC,Car_SET_ACC))
				machine.evt_SET_ACC.run_SET_ACC(A_SET_ACC,Car_SET_ACC); break;
			case 13: if (machine.evt_SET_MAXVEL.guard_SET_MAXVEL(Car_SET_MAXVEL,M_SET_MAXVEL))
				machine.evt_SET_MAXVEL.run_SET_MAXVEL(Car_SET_MAXVEL,M_SET_MAXVEL); break;
			case 14: if (machine.evt_UPDATE_VEL.guard_UPDATE_VEL(Car_UPDATE_VEL,Elapsed_UPDATE_VEL))
				machine.evt_UPDATE_VEL.run_UPDATE_VEL(Car_UPDATE_VEL,Elapsed_UPDATE_VEL); break;
			case 15: if (machine.evt_APPLY_FRICTION.guard_APPLY_FRICTION(Elapsed_APPLY_FRICTION,Lane_APPLY_FRICTION,Obj_APPLY_FRICTION))
				machine.evt_APPLY_FRICTION.run_APPLY_FRICTION(Elapsed_APPLY_FRICTION,Lane_APPLY_FRICTION,Obj_APPLY_FRICTION); break;
			case 16: if (machine.evt_CAR_RESET.guard_CAR_RESET(Car_CAR_RESET,Lane_CAR_RESET))
				machine.evt_CAR_RESET.run_CAR_RESET(Car_CAR_RESET,Lane_CAR_RESET); break;
			case 17: if (machine.evt_WALL_COLLISION.guard_WALL_COLLISION(Car_WALL_COLLISION,Lane_WALL_COLLISION))
				machine.evt_WALL_COLLISION.run_WALL_COLLISION(Car_WALL_COLLISION,Lane_WALL_COLLISION); break;
			case 18: if (machine.evt_OBJECT_COLLISION.guard_OBJECT_COLLISION(MyCar_OBJECT_COLLISION,Obj_OBJECT_COLLISION))
				machine.evt_OBJECT_COLLISION.run_OBJECT_COLLISION(MyCar_OBJECT_COLLISION,Obj_OBJECT_COLLISION); break;
			case 19: if (machine.evt_FINISHED_LANE.guard_FINISHED_LANE(Car_FINISHED_LANE,Lane_FINISHED_LANE))
				machine.evt_FINISHED_LANE.run_FINISHED_LANE(Car_FINISHED_LANE,Lane_FINISHED_LANE); break;
			case 20: if (machine.evt_UPDATE_POS.guard_UPDATE_POS(Elapsed_UPDATE_POS,Obj_UPDATE_POS))
				machine.evt_UPDATE_POS.run_UPDATE_POS(Elapsed_UPDATE_POS,Obj_UPDATE_POS); break;
			case 21: if (machine.evt_UPDATE_SCORE.guard_UPDATE_SCORE(Car_UPDATE_SCORE,S_UPDATE_SCORE))
				machine.evt_UPDATE_SCORE.run_UPDATE_SCORE(Car_UPDATE_SCORE,S_UPDATE_SCORE); break;
			case 22: if (machine.evt_SET_DRIFT.guard_SET_DRIFT(Car_SET_DRIFT,D_SET_DRIFT))
				machine.evt_SET_DRIFT.run_SET_DRIFT(Car_SET_DRIFT,D_SET_DRIFT); break;
			case 23: if (machine.evt_SET_ZERO_VEL.guard_SET_ZERO_VEL(Car_SET_ZERO_VEL))
				machine.evt_SET_ZERO_VEL.run_SET_ZERO_VEL(Car_SET_ZERO_VEL); break;
			}
			B_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_SET_LEFT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			B_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_SET_RIGHT_BORDER = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_DELETE_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			F_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_SET_FINISH_LINE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y_SET_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Finish_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Left_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Right_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			F_ADD_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Desc_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			H_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			W_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y_ADD_OBJECT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Desc_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			H_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			W_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			F_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			M_ADD_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_DELETE_CAR = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Desc_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			H_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obs_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			W_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			X_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Y_ADD_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obs_DELETE_OBSTACLE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			V_SET_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			A_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_SET_ACC = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			M_SET_MAXVEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Elapsed_UPDATE_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Elapsed_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj_APPLY_FRICTION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_CAR_RESET = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_WALL_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			MyCar_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj_OBJECT_COLLISION = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Lane_FINISHED_LANE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Elapsed_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Obj_UPDATE_POS = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			S_UPDATE_SCORE = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			D_SET_DRIFT = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Car_SET_ZERO_VEL = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
		}
	}

}

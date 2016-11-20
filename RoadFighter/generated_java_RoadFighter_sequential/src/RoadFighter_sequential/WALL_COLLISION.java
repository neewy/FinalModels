package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class WALL_COLLISION{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public WALL_COLLISION(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_WALL_COLLISION) && machine.get_lanes().has(Lane_WALL_COLLISION)); */
	public /*@ pure */ boolean guard_WALL_COLLISION( Integer Car_WALL_COLLISION, Integer Lane_WALL_COLLISION) {
		return (machine.get_cars().has(Car_WALL_COLLISION) && machine.get_lanes().has(Lane_WALL_COLLISION));
	}

	/*@ public normal_behavior
		requires guard_WALL_COLLISION(Car_WALL_COLLISION,Lane_WALL_COLLISION);
		assignable machine.collision;
		ensures guard_WALL_COLLISION(Car_WALL_COLLISION,Lane_WALL_COLLISION) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_WALL_COLLISION,(!((machine.get_left_border().apply(Lane_WALL_COLLISION)).compareTo(new Integer(machine.get_posX().apply(Car_WALL_COLLISION) - new Integer(machine.get_width().apply(Car_WALL_COLLISION) / 2))) <= 0 && (machine.get_right_border().apply(Lane_WALL_COLLISION)).compareTo(new Integer(machine.get_posX().apply(Car_WALL_COLLISION) + new Integer(machine.get_width().apply(Car_WALL_COLLISION) / 2))) >= 0)))))))); 
	 also
		requires !guard_WALL_COLLISION(Car_WALL_COLLISION,Lane_WALL_COLLISION);
		assignable \nothing;
		ensures true; */
	public void run_WALL_COLLISION( Integer Car_WALL_COLLISION, Integer Lane_WALL_COLLISION){
		if(guard_WALL_COLLISION(Car_WALL_COLLISION,Lane_WALL_COLLISION)) {
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();

			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_WALL_COLLISION,(!((machine.get_left_border().apply(Lane_WALL_COLLISION)).compareTo(new Integer(machine.get_posX().apply(Car_WALL_COLLISION) - new Integer(machine.get_width().apply(Car_WALL_COLLISION) / 2))) <= 0 && (machine.get_right_border().apply(Lane_WALL_COLLISION)).compareTo(new Integer(machine.get_posX().apply(Car_WALL_COLLISION) + new Integer(machine.get_width().apply(Car_WALL_COLLISION) / 2))) >= 0)))))));

			System.out.println("WALL_COLLISION executed Car_WALL_COLLISION: " + Car_WALL_COLLISION + " Lane_WALL_COLLISION: " + Lane_WALL_COLLISION + " ");
		}
	}

}

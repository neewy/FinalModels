package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class APPLY_FRICTION{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public APPLY_FRICTION(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Obj_APPLY_FRICTION) && NAT.instance.has(Elapsed_APPLY_FRICTION) && machine.get_lanes().has(Lane_APPLY_FRICTION) && (machine.get_acc().apply(Obj_APPLY_FRICTION)).compareTo(new Integer(1)) < 0 && (new Integer(machine.get_vel().apply(Obj_APPLY_FRICTION) - new Integer(machine.get_friction().apply(Lane_APPLY_FRICTION) * new Integer(5) * Elapsed_APPLY_FRICTION))).compareTo(new Integer(0)) >= 0); */
	public /*@ pure */ boolean guard_APPLY_FRICTION( Integer Elapsed_APPLY_FRICTION, Integer Lane_APPLY_FRICTION, Integer Obj_APPLY_FRICTION) {
		return (machine.get_cars().has(Obj_APPLY_FRICTION) && NAT.instance.has(Elapsed_APPLY_FRICTION) && machine.get_lanes().has(Lane_APPLY_FRICTION) && (machine.get_acc().apply(Obj_APPLY_FRICTION)).compareTo(new Integer(1)) < 0 && (new Integer(machine.get_vel().apply(Obj_APPLY_FRICTION) - new Integer(machine.get_friction().apply(Lane_APPLY_FRICTION) * new Integer(5) * Elapsed_APPLY_FRICTION))).compareTo(new Integer(0)) >= 0);
	}

	/*@ public normal_behavior
		requires guard_APPLY_FRICTION(Elapsed_APPLY_FRICTION,Lane_APPLY_FRICTION,Obj_APPLY_FRICTION);
		assignable machine.vel;
		ensures guard_APPLY_FRICTION(Elapsed_APPLY_FRICTION,Lane_APPLY_FRICTION,Obj_APPLY_FRICTION) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_APPLY_FRICTION,new Integer(machine.get_vel().apply(Obj_APPLY_FRICTION) - new Integer(machine.get_friction().apply(Lane_APPLY_FRICTION) * 5 * Elapsed_APPLY_FRICTION)))))))); 
	 also
		requires !guard_APPLY_FRICTION(Elapsed_APPLY_FRICTION,Lane_APPLY_FRICTION,Obj_APPLY_FRICTION);
		assignable \nothing;
		ensures true; */
	public void run_APPLY_FRICTION( Integer Elapsed_APPLY_FRICTION, Integer Lane_APPLY_FRICTION, Integer Obj_APPLY_FRICTION){
		if(guard_APPLY_FRICTION(Elapsed_APPLY_FRICTION,Lane_APPLY_FRICTION,Obj_APPLY_FRICTION)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_APPLY_FRICTION,new Integer(vel_tmp.apply(Obj_APPLY_FRICTION) - new Integer(machine.get_friction().apply(Lane_APPLY_FRICTION) * 5 * Elapsed_APPLY_FRICTION)))))));

			System.out.println("APPLY_FRICTION executed Elapsed_APPLY_FRICTION: " + Elapsed_APPLY_FRICTION + " Lane_APPLY_FRICTION: " + Lane_APPLY_FRICTION + " Obj_APPLY_FRICTION: " + Obj_APPLY_FRICTION + " ");
		}
	}

}

package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_VEL{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_VEL(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_SET_VEL) && NAT.instance.has(V_SET_VEL) && (V_SET_VEL).compareTo(machine.get_maxvel().apply(Car_SET_VEL)) <= 0); */
	public /*@ pure */ boolean guard_SET_VEL( Integer Car_SET_VEL, Integer V_SET_VEL) {
		return (machine.get_cars().has(Car_SET_VEL) && NAT.instance.has(V_SET_VEL) && (V_SET_VEL).compareTo(machine.get_maxvel().apply(Car_SET_VEL)) <= 0);
	}

	/*@ public normal_behavior
		requires guard_SET_VEL(Car_SET_VEL,V_SET_VEL);
		assignable machine.vel;
		ensures guard_SET_VEL(Car_SET_VEL,V_SET_VEL) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_VEL,V_SET_VEL)))))); 
	 also
		requires !guard_SET_VEL(Car_SET_VEL,V_SET_VEL);
		assignable \nothing;
		ensures true; */
	public void run_SET_VEL( Integer Car_SET_VEL, Integer V_SET_VEL){
		if(guard_SET_VEL(Car_SET_VEL,V_SET_VEL)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_VEL,V_SET_VEL)))));

			System.out.println("SET_VEL executed Car_SET_VEL: " + Car_SET_VEL + " V_SET_VEL: " + V_SET_VEL + " ");
		}
	}

}

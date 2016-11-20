package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_VEL{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_VEL(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_UPDATE_VEL) && NAT.instance.has(Elapsed_UPDATE_VEL) && (new Integer(machine.get_vel().apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / new Integer(1000)))).compareTo(new Integer(0)) >= 0 && (new Integer(machine.get_vel().apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / new Integer(1000)))).compareTo(machine.get_maxvel().apply(Car_UPDATE_VEL)) <= 0); */
	public /*@ pure */ boolean guard_UPDATE_VEL( Integer Car_UPDATE_VEL, Integer Elapsed_UPDATE_VEL) {
		return (machine.get_cars().has(Car_UPDATE_VEL) && NAT.instance.has(Elapsed_UPDATE_VEL) && (new Integer(machine.get_vel().apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / new Integer(1000)))).compareTo(new Integer(0)) >= 0 && (new Integer(machine.get_vel().apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / new Integer(1000)))).compareTo(machine.get_maxvel().apply(Car_UPDATE_VEL)) <= 0);
	}

	/*@ public normal_behavior
		requires guard_UPDATE_VEL(Car_UPDATE_VEL,Elapsed_UPDATE_VEL);
		assignable machine.vel;
		ensures guard_UPDATE_VEL(Car_UPDATE_VEL,Elapsed_UPDATE_VEL) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_UPDATE_VEL,new Integer(machine.get_vel().apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / 1000)))))))); 
	 also
		requires !guard_UPDATE_VEL(Car_UPDATE_VEL,Elapsed_UPDATE_VEL);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_VEL( Integer Car_UPDATE_VEL, Integer Elapsed_UPDATE_VEL){
		if(guard_UPDATE_VEL(Car_UPDATE_VEL,Elapsed_UPDATE_VEL)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_UPDATE_VEL,new Integer(vel_tmp.apply(Car_UPDATE_VEL) + new Integer(new Integer(machine.get_acc().apply(Car_UPDATE_VEL) * Elapsed_UPDATE_VEL) / 1000)))))));

			System.out.println("UPDATE_VEL executed Car_UPDATE_VEL: " + Car_UPDATE_VEL + " Elapsed_UPDATE_VEL: " + Elapsed_UPDATE_VEL + " ");
		}
	}

}

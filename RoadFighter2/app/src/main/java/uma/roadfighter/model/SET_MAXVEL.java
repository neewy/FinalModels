package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_MAXVEL{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_MAXVEL(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_SET_MAXVEL) && NAT.instance.has(M_SET_MAXVEL) && (M_SET_MAXVEL).compareTo(machine.get_vel().apply(Car_SET_MAXVEL)) >= 0); */
	public /*@ pure */ boolean guard_SET_MAXVEL( Integer Car_SET_MAXVEL, Integer M_SET_MAXVEL) {
		return (machine.get_cars().has(Car_SET_MAXVEL) && NAT.instance.has(M_SET_MAXVEL) && (M_SET_MAXVEL).compareTo(machine.get_vel().apply(Car_SET_MAXVEL)) >= 0);
	}

	/*@ public normal_behavior
		requires guard_SET_MAXVEL(Car_SET_MAXVEL,M_SET_MAXVEL);
		assignable machine.maxvel;
		ensures guard_SET_MAXVEL(Car_SET_MAXVEL,M_SET_MAXVEL) &&  machine.get_maxvel().equals(\old((machine.get_maxvel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_MAXVEL,M_SET_MAXVEL)))))); 
	 also
		requires !guard_SET_MAXVEL(Car_SET_MAXVEL,M_SET_MAXVEL);
		assignable \nothing;
		ensures true; */
	public void run_SET_MAXVEL( Integer Car_SET_MAXVEL, Integer M_SET_MAXVEL){
		if(guard_SET_MAXVEL(Car_SET_MAXVEL,M_SET_MAXVEL)) {
			BRelation<Integer,Integer> maxvel_tmp = machine.get_maxvel();

			machine.set_maxvel((maxvel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_MAXVEL,M_SET_MAXVEL)))));

			System.out.println("SET_MAXVEL executed Car_SET_MAXVEL: " + Car_SET_MAXVEL + " M_SET_MAXVEL: " + M_SET_MAXVEL + " ");
		}
	}

}

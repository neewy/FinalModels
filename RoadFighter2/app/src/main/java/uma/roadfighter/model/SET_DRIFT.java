package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_DRIFT{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_DRIFT(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_SET_DRIFT) && new BSet<Integer>(new Integer(0),new Integer(-1),new Integer(1)).has(D_SET_DRIFT)); */
	public /*@ pure */ boolean guard_SET_DRIFT( Integer Car_SET_DRIFT, Integer D_SET_DRIFT) {
		return (machine.get_cars().has(Car_SET_DRIFT) && new BSet<Integer>(new Integer(0),new Integer(-1),new Integer(1)).has(D_SET_DRIFT));
	}

	/*@ public normal_behavior
		requires guard_SET_DRIFT(Car_SET_DRIFT,D_SET_DRIFT);
		assignable machine.drift;
		ensures guard_SET_DRIFT(Car_SET_DRIFT,D_SET_DRIFT) &&  machine.get_drift().equals(\old((machine.get_drift().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_DRIFT,D_SET_DRIFT)))))); 
	 also
		requires !guard_SET_DRIFT(Car_SET_DRIFT,D_SET_DRIFT);
		assignable \nothing;
		ensures true; */
	public void run_SET_DRIFT( Integer Car_SET_DRIFT, Integer D_SET_DRIFT){
		if(guard_SET_DRIFT(Car_SET_DRIFT,D_SET_DRIFT)) {
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();

			machine.set_drift((drift_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_DRIFT,D_SET_DRIFT)))));

			System.out.println("SET_DRIFT executed Car_SET_DRIFT: " + Car_SET_DRIFT + " D_SET_DRIFT: " + D_SET_DRIFT + " ");
		}
	}

}

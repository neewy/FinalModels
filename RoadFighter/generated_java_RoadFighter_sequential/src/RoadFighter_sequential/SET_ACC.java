package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_ACC{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_ACC(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_SET_ACC) && NAT.instance.has(A_SET_ACC)); */
	public /*@ pure */ boolean guard_SET_ACC( Integer A_SET_ACC, Integer Car_SET_ACC) {
		return (machine.get_cars().has(Car_SET_ACC) && NAT.instance.has(A_SET_ACC));
	}

	/*@ public normal_behavior
		requires guard_SET_ACC(A_SET_ACC,Car_SET_ACC);
		assignable machine.acc;
		ensures guard_SET_ACC(A_SET_ACC,Car_SET_ACC) &&  machine.get_acc().equals(\old((machine.get_acc().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_ACC,A_SET_ACC)))))); 
	 also
		requires !guard_SET_ACC(A_SET_ACC,Car_SET_ACC);
		assignable \nothing;
		ensures true; */
	public void run_SET_ACC( Integer A_SET_ACC, Integer Car_SET_ACC){
		if(guard_SET_ACC(A_SET_ACC,Car_SET_ACC)) {
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();

			machine.set_acc((acc_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_SET_ACC,A_SET_ACC)))));

			System.out.println("SET_ACC executed A_SET_ACC: " + A_SET_ACC + " Car_SET_ACC: " + Car_SET_ACC + " ");
		}
	}

}

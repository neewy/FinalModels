package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_RIGHT_BORDER{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_RIGHT_BORDER(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_lanes().has(Lane_SET_RIGHT_BORDER) && INT.instance.has(B_SET_RIGHT_BORDER)); */
	public /*@ pure */ boolean guard_SET_RIGHT_BORDER( Integer B_SET_RIGHT_BORDER, Integer Lane_SET_RIGHT_BORDER) {
		return (machine.get_lanes().has(Lane_SET_RIGHT_BORDER) && INT.instance.has(B_SET_RIGHT_BORDER));
	}

	/*@ public normal_behavior
		requires guard_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER,Lane_SET_RIGHT_BORDER);
		assignable machine.right_border;
		ensures guard_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER,Lane_SET_RIGHT_BORDER) &&  machine.get_right_border().equals(\old((machine.get_right_border().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_SET_RIGHT_BORDER,B_SET_RIGHT_BORDER)))))); 
	 also
		requires !guard_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER,Lane_SET_RIGHT_BORDER);
		assignable \nothing;
		ensures true; */
	public void run_SET_RIGHT_BORDER( Integer B_SET_RIGHT_BORDER, Integer Lane_SET_RIGHT_BORDER){
		if(guard_SET_RIGHT_BORDER(B_SET_RIGHT_BORDER,Lane_SET_RIGHT_BORDER)) {
			BRelation<Integer,Integer> right_border_tmp = machine.get_right_border();

			machine.set_right_border((right_border_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_SET_RIGHT_BORDER,B_SET_RIGHT_BORDER)))));

			System.out.println("SET_RIGHT_BORDER executed B_SET_RIGHT_BORDER: " + B_SET_RIGHT_BORDER + " Lane_SET_RIGHT_BORDER: " + Lane_SET_RIGHT_BORDER + " ");
		}
	}

}

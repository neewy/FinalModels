package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_LEFT_BORDER{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_LEFT_BORDER(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_lanes().has(Lane_SET_LEFT_BORDER) && INT.instance.has(B_SET_LEFT_BORDER)); */
	public /*@ pure */ boolean guard_SET_LEFT_BORDER( Integer B_SET_LEFT_BORDER, Integer Lane_SET_LEFT_BORDER) {
		return (machine.get_lanes().has(Lane_SET_LEFT_BORDER) && INT.instance.has(B_SET_LEFT_BORDER));
	}

	/*@ public normal_behavior
		requires guard_SET_LEFT_BORDER(B_SET_LEFT_BORDER,Lane_SET_LEFT_BORDER);
		assignable machine.left_border;
		ensures guard_SET_LEFT_BORDER(B_SET_LEFT_BORDER,Lane_SET_LEFT_BORDER) &&  machine.get_left_border().equals(\old((machine.get_left_border().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_SET_LEFT_BORDER,B_SET_LEFT_BORDER)))))); 
	 also
		requires !guard_SET_LEFT_BORDER(B_SET_LEFT_BORDER,Lane_SET_LEFT_BORDER);
		assignable \nothing;
		ensures true; */
	public void run_SET_LEFT_BORDER( Integer B_SET_LEFT_BORDER, Integer Lane_SET_LEFT_BORDER){
		if(guard_SET_LEFT_BORDER(B_SET_LEFT_BORDER,Lane_SET_LEFT_BORDER)) {
			BRelation<Integer,Integer> left_border_tmp = machine.get_left_border();

			machine.set_left_border((left_border_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_SET_LEFT_BORDER,B_SET_LEFT_BORDER)))));

			System.out.println("SET_LEFT_BORDER executed B_SET_LEFT_BORDER: " + B_SET_LEFT_BORDER + " Lane_SET_LEFT_BORDER: " + Lane_SET_LEFT_BORDER + " ");
		}
	}

}

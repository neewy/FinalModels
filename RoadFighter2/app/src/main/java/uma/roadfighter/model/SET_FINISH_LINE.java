package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class SET_FINISH_LINE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_FINISH_LINE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_lanes().has(Lane_SET_FINISH_LINE) && INT.instance.has(F_SET_FINISH_LINE)); */
	public /*@ pure */ boolean guard_SET_FINISH_LINE( Integer F_SET_FINISH_LINE, Integer Lane_SET_FINISH_LINE) {
		return (machine.get_lanes().has(Lane_SET_FINISH_LINE) && INT.instance.has(F_SET_FINISH_LINE));
	}

	/*@ public normal_behavior
		requires guard_SET_FINISH_LINE(F_SET_FINISH_LINE,Lane_SET_FINISH_LINE);
		assignable machine.finish_line;
		ensures guard_SET_FINISH_LINE(F_SET_FINISH_LINE,Lane_SET_FINISH_LINE) &&  machine.get_finish_line().equals(\old((machine.get_finish_line().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_SET_FINISH_LINE,F_SET_FINISH_LINE)))))); 
	 also
		requires !guard_SET_FINISH_LINE(F_SET_FINISH_LINE,Lane_SET_FINISH_LINE);
		assignable \nothing;
		ensures true; */
	public void run_SET_FINISH_LINE( Integer F_SET_FINISH_LINE, Integer Lane_SET_FINISH_LINE){
		if(guard_SET_FINISH_LINE(F_SET_FINISH_LINE,Lane_SET_FINISH_LINE)) {
			BRelation<Integer,Integer> finish_line_tmp = machine.get_finish_line();

			machine.set_finish_line((finish_line_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_SET_FINISH_LINE,F_SET_FINISH_LINE)))));

			System.out.println("SET_FINISH_LINE executed F_SET_FINISH_LINE: " + F_SET_FINISH_LINE + " Lane_SET_FINISH_LINE: " + Lane_SET_FINISH_LINE + " ");
		}
	}

}

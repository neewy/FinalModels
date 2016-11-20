package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class ADD_LANE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_LANE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.LANES.difference(machine.get_lanes()).has(Lane_ADD_LANE) && INT.instance.has(Left_ADD_LANE) && INT.instance.has(Right_ADD_LANE) && (Left_ADD_LANE).compareTo(Right_ADD_LANE) < 0 && INT.instance.has(Finish_ADD_LANE) && NAT.instance.has(F_ADD_LANE)); */
	public /*@ pure */ boolean guard_ADD_LANE( Integer Finish_ADD_LANE, Integer Lane_ADD_LANE, Integer Left_ADD_LANE, Integer Right_ADD_LANE, Integer F_ADD_LANE) {
		return (machine.LANES.difference(machine.get_lanes()).has(Lane_ADD_LANE) && INT.instance.has(Left_ADD_LANE) && INT.instance.has(Right_ADD_LANE) && (Left_ADD_LANE).compareTo(Right_ADD_LANE) < 0 && INT.instance.has(Finish_ADD_LANE) && NAT.instance.has(F_ADD_LANE));
	}

	/*@ public normal_behavior
		requires guard_ADD_LANE(Finish_ADD_LANE,Lane_ADD_LANE,Left_ADD_LANE,Right_ADD_LANE,F_ADD_LANE);
		assignable machine.lanes, machine.finish_line, machine.left_border, machine.right_border, machine.friction;
		ensures guard_ADD_LANE(Finish_ADD_LANE,Lane_ADD_LANE,Left_ADD_LANE,Right_ADD_LANE,F_ADD_LANE) &&  machine.get_lanes().equals(\old((machine.get_lanes().union(new BSet<Integer>(Lane_ADD_LANE))))) &&  machine.get_finish_line().equals(\old((machine.get_finish_line().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,Finish_ADD_LANE)))))) &&  machine.get_left_border().equals(\old((machine.get_left_border().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,Left_ADD_LANE)))))) &&  machine.get_right_border().equals(\old((machine.get_right_border().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,Right_ADD_LANE)))))) &&  machine.get_friction().equals(\old((machine.get_friction().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,F_ADD_LANE)))))); 
	 also
		requires !guard_ADD_LANE(Finish_ADD_LANE,Lane_ADD_LANE,Left_ADD_LANE,Right_ADD_LANE,F_ADD_LANE);
		assignable \nothing;
		ensures true; */
	public void run_ADD_LANE( Integer Finish_ADD_LANE, Integer Lane_ADD_LANE, Integer Left_ADD_LANE, Integer Right_ADD_LANE, Integer F_ADD_LANE){
		if(guard_ADD_LANE(Finish_ADD_LANE,Lane_ADD_LANE,Left_ADD_LANE,Right_ADD_LANE,F_ADD_LANE)) {
			BSet<Integer> lanes_tmp = machine.get_lanes();
			BRelation<Integer,Integer> finish_line_tmp = machine.get_finish_line();
			BRelation<Integer,Integer> left_border_tmp = machine.get_left_border();
			BRelation<Integer,Integer> right_border_tmp = machine.get_right_border();
			BRelation<Integer,Integer> friction_tmp = machine.get_friction();

			machine.set_lanes((lanes_tmp.union(new BSet<Integer>(Lane_ADD_LANE))));
			machine.set_finish_line((finish_line_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,Finish_ADD_LANE)))));
			machine.set_left_border((left_border_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,Left_ADD_LANE)))));
			machine.set_right_border((right_border_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,Right_ADD_LANE)))));
			machine.set_friction((friction_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane_ADD_LANE,F_ADD_LANE)))));

			System.out.println("ADD_LANE executed Finish_ADD_LANE: " + Finish_ADD_LANE + " Lane_ADD_LANE: " + Lane_ADD_LANE + " Left_ADD_LANE: " + Left_ADD_LANE + " Right_ADD_LANE: " + Right_ADD_LANE + " F_ADD_LANE: " + F_ADD_LANE + " ");
		}
	}

}

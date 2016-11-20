package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_SCORE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_SCORE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(Car_UPDATE_SCORE) && INT.instance.has(S_UPDATE_SCORE) && NAT.instance.has(new Integer(machine.get_score().apply(Car_UPDATE_SCORE) + S_UPDATE_SCORE))); */
	public /*@ pure */ boolean guard_UPDATE_SCORE( Integer Car_UPDATE_SCORE, Integer S_UPDATE_SCORE) {
		return (machine.get_cars().has(Car_UPDATE_SCORE) && INT.instance.has(S_UPDATE_SCORE) && NAT.instance.has(new Integer(machine.get_score().apply(Car_UPDATE_SCORE) + S_UPDATE_SCORE)));
	}

	/*@ public normal_behavior
		requires guard_UPDATE_SCORE(Car_UPDATE_SCORE,S_UPDATE_SCORE);
		assignable machine.score;
		ensures guard_UPDATE_SCORE(Car_UPDATE_SCORE,S_UPDATE_SCORE) &&  machine.get_score().equals(\old((machine.get_score().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_UPDATE_SCORE,new Integer(machine.get_score().apply(Car_UPDATE_SCORE) + S_UPDATE_SCORE))))))); 
	 also
		requires !guard_UPDATE_SCORE(Car_UPDATE_SCORE,S_UPDATE_SCORE);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_SCORE( Integer Car_UPDATE_SCORE, Integer S_UPDATE_SCORE){
		if(guard_UPDATE_SCORE(Car_UPDATE_SCORE,S_UPDATE_SCORE)) {
			BRelation<Integer,Integer> score_tmp = machine.get_score();

			machine.set_score((score_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_UPDATE_SCORE,new Integer(score_tmp.apply(Car_UPDATE_SCORE) + S_UPDATE_SCORE))))));

			System.out.println("UPDATE_SCORE executed Car_UPDATE_SCORE: " + Car_UPDATE_SCORE + " S_UPDATE_SCORE: " + S_UPDATE_SCORE + " ");
		}
	}

}

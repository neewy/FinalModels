package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_POS{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_POS(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (NAT.instance.has(Elapsed_UPDATE_POS) && machine.get_cars().has(Obj_UPDATE_POS)); */
	public /*@ pure */ boolean guard_UPDATE_POS( Integer Elapsed_UPDATE_POS, Integer Obj_UPDATE_POS) {
		return (NAT.instance.has(Elapsed_UPDATE_POS) && machine.get_cars().has(Obj_UPDATE_POS));
	}

	/*@ public normal_behavior
		requires guard_UPDATE_POS(Elapsed_UPDATE_POS,Obj_UPDATE_POS);
		assignable machine.posX, machine.posY;
		ensures guard_UPDATE_POS(Elapsed_UPDATE_POS,Obj_UPDATE_POS) &&  machine.get_posX().equals(\old((machine.get_posX().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_UPDATE_POS,new Integer(machine.get_posX().apply(Obj_UPDATE_POS) + new Integer(new Integer(machine.get_drift().apply(Obj_UPDATE_POS) * Elapsed_UPDATE_POS * 50) / 1000)))))))) &&  machine.get_posY().equals(\old((machine.get_posY().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_UPDATE_POS,new Integer(machine.get_posY().apply(Obj_UPDATE_POS) + new Integer(new Integer(machine.get_vel().apply(Obj_UPDATE_POS) * Elapsed_UPDATE_POS) / 1000)))))))); 
	 also
		requires !guard_UPDATE_POS(Elapsed_UPDATE_POS,Obj_UPDATE_POS);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_POS( Integer Elapsed_UPDATE_POS, Integer Obj_UPDATE_POS){
		if(guard_UPDATE_POS(Elapsed_UPDATE_POS,Obj_UPDATE_POS)) {
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();

			machine.set_posX((posX_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_UPDATE_POS,new Integer(posX_tmp.apply(Obj_UPDATE_POS) + new Integer(new Integer(machine.get_drift().apply(Obj_UPDATE_POS) * Elapsed_UPDATE_POS * 50) / 1000)))))));
			machine.set_posY((posY_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_UPDATE_POS,new Integer(posY_tmp.apply(Obj_UPDATE_POS) + new Integer(new Integer(machine.get_vel().apply(Obj_UPDATE_POS) * Elapsed_UPDATE_POS) / 1000)))))));

			System.out.println("UPDATE_POS executed Elapsed_UPDATE_POS: " + Elapsed_UPDATE_POS + " Obj_UPDATE_POS: " + Obj_UPDATE_POS + " ");
		}
	}

}

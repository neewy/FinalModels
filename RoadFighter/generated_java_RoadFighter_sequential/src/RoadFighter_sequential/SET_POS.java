package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_POS{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_POS(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_objects().has(Obj_SET_POS) && INT.instance.has(X_SET_POS) && INT.instance.has(Y_SET_POS) &&  (\forall Integer ObjExisted;((machine.get_objects().has(ObjExisted)) ==> ((new Integer(machine.get_posX().apply(ObjExisted) - X_SET_POS)).compareTo(new Integer(new Integer(machine.get_width().apply(ObjExisted) / new Integer(2)) + new Integer(machine.get_width().apply(Obj_SET_POS) / new Integer(2)))) > 0 || (new Integer(machine.get_posY().apply(ObjExisted) - Y_SET_POS)).compareTo(new Integer(new Integer(machine.get_height().apply(ObjExisted) / new Integer(2)) + new Integer(machine.get_height().apply(Obj_SET_POS) / new Integer(2)))) > 0 || (new Integer(X_SET_POS - machine.get_posX().apply(ObjExisted))).compareTo(new Integer(new Integer(machine.get_width().apply(ObjExisted) / new Integer(2)) + new Integer(machine.get_width().apply(Obj_SET_POS) / new Integer(2)))) > 0 || (new Integer(Y_SET_POS - machine.get_posY().apply(ObjExisted))).compareTo(new Integer(new Integer(machine.get_height().apply(ObjExisted) / new Integer(2)) + new Integer(machine.get_height().apply(Obj_SET_POS) / new Integer(2)))) > 0))) &&  (\forall Integer ObjExisted;((machine.get_objects().has(ObjExisted)) ==> (!machine.get_posY().apply(ObjExisted).equals(Y_SET_POS))))); */
	public /*@ pure */ boolean guard_SET_POS( Integer Obj_SET_POS, Integer X_SET_POS, Integer Y_SET_POS) {
		return (machine.get_objects().has(Obj_SET_POS) && INT.instance.has(X_SET_POS) && INT.instance.has(Y_SET_POS) && true && true);
	}

	/*@ public normal_behavior
		requires guard_SET_POS(Obj_SET_POS,X_SET_POS,Y_SET_POS);
		assignable machine.posX, machine.posY;
		ensures guard_SET_POS(Obj_SET_POS,X_SET_POS,Y_SET_POS) &&  machine.get_posX().equals(\old((machine.get_posX().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_SET_POS,X_SET_POS)))))) &&  machine.get_posY().equals(\old((machine.get_posY().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_SET_POS,Y_SET_POS)))))); 
	 also
		requires !guard_SET_POS(Obj_SET_POS,X_SET_POS,Y_SET_POS);
		assignable \nothing;
		ensures true; */
	public void run_SET_POS( Integer Obj_SET_POS, Integer X_SET_POS, Integer Y_SET_POS){
		if(guard_SET_POS(Obj_SET_POS,X_SET_POS,Y_SET_POS)) {
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();

			machine.set_posX((posX_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_SET_POS,X_SET_POS)))));
			machine.set_posY((posY_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_SET_POS,Y_SET_POS)))));

			System.out.println("SET_POS executed Obj_SET_POS: " + Obj_SET_POS + " X_SET_POS: " + X_SET_POS + " Y_SET_POS: " + Y_SET_POS + " ");
		}
	}

}

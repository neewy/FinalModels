package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class ADD_OBJECT{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_OBJECT(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.difference(machine.get_objects()).has(Obj_ADD_OBJECT) && NAT.instance.has(Desc_ADD_OBJECT) && INT.instance.has(X_ADD_OBJECT) && INT.instance.has(Y_ADD_OBJECT) && NAT.instance.has(W_ADD_OBJECT) && NAT.instance.has(H_ADD_OBJECT) &&  (\forall Integer ObjExisted;((machine.get_objects().has(ObjExisted)) ==> ((new Integer(machine.get_posX().apply(ObjExisted) - X_ADD_OBJECT)).compareTo(new Integer(new Integer(machine.get_width().apply(ObjExisted) / new Integer(2)) + new Integer(W_ADD_OBJECT / new Integer(2)))) > 0 || (new Integer(machine.get_posY().apply(ObjExisted) - Y_ADD_OBJECT)).compareTo(new Integer(new Integer(machine.get_height().apply(ObjExisted) / new Integer(2)) + new Integer(H_ADD_OBJECT / new Integer(2)))) > 0 || (new Integer(X_ADD_OBJECT - machine.get_posX().apply(ObjExisted))).compareTo(new Integer(new Integer(machine.get_width().apply(ObjExisted) / new Integer(2)) + new Integer(W_ADD_OBJECT / new Integer(2)))) > 0 || (new Integer(Y_ADD_OBJECT - machine.get_posY().apply(ObjExisted))).compareTo(new Integer(new Integer(machine.get_height().apply(ObjExisted) / new Integer(2)) + new Integer(H_ADD_OBJECT / new Integer(2)))) > 0)))); */
	public /*@ pure */ boolean guard_ADD_OBJECT( Integer Desc_ADD_OBJECT, Integer H_ADD_OBJECT, Integer Obj_ADD_OBJECT, Integer W_ADD_OBJECT, Integer X_ADD_OBJECT, Integer Y_ADD_OBJECT) {
		return (machine.OBJECTS.difference(machine.get_objects()).has(Obj_ADD_OBJECT) && NAT.instance.has(Desc_ADD_OBJECT) && INT.instance.has(X_ADD_OBJECT) && INT.instance.has(Y_ADD_OBJECT) && NAT.instance.has(W_ADD_OBJECT) && NAT.instance.has(H_ADD_OBJECT) && true);
	}

	/*@ public normal_behavior
		requires guard_ADD_OBJECT(Desc_ADD_OBJECT,H_ADD_OBJECT,Obj_ADD_OBJECT,W_ADD_OBJECT,X_ADD_OBJECT,Y_ADD_OBJECT);
		assignable machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc;
		ensures guard_ADD_OBJECT(Desc_ADD_OBJECT,H_ADD_OBJECT,Obj_ADD_OBJECT,W_ADD_OBJECT,X_ADD_OBJECT,Y_ADD_OBJECT) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Obj_ADD_OBJECT))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,X_ADD_OBJECT)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,Y_ADD_OBJECT)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,W_ADD_OBJECT)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,H_ADD_OBJECT)))))) &&  machine.get_obj_desc().equals(\old((machine.get_obj_desc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,Desc_ADD_OBJECT)))))); 
	 also
		requires !guard_ADD_OBJECT(Desc_ADD_OBJECT,H_ADD_OBJECT,Obj_ADD_OBJECT,W_ADD_OBJECT,X_ADD_OBJECT,Y_ADD_OBJECT);
		assignable \nothing;
		ensures true; */
	public void run_ADD_OBJECT( Integer Desc_ADD_OBJECT, Integer H_ADD_OBJECT, Integer Obj_ADD_OBJECT, Integer W_ADD_OBJECT, Integer X_ADD_OBJECT, Integer Y_ADD_OBJECT){
		if(guard_ADD_OBJECT(Desc_ADD_OBJECT,H_ADD_OBJECT,Obj_ADD_OBJECT,W_ADD_OBJECT,X_ADD_OBJECT,Y_ADD_OBJECT)) {
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();

			machine.set_objects((objects_tmp.union(new BSet<Integer>(Obj_ADD_OBJECT))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,X_ADD_OBJECT)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,Y_ADD_OBJECT)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,W_ADD_OBJECT)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,H_ADD_OBJECT)))));
			machine.set_obj_desc((obj_desc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj_ADD_OBJECT,Desc_ADD_OBJECT)))));

			System.out.println("ADD_OBJECT executed Desc_ADD_OBJECT: " + Desc_ADD_OBJECT + " H_ADD_OBJECT: " + H_ADD_OBJECT + " Obj_ADD_OBJECT: " + Obj_ADD_OBJECT + " W_ADD_OBJECT: " + W_ADD_OBJECT + " X_ADD_OBJECT: " + X_ADD_OBJECT + " Y_ADD_OBJECT: " + Y_ADD_OBJECT + " ");
		}
	}

}

package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class ADD_OBSTACLE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_OBSTACLE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.difference(machine.get_objects()).has(Obs_ADD_OBSTACLE) && NAT.instance.has(Desc_ADD_OBSTACLE) && INT.instance.has(X_ADD_OBSTACLE) && INT.instance.has(Y_ADD_OBSTACLE) && NAT.instance.has(W_ADD_OBSTACLE) && NAT.instance.has(H_ADD_OBSTACLE) &&  (\forall Integer ObjExisted;((machine.get_objects().has(ObjExisted)) ==> ((new Integer(machine.get_posX().apply(ObjExisted) - X_ADD_OBSTACLE)).compareTo(new Integer(new Integer(machine.get_width().apply(ObjExisted) / new Integer(2)) + new Integer(W_ADD_OBSTACLE / new Integer(2)))) > 0 || (new Integer(machine.get_posY().apply(ObjExisted) - Y_ADD_OBSTACLE)).compareTo(new Integer(new Integer(machine.get_height().apply(ObjExisted) / new Integer(2)) + new Integer(H_ADD_OBSTACLE / new Integer(2)))) > 0 || (new Integer(X_ADD_OBSTACLE - machine.get_posX().apply(ObjExisted))).compareTo(new Integer(new Integer(machine.get_width().apply(ObjExisted) / new Integer(2)) + new Integer(W_ADD_OBSTACLE / new Integer(2)))) > 0 || (new Integer(Y_ADD_OBSTACLE - machine.get_posY().apply(ObjExisted))).compareTo(new Integer(new Integer(machine.get_height().apply(ObjExisted) / new Integer(2)) + new Integer(H_ADD_OBSTACLE / new Integer(2)))) > 0))) &&  (\forall Integer ObjExisted;((machine.get_obstacles().has(ObjExisted)) ==> (!machine.get_posY().apply(ObjExisted).equals(Y_ADD_OBSTACLE))))); */
	public /*@ pure */ boolean guard_ADD_OBSTACLE( Integer Desc_ADD_OBSTACLE, Integer H_ADD_OBSTACLE, Integer Obs_ADD_OBSTACLE, Integer W_ADD_OBSTACLE, Integer X_ADD_OBSTACLE, Integer Y_ADD_OBSTACLE) {
		return (machine.OBJECTS.difference(machine.get_objects()).has(Obs_ADD_OBSTACLE) && NAT.instance.has(Desc_ADD_OBSTACLE) && INT.instance.has(X_ADD_OBSTACLE) && INT.instance.has(Y_ADD_OBSTACLE) && NAT.instance.has(W_ADD_OBSTACLE) && NAT.instance.has(H_ADD_OBSTACLE) && true && true);
	}

	/*@ public normal_behavior
		requires guard_ADD_OBSTACLE(Desc_ADD_OBSTACLE,H_ADD_OBSTACLE,Obs_ADD_OBSTACLE,W_ADD_OBSTACLE,X_ADD_OBSTACLE,Y_ADD_OBSTACLE);
		assignable machine.obstacles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.active;
		ensures guard_ADD_OBSTACLE(Desc_ADD_OBSTACLE,H_ADD_OBSTACLE,Obs_ADD_OBSTACLE,W_ADD_OBSTACLE,X_ADD_OBSTACLE,Y_ADD_OBSTACLE) &&  machine.get_obstacles().equals(\old((machine.get_obstacles().union(new BSet<Integer>(Obs_ADD_OBSTACLE))))) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Obs_ADD_OBSTACLE))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,X_ADD_OBSTACLE)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,Y_ADD_OBSTACLE)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,W_ADD_OBSTACLE)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,H_ADD_OBSTACLE)))))) &&  machine.get_obj_desc().equals(\old((machine.get_obj_desc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,Desc_ADD_OBSTACLE)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obs_ADD_OBSTACLE,true)))))); 
	 also
		requires !guard_ADD_OBSTACLE(Desc_ADD_OBSTACLE,H_ADD_OBSTACLE,Obs_ADD_OBSTACLE,W_ADD_OBSTACLE,X_ADD_OBSTACLE,Y_ADD_OBSTACLE);
		assignable \nothing;
		ensures true; */
	public void run_ADD_OBSTACLE( Integer Desc_ADD_OBSTACLE, Integer H_ADD_OBSTACLE, Integer Obs_ADD_OBSTACLE, Integer W_ADD_OBSTACLE, Integer X_ADD_OBSTACLE, Integer Y_ADD_OBSTACLE){
		if(guard_ADD_OBSTACLE(Desc_ADD_OBSTACLE,H_ADD_OBSTACLE,Obs_ADD_OBSTACLE,W_ADD_OBSTACLE,X_ADD_OBSTACLE,Y_ADD_OBSTACLE)) {
			BSet<Integer> obstacles_tmp = machine.get_obstacles();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_obstacles((obstacles_tmp.union(new BSet<Integer>(Obs_ADD_OBSTACLE))));
			machine.set_objects((objects_tmp.union(new BSet<Integer>(Obs_ADD_OBSTACLE))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,X_ADD_OBSTACLE)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,Y_ADD_OBSTACLE)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,W_ADD_OBSTACLE)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,H_ADD_OBSTACLE)))));
			machine.set_obj_desc((obj_desc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs_ADD_OBSTACLE,Desc_ADD_OBSTACLE)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obs_ADD_OBSTACLE,true)))));

			System.out.println("ADD_OBSTACLE executed Desc_ADD_OBSTACLE: " + Desc_ADD_OBSTACLE + " H_ADD_OBSTACLE: " + H_ADD_OBSTACLE + " Obs_ADD_OBSTACLE: " + Obs_ADD_OBSTACLE + " W_ADD_OBSTACLE: " + W_ADD_OBSTACLE + " X_ADD_OBSTACLE: " + X_ADD_OBSTACLE + " Y_ADD_OBSTACLE: " + Y_ADD_OBSTACLE + " ");
		}
	}

}

package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class OBJECT_COLLISION{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public OBJECT_COLLISION(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_cars().has(MyCar_OBJECT_COLLISION) && (machine.get_obstacles().union(machine.get_cars())).has(Obj_OBJECT_COLLISION)); */
	public /*@ pure */ boolean guard_OBJECT_COLLISION( Integer MyCar_OBJECT_COLLISION, Integer Obj_OBJECT_COLLISION) {
		return (machine.get_cars().has(MyCar_OBJECT_COLLISION) && (machine.get_obstacles().union(machine.get_cars())).has(Obj_OBJECT_COLLISION));
	}

	/*@ public normal_behavior
		requires guard_OBJECT_COLLISION(MyCar_OBJECT_COLLISION,Obj_OBJECT_COLLISION);
		assignable machine.collision, machine.active;
		ensures guard_OBJECT_COLLISION(MyCar_OBJECT_COLLISION,Obj_OBJECT_COLLISION) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(MyCar_OBJECT_COLLISION,((new Integer(machine.get_posX().apply(MyCar_OBJECT_COLLISION) - machine.get_posX().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(MyCar_OBJECT_COLLISION) - machine.get_posY().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posX().apply(Obj_OBJECT_COLLISION) - machine.get_posX().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj_OBJECT_COLLISION) - machine.get_posY().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0))))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obj_OBJECT_COLLISION,(!((new Integer(machine.get_posX().apply(MyCar_OBJECT_COLLISION) - machine.get_posX().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(MyCar_OBJECT_COLLISION) - machine.get_posY().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posX().apply(Obj_OBJECT_COLLISION) - machine.get_posX().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj_OBJECT_COLLISION) - machine.get_posY().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0)))))))); 
	 also
		requires !guard_OBJECT_COLLISION(MyCar_OBJECT_COLLISION,Obj_OBJECT_COLLISION);
		assignable \nothing;
		ensures true; */
	public void run_OBJECT_COLLISION( Integer MyCar_OBJECT_COLLISION, Integer Obj_OBJECT_COLLISION){
		if(guard_OBJECT_COLLISION(MyCar_OBJECT_COLLISION,Obj_OBJECT_COLLISION)) {
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(MyCar_OBJECT_COLLISION,((new Integer(machine.get_posX().apply(MyCar_OBJECT_COLLISION) - machine.get_posX().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(MyCar_OBJECT_COLLISION) - machine.get_posY().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posX().apply(Obj_OBJECT_COLLISION) - machine.get_posX().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj_OBJECT_COLLISION) - machine.get_posY().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0))))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obj_OBJECT_COLLISION,(!((new Integer(machine.get_posX().apply(MyCar_OBJECT_COLLISION) - machine.get_posX().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(MyCar_OBJECT_COLLISION) - machine.get_posY().apply(Obj_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posX().apply(Obj_OBJECT_COLLISION) - machine.get_posX().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_width().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_width().apply(Obj_OBJECT_COLLISION) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj_OBJECT_COLLISION) - machine.get_posY().apply(MyCar_OBJECT_COLLISION))).compareTo(new Integer(new Integer(machine.get_height().apply(MyCar_OBJECT_COLLISION) / 2) + new Integer(machine.get_height().apply(Obj_OBJECT_COLLISION) / 2))) < 0)))))));

			System.out.println("OBJECT_COLLISION executed MyCar_OBJECT_COLLISION: " + MyCar_OBJECT_COLLISION + " Obj_OBJECT_COLLISION: " + Obj_OBJECT_COLLISION + " ");
		}
	}

}

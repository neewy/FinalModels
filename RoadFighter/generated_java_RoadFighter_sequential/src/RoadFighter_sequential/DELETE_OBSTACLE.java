package RoadFighter_sequential; 

import eventb_prelude.*;
import Util.Utilities;

public class DELETE_OBSTACLE{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public DELETE_OBSTACLE(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.get_obstacles().has(Obs_DELETE_OBSTACLE); */
	public /*@ pure */ boolean guard_DELETE_OBSTACLE( Integer Obs_DELETE_OBSTACLE) {
		return machine.get_obstacles().has(Obs_DELETE_OBSTACLE);
	}

	/*@ public normal_behavior
		requires guard_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE);
		assignable machine.obstacles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.active;
		ensures guard_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE) &&  machine.get_obstacles().equals(\old(machine.get_obstacles().difference(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_objects().equals(\old(machine.get_objects().difference(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_posX().equals(\old(machine.get_posX().domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_posY().equals(\old(machine.get_posY().domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_width().equals(\old(machine.get_width().domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_height().equals(\old(machine.get_height().domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_obj_desc().equals(\old(machine.get_obj_desc().domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)))) &&  machine.get_active().equals(\old(machine.get_active().domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)))); 
	 also
		requires !guard_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE);
		assignable \nothing;
		ensures true; */
	public void run_DELETE_OBSTACLE( Integer Obs_DELETE_OBSTACLE){
		if(guard_DELETE_OBSTACLE(Obs_DELETE_OBSTACLE)) {
			BSet<Integer> obstacles_tmp = machine.get_obstacles();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_obstacles(obstacles_tmp.difference(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_objects(objects_tmp.difference(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_posX(posX_tmp.domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_posY(posY_tmp.domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_width(width_tmp.domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_height(height_tmp.domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_obj_desc(obj_desc_tmp.domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)));
			machine.set_active(active_tmp.domainSubtraction(new BSet<Integer>(Obs_DELETE_OBSTACLE)));

			System.out.println("DELETE_OBSTACLE executed Obs_DELETE_OBSTACLE: " + Obs_DELETE_OBSTACLE + " ");
		}
	}

}

package uma.roadfighter.model;

import eventb_prelude.*;
import Util.Utilities;

public class ADD_CAR{
	/*@ spec_public */ private RoadFighter machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_CAR(RoadFighter m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.difference(machine.get_objects()).has(Car_ADD_CAR) && NAT.instance.has(Desc_ADD_CAR) && NAT.instance.has(W_ADD_CAR) && NAT.instance.has(H_ADD_CAR) && INT.instance.has(X_ADD_CAR) && INT.instance.has(Y_ADD_CAR) &&  (\forall Integer CarExisted;((machine.get_objects().has(CarExisted)) ==> ((new Integer(machine.get_posX().apply(CarExisted) - X_ADD_CAR)).compareTo(new Integer(new Integer(machine.get_width().apply(CarExisted) / new Integer(2)) + new Integer(W_ADD_CAR / new Integer(2)))) > 0 || (new Integer(machine.get_posY().apply(CarExisted) - Y_ADD_CAR)).compareTo(new Integer(new Integer(machine.get_height().apply(CarExisted) / new Integer(2)) + new Integer(H_ADD_CAR / new Integer(2)))) > 0 || (new Integer(X_ADD_CAR - machine.get_posX().apply(CarExisted))).compareTo(new Integer(new Integer(machine.get_width().apply(CarExisted) / new Integer(2)) + new Integer(W_ADD_CAR / new Integer(2)))) > 0 || (new Integer(Y_ADD_CAR - machine.get_posY().apply(CarExisted))).compareTo(new Integer(new Integer(machine.get_height().apply(CarExisted) / new Integer(2)) + new Integer(H_ADD_CAR / new Integer(2)))) > 0))) && NAT.instance.has(M_ADD_CAR) && NAT.instance.has(F_ADD_CAR) && (M_ADD_CAR).compareTo(new Integer(0)) >= 0); */
	public /*@ pure */ boolean guard_ADD_CAR( Integer Car_ADD_CAR, Integer Desc_ADD_CAR, Integer H_ADD_CAR, Integer W_ADD_CAR, Integer X_ADD_CAR, Integer Y_ADD_CAR, Integer F_ADD_CAR, Integer M_ADD_CAR) {
		return (machine.OBJECTS.difference(machine.get_objects()).has(Car_ADD_CAR) && NAT.instance.has(Desc_ADD_CAR) && NAT.instance.has(W_ADD_CAR) && NAT.instance.has(H_ADD_CAR) && INT.instance.has(X_ADD_CAR) && INT.instance.has(Y_ADD_CAR) && true && NAT.instance.has(M_ADD_CAR) && NAT.instance.has(F_ADD_CAR) && (M_ADD_CAR).compareTo(new Integer(0)) >= 0);
	}

	/*@ public normal_behavior
		requires guard_ADD_CAR(Car_ADD_CAR,Desc_ADD_CAR,H_ADD_CAR,W_ADD_CAR,X_ADD_CAR,Y_ADD_CAR,F_ADD_CAR,M_ADD_CAR);
		assignable machine.cars, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.obj_desc, machine.drift, machine.vel, machine.acc, machine.maxvel, machine.finished, machine.active, machine.collision, machine.score;
		ensures guard_ADD_CAR(Car_ADD_CAR,Desc_ADD_CAR,H_ADD_CAR,W_ADD_CAR,X_ADD_CAR,Y_ADD_CAR,F_ADD_CAR,M_ADD_CAR) &&  machine.get_cars().equals(\old((machine.get_cars().union(new BSet<Integer>(Car_ADD_CAR))))) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Car_ADD_CAR))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,X_ADD_CAR)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,Y_ADD_CAR)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,W_ADD_CAR)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,H_ADD_CAR)))))) &&  machine.get_obj_desc().equals(\old((machine.get_obj_desc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,Desc_ADD_CAR)))))) &&  machine.get_drift().equals(\old((machine.get_drift().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))))) &&  machine.get_vel().equals(\old((machine.get_vel().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))))) &&  machine.get_acc().equals(\old((machine.get_acc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))))) &&  machine.get_maxvel().equals(\old((machine.get_maxvel().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,M_ADD_CAR)))))) &&  machine.get_finished().equals(\old((machine.get_finished().union(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_ADD_CAR,false)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_ADD_CAR,true)))))) &&  machine.get_collision().equals(\old((machine.get_collision().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_ADD_CAR,false)))))) &&  machine.get_score().equals(\old((machine.get_score().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))))); 
	 also
		requires !guard_ADD_CAR(Car_ADD_CAR,Desc_ADD_CAR,H_ADD_CAR,W_ADD_CAR,X_ADD_CAR,Y_ADD_CAR,F_ADD_CAR,M_ADD_CAR);
		assignable \nothing;
		ensures true; */
	public void run_ADD_CAR( Integer Car_ADD_CAR, Integer Desc_ADD_CAR, Integer H_ADD_CAR, Integer W_ADD_CAR, Integer X_ADD_CAR, Integer Y_ADD_CAR, Integer F_ADD_CAR, Integer M_ADD_CAR){
		if(guard_ADD_CAR(Car_ADD_CAR,Desc_ADD_CAR,H_ADD_CAR,W_ADD_CAR,X_ADD_CAR,Y_ADD_CAR,F_ADD_CAR,M_ADD_CAR)) {
			BSet<Integer> cars_tmp = machine.get_cars();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> obj_desc_tmp = machine.get_obj_desc();
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();
			BRelation<Integer,Integer> maxvel_tmp = machine.get_maxvel();
			BRelation<Integer,Boolean> finished_tmp = machine.get_finished();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();
			BRelation<Integer,Boolean> collision_tmp = machine.get_collision();
			BRelation<Integer,Integer> score_tmp = machine.get_score();

			machine.set_cars((cars_tmp.union(new BSet<Integer>(Car_ADD_CAR))));
			machine.set_objects((objects_tmp.union(new BSet<Integer>(Car_ADD_CAR))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,X_ADD_CAR)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,Y_ADD_CAR)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,W_ADD_CAR)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,H_ADD_CAR)))));
			machine.set_obj_desc((obj_desc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,Desc_ADD_CAR)))));
			machine.set_drift((drift_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))));
			machine.set_vel((vel_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))));
			machine.set_acc((acc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))));
			machine.set_maxvel((maxvel_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,M_ADD_CAR)))));
			machine.set_finished((finished_tmp.union(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_ADD_CAR,false)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_ADD_CAR,true)))));
			machine.set_collision((collision_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Car_ADD_CAR,false)))));
			machine.set_score((score_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Car_ADD_CAR,0)))));

			System.out.println("ADD_CAR executed Car_ADD_CAR: " + Car_ADD_CAR + " Desc_ADD_CAR: " + Desc_ADD_CAR + " H_ADD_CAR: " + H_ADD_CAR + " W_ADD_CAR: " + W_ADD_CAR + " X_ADD_CAR: " + X_ADD_CAR + " Y_ADD_CAR: " + Y_ADD_CAR + " F_ADD_CAR: " + F_ADD_CAR + " M_ADD_CAR: " + M_ADD_CAR + " ");
		}
	}

}

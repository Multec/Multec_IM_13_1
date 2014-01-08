package supportClasses;

import org.mt4j.MTApplication;
import org.mt4j.util.math.Vector3D;
import org.jbox2d.dynamics.BodyDef; 
import org.jbox2d.dynamics.World; 
import advanced.physics.physicsShapes.PhysicsRectangle; 



public class MoviePhysics extends PhysicsRectangle{ 
            public MoviePhysics(Vector3D centerPoint, float width, float height, 
                        MTApplication mtapp, World world, float density, float friction, float restitution, float worldScale) { 
                  super(centerPoint, width, height,mtapp, world, density, friction, restitution, worldScale); 
            } 
           /* @Override 
            protected void polyDefB4CreationCallback(PolygonDef polyDef){ 
                  super.polyDefB4CreationCallback(polyDef); 
                  polyDef.filter.categoryBits = 0x0000; 
                  polyDef.filter.maskBits = 0x0000; 
            } */
            @Override 
            protected void bodyDefB4CreationCallback(BodyDef def) { 
                  super.bodyDefB4CreationCallback(def); 
                  def.angularDamping = 0.5f; 
                  def.linearDamping = 0.5f; 
            } 
      } 
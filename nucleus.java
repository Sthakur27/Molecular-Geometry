import java.util.*;
import java.lang.Math;
public class nucleus{
  int numOfBonds; int possibleBonds; float radius;
  public float xpos,ypos,zpos;
  float distance=150;
  Vector dipole;
  boolean allAffected=true;
  ArrayList<bond> mybonds=new ArrayList<>();
  ArrayList<bondAngle> myAngles=new ArrayList<>();
  ArrayList<bond> unbonded=new ArrayList<>();
  
  nucleus(int a,int b, float x,float y,float z, float r){
      numOfBonds=a;possibleBonds=b;  xpos=x;ypos=y;zpos=z; radius=r;
  }
  
  public void setUp(){
      mybonds.clear();
      unbonded.clear();
      if (possibleBonds==1){
        mybonds.add(new bond(-distance,0,0,radius/2,false));
      }
      if(possibleBonds==2){
        mybonds.add(new bond(-distance,0,0,radius/2,false));
        mybonds.add(new bond(+distance,0,0,radius/2,false));
      }
      if(possibleBonds==3){
        mybonds.add(new bond((float)(-(Math.cos(Math.PI/6)*distance)),(float)((Math.sin(Math.PI/6)*distance)),0,radius/2,false));
        mybonds.add(new bond((float)((Math.cos(Math.PI/6)*distance)),(float)((Math.sin(Math.PI/6)*distance)),0,radius/2,false));
        mybonds.add(new bond(0,-distance,0,radius/2,false));
      }
      if(possibleBonds==4){
        mybonds.add(new bond(distance,0,-((float)(Math.sin(Math.PI/4)*distance)),radius/2,false));
        mybonds.add(new bond(-distance,0,-((float)(Math.sin(Math.PI/4)*distance)),radius/2,false));
        mybonds.add(new bond(0,distance,((float)(Math.sin(Math.PI/4)*distance)),radius/2,false));
        mybonds.add(new bond(0,-distance,((float)(Math.sin(Math.PI/4)*distance)),radius/2,false));
      }
      if(possibleBonds==5){
        mybonds.add(new bond(0,0,distance,radius/2,false));
        mybonds.add(new bond(0,0,-distance,radius/2,false));
        mybonds.add(new bond((float)(-1*(Math.cos(Math.PI/6)*distance)),(float)((Math.sin(Math.PI/6)*distance)),0,radius/2,false));
        mybonds.add(new bond((float)((Math.cos(Math.PI/6)*distance)),(float)((Math.sin(Math.PI/6)*distance)),0,radius/2,false));
        mybonds.add(new bond(0,-distance,0,radius/2,false));
      }
      if(possibleBonds==6){
        mybonds.add(new bond(0,0,distance,radius/2,false));
        mybonds.add(new bond(0,0,-distance,radius/2,false));
        mybonds.add(new bond(-distance,0,0,radius/2,false));
        mybonds.add(new bond(distance,0,0,radius/2,false));
        mybonds.add(new bond(0,-distance,0,radius/2,false));
        mybonds.add(new bond(0,distance,0,radius/2,false));
      }
      if(possibleBonds==7){
        for (float i=0;i<2*Math.PI;i+=(2*Math.PI)/5){
             mybonds.add(new bond((float)((Math.cos(i)*distance)),(float)((Math.sin(i)*distance)),0,radius/2,false));            
        }
        mybonds.add(new bond(0,0,distance,radius/2,false));
        mybonds.add(new bond(0,0,-distance,radius/2,false));
      }
      if(possibleBonds==8){
        
        
      }
  }
  public void createBonds(){
     for (int i=0;i<numOfBonds;i++){
         mybonds.get(i).exists=true;
     }
     for (int i=numOfBonds;i<mybonds.size();i++){
         unbonded.add(mybonds.get(i));
     }
  }
  public void createBondAngles(){
    myAngles.clear();
    for (int i=0;i<mybonds.size()-1;i++){
       for(int j=i+1;j<mybonds.size();j++){
           myAngles.add(mybonds.get(i).showAngle(mybonds.get(j)));
       }
    }
  }
  public void createDipole(){
     dipole=Vector.vectorSum(this.unbonded);
  }
  public void unbondedRepel(){
    //only move bonded
    if (!allAffected){
          for (bond b:mybonds){
             if(b.exists==true){
             b.influence(unbonded);
             }
          }
          for (bond b:mybonds){
             if(b.exists==true){
             b.repel();
             }
          }
    }
    else{
        //move all bonded and unbonded
        for (int i=mybonds.size()-1;i>=0;i=i-1){        
            mybonds.get(i).influence(unbonded);
        }
        for (int i=mybonds.size()-1;i>=0;i=i-1){        
            mybonds.get(i).repel();
        }
    }
  }
  public void reset(int a, int b){
    numOfBonds=a;possibleBonds=b; this.setUp(); this.createBonds(); this.unbondedRepel(); this.createBondAngles(); this.createDipole();
  }
}
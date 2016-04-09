import java.lang.Math;
import java.util.*;
import java.text.DecimalFormat;
public class bond{
  float x,y,z,angle;
  double r1;
  //int radius=25;
  boolean exists;
  float dphi,dtheta;
  Vector displacement;
  nucleus n;
  bond (float dx,float dy,float dz,float r,boolean dexists){
     x=dx;y=dy;z=dz; exists=dexists; float radius=r;
  }
  public float trueRadius(){
     double x1=(float)x; double y1=(float)y; double z1=(float)z;
    return((float)Math.pow( (x1*x1)+(y1*y1)+(z1*z1) , 0.5));
  }
  public float trueRadiusSqd(){
     double x1=(float)x; double y1=(float)y; double z1=(float)z;
    return((float)((x1*x1)+(y1*y1)+(z1*z1)));
  }
  
  //create bond angles between other bonds
  public bondAngle showAngle(bond b){
     DecimalFormat df= new DecimalFormat("#.###");
     boolean visible=false;
     if(this.exists && b.exists){visible=true;}
     float[] temp1={this.x,this.y,this.z};
     float[] temp2={b.x,b.y,b.z};
     float theta= Coord.findAngle(temp1,temp2);
     theta=(float) ( (   (double)theta  *180/Math.PI));
     float[] midpoint={(x+b.x)/2,(y+b.y)/2,(z+b.z)/2};
     theta=(float)(Double.parseDouble(df.format(theta)));
     if (theta==180){
       return(new bondAngle(midpoint,Float.toString(theta),true,visible));}
       
     else{return(new bondAngle(midpoint,Float.toString(theta),false,visible));}
  }
  
  
  // cartesian vector/reduce r approach
  public void influence(ArrayList<bond> unbondedPairs){
     r1= Coord.toSpher(x,y,z)[0];
     displacement=new Vector(0,0,0);
     for (bond unb: unbondedPairs){
       if(this!=unb){
       displacement.addVector(Vector.betweenBonds(unb,this).unitVector().multiply(3000/Coord.distance(x,y,z,unb.x,unb.y,unb.z)));}       
     }
  }
  
  public void repel(){
         this.x+=displacement.x;    this.y+=displacement.y;  this.z+=displacement.z; 
         double[] coords=Coord.toSpher((double)x,(double)y,(double)z);
         coords=Coord.toCart(r1,coords[1],coords[2]);
         x=(float)coords[0]; y=(float)coords[1]; z=(float)coords[2];
  }
  
  
  /*//theta phi approach
  public void influence2(ArrayList<bond> unbondedPairs){
      float dtheta=0;float dphi=0;   float[] unbspher; float temp;
      DecimalFormat df= new DecimalFormat("#.###");
      float [] thisspher=Coord.toSpher(this.x,this.y,this.z);
      for (bond unb: unbondedPairs){
          //float distance=Coord.distance(x,y,z,unb.x,unb.y,unb.z);
          unbspher=Coord.toSpher(unb.x,unb.y,unb.z);
          //System.out.print(thisspher[2]*180/Math.PI); System.out.println("  "+unbspher[2]*180/Math.PI);
          temp=unbspher[1]-thisspher[1];
          if (temp==0){}
          else if (temp<0){
              dtheta+= 0.1*(Math.PI+(temp));
          }
          else{dtheta-= 0.1*(Math.PI-(temp));}
          //System.out.println(temp);
          temp=unbspher[2]-thisspher[2];
          //System.out.print(temp);
          if(temp==0){}
          else if (temp<0){
              dphi+= 0.1*(Math.PI+(temp));
          }
          else{dphi-= 0.1*(Math.PI-(temp));}
          //System.out.println(dphi);
      }
      thisspher[1]+=dtheta; thisspher[2]+=dphi;
      thisspher=Coord.toCart(thisspher);
      //this.x=thisspher[0]; this.y=thisspher[1]; this.z=thisspher[2];
  }*/
}
import java.util.*;
public class Vector{
   double x,y,z;
   //double xyangle;
   Vector(float xp, float yp, float zp){
       x=(double)xp; y=(double)yp; z=(double)zp;
     
   }
   Vector (double xp, double yp, double zp){
       x=xp; y=yp; z=zp;     
   }
   public double length(){
     return(Math.pow( (x*x)+(y*y) +(z*z),0.5));
   }
   static Vector extractVector(bond b){
      return (new Vector(b.x,b.y,b.z));
   }
   static Vector betweenBonds(bond a, bond b){
     return (new Vector(b.x-a.x,b.y-a.y,b.z-a.z));     
   }
   public Vector unitVector(){
       double len=length();
       return (new Vector(x/len,y/len,z/len));
       //this.x=this.x/len;  this.y=this.y/len;   this.z=this.z/len;
   }
   public Vector multiply(double mag){
        return (new Vector(x*mag,y*mag,z*mag));
   }
   void addVector(Vector v){
       this.x+=v.x; this.y+=v.y; this.z+=v.z;
   }
   double findXYAngle(){
      return(Math.atan2(y,x));
   }
   float[] findAngles(){
      float[] returnlist=new float[2];
      returnlist[0]=Coord.toSpher((float)x,(float)y,(float)z)[1];
      returnlist[1]=Coord.toSpher((float)x,(float)y,(float)z)[2];
      return(returnlist);
   }
   static Vector vectorSum(ArrayList<bond> blist){
        //double tempx=0; double tempy=0; double tempz=0;
        Vector temp=new Vector(0,0,0);
        for (bond b: blist){
           temp.addVector(extractVector(b));
        }
        return temp;
   }
}
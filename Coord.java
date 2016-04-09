import java.lang.Math;
import java.text.DecimalFormat;
public class Coord{
  static float[] toSpher(float x, float y, float z){
     double x1=(double)x;double y1=(double)y;double z1=(double)z;
     float theta=(float)(Math.atan2(y1,x1));
     float r=(float)Math.pow((y1*y1)+(z1*z1)+(x1*x1),0.5);
     float cylr=(float)Math.pow((y1*y1)+(x1*x1),0.5);
     float phi=(float)(Math.atan2(cylr,z1));
     float[] temp={r, theta, phi};
     return(temp);
  }
  static double[] toSpher(double x, double y, double z){
     double theta=(Math.atan2(y,x));
     double r=Math.pow((y*y)+(z*z)+(x*x),0.5);
     double cylr=Math.pow((y*y)+(x*x),0.5);
     double phi=(Math.atan2(cylr,z));
     double[] temp={r, theta, phi};
     return(temp);
  }
  static double distanceO(double x,double y, double z){
       return(Math.pow( (x*x) + (y*y) + (z*z), 0.5));    
  }
  static float distance(float x1, float y1,float z1,float x2, float y2,float z2){
     double px1=(double)x1; double py1=(double)y1; double pz1=(double)z1; double px2=(double)x2; double py2=(double)y2; double pz2=(double)z2;
     return((float)Math.pow( ((px2-px1) * (px2-px1)) + ((py2-py1) * (py2-py1)) + ((pz2-pz1) * (pz2-pz1)) , 0.5));
  }
  static float[] todSpher(float x, float y, float z){
     double x1=(double)x;double y1=(double)y;double z1=(double)z;
     float theta=(float)(Math.atan2(y1,x1)*180/Math.PI);
     float r=(float)Math.pow((y1*y1)+(z1*z1)+(x1*x1),0.5);
     float cylr=(float)Math.pow((y1*y1)+(x1*x1),0.5);
     float phi=(float)(Math.atan2(cylr,z1)*180/Math.PI);
     float[] temp={r, theta, phi};
     return(temp);
  }
  static float dotProd(bond a, bond b){
      return(  (a.x*b.x)  + (a.y*b.y)   + (a.z*b.z));
  }
  static float dotProd(float[] a, float[] b){
      return(  (a[0]*b[0])  + (a[1]*b[1])   + (a[2]*b[2]));
  }
  static float length(float[]a){
     double x=(double)a[0];double y=(double)a[1];double z=(double)a[2];
     return((float)Math.pow( (x*x) +(y*y) + (z*z) , 0.5));
  }
  static float findAngle(float[]a,float[]b){
     return((float)(Math.acos(dotProd(a,b)/(length(a)*length(b)))));
  }
  static float[] toCart(float r, float theta, float phi){
     DecimalFormat df= new DecimalFormat("#.###");  //df.setRoundingMode(RoundingMode.CEILING);
     double r1=(double)r;double theta1=(double)theta;double phi1=(double)phi;
     double z=r1*Math.cos(phi1);
     double cylr=r*Math.sin(phi1);
     double x=cylr*Math.cos(theta1);
     double y=cylr*Math.sin(theta1);
     //rounding
     z=Double.parseDouble(df.format(z));
     x=Double.parseDouble(df.format(x));
     y=Double.parseDouble(df.format(y));
     
     float[] temp={(float)x, (float)y, (float)z};
     return(temp);
  }  
  static double[] toCart(double r, double theta, double phi){
     DecimalFormat df= new DecimalFormat("#.###");  //df.setRoundingMode(RoundingMode.CEILING);
     double z=r*Math.cos(phi);
     double cylr=r*Math.sin(phi);
     double x=cylr*Math.cos(theta);
     double y=cylr*Math.sin(theta);
     //rounding
     z=Double.parseDouble(df.format(z));
     x=Double.parseDouble(df.format(x));
     y=Double.parseDouble(df.format(y));
     double[]temp={x,y,z};
     return(temp);
  }  
  static float[] toCart(float[] pos){
     return(toCart(pos[0],pos[1],pos[2]));
  }
}
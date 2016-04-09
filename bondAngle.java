public class bondAngle{
   float x,y,z;
   boolean flat=false;
   boolean displayon=true;
   String angle="";
   bondAngle(float[] pos, String myangle,boolean f, boolean don){
     x=pos[0];y=pos[1];z=pos[2];angle=myangle; flat=f; displayon=don;
   }
}
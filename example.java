public class example{
     static String[] elec={"N/A","N/A","linear","trigonal planar","tetrahedral","trigonal bipyramidal","octahedral","pentagonal bipyramidal"};
     public static String electronic(int a){
           return(elec[a]);
     }
     public static String molecular(int a, int b){
          if (a==1){
              return("N/A");
          }
          if (a==2){
              if(b==2){ return("linear");}
              return("N/A");
          }
          if (a==2){
              if(b==2){ return("linear");}
              return("N/A");
          }
          if(a==3){
              if(b==2){ return("bent");}
              if(b==3){ return("trigonal planar");}
              return("N/A");
          }
          if(a==4){
              if(b==2){ return("bent");}
              if(b==3){ return("trigonal planar");}
              if(b==4){ return("tetrahedral");}
              return("N/A");
          }
          return("N/A");
     }
    
    
}
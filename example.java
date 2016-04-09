public class example{
     static String[] elec={"N/A","N/A","linear","trigonal planar","tetrahedral","trigonal bipyramidal","octahedral","pentagonal bipyramidal","square antiprismatic"};
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

          
          if(a==3){
              if(b==2){ return("bent");}
              if(b==3){ return("trigonal planar");}
              return("N/A");
          }
          if(a==4){
              if(b==2){ return("bent");}
              if(b==3){ return("trigonal pyramidal");}
              if(b==4){ return("tetrahedral");}
              return("N/A");
          }
          if(a==5){
              if(b==2){ return("linear");}
              if(b==3){ return("T-Shaped");}
              if(b==4){ return("seesaw");}
              if(b==5){ return("trigonal bipyramidal");}
              return("N/A");            
          }
          if(a==6){
              if(b==4){ return("square planar");}
              if(b==5){ return("square pyramidal");}
              if(b==6){ return("octahedral");}
              return("N/A");            
          }
          if(a==7){
              if(b==5){ return("pentagonal planar");}
              if(b==6){ return("pentagonal pyramidal");}
              if(b==7){ return("pentagonal bipyramidal");}
              return("N/A");            
          }
          if(a==8){
              if(b==8){ return("square antiprismatic");}
              return("N/A");            
          }
          return("N/A");
     }
     
     
     
     public static String ex(int a, int b){
          if (a==1){              
              return("H2, O2");
          }
          if (a==2){
              if(b==2){ return("CO2");}
              return("N/A");
          }

          if(a==3){
              if(b==2){ return("SO2");}
              if(b==3){ return("BF3");}
              return("N/A");
          }
          if(a==4){
              if(b==2){ return("H2O");}
              if(b==3){ return("NH3");}
              if(b==4){ return("CH4");}
              return("N/A");
          }
          if(a==5){
              if(b==2){ return("XeF2");}
              if(b==3){ return("ClF3");}
              if(b==4){ return("SF4");}
              if(b==5){ return("PCl5");}
              return("N/A");            
          }
          if(a==6){
              if(b==4){ return("XeF4");}
              if(b==5){ return("BrF5");}
              if(b==6){ return("SF6");}
              return("N/A");            
          }
          if(a==7){
              if(b==5){ return("XeF5-");}
              if(b==6){ return("XeOF5-");}
              if(b==7){ return("IF7");}
              return("N/A");            
          }
          if(a==8){
              if(b==8){ return("XeF8 -2");}
              return("N/A");            
          }
          return("N/A");
     }  
}
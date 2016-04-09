/*controls:
z,x,c  then mousewheel  to rotate 
d  then mousehwheel to choose nucleus-electron distance
a to toggle displaying angles
b to toggle displaying unbonded pairs
press   'n'  then   '0-7' to choose number of bonds and then  '1-7' to choose number of electron domains to create new atom. 
ex.  'n  3  5'  creates a trigonal planar molecular geometry and a trigonal bipyramidal electronic geometry.
'r'  resets rotation
*/
double rx,ry,rz;
StringList rotationList1=new StringList();
FloatList rotationList2=new FloatList();
boolean angleDisplay=true;
int rchoose;
nucleus test;
float radius=100;
int oldwidth=800,oldheight=700;
boolean toggleUnbonded=true;
int newBonded=0;
int newPosBonded=0;
int creatingProcess=0;
float oldratio=1;
void setup(){
  size(800,700,P3D);
  oldwidth=width;
  oldheight=height;
  test=new nucleus(1,3,width/2,height/2,radius,50);
  test.setUp();
  test.createBonds();
  test.unbondedRepel();
  test.createBondAngles();
  test.createDipole();
  float [] a={2,4,-5};
  float [] b={-1,3,-2};
  //surface.setResizable(true);
}

void draw(){
  background(255,255,255);
  
          //xyz aligner
          pushMatrix();
          //resetMatrix();
          fill(0);
          //text("test",100,200,20);
          stroke(0,0,0);
          translate(0.13*width,0.87*height,30);
          rotate2();
          textAlign(LEFT);
          line(0,0,0,0.1*width,0,0);
          text("X",0.1*width,0,0);
          line(0,0,0,0,-0.1*width,0);
          text("Y",0,-0.1*width,0);
          line(0,0,0,0,0,0.1*width);
          text("Z",0,0,0.1*width);
          popMatrix();
          
          
  //resizing
  if(oldwidth!=width){
     oldratio=((float)(width))/((float)(oldwidth));
     test.distance+=(width-oldwidth);
     oldwidth=width;
  }
  test.xpos=width/2; //test.distance+=10*(width-oldwidth); 
  test.ypos=height/2; test.radius=width/16; test.setUp(); test.createBonds(); test.unbondedRepel(); test.createBondAngles();  test.createDipole();
  oldratio=1;
  lights();
  
  //info
  pushMatrix();
  //rotate2();
  fill(0);
  textSize(13);
  textAlign(RIGHT,BOTTOM);
  text("Number of Bonds: "+test.numOfBonds,(0.9)*width,0.1*height,10);
  text("Number of Possible Bonds: "+test.possibleBonds,(0.9)*width,0.13*height,10);
  text("Electronic Geometry: "+example.electronic(test.possibleBonds),(0.9)*width,0.16*height,10);
  popMatrix();
  
  
  fill(255,255,255);
  //nucleus
  pushMatrix();
  rotate();
  translate(test.xpos,test.ypos,test.zpos);
  stroke(#cb213d);
  sphere(test.radius); 
  popMatrix();
  
  
  //draw bonds
  for (bond b:test.mybonds){
       if(b.exists || toggleUnbonded){
           if(b.exists){
             //stroke(0,0,0);
              stroke(#09a5ff);
           }
           else{stroke(#00ff00);}
           pushMatrix();
           rotate();
           translate(test.xpos,test.ypos,test.zpos);
           translate(b.x,b.y,b.z);
           //sphere(b.radius);
           sphere(test.radius/2);
           translate(-b.x,-b.y,-b.z);
           line(b.x,b.y,b.z,0,0,0);
           popMatrix();
      }
  }
  
  //make bond angles display
  for (bondAngle b:test.myAngles){
       if(!b.flat  &&   (b.displayon||toggleUnbonded) && angleDisplay){
           pushMatrix();
           rotate();
           textAlign(CENTER);
           translate(test.xpos,test.ypos,test.zpos);
           translate(b.x,b.y,b.z);
           fill(0);
           for (int i=rotationList1.size()-1;i>=0;i=i-1){
              if(rotationList1.get(i).equals("x")){
                 rotateX(-1*rotationList2.get(i));
              }
              if(rotationList1.get(i).equals("y")){
                 rotateY(-1*rotationList2.get(i));
              }
              if(rotationList1.get(i).equals("z")){
                 rotateZ(-1*rotationList2.get(i));
              }
            }
           /*
           rotateZ((float)-rz);
           rotateY((float)-ry);
           rotateX((float)-rx);*/
           text(b.angle,0,0);
           popMatrix();
       }
  }
  
  //show dipole
  pushMatrix();
  rotate();
  translate(test.xpos,test.ypos,test.zpos);
  stroke(#ff00ac);
  float x2=(float)(1.3*test.dipole.x); float y2=(float)(1.3*test.dipole.y); float z2=(float)(1.3*test.dipole.z);
  float[] spherCoords=Coord.toSpher(x2,y2,z2);
  //dipole
  line(0,0,0,x2,y2,z2);
  //make arrows   using spherical coordinates
  float[] temp1={spherCoords[0]/1.05,spherCoords[1],spherCoords[2]-0.05};
  float[] temp2={spherCoords[0]/1.05,spherCoords[1],spherCoords[2]+0.05};
  temp1=Coord.toCart(temp1);
  temp2=Coord.toCart(temp2);
  line(x2,y2,z2,temp1[0],temp1[1],temp1[2]);
  line(x2,y2,z2,temp2[0],temp2[1],temp2[2]);
  float[] temp3={spherCoords[0]/1.05,spherCoords[1]-0.05,spherCoords[2]};
  float[] temp4={spherCoords[0]/1.05,spherCoords[1]+0.05,spherCoords[2]};
  temp3=Coord.toCart(temp3);
  temp4=Coord.toCart(temp4);
  line(x2,y2,z2,temp3[0],temp3[1],temp3[2]);
  line(x2,y2,z2,temp4[0],temp4[1],temp4[2]);
  popMatrix();
}

void rotate(){
  translate(test.xpos,test.ypos,test.zpos);
  for (int i=0;i<rotationList1.size();i++){
    if(rotationList1.get(i).equals("x")){
       rotateX(rotationList2.get(i));
    }
    if(rotationList1.get(i).equals("y")){
       rotateY(rotationList2.get(i));
    }
    if(rotationList1.get(i).equals("z")){
       rotateZ(rotationList2.get(i));
    }
  }
  /*rotateX((float)rx);
  rotateY((float)ry);
  rotateZ((float)rz);*/
  translate(-test.xpos,-test.ypos,-test.zpos);
}
void rotate2(){
  rotateX((float)rx);
  rotateY((float)ry);
  rotateZ((float)rz);
}




void keyPressed(){
   if(key=='n'||key=='N'){
     creatingProcess=1;
   }
   if(key=='0'){
     if(creatingProcess==1){
        newBonded=0;
        creatingProcess=2;
     }
   }
   if(key=='1'){
     if(creatingProcess==1){
        newBonded=1;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=1;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='2'){
     if(creatingProcess==1){
        newBonded=2;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=2;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='3'){
     if(creatingProcess==1){
        newBonded=3;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=3;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='4'){
     if(creatingProcess==1){
        newBonded=4;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=4;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='5'){
     if(creatingProcess==1){
        newBonded=5;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=5;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='6'){
     if(creatingProcess==1){
        newBonded=6;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=6;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='7'){
     if(creatingProcess==1){
        newBonded=7;
        creatingProcess=2;
     }
     else if(creatingProcess==2){
        newPosBonded=7;
        test.reset(newBonded,newPosBonded);
        creatingProcess=0;
     }
   }
   if(key=='b'||key=='B'){
     if(toggleUnbonded){toggleUnbonded=false;}  else{toggleUnbonded=true;}
   } 
   if(key=='a'||key=='A'){
     if(angleDisplay){angleDisplay=false;}  else{angleDisplay=true;}
   } 
   if(key=='x'||key=='X'){
     rchoose=0;
   }
   if(key=='c'||key=='C'){
     rchoose=1;
   }
   if(key=='z'||key=='Z'){
     rchoose=2;
   }
   if(key=='d'||key=='D'){
     rchoose=3;
   }
   if (keyCode==UP){
      rz+=PI/10;
   }
   if (keyCode==DOWN){
      rz-=PI/10;
   }
   if (keyCode==LEFT){
      ry-=PI/10;
   }
   if (keyCode==RIGHT){
      ry+=PI/10;
   }
   if(key=='r'||key=='R'){
     rx=0;ry=0;rz=0; test.distance=(int)test.radius*3;   rotationList1.clear(); rotationList2.clear();
   }
}
void mouseWheel(MouseEvent event) {
  int e = event.getCount();
  if(rchoose==2){
    rx-=(5*e)*PI/180;
    if (rotationList1.size()>0 && rotationList1.get(rotationList1.size()-1).equals("x")){
       rotationList2.add(rotationList2.size()-1,(-5*e)*PI/180);
    }
    else{
    rotationList1.append("x");
    rotationList2.append((-5*e)*PI/180);
    }
  }
  if(rchoose==1){
    rz-=(5*e)*PI/180;
    if (rotationList1.size()>0 && rotationList1.get(rotationList1.size()-1).equals("z")){
       rotationList2.add(rotationList2.size()-1,(-5*e)*PI/180);
    }
    else{
    rotationList1.append("z");
    rotationList2.append((-5*e)*PI/180);
    }
  }
  if(rchoose==0){
    ry-=(5*e)*PI/180;
    if (rotationList1.size()>0 && rotationList1.get(rotationList1.size()-1).equals("y")){
       rotationList2.add(rotationList2.size()-1,(-5*e)*PI/180);
    }
    else{
    rotationList1.append("y");
    rotationList2.append((-5*e)*PI/180);
    }
  }
  if(rchoose==3){
    if(test.distance>test.radius*0.9 && e>0){
    test.distance-=(5*e);} else if(e<0){test.distance-=(5*e);}   
  test.setUp(); test.createBonds(); test.createBondAngles();}
}
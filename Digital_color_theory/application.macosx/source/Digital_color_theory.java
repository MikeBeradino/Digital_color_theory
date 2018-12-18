import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Digital_color_theory extends PApplet {


String myName;
int bestColor;
int y;
int state;
boolean b1;

 
int R = 255;
int G = 0;
int B = 0; 

int R_3 = 0;
int G_3 = 255;
int B_3 = 0; 

int R_2 = 0;
int G_2 = 0;
int B_2 = 255; 



int r_count;
int g_count;
int b_count;
boolean r_up;
boolean g_up;
boolean b_up;

int dim;

int value = 0;
int value2 = 0;
int value3 = 0;
int press_logic_off=0;
int colorlock = 0;
boolean lock=false;
boolean press_logic=true;
PFont font;
String binary;
String ss1;
String ss2;
String ss3;
String ss4;

String hex_string;
String hex_r;
String hex_g;
String hex_b;

float bx;
float by;
float bx_blue;
float by_blue;
float bx_green;
float by_green;

int bs = 300;
int bs_blue = 300;
int bs_green = 300;

boolean bover = false;
boolean bover_blue = false;
boolean bover_green = false;

boolean locked = false;
boolean locked_blue = false;
boolean locked_green = false;

float bdifx = 0.0f; 
float bdify = 0.0f; 
float bdifx_blue = 0.0f; 
float bdify_blue = 0.0f; 
float bdifx_green = 0.0f; 
float bdify_green = 0.0f; 

int r;
int g;
int b;

public void setup(){
 
  
  ControlP5 MyController;
  ControlP5 MyController_blue;
  ControlP5 MyController_green;
  MyController = new ControlP5(this);
  MyController_blue = new ControlP5(this);
  MyController_green= new ControlP5(this);
  //The R (red slider):
//A.addSlider("B",C,D,E,F,G,H,J); fg

MyController.addSlider("R",0,255,255,20,100,10,100);

//The G (Green slider):
MyController.addSlider("G",0,255,0,60,100,10,100);// MyController.controller("G").setColorForeground(#0BFC00);
//The B (Blue slider):
MyController.addSlider("B",0,255,0,100,100,10,100);// MyController.controller("G").setColorForeground(#002CFC); 

MyController_blue.addSlider("R_2",0,255,0,20,220,10,100); //MyController.controller("R").setColorForeground(#FC0000);
//The G (Green slider):
MyController_blue.addSlider("G_2",0,255,0,60,220,10,100);// MyController.controller("G").setColorForeground(#0BFC00);
//The B (Blue slider):
MyController_blue.addSlider("B_2",0,255,255,100,220,10,100);// MyController.controller("G").setColorForeground(#002CFC);

MyController_green.addSlider("R_3",0,255,0,20,340,10,100); //MyController.controller("R").setColorForeground(#FC0000);
//The G (Green slider):
MyController_green.addSlider("G_3",0,255,255,60,340,10,100);// MyController.controller("G").setColorForeground(#0BFC00);
//The B (Blue slider):
MyController_green.addSlider("B_3",0,255,0,100,340,10,100);// MyController.controller("G").setColorForeground(#002CFC);


 
 //rectMode(CENTER); 
 ellipseMode(CENTER);
 
 blendMode(ADD);
 colorMode(RGB,255,255,255,255);
  reset();
 }

public void draw(){
  background(0);
  
  fill(255,0,0);
  text("RESET" ,690,40);
  fill(255,255,255);
  text("Hexadecimal" ,570,665);
  text("8Bit-Decimal",45,665);
  text("8Bit-Binary",45,730);
  strokeWeight(4);
  sample_rec();
  letters();
   fill((round ((R_3+1)/2)),(round((G_3+1)/2)),(round((B_3+1)/2)));
  MouseIsOver_green();
  fill((round ((R+1)/2)),(round((G+1)/2)),(round((B+1)/2)));
  MouseIsOver_red();
  fill((round ((R_2+1)/2)),(round((G_2+1)/2)),(round((B_2+1)/2)));
  MouseIsOver_blue();
  colorbox();
}

public void mousePressed() {
  
 
    if(b1 && mouseX > 690 && mouseX < 785 && 
     mouseY > 15 && mouseY < 40 ){
    reset(); // ADD LOGIC TO CALL YOUR RESET FUNCTION!!!
  } else {
    b1 = true;
  }
  
  if (value2 == 0) {
  // value = 1;
   //colorbox();
  // value2 = 1;
  //color c = get(mouseX, mouseY);

  //color r = c >> 020 & 0xFF;
  //color g = c >> 010 & 0xFF;
  //color b = c & 0xFF;

  //print( "(" + mouseX + "," + mouseY + ") \t\tColor: #" + hex(c) );
  //println( "\tR: " + r + "\t G: " + g + "\t B: " + b );
   
   
   //println("Clicked: val3" );
  } 
   value2 = 0;
   value = 0;

   
 
 
 
 
 if(bover) { 
    locked = true; 
    fill(255, 0, 0);
  } else {
    locked = false;
    fill(255, 0, 0);
  }
  bdifx = mouseX-bx; 
  bdify = mouseY-by; 
 
 if(bover_blue) { 
    locked_blue = true; 
    fill(0, 0, 255);
  } else {
    locked_blue = false;
    fill(0, 0, 255);
    }
  bdifx_blue = mouseX-bx_blue; 
  bdify_blue = mouseY-by_blue; 
  
 
 
 if(bover_green) { 
    locked_green = true; 
    fill(0, 255, 0);
  } else {
    locked_green = false;
    fill(0, 255, 0);
    }
  bdifx_green = mouseX-bx_green; 
  bdify_green = mouseY-by_green; 



}


public void mouseDragged() {
if(locked) {
  bx = mouseX-bdifx; 
  by = mouseY-bdify; 
  }
if(locked_blue) {
  bx_blue = mouseX-bdifx_blue; 
  by_blue = mouseY-bdify_blue; 
  }
if(locked_green) {
  bx_green = mouseX-bdifx_green; 
  by_green = mouseY-bdify_green; 
  }
}

public void mouseReleased() {
  locked = false;
  locked_blue = false;
  locked_green = false;
}


public void MouseIsOver_red(){
// Test if the cursor is over the box 
if (mouseX > bx-bs/3 && mouseX < bx+bs/3 && 
     mouseY > by-bs/3 && mouseY < by+bs/3) {
   bover = true;  
    if(!locked) { 
    stroke(0); 
    fill((round ((R+1)/2)),(round((G+1)/2)),(round((B+1)/2)));
   
    ellipse(bx, by, bs, bs);
  } 
  } else {
    noStroke();
    fill((round ((R+1)/2)),(round((G+1)/2)),(round((B+1)/2)));
    bover = false;
    ellipse(bx, by, bs, bs);
  }
    ellipse(bx, by, bs, bs);
}


public void MouseIsOver_green(){
if (mouseX > bx_green - bs_green/3 && mouseX < bx_green + bs_green/3 && 
     mouseY > by_green - bs_green/3 && mouseY < by_green + bs_green/3) {
   bover_green = true;  
    if(!locked_green) { 
    stroke(0); 
   fill((round ((R_3+1)/2)),(round((G_3+1)/2)),(round((B_3+1)/2)));
    ellipse(bx_green, by_green, bs_green, bs_green);
  } 
  } else {
    noStroke();
    //stroke(255);
    fill((round ((R_3+1)/2)),(round((G_3+1)/2)),(round((B_3+1)/2)));
    bover_green = false;
    ellipse(bx_green, by_green, bs_green, bs_green);
  }
    ellipse(bx_green, by_green, bs_green, bs_green);
}

public void MouseIsOver_blue(){
// Test if the cursor is over the box 
if (mouseX > bx_blue - bs_blue/3 && mouseX < bx_blue + bs_blue/3 && 
     mouseY > by_blue - bs_blue/3 && mouseY < by_blue + bs_blue/3) {
   bover_blue = true;  
    if(!locked_blue) { 
    stroke(0); 
    fill((round ((R_2+1)/2)),(round((G_2+1)/2)),(round((B_2+1)/2)));
    
    ellipse(bx_blue, by_blue, bs_blue, bs_blue);
  } 
  } else {
    strokeWeight(0);
    noStroke();
    //stroke(255);
    fill((round ((R_2+1)/2)),(round((G_2+1)/2)),(round((B_2+1)/2)));
    bover_blue = false;
    ellipse(bx_blue, by_blue, bs_blue, bs_blue);
  }
    ellipse(bx_blue, by_blue, bs_blue, bs_blue);

}



public void colorbox(){

  int c = get(mouseX, mouseY);
  int r = c >> 020 & 0xFF;
  int g = c >> 010 & 0xFF;
  int b = c & 0xFF;
  fill(r,g,b);
  stroke(10);
  rect(width-width/3.6f, height/1.15f, width/4.4f, height/11);
  hex_string = (hex(c,6));
  hex_r = hex_string.substring(0,2);
  fill(255, 0, 0);
  text(hex_r, width-width/3.9f, height/1.16f);
  hex_g = hex_string.substring(2,4);
  fill(0, 255, 0);
  text(hex_g, width-width/5.4f, height/1.16f);
  hex_b = hex_string.substring(4);
  fill(0, 0, 255);
  text(hex_b, width-width/9, height/1.16f);
  binary = (binary(c,24));
  ss1 = binary.substring(0,8);
  fill(255, 0, 0);
  text(ss1, width-width/1.06f, height/1.05f);
  ss2 = binary.substring(8,16);
  fill(0, 255, 0);
  text(ss2, width-width/1.35f, height/1.05f);
  ss3 = binary.substring(16);
  fill(0, 0, 255);
  text(ss3, width-width/1.85f, height/1.05f);
  binary = (binary(c,24));
  
  ss1 = binary.substring(0,8);
  fill(255, 0, 0);
  text(unbinary(ss1), width-width/1.06f, height/1.15f);
  
  ss2 = binary.substring(8,16);
  fill(0, 255, 0);
  text(unbinary(ss2), width-width/1.35f, height/1.15f);
 
  ss3 = binary.substring(16);
  fill(0, 0, 255);
  text(unbinary(ss3), width-width/1.85f, height/1.15f);
  //ss4 = binary.substring(8,15);
  //text(ss4, width-width/3, height/1.05);
}

public void letters(){
 textSize(32);
}
 
public void sample_rec(){

  if (r_count == 0){
    r_up = true;
  }
  if (r_count == 2550){
    r_up = false;
  }
  if(r_up == true){
  r_count ++;
  }
  if (r_up == false){
   r_count--; 
  }
  
  if (g_count == 0){
    g_up = true;
  }
  if (g_count == 2550){
    g_up = false;
  }
  if(g_up == true){
  g_count ++;
  }
  if (g_up == false){
   g_count--; 
  }
 
  if (b_count == 0){
  b_up = true;
  }
  if (b_count == 2550){
  b_up = false;
  }
  if(b_up == true){
  b_count ++;
  }
  if (b_up == false){
   b_count--; 
  }
  int r = color(r_count/10,0,0);
  fill(r);
  rect(30, 20, 55, 55);  // Draw rectangle
  
  int g = color(0,g_count/10,0);
  fill(g);
  rect(95, 20, 55, 55);  // Draw rectangle
  
  int b = color(0,0,b_count/10);
  fill(b);
  rect(160, 20, 55, 55);  // Draw rectangle

}

public void reset(){
  
 dim = width/2;
 bx = width/2.0f;
 by = height/2.0f;
 bx_blue = width/1.5f;
 by_blue = height/2.0f;
 bx_green = width/3.0f;
 by_green = height/2.0f;
  
}
  public void settings() {  size(800,800);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Digital_color_theory" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

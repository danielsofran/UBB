

function [] = P2_test ()
  fprintf("*N --- numarul de termeni parcursi din serie*\n");
  fprintf("sin(10*pi) cu errorare epsilon: \n"); [sin,N]=mysinT(10*pi,eps)
  fprintf("sin(20000*pi) cu errorare epsilon: \n"); [sin,N]=mysinT(20000*pi,eps)
  fprintf("cos(10*pi) cu errorare epsilon: \n"); [cos,N]=mysinT(10*pi,eps)
  fprintf("cos(20000*pi) cu errorare epsilon: \n"); [cos,N]=mysinT(20000*pi,eps)
  fprintf("sin(pi/2) cu errorare epsilon: \n"); [sin,N]=mysinT(pi/2,eps)
  fprintf("cos(pi/2) cu errorare epsilon: \n"); [sin,N]=mycosT(pi/2,eps)
  
end

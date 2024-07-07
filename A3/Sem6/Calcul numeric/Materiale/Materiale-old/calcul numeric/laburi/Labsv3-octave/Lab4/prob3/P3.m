function [] = P3 ()

  [A,B] = mySystems(10);
  A
  B
  
  [A1,x] = GaussPP(A,B,10);
  disp("Rezolvare cu eliminarea gaussiana:");
  x
  
  x = rezolvareLUP(A,B);
  disp("Rezolvarea cu descompunerea LUP:");
  x

end

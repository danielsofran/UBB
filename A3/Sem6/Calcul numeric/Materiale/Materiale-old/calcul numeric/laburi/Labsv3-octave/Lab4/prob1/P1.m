function [] = P1 ()
  
  n = 10;
  [A,B] = mySystems(n);
  [A,x] = GaussPP(A,B,n);
  A
  x
end

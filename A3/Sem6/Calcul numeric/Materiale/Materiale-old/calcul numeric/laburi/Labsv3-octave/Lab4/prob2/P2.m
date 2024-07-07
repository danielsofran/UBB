function [] = P2 ()
  %Pentru A:
  %2.0000 0 2.0000 0.6000
  %3.0000 3.0000 4.0000 -2.0000
  %5.0000 5.0000 4.0000 2.0000
  %-1.0000 -2.0000 3.4000 -1.0000
  A = exMatrixA()
  [L,U,P] = descLUP(A);
  
  disp("L*U");
  disp(L*U);
  disp("P*A");
  disp(P*A);
  
  disp("Rezolvarea unui sistem Ax = b folosind descompunerea LUP");
  
  A
  B = [ 1 ; 2 ; 3 ; 4 ]
  x = rezolvareLUP(A,B);  
  x
  
end

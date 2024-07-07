function retval = P3 (input1, input2)
  
  disp("Consideram n=10");
  [A1,b1] = firstSystem(10);
  disp("Pentru primul sistem unde A este ");
  A1
  disp("si b este ");
  b1
  
  disp("Solutia rezolvata cu Jacobi este :");
  x_j1 = jacobi(A1,b1)
  
  disp("Iar solutia rezolvata cu Sor este :");
  x_s1 = sor(A1,b1)

  [A2,b2] = secoundSystem(10);
  disp("Pentru al doilea sistem unde A este ");
  A2
  disp("si b este ");
  b2
  
  disp("Solutia rezolvata cu Jacobi este :");
  x_j2 = jacobi(A2,b2)
  
  disp("Iar solutia rezolvata cu Sor este :");
  x_s2 = sor(A2,b2)

  
end

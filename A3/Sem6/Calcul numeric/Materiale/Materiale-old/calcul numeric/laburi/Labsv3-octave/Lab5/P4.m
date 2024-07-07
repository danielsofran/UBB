function P4 ()
  
  disp("Consieram n=10");
  [A,b] = mddSystem(10);
  disp("Un system cu matrice diagonal dominanta aleatoare are matricea ");
  A
  disp("si matricea ");
  b  
  
  disp("Solutia rezultata dupa aplicarea metodei Jacobi este: ");
  solution = jacobi(A,b)
  
  disp("Solutia rezultata dupa aplicarea metodei Sor este: ");
  solution = sor(A,b)
  
  disp("Solutia rezultata dupa aplicarea metodei Gauss-Siedel este: ");
  solution = gaussSiedel(A,b)
  
end

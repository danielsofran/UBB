function [] = prob1 ()
  
  %Pentru sistemul A*x = B ; unde x = [ 1 1 1 1 ] ^ T
  A = [10 7 8 7 ; 7 5 6 5 ; 8 6 10 9 ; 7 5 9 10 ];
  %A
  B = [ 32 ; 23 ; 33 ; 31 ];
  %B
  x = A \ B;
  %x = [ 1 ; 1 ; 1 ; 1 ]; 
  
  
  %Perturbarea mebrului drept a.i. sa devina [ 32.1 22.9 33.1 30.9] :
  fprintf("a) Perturbarea membrului drept: \n");
  Bp = [ 32.1 ; 22.9 ; 33.1 ; 30.9 ];
  x_PA = A \ Bp;
  
  err_absB = norm(Bp - B);
  fprintf("Eroare relativa intrare: ");
  err_relB = err_absB / norm(B)
  err_absxPA = norm(x_PA - x);
  fprintf("Eroare relativa iesire: ");
  err_relxPA = err_absxPA / norm(x)
  fprintf("Factor de aplificare : ");
  factor_amplificare = err_relxPA / err_relB
  %cond(A)
  
  fprintf("b) Perturbarea matricei sistemului: \n");
  Ap = [ 10 7 8.1 7.2 ; 7.08 5.04 6 5 ; 8 9.98 9.98 9 ; 6.99 4.99 9 9.98 ];
  %Ap
  x_PA = Ap \ B;
  
  err_absA = norm(A - Ap);
  fprintf("Eroarea relativa intrare: ");
  err_relA = err_absA / norm(A)
  err_absxPA = norm(x_PA - x);
  fprintf("Eroare relativa iesire: ");
  err_rexPA = err_absxPA / norm(x)
  fprintf("Factor de aplificare : ");
  factor_amplificare = err_relxPA / err_relA  
 
end

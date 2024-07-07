%1. Implementati o rutina pentru calculul valorilor polinomului
%de interpolare Hermite cu noduri duble, dandu-se punctele in care
%se face evaluarea, nodurile, valorile functiei si ale derivatei

function H = P1 ()
  
   x   = 1.5;
   X   = [1.3 1.6 1.9];
   Y   = [0.6200860 0.4554022 0.2818186];
   Yd  = [-0.5220232 -0.5698959 -0.5811571]; 
   
   H = Hermite(X,Y,Yd);
   
endfunction

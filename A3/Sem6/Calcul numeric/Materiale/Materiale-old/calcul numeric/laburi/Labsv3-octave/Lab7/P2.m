%2. Reprezenta�ti pe acela�si grafic f �si polinomul sau de interpolar

function P2 ()
  
   x   = 1.5;
   X   = [1.3 1.6 1.9];
   Y   = [0.6200860 0.4554022 0.2818186];
   Yd  = [-0.5220232 -0.5698959 -0.5811571]; 
   
   [H,y] = Hermite_v2(x,X,Y,Yd);
   disp("Polinomul: "); H
   disp("Functia aproximata: "); y
endfunction

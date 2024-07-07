%Metoda de cuadratura adaptiva pentru formula trapezului
function ca = cuadraturaAdaptivaT (f,a,b,e)
  
   t = @trapez;
   n  = 5;
   ca1 = t(f,a,b,n);
   ca2 = t(f,a,b,n*2);
   if abs(ca1-ca2) < e 
       ca = ca2;
       return
   else
       ca = cuadraturaAdaptivaT(f,a,(a+b)/2,e) + cuadraturaAdaptivaT(f,(a+b)/2,b,e);
   end
  
endfunction

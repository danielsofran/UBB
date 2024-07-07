%Metoda de cuadratura adaptiva pentru formula lui Simpson
function ca = cuadraturaAdaptivaS(f,a,b,e)

   s = @Simpson;
   n  = 5;
   
   ca1 = s(f,a,b,n);
   ca2 = s(f,a,b,n*2);
   
   if abs(ca1-ca2) < e 
       ca = ca2;
       return
   else
       ca = cuadraturaAdaptivaS(f,a,(a+b)/2,e) + cuadraturaAdaptivaS(f,(a+b)/2,b,e);
   end

endfunction

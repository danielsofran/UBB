%Metoda de cuadratura adaptiva pentru formula dreptunghiului
function ca = cuadraturaAdaptivaD (f,a,b,e)

   d = @dreptunghi;
   n  = 5;
   ca1 = d(f,a,b,n);
   ca2 = d(f,a,b,n*2);
   
   if abs(ca1-ca2) < e 
       ca = ca2;
       return
   else
       ca = cuadraturaAdaptivaD(f,a,(a+b)/2,e) + cuadraturaAdaptivaD(f,(a+b)/2,b,e);
   end

endfunction

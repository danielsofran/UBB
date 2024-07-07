function s = Simpson (f,a,b,n)

  dx = (b-a)/n; 
  s = f(a)+f(b);
  
  for i = 1:n-1
      xi = a+i*dx;
      if mod(i,2) == 1
         s = s + (4 * f(xi));
      else
         s = s + (2 * f(xi));
      end
  end
  
  s =  (dx/3) * s;

endfunction

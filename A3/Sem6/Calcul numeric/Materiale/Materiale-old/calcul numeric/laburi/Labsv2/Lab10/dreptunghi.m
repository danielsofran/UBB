%Formula treptata a dreptunghiului
function d = dreptunghi (f,a,b,n)

  dx = (b-a)/n; 
  d = 0;
  
  prev = 0;
  for i=1:n
      xi = a+i*dx;
      d = d + f( (prev+xi) /2);
      prev = xi;
  end
  d =  dx * d;
  
endfunction

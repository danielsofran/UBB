function c=Chebyshevcoeff(f,n)
%CHEBYSHEVCOEFF - least square Cebyshev coeffs.
%call c=Chebyshevcoeff(f,n)
%f - function
%n - degree 
%c - coefficients

for k=0:n
    fceb=@(x) cos(k*x).*f(cos(x));
    c(k+1)=2/pi*quadgk(fceb,0,pi,'AbsTol',1e-12,'MaxIntervalCount',1000);
end

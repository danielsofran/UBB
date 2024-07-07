%{
    1. Solve the equation:
                x = cos(x)
    using Newton's method for: x0=pi/4, e = 10^(-4) and maximum number of
    iterations N = 100.
%}

% Solution:
x0 = pi/4;
e = 10^(-4);
%N = 4;
N = 100;
%f = x - cos(x);
%f_derivative = 1 + sin(x);

f_0 = x0 - cos(x0);
f_derivative_0 = 1 + sin(x0);

points0 = x0;
points1 = x0 - cos(x0);

%{
for i = 2:N
  points(i) = points(i-1) - cos(points(i-1));
endfor

for i = 1:N
  f_points(i) = points(i) - cos(points(i));
  f_derivated(i) = 1 + sin(points(i));
endfor

for i = 1:N
  F(i) = points(i) - (f_points(i)/f_derivated(i));
endfor
%}
k = 1;
while(abs(points1-points0)/abs(points1) > e)
  f_points = points1 - cos(points1);
  f_derivated = 1 - sin(points1);
  F = points1 - (f_points/f_derivated);
  points0 = points1;
  points1 = points0 - cos(points0);
  k = k + 1; 
endwhile
sol = F;
numberOfIterations = k;

clear()


%{
    2. For finding the position of a satellite for t = 9 minutes, we have to
       solve Kepler's equation:
       
       f(E) = E - 0.8*sin(E)-((2*pi)/10)=0
       
       Type the results obtained applying Newton's method 6 times, starting with
       E = 1.
%}

% Solution:
E0 = 1;
%f = E - 0.8*sin(E)-((2*pi)/10);
N = 6;
%f_derivative = 1 - 0.8*cos(E);

f_0 = E0 - 0.8*sin(E0)-((2*pi)/10);
f_derivative_0 = 1 - 0.8*cos(E0);

E = zeros(1,N);
E1 = E0 - 0.8*sin(E0)-((2*pi)/10);

for i = 3:N
  E(i) = E(i-1) - 0.8*sin(E(i-1))-((2*pi)/10);
endfor

for i = 1:N
  f_points(i) = E(i) - 0.8*sin(E(i))-((2*pi)/10);;
  f_derivated(i) = 1 - 0.8*cos(E(i));
endfor

for i = 1:N
  F(i) = E(i) - (f_points(i)/f_derivated(i));
endfor


sol = F(N);

clear()

%{
    3. Check the performances of Newton's method in two versions:
    
        standard: x(k+1) = x(k) - (f(x(k))/f'(x(k)))
        root of multiplicity m: x(k+1) = x(k) - m*(f(x(k))/f'(x(k)))
        
      to approximate the multiple zero alpha = 1 of the function 
      f(x) = (x^2-1)^p * log(x) (for p>=1 and x>0). The desired root has 
      multiplicity m = p+1. Consider the value p = 2 and x0 = 0.8, e = 10^(-10).
      Type the number of iterations required to converge for each case.
%}

% Solution:
alpha = 1;
%f = ((x^2-1)^p)*log(x);
p = 2;
m = p+1;
x0 = 0.8;
e = 10^(-10);

% Standard:
f_0 = ((x0^2-1)^p)*log(x0);
f_derivative_0 = 2*p*x0*((x0^2-1)^(p-1))*log(x0)+(((x0^2-1)^p)/x0);

x1 = x0-(f_0/f_derivative_0);
k = 1;
while(abs(x1-x0)/abs(x1) > e)
  %f_points = ((x1^2-1)^p)*log(x1);
  %f_derivated = 2*p*x1*((x1^2-1)^(p-1))*log(x1)+(((x1^2-1)^p)/x1);
  x1 = x1 - ((((x0^2-1)^p)*log(x0))/(2*p*x0*((x0^2-1)^(p-1))*log(x0)+(((x0^2-1)^p)/x0)));
  x0 = x1;
  %x1 = (((x0)^2-1)^p)*log(x0);
  k = k + 1;
endwhile
sol = x1;
numberOfIterations = k;
clear()

% Root of muliplicity m:
p = 2;
m = p+1;
x0 = 0.8;
e = 10^(-10);
f_0 = ((x0^2-1)^p)*log(x0);
f_derivative_0 = 2*p*x0*((x0^2-1)^(p-1))*log(x0)+(((x0^2-1)^p)/x0);

points0 = x0;
points1 = ((x0^2-1)^p)*log(x0);
k = 1;
while(abs(points1-points0)/abs(points1) > e)
  f_points = (((points1^2)-1)^p)*log(points1);
  f_derivated = 2*p*points1*(((points1^2)-1)^(p-1))*log(points1)+((((points1^2)-1)^p)/points1);
  F = points1 - m*(f_points/f_derivated);
  points0 = points1;
  points1 = (((points0)^2-1)^p)*log(points0);
endwhile
sol = F;
numberOfIterations = k;
clear()

%{
    4. Consider f(x) = x*e^x - 1 = 0 with solution alpha = 0.5671432. Consider a 
       fixed point iteration x(k+1) = e^(-x(k)), a second fixed point iteration 
       x(k+1) = (1+x(k))/(e^x(k)+1) and a third one 
       x(k+1) = x(k) + 1 - x(k)*e^(x(k)). Take the initial guess x0 = 0.5, 
       e = 10^(-10). For each case, type the approximation and the number of 
       iterations necessary to reach the given precision. (Use format long).
%}

% Solution:
alpha = 0.5671432;
x0 = 0.5;
e = 10^(-10);
%f = x*e^x-1=0

% First fixed point iteration:
numberOfIterations = 1;
x1 = exp(-x0);

while(abs(x1-x0) > e)
  f_points = x1*exp(x1)-1;
  f_derivated = exp(x1) + x1*exp(x1);
  F = x1 - (f_points/f_derivated);
  x0 = x1;
  x1 = exp(-x0);
  numberOfIterations = numberOfIterations + 1;
endwhile
numberOfIterations
F
format long 

clear()

% Second fixed point iteration:
alpha = 0.5671432;
x0 = 0.5;
e = 10^(-10);
numberOfIterations = 1;
x1 = (1+x0)/(exp(x0)+1);

while(abs(x1-x0) > e)
  f_points = x1*exp(x1)-1;
  f_derivated = exp(x1) + x1*exp(x1);
  F = x1 - (f_points/f_derivated);
  x0 = x1;
  x1 = (1+x0)/(exp(x0)+1);
  numberOfIterations = numberOfIterations + 1;
endwhile
numberOfIterations
F
format long

clear()

% Third fixed point iteration:
alpha = 0.5671432;
x0 = 0.5;
e = 10^(-10);
k = 1;
x1 = x0+1-x0*exp(x0);

while(abs(x1-x0) > e)
  f_points = x1*exp(x1)-1;
  f_derivated = exp(x1) + x1*exp(x1);
  F = x1 - (f_points/f_derivated);
  x0 = x1;
  x1 = x0+1-x0*exp(x0);
  k = k + 1;
endwhile
numberOfIterations = k;
F
format long

clear()





































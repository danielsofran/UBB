%{
    1. Solve the system:
    
      { x1^3+3*x2^2-21 = 0
      { x1^2+2*x2+2 = 0
      
     using Newton's method with x0 = (1,-1), e=10^(-6);
%}

% Solution:
x0 = [1 ; -1];
e = 10^(-6);

J = [3*(x0(1)^2), 6*x0(2) ; 2*x0(1), 2];
f_0 = [x0(1)^3+3*x0(2)^2-21 ; x0(1)^2+2*x0(2)+2];
x1 = x0 - inv(J)*f_0;

numberOfSteps = 0;

k = 1;
while((abs(norm(x1)-norm(x0)) > e) && (k <= 100))
  J = [3*(x0(1)^2), 6*x0(2) ; 2*x0(1), 2];
  f_0 = [x0(1)^3+3*x0(2)^2-21; x0(1)^2+2*x0(2)+2];
  [aux] = x1;
  x1 = x0 - inv(J)*f_0;
  [x0] = aux;
  k = k + 1;
endwhile

[sol] = x1;
numberOfSteps = k;


clear()


%{
    2. Find the approximative solution of the following Cauchy problem:
    
         { y'(x) = 2*x-y
         { y(0) = -1
      
      for the equidistant nodes xi = a+i*h, i=0,...,N; h = (b-a)/N, with a=0,
      b = 1, N = 10, using Euler's method and Runge-Kutta method of 4th order. 
      Plot the obtained approximations together with the exact solution 
      y(x)=exp(-x)+2*x-2, in the same figure.
%}

% Solution:
a = 0;
b = 1;
N = 10;

h = (b-a)/N;

for i = 1:(N+1)
  x(i) = a + (i-1)*h; 
endfor

% Euler's method:
alpha = -1;
y0 = alpha;
y1 = y0 + h*(2*x(1)-y0);

k = 2;
while((abs(y1-y0) > e) && (k <= 100))
  aux = y1;
  y1 = y0 + h*(2*x(k)-y0);
  y0 = aux;
  k = k + 1;
endwhile

sol = y1;
numberOfIterations = k;

clear()

% Runge-Kutta method:
a = 0;
b = 1;
N = 10;

h = (b-a)/N;

for i = 1:(N+1)
  x(i) = a + (i-1)*h; 
endfor

exact_sol = exp(-x)+2*x-2;
plot(x,exact_sol, 'k');
hold on

alpha = -1;
y0 = alpha;

k1 = h * (2*x(1)-y0);
k2 = h * (2*(x(1)+h/2)-(y0+k1/2));
k3 = h * (2*(x(1)+h/2)-(y0+k2/2));
k4 = h * (2*x(2)-(y0+k3));
y1 = y0 + (k1+2*k2+2*k3+k4)/6;

pol = zeros(01, 100);
pol(1) = y0;
pol(2) = y1;

k = 2;
while((abs(y1-y0) > e) && (k <= 100))
  aux = y1;
  k1 = h * (2*x(k)-y0);
  k2 = h * (2*(x(k)+h/2)-(y0+k1/2));
  k3 = h * (2*(x(k)+h/2)-(y0+k2/2));
  k4 = h * (2*x(k+1)-(y0+k3));
  y1 = y0 + (k1+2*k2+2*k3+k4)/6;
  y0 = aux;
  k = k + 1;
endwhile
 
plot(x,pol)

sol = y1; 
numberOfIterations = k;

clear()

























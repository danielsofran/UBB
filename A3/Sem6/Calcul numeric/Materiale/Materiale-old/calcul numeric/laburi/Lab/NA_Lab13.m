%{
    1. Use the secant method, with x0 = 1 and x1 = 2 to solve:
        x^3-x^2-1 = 0
       with e = 10^(-4) and maximum number of iterations N=100. Type also the
       the necessary number of steps.
%}

% Solution:

x0 = 1;
x1 = 2;
%x2 = x1-(x1^3 - x1^2 - 1)*((x1-x0)/((x1^3 - x1^2 - 1)-(x0^3 - x0^2 - 1)));
x2 = 0;

f_0 = x0^3 - x0^2 - 1;
f_1 = x1^3 - x1^2 - 1;
f_2 = x2^3 - x2^2 - 1;

e = 10^(-4);
N = 100;

numberOfSteps = 0;

FL1 = x1 - (((x1-x0)*f_1)/(f_1-f_0));

k = 1;
while((abs(x2-x1) > e) && (k <= N))
  aux1 = x2;
  aux2 = x1;
  x2 = x1-(x1^3 - x1^2 - 1)*((x1-x0)/((x1^3 - x1^2 - 1)-(x0^3 - x0^2 - 1)));
  x1 = aux1;
  x0 = aux2;
  k = k + 1;
endwhile

sol = x2;
numberOfSteps = k;

clear()


%{
    2. Let f:[1,2] -> R, f(x) = (x-2)^2-log(x). Solve the equation f(x) = 0, 
       using bisection and false position methods, for e = 10^(-4) and maximum
       number of iterations N=100. Type also the necessary number of steps. (Use
       abs(f(c))<e as a stopping criterion.)
%}

% Solution:
%x = 1:0.1:2;
%f = (x.-2).^2.-log(x);
e = 10^(-4);
N = 100;
numberOfSteps = 0;

% Bisection method:
a = 1;
b = 2;
f_a = (a-2)^2-log(a);
f_b = (b-2)^2-log(b);
c = (a+b)/2;
f_c = (c-2)^2-log(c);

k = 1;
while((abs((c-2)^2.-log(c)) > e) && (k <= N))
  f_a = (a-2)^2-log(a);
  f_b = (b-2)^2-log(b);  
  f_c = (c-2)^2-log(c);
  
  if(f_c*f_b < 0)
    a = c;
    c = (a+b)/2;
  else
    if(f_a*f_c < 0)
      b = c;
      c = (a+b)/2;
    endif
  endif
  k = k + 1;
endwhile

sol = (a+b)/2;
numberOfSteps = k;

clear()

% False position method:
a = 1;
b = 2;

e = 10^(-4);
N = 100;

numberOfSteps = 0;
f_a = (a-2)^2-log(a);
f_b = (b-2)^2-log(b);
c = (a+b)/2;
f_c = (c-2)^2-log(c);

k = 1;
while((abs((c-2)^2.-log(c)) > e) && (k <= N))
  f_a = (a-2)^2-log(a);
  f_b = (b-2)^2-log(b);  
  f_c = (c-2)^2-log(c);
  
  if(f_c*f_b < 0)
    a = c;
    c = (a*f_b-b*f_a)/(f_b-f_a);
  else
    if(f_a*f_c < 0)
      b = c;
      c = (a*f_b-b*f_a)/(f_b-f_a);
    endif
  endif
  k = k + 1;
endwhile

sol = (a+b)/2;
numberOfSteps = k;


clear()


%{
    3. The function f(x) = x-0.2*sin(x)-0.5 has exactly one zero between
       x0 = 0.5 and x1 = 1, since f(0.5)*f(1) < 0, while f'(x) does not vanish
       on [0.5,1]. Locate the zero correct to six significant digits using 
       Newton's, secant and bisection methods. Type how many steps each method 
       would require to produce six significant digits and compute the errors 
       at each step.
       (Exact solution is 0.61546850)
%} 

% Solution:

x0 = 0.5;
x1 = 1;
f_0 = x0-0.2*sin(x0)-0.5;
%f_1 = x1-0.2*sin(x1)-0.5;
e = 10^(-6);
N = 100;
actual_sol = 0.61546850;

f_derivated_0 = 1 - 0.2*cos(x0);
f_derivated_1 = 1 - 0.2*cos(x1);

% Newton's method:
numberOfIterations = 0;
k = 1;
while((abs(x1-x0) > e) && (k <= N))
  f_0 = x0 - 0.2*sin(x0) - 0.5;
  f_derivated_0 = 1 - 0.2*cos(x0);
  aux = x1;
  x1 = x0 - (f_0/f_derivated_0); 
  x0 = aux;
  k = k + 1;
endwhile

sol = x1;
err = abs(actual_sol-sol);
numberOfIterations = k;

clear()

% Secant method:
actual_sol = 0.61546850;
x0 = 0.5;
x1 = 1;
x2 = 0;
f_0 = x0-0.2*sin(x0)-0.5;
f_1 = x1-0.2*sin(x1)-0.5;
e = 10^(-6);
N = 100;
numberOfIterations = 0;

k = 1;
while((abs(x2-x1) > e) && (k <= N))
  aux1 = x2;
  aux2 = x1;
  x2 = x1-(x1-0.2*sin(x1)-0.5)*((x1-x0)/((x1-0.2*sin(x1)-0.5)-(x0-0.2*sin(x0)-0.5)));
  x1 = aux1;
  x0 = aux2;
  k = k + 1;
endwhile

sol = x2;
err = abs(actual_sol-sol);
numberOfIterations = k;

clear()

% Bisection method:
actual_sol = 0.61546850;
e = 10^(-6);
N = 100;
a = 0.5;
b = 1;
c = (a+b)/2;
f_a = a-0.2*sin(a)-0.5;
f_b = b-0.2*sin(b)-0.5;
f_c = c-0.2*sin(c)-0.5;
numberOfSteps = 0;

k = 1;
while((abs(c-0.2*sin(c)-0.5) > e) && (k <= N))
  f_a = a-0.2*sin(a)-0.5;
  f_b = b-0.2*sin(b)-0.5;
  f_c = c-0.2*sin(c)-0.5;
  
  if(f_c*f_b < 0)
    a = c;
    c = (a+b)/2;
  else
    if(f_a*f_c < 0)
      b = c;
      c = (a+b)/2;
    endif
  endif
  k = k + 1;
endwhile

sol = (a+b)/2;
err = abs(actual_sol-sol);
numberOfSteps = k;

clear()


%{
    4. Inverse interpolation:
        Suppose f is of class C1 on [a,b], f'(x)!=0 on [a,b] and f has one zero
        p in [a,b]. Let x0,...,xn be n+1 distinct numbers in [a,b] with
        f(xk) = yk, for each k=0,1,...,n.  To approximate p, construct the
        interpolating polynomial of degree n on the nodes y0,..., yn for f^(-1).
        Since yk=f(xk) and 0 = f(p), it follows that f^(-1)(yk)=xk and 
        p = f^(-1)(0). Using iterated interpolation to approximate f^(-1)(0) is 
        called ITERATED INVERSE INTERPOLATION.
        
       a) Use iterated inverse interpolation to find an approximation to the 
          solution of x-exp(-x)=0, using the data:
         
         x    |   0.3           0.4           0.5         0.6
      --------|--------------------------------------------------
      exp(-x) | 0.740818      0.670320     0.606531    0.548812

       b) Compare your result with the one obtained applying Newton's method
          for solving this equation.
      (Indication: for the construction of the interpolating polynomial you may
      use a function that returns the value of the Lagrange polynomial or the
      Matlab functions polyfit and polyval. Notice that you don't have 
      yk = f(xk) given in the table, but you may obtain them from the 
      given data.)
%}

% Solution:
% a)
x = [0.3, 0.4, 0.5, 0.6];
n = length(x)-1;
exp_values = [0.740818, 0.670320, 0.606531, 0.548812];
y = x .- exp_values;

f_inverse = polyfit(y,x,4);

p = polyval(f_inverse,0);

clear()

% b)
% Newton's method:
x0 = 0.3;
x1 = 0.4;
exp_values = [0.740818, 0.670320, 0.606531, 0.548812];
f_0 = x0-exp_values(1);
%f_1 = x1-0.2*sin(x1)-0.5;
e = 10^(-6);
N = 100;

f_derivated_0 = 1 + exp_values(1);
f_derivated_1 = 1 + exp_values(2);

% Newton's method:
numberOfIterations = 0;
k = 1;
while((abs(x1-x0) > e) && (k <= N))
  f_0 = x0-exp(-x0);
  f_derivated_0 = 1 + exp(-x0);
  aux = x1;
  x1 = x0 - (f_0/f_derivated_0); 
  x0 = aux;
  k = k + 1;
endwhile

p = x1;
numberOfIterations = k;

clear()
























































































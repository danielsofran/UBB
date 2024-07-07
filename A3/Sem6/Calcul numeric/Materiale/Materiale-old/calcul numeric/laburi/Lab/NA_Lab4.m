% 1. Using the data from the following table:

%   x  |  1  |    1.5    |     2     |     3     |     4   
% -----------------------------------------------------------
% lg(x)|  0  |  0.17609  |  0.30103  |  0.47712  |  0.60206

% Approximate lg(2.5) and lg(3.25) using the Newton interpolation polynomial.
% Estimate the maximum interpolation error E = max|f(yi)-(N4f)(yi)|, with 
% yi=i/10, for i = 10, ..., 35

% Solution:
x = [1, 1.5, 2, 3, 4];
f = [0, 0.17609, 0.30103, 0.47712, 0.60206];
x0 = 2.5;
function [np] = NP(x,f,x0)
  n = length(x);
  a(1) = f(1);
  for k = 1:1:n-1
     % The divided difference formula for m=1:
     d(k, 1) = (f(k+1).-f(k))./(x(k+1).-x(k));
  endfor
  
  for j = 2:1:n-1
    % The divided difference formula for m>=2:
     for k = 1:1:n-j
        d(k, j) = (d(k+1, j-1).-d(k, j-1))./(x(k+j).-x(k));
     endfor
  endfor
  
  for j = 2:1:n
     a(j) = d(1, j-1);
  endfor
  Df(1) = 1;
  c(1) = a(1);
  for j = 2 : n
     Df(j) = (x0.-x(j-1)).*Df(j-1);
     c(j) = a(j).*Df(j);
  endfor
  np = sum(c);
endfunction

x = [1, 1.5, 2, 3, 4];
f = [0, 0.17609, 0.30103, 0.47712, 0.60206];
x0 = 2.5;
sol = NP(x,f,x0);

for i = 10:1:35
  y(i) = i/10;
  f1(i) = log10(y(i));
  E(i) = abs(f1(i).-NP(x,f,y(i)));
endfor
E_max = max(E);

clear()

% 2. To investigate the relationship between yield of potatoes, y, and level of
%    fertilizer, x, an experimenter divided a field into 5 plots of equal size 
%    and applied differing amounts of fertilizer to each. The recorded data are 
%    given in the table(in pounds).

%   x  |  1 |  2 |  3 |  4 |  5   
% -------------------------------
%   y  | 22 | 23 | 25 | 30 | 28  

% a) According to Newton interpolation polynomial, approximate how many
%    pounds of potatoes are expected from a plot to which 2.5: pounds of 
%    fertilizer had been applied.
% b) Plot the data given in the table and the corresponding Newton interpolation 
%    polynomial.

% Solution:
% a) Little explanation first, for when I come back to this exercise:
%    - x = level of fertilizer (the quantity of fertilizer applied to that plot)
%    - y = the quantity of potatoes harvested from said plot

x = [1, 2, 3, 4, 5];
y = [22, 23, 25, 30, 28];

x0 = 2.5;
function [np] = NP(x,f,x0)
  n = length(x);
  a(1) = f(1);
  for k = 1:1:n-1
     % The divided difference formula for m=1:
     d(k, 1) = (f(k+1).-f(k))./(x(k+1).-x(k));
  endfor
  
  for j = 2:1:n-1
    % The divided difference formula for m>=2:
     for k = 1:1:n-j
        d(k, j) = (d(k+1, j-1).-d(k, j-1))./(x(k+j).-x(k));
     endfor
  endfor
  
  for j = 2:1:n
     a(j) = d(1, j-1);
  endfor
  Df(1) = 1;
  c(1) = a(1);
  for j = 2 : n
     Df(j) = (x0.-x(j-1)).*Df(j-1);
     c(j) = a(j).*Df(j);
  endfor
  np = sum(c);
endfunction

sol = NP(x,y,x0);

% b)
plot(x, y, 'k')
hold on 

for i=1:1:5
  plot(i, NP(x,y,i),'o- r')
  hold on
endfor
hold off

clear()

% 3. Consider the function f : [0, 6] -> R, f(x) = e^(sin(x)) and 13 equidistant 
%    interpolation points. Plot the interpolation points, the function f and 
%    the Newton intepolation polynomial N12f.

% Solution:

function [np] = NP(x,f,x0)
  n = length(x);
  a(1) = f(1);
  for k = 1:1:n-1
     % The divided difference formula for m=1:
     d(k, 1) = (f(k+1).-f(k))./(x(k+1).-x(k));
  endfor
  
  for j = 2:1:n-1
    % The divided difference formula for m>=2:
     for k = 1:1:n-j
        d(k, j) = (d(k+1, j-1).-d(k, j-1))./(x(k+j).-x(k));
     endfor
  endfor
  
  for j = 2:1:n
     a(j) = d(1, j-1);
  endfor
  Df(1) = 1;
  c(1) = a(1);
  for j = 2 : n
     Df(j) = (x0.-x(j-1)).*Df(j-1);
     c(j) = a(j).*Df(j);
  endfor
  np = sum(c);
endfunction

x = [0, 2, 4, 6];
f = exp(sin(x));
k = 1;
for i = 0:0.5:6
    p(k) = NP(x, f, i);
    k = k + 1;
end
i = 0:0.01:6;
f = exp(sin(i));
plot(i, f);
hold on
i = 0:0.5:6;
plot(i, p, '*');
hold on

k = 1;
x = [0, 2, 4, 6];
f = exp(sin(x));
for i = 0:0.1:6
    p(k) = NP(x, f, i);
    k = k + 1;
end

i = 0:0.1:6;
plot(i, p);

clear()

% 4. Approximate sqrt(115) with precision e = 10^(-3), using Aitken's algorithm.

% Solution:
x = [64, 81, 100, 121, 144];
f = [8, 9, 10, 11, 12];
x0 = 115;
f1 = sqrt(x0);

function f1 = Aitken(x,f,x0)    
    n=length(x);
    D=x0.-x;
    A(:,1)=f;
    for i=2:n
        for j=2:i
            A(i,j) = (D(j-1).*A(i,j-1).-D(i).*A(j-1,j-1))./(x(i).-x(j-1));
        endfor
    endfor
    f1 = A(n,n);
endfunction

sol = Aitken(x,f,x0);
remainder = abs(f1 - sol);


clear()


% 5. Use Neville's algorithm to approximate sqrt(3) for:
% a) the function f1(x) = 3^x and the nodes x0 = -2, x1 = -1, x2 = 0, x3 = 1, 
%    x4 = 2.
% b) the function f2(x) = sqrt(x) and the nodes x0 = 0, x1 = 1, x2 = 2, 
%    x3 = 4, x4 = 5. 

% Solution:
% a) 
x = [-2, -1, 0, 1, 2];
f1 = 3.^x;
x0 = 3;

function [sol] = Neville(x,f,x0)
  n = length(x);
  Q = zeros(n,n);
  for i = 1:n
      Q(i,1) = f(i);
  end
  for j = 2:n
      for i = j+1:n
          Q(i,j) = ((x0-x(i-j)) * Q(i,j-1)/(x(i)-x(i-j))) + ((x(i)-x0) * Q(i-1,j-1)/(x(i)-x(i-j)))
      end
  end
  sol = Q(n,n-1);
endfunction

sol_a = Neville(x,f1,x0);

clear()

% b) 
x0 = 3
x = [0, 1, 2, 4, 5];
f1 = sqrt(x);

function [sol] = Neville(x,f,x0)
  n = length(x);
  Q = zeros(n,n);
  for i = 1:n
      Q(i,1) = f(i);
  end
  for j = 2:n
      for i = j+1:n
          Q(i,j) = ((x0-x(i-j)) * Q(i,j-1)/(x(i)-x(i-j))) + ((x(i)-x0) * Q(i-1,j-1)/(x(i)-x(i-j)))
      end
  end
  sol = Q(n,n-1);
endfunction

sol_b = Neville(x,f1,x0);

clear()


% Facultative problems:
% 1. Consider the function f:[-5,5]->R, f(x) = sin(x) and 20 equidistant 
%    interpolation points. Plot the interpolation points, the function f and the 
%    Lagrange interpolation polynomial obtained using Aitken's algorithm with 
%    precision e = 10^(-3).

% Solution:
x = -5:0.1:5;
f = sin(x);
x0 = 0:pi:19*pi;

function f1 = Aitken(x,f,x0)    
    n=length(x);
    D=x0.-x;
    A(:,1)=f;
    for i=2:n
        for j=2:i
            A(i,j) = (D(j-1).*A(i,j-1).-D(i).*A(j-1,j-1))./(x(i).-x(j-1));
        endfor
    endfor
    f1 = A(n,n);
endfunction






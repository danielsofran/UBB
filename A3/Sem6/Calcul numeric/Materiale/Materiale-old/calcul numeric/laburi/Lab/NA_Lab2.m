% 1. The first 4 Legendre polynomials are given by:
% l1(x) = x
% l2(x) = (3/2)*(x^2)-(1/2)
% l3(x) = (5/2)*(x^3)-(3/2)*x
% l4(x) = (35/8)*(x^4)-(15/4)*(x^2)+(3/8),
% x in [0,1].
% Divide the display in 4 parts and plot in each part the Legendre polynomial
% li, i = 1,2,3,4. (Use the subplot command).

% Solution:
x = 0:0.01:1
l1 = x
l2 = (3/2)*(x.^2)-(1/2)
l3 = (5/2)*(x.^3)-(3/2)*x
l4 = (35/8)*(x.^4)-(15/4)*(x.^2)+(3/8)

subplot(221);
plot(x,l1);
title (sprintf("l1"));

subplot(222);
plot(x,l2);
title (sprintf("l2"));

subplot(223);
plot(x,l3);
title (sprintf("l3"));

subplot(224);
plot(x,l4);
title (sprintf("l4"));

clear()

% 2. a) Chebyshev polynomials of the first kind are defined by:
%       Tn(t) = cos(n*arccos(t)), t in [-1,1]
%    Plot, in the same figure, the polynomials T1, T2, T3.
%    b) Plot, in the same figure, the first n Chebyshev polynomials of the first
%    kind, using the following reccurence formula:
%    Tn+1(x) = 2*x*Tn(x)-Tn-1(x), x in [-1,1], with T0(x) = 1 and T1(x) = x.

% Solution:
% a)
t = -1:0.1:1
T1 = cos(acos(t))
T2 = cos(2*acos(t))
T3 = cos(3*acos(t))

plot(t, T1)
hold on 
plot(t, T2)
hold on 
plot(t, T3)

clear()

% b)
function T(n)
  x = -1:0.1:1
  if(n = 0)
    Tf = 1;
  elseif(n = 1)
    Tf = x;
  else
    Tf = 2*x.*T(n-1) - T(n-2);
  endif
end
x = -1:0.1:1
n = input("Enter the value for n:");

for i = 1:n
  subplot(n, n, i);
  plot(x,T(i));
  title(sprintf("T%d",i));
endfor

clear() 

% 3. Taylor polynomial of n-th degree, associated to the function f and
%    the point x0 is given by Pn(x) = ... .Plot, in the same figure, the first 
%    six Taylor polynomials for f(x) = exp(x) and x0 = 0, on the interval [-1,3]

% Solution:

x = -1:0.01:3
x0 = 0

tay = 1
t = 1

plot(x, exp(x), 'r')
hold on 
for k = 1:6
  
  t = t.*x/k;
  tay = tay + t;
  plot(x, tay);
  hold on;
endfor

clear() 
  
% 4. Considering h = 0.25, xi = 1+ih; i = 0,6, and f(x) = sqrt(5*x^2+1),
%    construct the finite differences table.

% Solution:


function fi
  h = 0.25;
  i = 0:1:6;
  x = 1 + i.*h;
  f = sqrt(5.*x.^2+1);
  for i = 1:7
    diff(i, 1) = f(i);
  endfor
  
  for j = 2:7
    
    for i = 1:7-j+1
      diff(i,j) = diff(i+1, j-1) - diff(i, j-1);
    endfor
    
  endfor
  
  fprintf('\n\tx\t        f(x)\t       D1\t         D2\t         D3\t          D4\t        D5\t         D6\t');
  for i = 1:7
    fprintf('\n        %.4f', x(i));
    for j = 1:7-i+1
      fprintf('\t      %.4f', diff(i,j));
    endfor
  endfor
end
 

% 5. For x0 = 2, x1 = 4, x2 = 6, x3 = 8 and f0 = 4, f1 = 8, f2 = 14, f3 = 16 
%    construct the divided differences table.

% Solution:

function fii
  x = 2:2:8;
  f = ([4 8 14 16]);
  for i = 1:4
    diff(i, 1) = f(i);
  endfor
  k = 1;
  for j = 2:4
    
    for i = 1:4-j+1
      diff(i,j) = (diff(i+1, j-1) - diff(i, j-1))/(x(i+k)-x(i));
    endfor
    k = k + 1;
  endfor
  
  fprintf('\n\tx\t        f(x)\t          D1\t         D2\t         D3\t          D4\t        D5\t         D6\t');
  for i = 1:4
    fprintf('\n        %.4f', x(i));
    for j = 1:4-i+1
      fprintf('\t      %.4f', diff(i,j));
    endfor
  endfor
end































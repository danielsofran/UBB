% 1. In the following table there are some data regarding a moving car. Use
%    Hermite interpolation to estimate position and speed of the car when the 
%    time is t = 10.
%  Time:        0    3     5      8      13
%  Distance:    0   225   383    623    993
%  Speed:      75    77    80     74     72

% Solution:

% Little side note: Distance: value of the function at the given time 
%                   Speed   : first derivative in function of time

nodes = [0, 0, 3, 3, 5, 5, 8, 8, 13, 13];
distance = [0, 225, 383, 623, 993];     % first derivative
speed = [75, 77, 80, 74, 72];           % second derivative


function [hp] = Hermite(x0,f,nodes)
  nodes = [0, 0, 3, 3, 5, 5, 8, 8, 13, 13];
  f = [0, 0, 225, 225, 383, 383, 623, 623, 993, 993];
  speed = [75, 75, 77, 77, 80, 80, 74, 74, 72, 72];
  n = length(nodes);
  a(1) = f(1);
  for k = 1:1:n-1
     % The divided difference formula for m=1:
     if nodes(k)==nodes(k+1)
       d(k,1) = speed(k);
     else 
       d(k,1) = (f(k+1).-f(k))./(nodes(k+1).-nodes(k));
     endif 
  endfor
  
  for j = 2:1:n-1
    % The divided difference formula for m>=2:
     for k = 1:1:n-j
        d(k, j) = (d(k+1, j-1).-d(k, j-1))./(nodes(k+j).-nodes(k));
     endfor
  endfor
  
  for j = 2:1:n
     a(j) = d(1, j-1);
  endfor
  Df(1) = 1;
  c(1) = a(1);
  for j = 2 : n
     Df(j) = (x0.-nodes(j-1)).*Df(j-1);
     c(j) = a(j).*Df(j);
  endfor
  hp = c
  
endfunction

sol = sum(Hermite(10, distance, nodes));
derivative_f = sum(diff(Hermite(10, distance, nodes)))


clear()

% 2. With f(x) = ln x, calculate f(1.5) by cubic interpolation, using f(1) = 0,
%    f(2) = 0.6931, f'(1) = 1, f'(2) = 0.5. Find the absolute approximation 
%    error.

% Solution:
% Cubic interpolation = fancy scary name for Hermite interpolation polynomial 
% of order 3
x0 = 1.5;
x = [1, 1, 2, 2];
f1 = log(x0);
f = [0, 0, 0.6931, 0.6931];
f_derivated = [1, 1, 0.5, 0.5];

function [hp] = Hermite(x0,f,nodes)
  nodes = [1, 1, 2, 2];
  f = [0, 0, 0.6931, 0.6931];
  f_derivated = [1, 1, 0.5, 0.5];
  n = length(nodes);
  a(1) = f(1);
  for k = 1:1:n-1
     % The divided difference formula for m=1:
     if nodes(k)==nodes(k+1)
       d(k,1) = f_derivated(k);
     else 
       d(k,1) = (f(k+1).-f(k))./(nodes(k+1).-nodes(k));
     endif 
  endfor
  
  for j = 2:1:n-1
    % The divided difference formula for m>=2:
     for k = 1:1:n-j
        d(k, j) = (d(k+1, j-1).-d(k, j-1))./(nodes(k+j).-nodes(k));
     endfor
  endfor
  
  for j = 2:1:n
     a(j) = d(1, j-1);
  endfor
  Df(1) = 1;
  c(1) = a(1);
  for j = 2 : n
     Df(j) = (x0.-nodes(j-1)).*Df(j-1);
     c(j) = a(j).*Df(j);
  endfor
  hp = polyval(sum(c),x0);
  
endfunction

val = Hermite(x0, f, x);

err = abs(f1-Hermite(x0,f,x));

clear()

% 3. Plot, in the same figure, the graphs of the function f:[-5,5] ->R, 
%    f(x) = sin(2x), and of the corresponding Hermite interpolation polynomial, 
%    considering 15 equidistant nodes in [-5,5].

% Solution:

% Plotting the graph of the function:
x = -5:0.1:5;
f1 = sin(2.*x);
plot(x,f1,'k')
hold on

% For the Hermite interpolation ploynomial, we need:
% (i) the 15 equidistand nodes, doubled
nodes_half = -5:0.7:5;
nodes = zeros(1, 30);

for i = 1:2:29
  nodes(i) = nodes_half((i+1)/2);
endfor

for i = 2:2:30
  nodes(i) = nodes_half(i/2);
endfor

% (ii) the values of the function at the 15 equidistant nodes, doubled as well
f = sin(2.*nodes);

% (iii) the values of the derivative of the function at the 15 equidistant 
% points, again doubled ((sin(2x))' = 2*cos(2x))
f_derivated = 2.*cos(2.*nodes);

% Now, we will write the Hermite interpolation ploynomial function with the data
% above: 
function [hp] = Hermite(x0, f, nodes, f_derivated)
  %{
    The Hermite interpolation polynomial 
    
    Parameters:
    x0 - the point where we want to approximate the value of the function 
    f - the values of the function at the nodes chosen as data for the interp.
    nodes - the data used for forming the interpolation polynomial 
    f_derivated = the values of the first derivative of f at the nodes 
    
    Returns: a number representing the approximation of f(x0)
  %}
  n = length(nodes);
  a(1) = f(1);
  for k = 1:1:n-1
     % The divided difference formula for m=1:
     if nodes(k)==nodes(k+1)
       d(k,1) = f_derivated(k);
     else 
       d(k,1) = (f(k+1).-f(k))./(nodes(k+1).-nodes(k));
     endif 
  endfor
  
  for j = 2:1:n-1
    % The divided difference formula for m>=2:
     for k = 1:1:n-j
        d(k, j) = (d(k+1, j-1).-d(k, j-1))./(nodes(k+j).-nodes(k));
     endfor
  endfor
  
  for j = 2:1:n
     a(j) = d(1, j-1);
  endfor
  Df(1) = 1;
  c(1) = a(1);
  for j = 2 : n
     Df(j) = (x0.-nodes(j-1)).*Df(j-1);
     c(j) = a(j).*Df(j);
  endfor
  hp = polyval(sum(c),x0);
endfunction

% We will form a vector of the values of the Hermite interpolation on the 
% interval -5:5:

k = 1
for i = -5:0.01:5
  p(k) = Hermite(i, f, nodes, f_derivated);
  k = k + 1;
endfor
i = -5:0.01:5;
plot(i,p, 'b')


clear()































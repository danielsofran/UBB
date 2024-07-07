%Using the barycentric form of the Lagrange interpolation polynomial, solve the following problems:

% 1. The table below contains the population of the USA from 1930 to 1980 (in thousands of inhabitants):
%  1930      1940       1950         1960       1970         1980   
% 123203    131669     150697       179323     203212       226505

% Approximate the population in 1955 and 1995.

% Solution:
x0 = [1930, 1940, 1950, 1960, 1970, 1980];
f = [123203, 131669, 150697, 179323, 203212, 226505];
m = 6;

function [u] = ui(i)
   x0 = 1930:10:1980;
   m = 6;
   u = 1;
   for j = 1:1:m
      if j == i
        continue;
      endif
      u = u.*(x0(i).-x0(j));  
    endfor
end

function [A] = Ai(i)
   A = 1./ui(i);
end

function [L] = LP(x)
    x0 = [1930 1940 1950 1960 1970 1980];
    f = [123203 131669 150697 179323 203212 226505];
    m = 6;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:m
      sum1 = sum1 .+ ((Ai(i).*f(i))./(x.-x0(i)));
      sum2 = sum2 .+ (Ai(i)./(x.-x0(i)));
    endfor
    L = sum1./sum2;
end
val_1975 = LP(1975)
val_1955 = LP(1955) % => 164766.01171875
val_1995 = LP(1995) % => 401064.5429687492

clear()


% 2. Approximate sqrt(115) with Lagrange interpolation, using the known values 
%    for three given nodes.

% Solution:
x0 = [100, 121, 144];
f = [10, 11, 12];
m = 3;

function [u] = ui(i)
   x0 = [100, 121, 144];
   m = 3;
   u = 1;
   for j = 1:1:m
      if j == i
        continue;
      endif
      u = u.*(x0(i).-x0(j));  
    endfor
end

function [A] = Ai(i)
   A = 1./ui(i);
end

function [L] = LP(x)
    x0 = [100, 121, 144];
    f = [10, 11, 12];
    m = 3;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:m
      sum1 = sum1 .+ ((Ai(i).*f(i))./(x.-x0(i)));
      sum2 = sum2 .+ (Ai(i)./(x.-x0(i)));
    endfor
    L = sum1./sum2;
end

val_sqrt_115 = LP(115) % => sqrt(115) ~ 10.723

clear()

% 3. Plot the graphics of the function f : [0, 10]->R, f(x)=(1+cos(pi*x))/(1+x)
%    and of the Lagrange interpolation polynomial that interpolates the function
%    f at 21 equally spaced points in the interval [0, 10].

% Solution:
x = 0:0.1:10;
x0 = 0:0.47:10;
f1 = (1.+cos(pi.*x))./(1.+x);
f = (1.+cos(pi.*x0))./(1.+x0);
m = 21;
plot(x,f1,'r')
hold on 

function [u] = ui(i)
   x0 = 0:0.47:10;
   m = 21;
   u = 1;
   for j = 1:1:m+1
      if j == i
        continue;
      else 
        u = u.*(x0(i).-x0(j));
      endif  
    endfor
end

function [A] = Ai(i)
   A = 1./ui(i);
end

function [L] = LP(x)
    x0 = 0:0.47:10;
    f = (1.+cos(pi.*x0))./(1.+x0);
    m = 21;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:m+1
      sum1 = sum1 .+ ((Ai(i).*f(i))./(x.-x0(i)));
      sum2 = sum2 .+ (Ai(i)./(x.-x0(i)));
    endfor
    L = sum1./sum2;
end

plot(x, LP(x))
for i = 0:0.01:10 
  plot(i,LP(i))
  hold on
endfor
hold off 

clear()

% 4. Consider the function f:[-5,5]->R, f(x)=1/(1+x^2). For n=2,4,...,8, compute
%    Lagrange polynomial of degree n which interpolates f(x) at the n+1 equally 
%    spaced points xi=((i*10)/n)-5, i=0,...,n. Then estimate the maximum
%    interpolation error:
%                  En: max|f(x)-Pn(x)|, n=2,4,...,8
%                   -5<=x<=5

%    on the interval [-5,5] by computing:
%                  En ~ max|f(yi)-Pn(yi)|,

%    where yi=(i/10)-5, i=0,...,100

% Solution:
x = -5:0.1:5;
f = 1./(1.+x.^2);

n = 2:2:8;

function [u] = ui(i,x0)
      m = length(x0)-1;
      u = 1;
      for j = 1:1:m+1
          if j == i
            continue;
          else 
            u = u.*(x0(i).-x0(j));
          endif  
      endfor
    end

function [A] = Ai(i,x0)
    A = 1./ui(i,x0);
end

function [L] = LP(x,f,x0)
    m = length(x0)-1;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:m+1
        sum1 = sum1 .+ ((Ai(i,x0).*f(i))./(x.-x0(i)));
        sum2 = sum2 .+ (Ai(i,x0)./(x.-x0(i)));
    endfor
    L = sum1./sum2;
end

x = -5:0.01:5;
f = 1./(1.+x.^2);
plot(x,f,'k')
hold on

for n = 2:2:8
  for i = 1:1:n+1
    x0(i) = (((i-1)*10)/n)-5;
  endfor
  f1 = 1./(1.+x0.^2);
  k(:,n/2) = LP(x,f1,x0);
  plot(x,k)
  hold on
endfor
hold off 

clear()


x1 = 0:1:100;
x = x1./10 - 5;
f = 1./(1+x.^2);
plot(x, f, 'k');
hold on
for n = 2:2:8
    for i = 0:1:n
        j = i.*10./n - 5;
        h(i+1) = j;
    end
    f1 = 1./(1+h.^2);
    k(:,n/2) = LP(x,f1,h);
    plot (x,k);
    clear h;
    hold on
end
hold off
for i = 1:1:4
    for j = 1:1:101
        l(j) = abs(f(j)-k(j, i));
    end
    Ex1(i) = max(l);
end
Ex = max(Ex1);



clear()

for i = 1:1:9
  sol = LP(x,f,x0(i));
endfor

% The part with the max error:
x = -5:0.01:5;
f = 1./(1.+x.^2);
k = length(sol);
for i = 1:1:k
  for j = 1:1:k
    l(j) = abs(f(j)-sol(j));
  endfor
  E1(i)=max(l);
endfor
E_max = max(E1);


clear()


% Facultative:
% Consider the function f:[-pi/4,pi/4]->R, f(x)=cos(x), and the given nodes 0,
% pi/4, pi/3.
% a) Plot the fundamental interpolation polynomials li(x)=ui(x)/ui(xi), 
%    i=0,...m.
% b) Compute the value of Lagrange interpolation polynomial at x=pi/6 using both
%    the classical formula (Lmf)(x)=sum(li(x)*f(xi)), i=0,m, and the barycentric
%    formula.
% c) Plot the graphs of the function f and of the corresponding Lagrange
%    interpolation polynomial.
% d) Give two other sets of nodes in [-pi/4,pi/4] and plot the corresponding 
%    Lagrange interpolation polynomials.

% Solution:
x = -pi/4:0.001:pi/4;
x0 = [0, pi/4, pi/3];
f1 = cos(x)
f = cos(x0);
m = 2;
plot(x,f1,'r')
hold on

% a)
function [u] = u(x)
  x = -pi/4:0.01:pi/4;
  x0 = [0, pi/4, pi/3];
  m = length(x0);
  u = 1;
  for j = 1:1:m+1
    u = u.*(x(j).-x0(j))
  endfor
end


function [u] = ui(i)
   x0 = [0, pi/4, pi/3];
   m = 2;
   u = 1;
   for j = 1:1:m+1
      if j == i
        continue;
      endif
      u = u.*(x0(i).-x0(j));  
    endfor
end

function [l] = li(i,x)
  l = u(x)./ui(i);
end

x0 = [0, pi/4, pi/3];
f = cos(x0);
plot(x,LP(x,f,x0))

clear()

% b)
% Barycentric form:
function [u] = ui(i)
   x0 = [0, pi/4, pi/3];
   m = 2;
   u = 1;
   for j = 1:1:m+1
      if j == i
        continue;
      endif
      u = u.*(x0(i).-x0(j));  
    endfor
end

function [A] = Ai(i)
   A = 1./ui(i);
end

function [L] = LP(x)
    x0 = [0, pi/4, pi/3];
    f = cos(x0);
    m = 2;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:m+1
      sum1 = sum1 .+ ((Ai(i).*f(i))./(x.-x0(i)));
      sum2 = sum2 .+ (Ai(i)./(x.-x0(i)));
    endfor
    L = sum1./sum2;
end

val_x_bary = LP(pi/6)

clear()

% Classical form:
x = -pi/4:0.001:pi/4;
x0 = [0, pi/4, pi/3];
f = cos(x0);
m = 2;
function [u] = u(x)
  x = -pi/4:0.01:pi/4;
  x0 = [0, pi/4, pi/3];
  m = 2;
  u = 1;
  for j = 1:1:m+1
    u = u.*(x(j).-x0(j))
  endfor
end

function [u] = ui(i)
   x0 = [0, pi/4, pi/3];
   m = 2;
   u = 1;
   for j = 1:1:m+1
      if j == i
        continue;
      endif
      u = u.*(x0(i).-x0(j));  
    endfor
end

function [l] = li(i,x)
  l = u(x)./ui(i);
end

function [L] = LPC(x)
  x0 = [0, pi/4, pi/3];
  f = cos(x0);
  m = 2;
  
  sum = 0;
  
  for i = 1:1:m+1
    sum = sum .+ (li(i,x).*f(i));
  endfor
  L = sum
end

var_x_classic = LPC(pi/6)

clear()

% c)
x = -pi/4:0.01:pi/4;
x0 = [0, pi/4, pi/3];
f1 = cos(x);
f = cos(x0);
m = 2;

plot (x,f1,'k')
hold on 

function [u] = ui(i)
   x0 = [0, pi/4, pi/3];
   m = 2;
   u = 1;
   for j = 1:1:m+1
      if j == i
        continue;
      endif
      u = u.*(x0(i).-x0(j));  
    endfor
end

function [A] = Ai(i)
   A = 1./ui(i);
endfunction

function [L] = LP(x)
    x0 = [0, pi/4, pi/3];
    f = cos(x0);
    m = 2;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:1:m+1
      sum1 = sum1 .+ ((Ai(i).*f(i))./(x.-x0(i)));
      sum2 = sum2 .+ (Ai(i)./(x.-x0(i)));
    endfor
    L = sum1./sum2;
endfunction

x = -pi/4:0.01:pi/4;
plot(x,LP(x),'c')
hold on

clear()


% d)
x = -pi/4:0.01:pi/4;
x0_new = [0, pi/2, pi, 3*pi/2, 2*pi];
x0 = [0, pi/4, pi/3];
f1 = cos(x);
f = cos(x0);
f2 = cos(x0_new)
m = 4;

plot (x,f1,'k')
hold on 

function [u] = ui_new(i)
   x0_new = [0, pi/2, pi, 3*pi/2, 2*pi];
   m = 4;
   u = 1;
   for j = 1:1:m+1
      if j == i
        continue;
      endif
      u = u.*(x0_new(i).-x0_new(j));  
    endfor
endfunction

function [A] = Ai_new(i)
   A = 1./ui_new(i);
endfunction

function [L] = LP_new(x)
    x0_new = [0, pi/2, pi, 3*pi/2, 2*pi];
    f2 = cos(x0_new);
    m = 4;
    
    sum1 = 0;
    sum2 = 0;
  
    for i = 1:1:m+1
      sum1 = sum1 .+ ((Ai_new(i).*f2(i))./(x.-x0_new(i)));
      sum2 = sum2 .+ (Ai_new(i)./(x.-x0_new(i)));
    endfor
    L = sum1./sum2;
endfunction

x = -pi/4:0.01:pi/4;
plot(x,LP_new(x),'y')
hold off 

clear()
























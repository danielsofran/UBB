%{
    1. a) Use the rectangle formula to evaluate the integral:
          I = integral from 1 to 1.5 from e^(-x^2)dx
       b) Plot the graph of the function f and the graph of the rectangle whose
          area approximates the integral, using rectangle (midpoint) formula.
       c) Use the repeated rectangle formula, for n = 150 and 500, to evaluate
          the integral:
          I = integral from 1 to 1.5 from e^(-x^2)dx.
          (Result: 0.1094)
%}

% Solution:

% a)
a = 1;
b = 1.5;
M = (a+b)/2;
f_M = exp(-M^2);
f_a = exp(-a^2);
f_b = exp(-b^2);

I = (b-a)*f_M;
% b)
x = 1:0.01:1.5;
f = exp(-x.^2);
plot(x,f,'k')
hold on
plot([a,a,b,b,a],[0,f_M,f_M,0,0], 'g')

% c)
a = 1;
b = 1.5;
n = 150;
x1 = a + (b-a)/(2*n);

for i = 2:n
  x(i) = x1 + ((i-1)*(b-a))/n;
endfor

sum = 0;
for i = 1:n
  sum = sum + exp(-x(i)^2);
endfor

I = ((b-a)/n)*sum;


clear()


%{
    2.  Consider the integral:
        I = integral from 0 to 1 of 2/(1+x^2)dx
     a) Approximate the integral using the Romberg algorithm for trapezium
     formula, for precision e = 10^(-4).
     b) Approximate the integral using the Romberg algorithm in Aitken�s
     form, for precision e = 10^(-4).
%}

% Solution:

% a)
a = 0;
b = 1;
e = 10^(-4);
f_a = 2/(1+a^2);
f_b = 2/(1+b^2);

h = b-a;

function rt = RombergTrapezium(a,b,err)
  h = b-a;
  f_a = 2/(1+a^2);
  f_b = 2/(1+b^2); 
  f_c = 2/(1+(a+(h/2))^2);
  rt1 = (h/2)*(f_a+f_b);
  rt2 = (h/4)*(f_a+2*f_c+f_b);
  k = 3;
  aux = 0;
  sum1 = 0;
  
  while(abs(rt1-rt2)<err)
    aux = rt2;
    for j=1:1:2^(k-1)
      sum1 = sum1 + 2/(1+((a+((2*j-1)/(2^k)))*h)^2);  
    endfor
    
    rt2 = rt1/2 + (h/(2^k))*sum1;
    rt1 = aux;
    aux = 0;
    k = k + 1;
  endwhile
  rt = rt2;
end

sol = RombergTrapezium(a,b,e);

clear()


% b)
a = 0;
b = 1;
e = 10^(-4);
f_a = 2/(1+a^2);
f_b = 2/(1+b^2);

function f1 = RombergAitken(a,b,err)    
    h = b-a;
    f_a = 2/(1+a^2);
    f_b = 2/(1+b^2);
    ra = 

end

% Solutia lui Adryel:
e = 10^(-4);
a = 0;
b = 1;
T=zeros(20);

okay = 1;
i = 1;
while(okay==1)
  %{ 
    Some explanations because I'm stupid and I forget easily:
    1. We divide the interval [0,1] in i+1 portions of equal length
    2. We apply the trapezium formula to get the first column
    3. With the next for, we generate the rest of the table (formula in Course 8)
    4. With the following if, we check the epsilon condition, to see if the error 
    we get by subtracting the consecutive elements on the main diagonal is less
    than the value 10^(-4). If it is, we can exit the while loop and the last 
    element on the main diagonal is the approximation we need
  %}
  
  % 1.
  x = 0:1/(i+1):1;
  f = 2./(1+x.^2);
  
  % 2.
  trapezium = f(1)+f(i+2);
  for k = 2:i+1
    trapezium = trapezium .+ 2.*f(k);
  endfor
  trapezium = trapezium.*(1./(2*i));
  T(i,1)=trapezium;

  % 3.
  for k=2:i
    T(i,k)=(4^(-k+1).*T(i-1,k-1).-T(i,k-1))./(4^(-k+1)-1);
  endfor

  % 4.
  if(i>1)
    if(abs(T(i,i)-T(i-1,i-1))<e)
      okay=0;
    else
      i+=1;
    endif
    
  else
    i+=1;
  endif
endwhile

sol = T(i,i)

clear()


%{
    3. Plot the graph of f:[1,3]->R, f(x)=(100/(x^2))*sin(10/x). Use an adaptive
       quadrature algorithm for Simpson's formula to approximate the integral:
       I = integral from 1 to 3 from f(x) dx,
       with precision e = 10^(-4). Compare the obtained result with the one 
       obtained applying repeated Simpson's formula for n = 50 and 100. (The
       exact value is -1.4260247818.)
%}

% Solution:

x = 1:0.1:3;
f = (100./(x.^2)).*sin(10./x);
plot(x,f,'k')
e = 10^(-4);

function s = Simpson(a,b)
  c = (a+b)/2;
  fa = (100/(a^2))*sin(10/a);
  fb = (100/(b^2))*sin(10/b);
  fc = (100/(c^2))*sin(10/c);
  s = ((b-a)/6)*(fa + 4*fc + fb);
end

function I=AdaptiveQuadrature(a,b,err)
  I1 = Simpson(a,b);
  I2 = Simpson(a,(a+b)/2)+Simpson((a+b)/2, b);
  
  if(abs(I1-I2)<15*err)
    I=I2;
    return 
  else 
    I = AdaptiveQuadrature(a,(a+b)/2,err/2)+AdaptiveQuadrature((a+b)/2,b,err/2);
  endif
end

I = AdaptiveQuadrature(1,3,e);

a = 1;
b = 3;
n1 = 50;
n2 = 100;

function rs = RepeatedSimpson(a,b,n)
  k = 0;
  h = (b-a)/n;
  f_a = (100/(a^2))*sin(10/a);
  f_b = (100/(b^2))*sin(10/b);
  sum1 = 0;
  sum2 = 0;
  for i = 1:2:n-1
    xi=a+(i*h);
    sum1=sum1+(100/(xi^2))*sin(10/xi);
  end
  for i = 2:2:n-2
    xi=a+(i*h);
    sum2=sum2+(100/(xi^2))*sin(10/xi);
  end

  rs = (h/3)*(f_a+f_b+4*sum1+2*sum2);
end

I_Simpson1 = RepeatedSimpson(a,b,n1);
I_Simpson2 = RepeatedSimpson(a,b,n2);

real_sol = -1.4260247818;


clear()































%{
  1. a) Approximate the integral:
      I = intergal from 0 to 1 from f(x)dx  for f(x)=2/(1+x^2)
     using the trapezium formula.
     
     b) Plot the graph of the function f and the graph of the trapezium with
     vertices (0,0), (0,f(0)), (1,f(1)) and (1,0).
     
     c) Approximate the integral I using Simpson's formula.
%}

% Solution:
% a)
x = 0:0.01:1;
f = 2./(1.+x.^2);
a = 0;
b = 1;
fa = 2/(1+a^2);
fb = 2/(1+b^2);
c = (a+b)/2;
fc = 2/(1+c^2);
diff2_f = -4.*((1.-3.*x.^2)./((1.+x.^2).^3));

sol = ((b-a)/2)*(fb + fa);

% b) 

plot(x,f)
hold on 
plot([0,0,1,1,0],[0,fa,fb,0,0],"g")

% c) 

simpson = ((b-a)/6)*(fa + 4*fc + fb);


clear()

%{
  2. Approximate the following double integral:
  
     I = integral from 1.4 to 2 (integral from 1 to 1.5 from ln(x+2y)dy)dx
 
     using trapezium formula for double integrals, given in (1). 
     (Result: 0.4295545)
%}

% Solution:

a = 1.4;
b = 2;
c = 1;
d = 1.5;

f_ac = log(a.+2.*c);
f_ad = log(a.+2.*d);
f_bc = log(b.+2.*c);
f_bd = log(b.+2.*d);
f_a_b_2_c = log(((a.+b)./2).+2.*c);
f_a_b_2_d = log(((a.+b)./2).+2.*d);
f_a_c_d_2 = log(a.+2.*((c.+d)./2));
f_b_c_d_2 = log(b.+2.*((c.+d)./2));
f_a_b_2_c_d_2 = log(((a.+b)./2).+2.*((c.+d)./2));

I = (((b.-a).*(d.-c))./16).*(f_ac.+f_ad.+f_bc.+f_bd.+2.*f_a_b_2_c.+2.*f_a_b_2_d.+2.*f_a_c_d_2.+2.*f_b_c_d_2.+4.*f_a_b_2_c_d_2);

clear()

%{
  3. Evaluate the integral that arises in electrical field theory (check Lab8),
     for r = 110, p = 75, using the repeated trapezium formula for two given 
     values of n.
     (Result : 6.3131) 
%}

% Solution:
r = 110;
p = 75;

%f = (1.-((p./r).^2).*sin(x1(i))).^(1/2);

a = 0;
b = 2*pi;
n1 = 500000;

k = 0;
h = (b-a)/n1;
for i = 1:n1+1
  x1(i) = a+k*h;
  k = k+1;
endfor

sum1 = 0;
for i = 1:n1+1
  sum1 = sum1 + (1.-((p./r).^2).*sin(x1(i))).^(1/2);
endfor

f_a = (1.-((p./r).^2).*sin(a)).^(1/2);
f_b = (1.-((p./r).^2).*sin(b)).^(1/2);
I1 = ((b-a)/(2*n1))*(f_a+f_b+2*sum1)

H1 = ((60*r)/(r^2-p^2))*I1;

clear()


%{
    4. Find the smallest value of n that gives an approximation of the integral
    I = integral from 1 to 2 of x*log(x)dx which is correct to three decimals, 
    using the repeated trapezium formula. Apply the repeated trapezium formula 
    for the obtained value of n to approximate the integral. 
    (Result: 0.636294368858383 )
%}

% Solution:

a = 1;
b = 2;
f_a = a*log(a);
f_b = b*log(b);

n = floor(sqrt(((b-a)^3)/(12*0.001)))+1;

k = 0;
h = (b-a)/n;
for i = 1:n+1
  x1(i) = a+k*h;
  k = k+1;
endfor

sum = 0;
for i = 1:n+1
  sum = sum + (x1(i)*log(x1(i)));
endfor

I = ((b-a)/(2*n))*(f_a+f_b+2*sum);

clear()


%{
    5. Evaluate the integral:
    I = integral from 0 to pi from dx/(1+sin(20x))
    using the repeated Simpson's formula for n = 10 and 30.
    (Result : 0.8111579 )

%}

% Solution:

a = 0;
b = pi;
f_a = 1/(1+sin(20*a));
f_b = 1/(1+sin(20*b));
% For n1 = 10:
n1 = 10;

k = 0;
h = (b-a)/n1;
for i = 1:n1+1
  x1(i) = a+k*h;
  k = k+1;
endfor

sum2 = 0;
for i = 1:n1-1
  sum2 = sum2 + (1/(1+sin(20*x1(i))));
endfor

for i = 1:n1
  y(i) = (x1(i)+x1(i+1))/2;
endfor

sum1 = 0;
for i = 1:n1
  sum1 = sum1 + 1/(1+sin(20*y(i)));
endfor

I_n1 = ((b-a)/(6*n1))*(f_a+f_b+4*sum1+2*sum2);

clear()

% For n2 = 30:
a = 0;
b = pi;
f_a = 1/(1+sin(20*a));
f_b = 1/(1+sin(20*b));
n2 = 30;

k = 0;
h_2 = (b-a)/n2;
for i = 1:n2+1
  x1_2(i) = a+k*h_2;
  k = k+1;
endfor

sum2_2 = 0;
for i = 1:n2-1
  sum2_2 = sum2_2 + 1/(1+sin(20*x1_2(i)));
endfor

for i = 1:n2
  y_2(i) = (x1_2(i)+x1_2(i+1))/2;
endfor

sum1_2 = 0;
for i = 1:n2
  sum1_2 = sum1_2 + 1/(1+sin(20*y_2(i)));
endfor

I_n2 = ((b-a)/(6*n2))*(f_a+f_b+4*sum2_2+2*sum1_2);

clear()


%{
    6. The error function erf(x) is denoted by:
    erf(x)=(2/sqrt(pi))*(integral from 0 to x from e^(-t^2)dt)
    Use the repeated Simpson's formula to evaluate erf(0.5) with n = 4 and
    n = 10. Estimate the accuracy of your result and compare with the correct
    value erf(0.5) = 0.520499876.
%}

% Solution:
n1 = 10;
a = 0;
x = 0.5;
f_a = exp(e^(-a^2));
f_x = exp(e^(-x^2));

h = (x-a)/n1;
k = 0;
for i = 1:n1+1
  x1(i) = a+k*h;
  k = k+1;
endfor

sum2 = 0;
for i = 1:n1-1
  sum2 = sum2 + exp(e^(-x1(i)^2));
endfor

for i = 1:n1
  y(i) = (x1(i)+x1(i+1))/2;
endfor

sum1 = 0;
for i = 1:n1
  sum1 = sum1 + exp(e^(-y(i)^2));
endfor

I = ((x-a)/(6*n1))*(f_a+f_x+4*sum1+2*sum2);

erf = (2/sqrt(pi))*I;

difference = abs(0.520499876-erf);

clear()







































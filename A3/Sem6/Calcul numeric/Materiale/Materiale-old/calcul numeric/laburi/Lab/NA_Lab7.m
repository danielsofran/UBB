%{
  1. The following table list the temperatures of a room recorded during the
     time interval [1:00, 7:00]. Find the best liniar least squares function 
     phi(x)=a*x+b that approximates the table, using the normal equations 
     (equations (2) from Course 6). Use your result to predict the temperature 
     of the room at 8:00. Find the minimum value E(a,b) (equation (1) from 
     Course 6), for the obtained a and b. In the same figure, plot the points 
     (Time, Temperature) and the least squares function.
     
        Time:     1:00    2:00    3:00    4:00    5:00    6:00    7:00
     Temperature:  13      15      20      14      15      13      10
%}

% Solution:

time = [1, 2, 3, 4, 5, 6, 7]; % this is x
temperature = [13, 15, 20, 14, 15, 13, 10]; % this is f(x)

function [phi] = LeastSquares(x)
  time = [1, 2, 3, 4, 5, 6, 7]; % this is x
  temperature = [13, 15, 20, 14, 15, 13, 10]; % this is f(x)
  sum1 = 0;
  sum2 = 0;
  sum3 = 0;
  sum4 = 0;
  sum5 = 0;
  m = length(time)-1;
  for i=1:m+1
    sum1 = sum1 .+ time(i).*temperature(i); 
    sum2 = sum2 .+ time(i);
    sum3 = sum3 .+ temperature(i);
    sum4 = sum4 .+ (time(i))^2;
    sum5 = sum5 .+ time(i); 
  endfor

  a = ((m+1).*sum1 .- sum2 .* sum3)./((m+1).*sum4 .- (sum5)^2);
  b = (sum4.*sum3 .- sum1.*sum2)./((m+1).*sum4 .- (sum5)^2);
  phi = a.*x .+ b;  
endfunction

sol = LeastSquares(8);

plot(time, temperature, "+")
hold on 

plot(time, LeastSquares(time), "r")

sum_0 = 0;
time = [1, 2, 3, 4, 5, 6, 7]; % this is x
temperature = [13, 15, 20, 14, 15, 13, 10]; % this is f(x)
m = length(time)-1;

for i = 1:m+1
  sum_0 = sum_0 .+ (temperature(i).-LeastSquares(i))^2;
endfor
e = sum_0;



clear()


%{
  2. The vapor pressure P of the water (in bars) as a function of temperature T 
     (in bars) is:
     
     T (temperature):   0        10       20       30       40       60      80      100
      P (pressure):   0.0061   0.0123   0.0234   0.0424   0.0728   0.1992  0.4736   1.0133  

  a)  Obtain two least squares approximations for the given data, using polyfit 
for 2 different degrees of the polynomials. Find their values for T = 45 using
polyval. Compute the approximation errors, knowing that the exact value is
P(45) = 0.095848.

  b) Plot the interpolation points, the least squares approximants and the
interpolation polynomial, in the same figure.
%}

% Solution:

temperature = [0, 10, 20, 30, 40, 60, 80, 100];
pressure = [0.0061, 0.0123, 0.0234, 0.0424, 0.0728, 0.1992, 0.4736, 1.0133];

% a)
approx_1 = polyfit(temperature, pressure, 2);
approx_2 = polyfit(temperature, pressure, 3);

T = 45;
P = 0.095848;
sol_1 = polyval(approx_1,T);
sol_2 = polyval(approx_2,T);

approx_error_1 = abs(P-sol_1);
approx_error_2 = abs(P-sol_2);

% b)
plot(temperature, pressure, "+")
hold on 
plot(temperature, polyval(approx_1,temperature), "r")
hold on 
plot(temperature, polyval(approx_2,temperature), "g")


clear()


%{
  3. Consider 10 random points in the plane [0,3]X[0,5] using Matlab function
     ginput. Plot the points and the least squares polynomial of 2nd degree 
     that best fits these points.
%}

% Solution:

[x, y, buttons] = ginput(10);
plot(x,y)
hold on

least_squares_1 = polyfit(x, y, 2);
plot(x, polyval(least_squares_1,x))





clear()





































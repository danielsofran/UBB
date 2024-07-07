%{
   1. Solve the following system using Jacobi, Gauss-Seidel and relaxation
      iterative methods, for e = 10^(-3):
      (  3  -1   0   0   0   0  )      ( x1 )     ( 2 )
      ( -1   3  -1   0   0   0  )      ( x2 )     ( 1 )
      (  0  -1   3  -1   0   0  )  *   ( x3 )  =  ( 1 )
      (  0   0  -1   3  -1   0  )      ( x4 )     ( 1 )
      (  0   0   0  -1   3  -1  )      ( x5 )     ( 1 )
      (  0   0   0   0  -1   3  )      ( x6 )     ( 2 )
      
      Return the solution of the system and the number of iterations needed for
      finding the solution.
%}

% Solution

A = [3, -1, 0, 0, 0, 0 ; -1, 3, -1, 0, 0, 0 ; 0, -1, 3, -1, 0, 0 ; 0, 0, -1, 3, -1, 0 ; 0, 0, 0, -1, 3, -1 ; 0, 0, 0, 0, -1, 3];
b = [2; 1; 1; 1; 1; 2];
e = 10^(-3);

% Jacobi method:
x_0 = zeros(6,1);
%x_0 = [ 1 ; 1 ; 1 ; 1 ; 1 ; 1 ];
x_1 = zeros(6,1);

for i = 1:6
  % We construct the first approximation of x:
  for j = 1:6
    u(i,j) = (-A(i,j))/A(i,i);
  endfor
  c(i) = b(i)/A(i,i);
endfor

for i = 1:6
  for j = 1:6
    if(j!=i)
      x_1(i) = u(i,j)*x_0(j) + c(i);
    endif
  endfor
endfor
x_0 = x_1;
x_1 = zeros(6,1);

%{
sum1 = 0;
for i = 1:6
  % We construct the first approximation of x:
  for j = 1:6
    if(j==i)
      continue;
    else 
      sum1 = sum1 + A(i,j)*x_0(j);       
    endif
  endfor
  x_1(i) = (b(i)-sum1)/A(i,i);
  sum1 = 0;
endfor
x_0 = x_1;
%}

iteration_number = 1;
while(abs(x_1 - x_0) > e)
  aux = x_1;
  sum1 = 0;
  for i = 1:6
    for j = 1:6
      if(j!=i)
        sum1 = sum1 + A(i,j)*x_0(j);       
      endif
    endfor
    x_1(i) = (b(i)-sum1)/A(i,i);
  endfor
  x_0 = aux;
  iteration_number = iteration_number + 1;
endwhile

[sol] = x_1;

clear()

% Gauss-Seidel method:
A = [3, -1, 0, 0, 0, 0 ; -1, 3, -1, 0, 0, 0 ; 0, -1, 3, -1, 0, 0 ; 0, 0, -1, 3, -1, 0 ; 0, 0, 0, -1, 3, -1 ; 0, 0, 0, 0, -1, 3];
b = [2; 1; 1; 1; 1; 2];
e = 10^(-3);
n = length(A);

x_0 = zeros(n,1);
%x_0 = [1 ; 1 ; 1 ; 1 ; 1 ; 1 ];

for i = 1:n
  % We construct the first approximation of x:
  sum1 = 0;
  sum2 = 0;
  for j = 1:(i-1)
    sum1 = sum1 + A(i,j)*x_1(j);
  endfor
  
  for j = (i+1):n
    sum2 = sum2 + A(i,j)*x_0(j);
  endfor
  
  x_1(i) = (b(i)-sum1-sum2)/A(i,i);
endfor

x_0 = x_1;
x_1 = zeros(n,1);

iteration_number = 1;
while(abs(x_1 - x_0) > e)
  aux = x_1;
  for i = 1:n
    sum1 = 0;
    sum2 = 0;
    for j = 1:(i-1)
      sum1 = sum1 + A(i,j)*x_1(j);
    endfor
  
    for j = (i+1):n
      sum2 = sum2 + A(i,j)*x_0(j);
    endfor
  
    x_1(i) = (b(i)-sum1-sum2)/A(i,i);
  endfor
  x_0 = aux;
  iteration_number = iteration_number + 1;
endwhile

[sol] = x_1;

clear()

% Relaxation method:
A = [3, -1, 0, 0, 0, 0 ; -1, 3, -1, 0, 0, 0 ; 0, -1, 3, -1, 0, 0 ; 0, 0, -1, 3, -1, 0 ; 0, 0, 0, -1, 3, -1 ; 0, 0, 0, 0, -1, 3];
b = [2; 1; 1; 1; 1; 2];
e = 10^(-3);
n = length(A);
% w in (0,1) -> UNDER RELAXATION METHOD
% w > 1 -> OVER RELAXATION METHOD
% w = 1 -> GAUSS-SEIDEL METHOD
w = 1;

x_0 = zeros(n,1);

for i = 1:n
  % We construct the first approximation of x:
  sum1 = 0;
  sum2 = 0;
  for j = 1:(i-1)
    sum1 = sum1 + A(i,j)*x_1(j);
  endfor
  
  for j = (i+1):n
    sum2 = sum2 + A(i,j)*x_0(j);
  endfor
  
  x_1(i) = ((b(i)-sum1-sum2)/A(i,i))*w+(1-w)*x_0(i);
endfor
x_0 = x_1;
x_1 = zeros(n,1);

iteration_number = 1;
while(abs(x_1 - x_0) > e)
  aux = x_1;
  for i = 1:n
    sum1 = 0;
    sum2 = 0;
    for j = 1:(i-1)
      sum1 = sum1 + A(i,j)*x_1(j);
    endfor
  
    for j = (i+1):n
      sum2 = sum2 + A(i,j)*x_0(j);
    endfor
  
    x_1(i) = ((b(i)-sum1-sum2)/A(i,i))*w+(1-w)*x_0(i);
    sum1 = 0;
  endfor
  x_0 = aux;
  iteration_number = iteration_number + 1;
endwhile

[sol] = x_1;

clear()

%{
    2. Solve the following system using the matriceal forms of Jacobi,
       Gauss-Seidel and relaxation methods, for e = 10^(-5):
       
       (  3   1   1 )   ( x1 )   ( 12 )
       ( -2   4   0 ) * ( x2 ) = (  2 )
       ( -1   2  -6 )   ( x3 )   ( -5 )
%}
% Solution:
A = [3, 1, 1 ; -2, 4, 0 ; -1, 2, -6];
b = [12 ; 2 ; -5];
e = 10^(-5);
n = length(A);

% Jacobi method:
x_0 = zeros(n,1);
%x_0 = [ 1 ; 1 ; 1 ; 1 ; 1 ; 1 ];
x_1 = zeros(n,1);

for i = 1:n
  % We construct the first approximation of x:
  for j = 1:n
    u(i,j) = (-A(i,j))/A(i,i);
  endfor
  c(i) = b(i)/A(i,i);
endfor

for i = 1:n
  for j = 1:n
    if(j!=i)
      x_1(i) = x_1(i) + u(i,j)*x_0(j) + c(i);
    endif
  endfor
endfor
x_0 = x_1;
x_1 = zeros(n,1);
%{
sum1 = 0;
for i = 1:6
  % We construct the first approximation of x:
  for j = 1:6
    if(j==i)
      continue;
    else 
      sum1 = sum1 + A(i,j)*x_0(j);       
    endif
  endfor
  x_1(i) = (b(i)-sum1)/A(i,i);
  sum1 = 0;
endfor
x_0 = x_1;
%}

iteration_number = 1;
while(abs(x_1 - x_0) > e)
  aux = x_1;
  sum1 = 0;
  for i = 1:n
    for j = 1:n
      if(j!=i)
        sum1 = sum1 + A(i,j)*x_0(j);       
      endif
    endfor
    x_1(i) = (b(i)-sum1)/A(i,i);
  endfor
  x_0 = aux;
  iteration_number = iteration_number + 1;
endwhile

[sol] = x_1;

clear()

% Gauss-Seidel method:
A = [3, 1, 1 ; -2, 4, 0 ; -1, 2, -6];
b = [12 ; 2 ; -5];
e = 10^(-5);
n = length(A);

x_0 = zeros(n,1);
%x_0 = [1 ; 1 ; 1 ; 1 ; 1 ; 1 ];

for i = 1:n
  % We construct the first approximation of x:
  sum1 = 0;
  sum2 = 0;
  for j = 1:(i-1)
    sum1 = sum1 + A(i,j)*x_1(j);
  endfor
  
  for j = (i+1):n
    sum2 = sum2 + A(i,j)*x_0(j);
  endfor
  
  x_1(i) = (b(i)-sum1-sum2)/A(i,i);
endfor

x_0 = x_1;
x_1 = zeros(n,1);

iteration_number = 1;
while(abs(x_1 - x_0) > e)
  aux = x_1;
  for i = 1:n
    sum1 = 0;
    sum2 = 0;
    for j = 1:(i-1)
      sum1 = sum1 + A(i,j)*x_1(j);
    endfor
  
    for j = (i+1):n
      sum2 = sum2 + A(i,j)*x_0(j);
    endfor
  
    x_1(i) = (b(i)-sum1-sum2)/A(i,i);
  endfor
  x_0 = aux;
  iteration_number = iteration_number + 1;
endwhile

[sol] = x_1;

clear()

% Relaxation method:
A = [3, 1, 1 ; -2, 4, 0 ; -1, 2, -6];
b = [12 ; 2 ; -5];
e = 10^(-5);
n = length(A);
% w in (0,1) -> UNDER RELAXATION METHOD
% w > 1 -> OVER RELAXATION METHOD
% w = 1 -> GAUSS-SEIDEL METHOD
w = 0.05;

x_0 = zeros(n,1);

for i = 1:n
  % We construct the first approximation of x:
  sum1 = 0;
  sum2 = 0;
  for j = 1:(i-1)
    sum1 = sum1 + A(i,j)*x_1(j);
  endfor
  
  for j = (i+1):n
    sum2 = sum2 + A(i,j)*x_0(j);
  endfor
  
  x_1(i) = ((b(i)-sum1-sum2)/A(i,i))*w+(1-w)*x_0(i);
endfor
x_0 = x_1;
x_1 = zeros(n,1);

iteration_number = 1;
while(abs(x_1 - x_0) > e)
  aux = x_1;
  for i = 1:n
    sum1 = 0;
    sum2 = 0;
    for j = 1:(i-1)
      sum1 = sum1 + A(i,j)*x_1(j);
    endfor
  
    for j = (i+1):n
      sum2 = sum2 + A(i,j)*x_0(j);
    endfor
  
    x_1(i) = ((b(i)-sum1-sum2)/A(i,i))*w+(1-w)*x_0(i);
    sum1 = 0;
  endfor
  x_0 = aux;
  iteration_number = iteration_number + 1;
endwhile

[sol] = x_1;

clear()































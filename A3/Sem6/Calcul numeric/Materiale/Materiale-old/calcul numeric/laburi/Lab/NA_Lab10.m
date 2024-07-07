%{
  1. Consider the system:
                      Ax=b, 
  with:
       ( 10  7   8   7 )           ( 32 )
       (  7  5   6   5 )           ( 23 )
  A =  (  8  6  10   9 )  and b =  ( 33 )
       (  7  5   9  10 )           ( 31 )
  
  a) Solve this system and find its conditioning number.
   (Use Matlab function cond.)
   
  b) Solve the system:
                      Ax1 = b1
  where:
        ( 32.1 )
        ( 22.9 )
   b1 = ( 33.1 )
        ( 30.9 )
%}

% Solution: 
% a)
n = 4;
A = [10, 7, 8, 7 ; 7, 5, 6, 5 ; 8, 6, 10, 9 ; 7, 5, 9, 10];
b = [32; 23; 33; 31];

[x] = inv(A)*b;

conditioning_number = cond(A);

clear()

% b)

A_second = [10, 7, 8, 7 ; 7, 5, 6, 5 ; 8, 6, 10, 9 ; 7, 5, 9, 10];
b_second = [32.1; 22.9; 33.1; 30.9];

[x_second] = inv(A_second)*b_second;

clear()

% c)
A_third = [10, 7, 8.1, 7.2 ; 7.08, 5.04, 6, 5 ; 8, 5.98, 9.89, 9 ; 6.99, 4.99, 9, 9.98];
b_third = [32.1; 22.9; 33.1; 30.9];

[x_third] = inv(A_third)*b_third;

upper_part1 = norm(A-A_third);
lower_part1 = norm(A);

input_relative_error = upper_part1/lower_part1;


upper_part2 = norm(x-x_third);
lower_part2 = norm(x);

output_relative_error = upper_part2/lower_part2; 

rez = output_relative_error/input_relative_error;

clear()

% 2.
conds = zeros(1,6);
for n = 10:15;
  for k = 1:n
    t(k) = 1/k;
  endfor
  
  V = vander(t);
  conds(n-9) = cond(V);
endfor

[sol] = conds;


clear()

% 3.
n = 4;
A = [1, 1, 1, 1 ; 2, 3, 1, 5 ; -1, 1, -5, 3 ; 3, 1, 7, -2];
B = [10; 31; -2; 18];

% Algorithm itself:

X=zeros(1,4);
okay=1;
n=4;
for p=1:n-1
  max=A(p,p);
  maxi=p;
  for i=p+1:n
    if(A(i,p)>max)
    max = A(i,p);
    maxi=i;
    endif
  endfor
  if(maxi!=p)
    for i=1:n
      aux=A(p,i);
      A(p,i)=A(maxi,i);
      A(maxi,i)=aux;
    endfor
    aux=B(p);
    B(p)=B(maxi);
    B(maxi)=aux;
  endif
  
  for i=p+1:n
    coeff=(-1)*A(i,p)/A(p,p);
    for j=p:n
      A(i,j)+=A(p,j)*coeff;
    endfor
    B(i)+=B(p)*coeff;
  endfor
endfor

if(A(n,n)==0)
  mes="not okay"
  okay=0;
endif

if(okay==1)
  for i=n:-1:1
  X(i)=B(i);
    for j=i+1:n
    X(i)-=A(i,j)*X(j);
    endfor
    X(i)=X(i)/A(i,i);
  endfor
endif
A
X
 






for p = 1:(n-1)
  % We go by columns and we take the maximum value from the current column
  % without taking in consideration the numbers above the main diagonal
  % compute the max with the classical algorithm to get q; that's the problem: you need this specific q to make it work
  maximum = A(p,p);
  max_index = p;
  for i = (p+1):n
    if (A(i,p) > maximum)
      maximum = A(i,p);
      max_index = i;
    endif
  endfor
  
  absoluteMax = maximum;
  q = max_index;
 
  if (absoluteMax == 0)
      %fprintf("Singular matrix!");
      return
    
  else 
    if(q != p)
      % Case 2: we switch the lines
      for i=1:n
        aux = A(p,i);
        A(p,i) = A(q,i);
        A(q,i) = aux;
      endfor
      aux = b(p);
      b(p) = b(q);
      b(q) = aux;
    endif
      
    for t = p+1:n
       % We perform the necessary operations to obtain 0 below the column p:
       % i.e we put 0 under the element A(q,p) from the main diagonal and 
       % for the rest of the elements we perform the operations we would have
       % performed if we would have computed this by hand
       for j=p:n
          A(i,j) = A(i,j) - A(p,j)*(A(i,p)/A(p,p));
       endfor
          b(i) = b(i) - b(p)*(A(i,p)/A(p,p));
    endfor
    
  endif
endfor

if(A(n,n) == 0)
  % The last column is made out only of zeroes 
  fprintf("Incompatible system!");
else 
  for i=n:(-1):1
    x(i) = b(i);
    for j=i+1:n
      x(i) = x(i) - A(i,j)*x(j);
    endfor
    x(i) = x(i)/A(i,i);
  endfor
endif

[sol] = x;

clear()











































































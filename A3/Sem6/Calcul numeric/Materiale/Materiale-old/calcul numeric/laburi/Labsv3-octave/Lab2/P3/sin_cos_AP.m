
%#typeF - tipul functiei calculate : "sin" / "cos"
%#x - punctul in care se face aproximarea 
%#m - gradul polinomului de la numaratorul aproximatiei
%#n - gradul polinomului de la numitorul aproximatiei 
function result = sin_cos_AP(typeF,x,m,n)
  %#prima reducere a rangului
  x = mod(x, 2 * pi);
  
  %#Determinarea aproximatiei Pade
  
  %# A * x = B
  %#A:
  A = zeros(m,m);
  for i=m:m+n-1  
    row = i-m+1;
    for j=i:-1:i-n+1
      col = i-j+1;
      A(row,col) = deriv(typeF,j)/factorial(j);
    end
  end

  %#B:
  B = zeros(m,1);
  for i =1:n
    B(i,1) = - deriv(typeF,m+i)/factorial(m+i);
  end
  
  %#x:  (x = A/B)
  b = A \ B;
  b = [1;b];
  b = b';
  
  %#determinarea vectorului a (numarator)
  a = zeros(1,m + 1);
  for j=0:m
    s = 0;
    for l=0:j
       s = s + deriv(typeF,j-l)/factorial(j-l) * b(l+1);   
    end
    a(j+1) = s;
  end
  
  % f(x) ~ p_m/q_n
  % p_m = Sum a(i)*x^i , i=0:m  
  % q_n = Sum b(j)*x^j , j=0:n
  p_m = 0;
  for i = 1:m+1
    p_m = p_m + a(i) * x^(i-1);
  end
  
  q_n = 0;
  for i = 1:n+1
    q_n = q_n + b(i) * x^(i-1);
  end
  
  result = p_m / q_n;
end
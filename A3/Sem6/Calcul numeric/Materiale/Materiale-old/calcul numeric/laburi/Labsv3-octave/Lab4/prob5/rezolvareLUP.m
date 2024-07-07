function x = rezolvareLUP (A, B)

  [L,U,P] = descLUP(A);
  B = P*B;
  
  n = length(B);
  
  y = zeros(size(B));
  for i=1:n
    y(i) = (B(i)-L(i,1:i-1) * y(1:i-1)) / L(i,i);
  end
  
  x = zeros(size(B));
  for i=n:-1:1
    x(i) = (y(i) - U(i,i+1:n) * x(i+1:n)) / U(i,i);
  end

end

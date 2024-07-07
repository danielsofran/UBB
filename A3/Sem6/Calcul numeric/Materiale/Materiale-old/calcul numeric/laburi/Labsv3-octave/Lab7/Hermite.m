% X   - punctele date
% Y = f(X)
% Yd - derivata lui Y
% H - valorile polinomului Hermite
function H = Hermite (X,Y,Yd)
  
  size = 2*length(X);
  H = zeros(size);
  i=1;
  while i < size
      H(i,1) = Y(round(i/2));
      if i+1 <= size
        H(i+1,1) = Y(round(i/2));
      endif
      i=i+2;
  endwhile

  for i= 1: size-1
    if mod(i,2) == 0
       H(i,2) = (Y(round(i/2)+1)-Y(round(i/2)))/ (X(round(i/2)+1)-X(round(i/2)));
    else
        H(i,2) = Yd(round(i/2));
    endif
  endfor

  for i=1:size-1
    for j=3: (size - i +1)
       H(i,j) = (H(i+1,j-1) - H(i,j-1))/ (H(i+j-1,1) - H(i,1));
    endfor
  endfor
  
endfunction

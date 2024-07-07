
% X - punctele date
% Y - valorile punctelor date
% x - punct aproximat
% CN - coeficientii necunoscuti ale ecuatiilor
% b - M * c = b
% index - indexul ultimei ecuatii adaugate
function [CN,b,index] = ecuatieSpline(X,Y,x)
  
  n = length(X);
  CN = zeros(4*(n-1), 4*(n-1)); 
  b = zeros(4*(n-1),1);
  
  index = 1; 
  
  for(i=1:n-1)
    col = 4*i-3;
    CN(index,col) = 1;
		b(index) = Y(i);
    index++;
  end
  
  for(i=1:n-1)
    col = 4*i-3;
    CN(index,col:col+3) = ((X(i+1)-X(i)) * ones(1,4)) .^ [0 1 2 3];
    b(index) = Y(i+1);
    index++;
  end
  
  for(i=1:n-2)
    col = 4*i-3;
    CN(index,col+1:col+3) = ((X(i+1)-X(i)) * [1 2 3]) .^ [0 1 2];
    CN(index,4*(i+1)-2) = -1;
    index++;
  end
  
  for(i=1:n-2)
    col = 4*i-3;
    CN(index,col+2:col+3) = [2 6*(X(i+1)-X(i))];
    CN(index,4*(i+1)-1)   = -2;
    index++;
  end 
end

k=1/3;
n=1;
rest=k/1*10/1000;
while abs(rest) >=1e-12
  n++;
  rest*=(k-n+1)/n*1/1000;
 end
 n

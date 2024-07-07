
function rez=myE(n)
  
  e=abs(exp(n+1)-exp(n))/exp(n);
  i=n-1;
  E=zeros;
  E(n)=(1/(n+1))/2;
  while i>=1 
    E(i)=(1-E(i+1))/(i+1);  
    i=i-1;
  end
  %E
  rez=1/E(1);
end



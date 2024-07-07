function ttan=tanred(x)
 ttan=0;
 u=x;
 n=1;
 while abs(u)
   ttan=ttan+u;
   n+=2;
   u=-u*x^2/(n-1)/n;
 end

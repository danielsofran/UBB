function x=backwardsubs(A,b)
  n=length(b);
  x=b;
  for i=...:...:...
    x(i)=(b(i)-A(i,...:...)*x(...:...))/A(i,i);
  endfor


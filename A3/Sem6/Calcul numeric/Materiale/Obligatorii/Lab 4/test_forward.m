A=magic(5);
A=tril(A);
b=(1:5)';
x=forwardsubs(A,b)
A*x

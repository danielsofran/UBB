A=magic(5);
A=triu(A);
b=(1:5)';
x=backwardsubs(A,b)
A*x

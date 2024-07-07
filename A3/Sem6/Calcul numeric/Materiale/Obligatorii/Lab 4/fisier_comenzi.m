# comenzi folosite la lab4

A * x
A = magic(5)
A = triu(A)
A(3, :)
A(3, 3:end)
A(3, :)
b = (1:5)'
backwardsubs(A, b)
x = backwardsubs(A, b)
A * x
forwardsubs(A, b)
xf = forwardsubs(A, b)
A * xf
A / xf
xf = forwardsubs(A, b)
A * xf
clear
A = magic(5)
A = tril(A)
b = (1:5)'
x = forwardsubs(A, b)
A * x
x = forwardsubs(A, b)
A * x
A = magic(5)
b = (1:5)'
x = GaussElim(A, b)
A * x
A = magic(5)
[L, U, P] = lup(A)
L*U
P*A
A = magic(5)
b = (1:5)'
sol_sys_lup(A, b)
x = sol_sys_lup(A, b)
A * x
B = magic(5)
A = B' * B
R = Cholesky(A)
R' * R
sol_sys_chol(B, B')
sol_sys_chol(A,b)
x = sol_sys_chol(A,b)

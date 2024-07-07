% Date de intrare
n=1000000;
d0 = 3 * ones(1, n); % diagonala principalã
d1 = -1 * ones(1, n-1); % diagonala de deasupra diagonalei principale
d_1 = -1 * ones(1, n-1); % diagonala de dedesubtul diagonalei principale
b = transpose([2 , ones(1, n-2) , 4 ])% vectorul termenilor liberi
omega = 1.2; % parametrul de relaxare
error = 1e-6; % eroarea

% Apelul func?iei SOR pentru sistemul tridiagonal
[x, iterations] = SOR_tridiagonal(d0, d1, d_1, b, omega, error);

% Afi?area solu?iei ?i numãrul de itera?ii
fprintf('Solu?ia sistemului: x = \n');
disp(x);
fprintf('Numãrul de itera?ii: %d\n', iterations);

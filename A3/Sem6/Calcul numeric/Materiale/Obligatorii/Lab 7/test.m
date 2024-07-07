% Example data
x = [0, 1, 2]; % Interpolation nodes
f = [1, 2, 3]; % Function values at interpolation nodes
df = [0, 1, 0]; % Derivative values at interpolation nodes
X = linspace(0, 2, 100); % Points where you want to evaluate the interpolation

% Call the function
[H, dH] = Hermite_interpolare(x, f, df, X)


% sin(0.34)
% Hermite([0.3 0.32 0.33 0.35], [0.29552 0.31457 0.3240 0.34290], [0.95534 0.94924 0.9460 0.93937], 0.34)

% Hermite([0.3 0.32 0.35], [0.29552 0.31457 0.34290], [0.95534 0.94924 0.93937], 0.34)

% Given data
nodes = [0.3 0.32 0.33 0.35];
nodevals = [0.29552 0.31457 0.3240 0.34290];
derivs = [0.95534 0.94924 0.9460 0.93937];
point = 0.34;

% Compute Hermite interpolation
hermite_result = Hermite(nodes, nodevals, derivs, point);

% Compute function value
function_value = sin(point);

% Display the results
disp('sin(0.34):');
disp(function_value);
disp('Hermite interpolation:');
disp(hermite_result);

% Generate points for visualization
t = 0 : 0.01 : 1;

% Compute function values for visualization
sin_values = sin(t);
hermite_values = HermiteMaiMultePuncte(nodes, nodevals, derivs, t);

% Plot the results
figure;
hold on;
plot(t, sin_values, 'b', 'LineWidth', 2); % Actual function values
plot(nodes, nodevals, 'ro', 'MarkerSize', 8); % Given data points
plot(point, function_value, 'gx', 'MarkerSize', 10); % Point to interpolate
plot(t, hermite_values, 'k--', 'LineWidth', 1.5); % Hermite interpolation
legend('Actual Function', 'Given Data Points', 'Interpolation Point', 'Hermite Interpolation', 'Location', 'best');
xlabel('x');
ylabel('y');
title('Comparison of Hermite Interpolation with Actual Function');
hold off;

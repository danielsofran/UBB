exp(1.25^2-1)
l = LagrangeMaiMultePuncte([1 1.1 1.2 1.3 1.4],[exp(1^2-1),exp(1.1^2-1),exp(1.2^2-1),exp(1.3^2-1),exp(1.4^2-1)],[1:0.1:2])
LagrangeV2(1.25, @(x)exp(x.^2-1), 5, [1 1.1 1.2 1.3 1.4])
%graf2(4,[1 1.1 1.2 1.3 1.4])
graf3([1:0.1:2],exp([1:0.1:2].^2-1),l)
barycentric([1.25],[1 1.1 1.2 1.3 1.4],[exp(1^2-1),exp(1.1^2-1),exp(1.2^2-1),exp(1.3^2-1),exp(1.4^2-1)])

x_values = 1:0.1:1.4; % Define x-coordinates
y_values = exp(x_values.^2 - 1); % Calculate y-coordinates

% Plot the points
plot(x_values, y_values, 'o', 'MarkerFaceColor', 'blue', 'MarkerEdgeColor', 'blue');
xlabel('x');
ylabel('f(x)');
title('Plot of Points');
grid on;
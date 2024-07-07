function []=graf3(x,f,lag)
    figure;
    plot(x, lag, 'b'); % Plot lagrange interpolation in blue
    hold on;
    plot(x, f, 'k'); % Plot original function in black
    xlabel('x');
    ylabel('y');
    title('Comparison of Lagrange Interpolation and Original Function');
    legend('Lagrange Interpolation', 'Original Function'); % Add legend
    grid on;
    hold on;
  
end
clf; hold on;
axis square;
axis off;

N = 500;
count=0;

rectangle('Position', [0 0 1 1]);

for i=1:N
    x=rand;
    y=rand;
    distance=pdist([0.5 0.5; x y]);
    if distance < 0.5
      count++;
      plot(x, y, 'dm', 'MarkerSize', 2, 'MarkerFaceColor', 'r')
    endif
end

p = count/N



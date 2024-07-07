clf; hold on;
axis square;
axis off;

N = 500;
count=0;

rectangle('Position', [0 0 1 1]);

for i=1:N
    x=rand;
    y=rand;
    D0=pdist([0.5 0.5; x y]);
    DA=pdist([0 1; x y]);
    DB=pdist([1 1; x y]);
    DC=pdist([1 0; x y]);
    DD=pdist([0 0; x y]);
    minim=min([DA, DB, DC, DD]);
    if D0 < minim
      count++;
      plot(x, y, 'dm', 'MarkerSize', 2, 'MarkerFaceColor', 'r');
    endif
end

p = count/N



clf; hold on;
axis square;
axis off;
pkg load statistics;

N = 500;
count=0;

rectangle('Position', [0 0 1 1]);

for i=1:N
    x=rand;
    y=rand;
    P0=pdist([0.5 0.5; x y]);
    PA=pdist([0 1; x y]);
    PB=pdist([1 1; x y]);
    PC=pdist([1 0; x y]);
    PD=pdist([0 0; x y]);
    obtuze = 0;
    if PA^2+PB^2 < 1
      obtuze++;
    endif
    if PB^2+PC^2 < 1
      obtuze++;
    endif
    if PC^2+PD^2 < 1
      obtuze++;
    endif
    if PD^2+PA^2 < 1
      obtuze++;
    endif
    if obtuze == 2
      count++;
      plot(x, y, 'dg', 'MarkerSize', 2, 'MarkerFaceColor', 'r');
    endif
end

p = count/N



count = 0;
sim = 500;

for i=1: sim
    v=randi(365, 1, 23);
    if length(unique(v)) ~= length(v) # diferit
      count++;
    endif
end

rez = count/sim


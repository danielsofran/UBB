pkg load statistics

function pos=f(p=0.5, k=1000) # preferabil in alt fisier
  steps2r = 0;
  x=binornd(1, p, 1, k); # 1 - succes, 0 - esec
  x=2*x-1; # 0 => -1, 1 => 1, transform in pas st si dr
  pos=zeros(1, k+1);
  for i=1:k
    # nu mai calculez steps2r = steps to right
    pos(i+1)=pos(i)+x(i);
  endfor
endfunction


function lastFrq=g(m=1000) # de cate ori simulam
  p=rand;
  k=10;
  last=zeros(1, m);
  for i=1:m
    last(i)=f(p, k)(end); # (end) -> ultima valoare din vector
  endfor
  hist(last, -k:k)
  lastFrq=hist(last, -k:k);
  [-k:k;lastFrq]'
endfunction

g();




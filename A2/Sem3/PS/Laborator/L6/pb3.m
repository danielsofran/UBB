function pb3(n=1000)
  r=rand(1,n)
  t=exprnd(5, 1, n) .* (r<=0.4) + unifrnd(4,6,1,n) .* (r>0.4);
  % a
  mean(t)
  std(t)

  % b
  mean(t>5) % probabilitate -> mean; conditie -> timp > 5

  % c
  countTime=sum(t>5)
  count2ndPrinter=0;
  countPrinter=sum(t>5&r>0.4)
  countPrinter/countTime

    % sau cu for
  c1=0; c2=0;
  for i=1:n
    r=rand;
    if r<0.4
      I=1;
      T=exprnd(5);
    else
      I=2;
      T=unifrnd(4, 6);
    end

    if T > 5
      c1++;
      if I == 2
        c2++;
      endif
    endif
  endfor
  c2/c1
endfunction

pb3()

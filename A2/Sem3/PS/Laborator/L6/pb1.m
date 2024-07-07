function pb1(N=1000, m=165, sigma=10)
  close all;
  x=normrnd(m, sigma, 1, N);
  % i
  figure
  hist(x, 10);
  % ii
  figure, hold on
  [app, centers] = hist(x, 10);
  hist(x, centers, 10/(max(x)-min(x))); #U=
  t=linspace(min(x), max(x), N);
  plot(t, normpdf(t, m, sigma), '-g');
  % iii
  [mean(x), m]
  [std(x), sigma]
  u=mean((160<=x)&(x<=170));
  v=normcdf(170, m, sigma)-normcdf(160, m, sigma);
  [u, v]
endfunction

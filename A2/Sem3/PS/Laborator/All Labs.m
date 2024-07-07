function retval = aranjamente (v, k)
  w = nchoosek(v, k);
  [linii, ~]=size(w)
  for ind=1:linii;
    perms(w(ind,:))
  endfor
endfunction

clear all
clc

f=@(x) cos(x).^2
f([pi pi/2])

function rez = combinari (v)
  rez=nchoosek(v, 2)
endfunction

function rez = permutari (word)

  rez = perms(word)

endfunction

count = 0;
sim = 500;

for i=1: sim
    v=randi(365, 1, 23);
    if length(unique(v)) ~= length(v)
      count++;
    endif
end

rez = count/sim


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



pkg load statistics

N=5000;
urm='rrrrrggbbb';
PA=0; PAB=0; Pcond=0;
for i=1:N
    ext = randsample(urm, 3, 0); # 0 - no replacement - nu se pune bila inapoi
    incs=0;
    if any(ext=='r') # A
      PA++;
      incs++;
    endif
    if all(ext=='r') || all(ext=='g') || all(ext=='b') # B
      if incs == 1
        PAB++;
      endif
    endif
end

ProbA = PA/N
ProbAB = PAB/N
ProbBdA = ProbAB/ProbA


#pkg load statistics
clf; grid on; hold on;
p=0.25; n=5; m=2000;
x=binornd(n,p,1,m);
N=hist(x,0:n);
bar(0:n,N/m,'hist','FaceColor','b');
bar(0:n,binopdf(0:n,n,p),'FaceColor','y');
legend('probabilitatile estimate','probabilitatile teroretice');
set(findobj('type','patch'),'facealpha',0.7);
xlim([-1 n+1]);

# binopdf(k, n, p) - prob teoretica
# k cate succese, p - prob unui succes
# hist (histograma) - rep un grafic cu bare
# hist(binornd(), 0:5) - 0:5 - val posibile, restul e vectorul


#pkg load statistics
clf;
p=1/3; n=5; m=1000; # 5 zaruri, 1/3 sanse, 5000
v=binornd(n,p,1,m);
fa=sum(v==2); # cazuri favorabile
# format long - mai multe zecimale
ProbP=fa/1000 # prob practica
ProbT=binopdf(2, 5, 1/3) # prob teoretica


# i
N=1000;
zaruri=randi(6, 4, N); # matrice de 4x1000 fiecare coloana=o aruncare
sumeP=sum(zaruri); # suma pe linii
FA=hist(sumeP, 4:24); # frecv abs
CateZaruriAuSuma=[4:24; FA]' # ' = transpus

# ii
clf; hold on; grid on;
xticks(4:24); xlim([3 25]);
yticks(0:0.01:0.15); ylim([0 0.15]);
# xticks - pe ox liniutele alea de pe grafic
bar(4:24, FA/N, 'FaceColor', 'red')


sume_pos=4:24; # sume posibile
fm=max(FA); # frecv maxima - ret val max
mostFreqSum=sume_pos(FA==fm) # sume_pos(0 0 0 0 1 0 1 0 0) val de la index 5 si 7

# iii
st = []; # sume teoretice
for i1=1:6
  for i2=1:6
    for i3=1:6
      for i4=1:6
        st = [st i1+i2+i3+i4];
      endfor
    endfor
  endfor
endfor

FAst=hist(st, 4:24); # frecv abs
CatezaruriAuSumaTeoretica=[4:24; FAst]' # ' = transpus

bar(4:24, FAst/N, 'FaceColor', 'green')
set(findobj('type','patch'),'facealpha',0.4); # seteaza opacitatea barelor

fmst=max(FAst); # frecv maxima - ret val max
mostFreqSumT=sume_pos(FAst==fmst) # sume_pos(0 0 0 0 1 0 1 0 0) val de la index 5 si 7

# iv
PA=sum(sumeP<=20)/N; # ev A - sumele mai mici sau egale cu 20
PAB=sum(sumeP>=10 & sumeP <= 20)/N; # ev A intersectat cu B
PBdA=PAB/PA # P(B determinat de A) = P(B|A)


pkg load statistics

# HAM FREQ
ham=fileread('keywords_ham.txt'); # continutul
hWords=strsplit(ham, ' '); # vector de cv
uhWords=unique(hWords); # doar cv unice
lh=length(uhWords);
uhWords=uhWords(2:lh); # ia primul cv spatiu, sau un substr

hFreq = [];
uhL=length(uhWords);
hL=length(hWords);

for i=1:uhL
  counter=0;
  for j=1:hL
    counter+=strcmp(uhWords(i), hWords(j));
  endfor
  hFreq = [hFreq, counter / hL];
  # probabilitatea care ne trebe P(Wi=true|C=ham)
  # numarul de aparitii ale cuvantului call ın keywords ham.txt / numarul de cuvinte din keywords ham.txt
endfor

# SPAM FREQ
spam=fileread('keywords_spam.txt'); # continutul
sWords=strsplit(spam, ' '); # vector de cv
usWords=unique(sWords); # doar cv unice
ls=length(usWords);
usWords=usWords(2:ls); # ia primul cv spatiu, sau un substr

sFreq = [];
usL=length(usWords);
sL=length(sWords);

for i=1:usL
  counter=0;
  for j=1:sL
    counter+=strcmp(usWords(i), sWords(j));
  endfor
  sFreq = [sFreq, counter / sL];
  # probabilitatea care ne trebe P(Wi=true|C=spam)
  # numarul de aparitii ale cuvantului call ın keywords spam.txt / numarul de cuvinte din keywords spam.txt
endfor

# EMAIL
email=fileread('email1.txt');
eWords=strsplit(email, ' ');
ueWords=unique(eWords);
eL=length(eWords);

for i=1:uhL
  counter=0;
  for j=1:eL
    counter+=strcmp(uhWords(i), eWords(j));
  endfor
  if counter == 0
    hFreq(i)=1-hFreq(i);
  endif
endfor

for i=1:usL
  counter=0;
  for j=1:eL
    counter+=strcmp(usWords(i), eWords(j));
  endfor
  if counter == 0
    sFreq(i)=1-sFreq(i);
  endif
endfor

# inmultim probabilitatile
PH=length(hWords)/(length(hWords)+length(sWords));
PS=length(sWords)/(length(hWords)+length(sWords));
PW1_14_ham=prod(hFreq)*PH
PW1_14_spam=prod(sFreq)*PS

if PW1_14_spam > PW1_14_ham
  printf("Email is spam!\n")
else
  printf("Email is ham!\n")
endif






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
  lastFrq=hist(last, -k:k);
  [-k:k;lastFrq]'
endfunction

g();




m=1000;
p=sum(hygepdf(3:6, 49, 6, 6)); # fara sum - pt exact 3, cu sum - cel putin 3
# 3:6 - numarul de bile marcate din extrageri (cel putin 3 bile din 6)
# 49 bile in urna,
# primul 6: bile marcate
# al doilea 6: extrageri
x=geornd(p, 1, m); # cat a jucat omu sa-i iasa un bilet

val=mean(x >= 10); # x = [1 0 1 1 0 ...]
printf('Probablilitatea estimata: %2.7f\n', val)
Tval=1-sum(geopdf(0:9, p)) # cel mult 9, probab teoretica
# fac probabilitatea opusa, pt ca nu pot pune 10:inf => 1-p(0:9)




function [X, Y] = boxmuller(N)
  U = rand(2, N);

  R = sqrt(-2 * log(U(1, :))); % raza
  V = 2 * pi * U(2, :); % unghiul

  X = R .* cos(V)
  Y = R .* sin(V)
endfunction

clf; grid on; hold on;

N = 1000;

v = [0 1 2 3]; # 0 - 0, 1 - A, 2 - B, 3 - AB
p = [0.46 0.4 0.1 0.04] ;

x1 = rndvardisc(v, p, N);
x2 = randsample(v, N, replacement = true, p);

rel_frq_x1 = hist(x1, v);
bar(v, rel_frq_x1, 'hist', 'Facecolor', 'b');

rel_frq_x2 = hist(x2, v);
bar(v, rel_frq_x2, 'hist', 'Facecolor', 'r');

set(findobj('type', 'patch'), 'facealpha', 0.7);
xlim([0 5]); xticks(1 : 4);

function ex3(N = 500)
  clf;
  t = linspace(0, 2*pi, 360);
  polar(t, 4*ones(1, 360), 'w');
  hold on;

  [X, Y] = boxmuller(N);
  plot(X, Y, 'r*');
  polar(t, 0.5*ones(1, 360), 'k');

  sum(X .^ 2 + Y .^ 2 < 0.5 .^ 2) / N # (x - cx).^2 + (y - cy).^2 < r .^ 2
  % pentru frecventa relativa, putem sa folosim si mean(x .^ 2 + Y .^ 2 < 0.25)

  1 - exp(-1/8) # teoretica

  % X = R .* cos(V)
  % Y = R .* sin(V)
  % P(X .^ 2 + Y .^ 2 < 0.25) = P(-2 * log(U(1, :)) < 0.25)
  % P(log(U(1, :)) > -1 / 8) = P(U(1, :) > exp( -1 / 8 ))
  % 1 - P(U(1, :) < exp(-1/8))

  Z = normrnd(0, 1, 2, N);
  plot(Z(1, :), Z(2, :), 'c*');
  mean(Z(1, :) .^ 2 + Z(2, :) .^ 2 < 0.25)

endfunction

function ex2(N = 1000)
  x1 = rndexp(1 / 12, N);
  x2 = exprnd(12, 1, N);

  [mean(x1) mean(x2)]
  [std(x1) std(x2)]
endfunction

function X = rndexp(lambda, N)
  X = -log(1 - rand(1, N)) / lambda;
endfunction

function X = rndvardisc(x, p, N)
  q = cumsum(p);
  X = zeros(1, N);

  for i=1:N
    u=rand;
    j=1;
    while u > q(j)
      j++;
    endwhile
    X(i) = x(j);
  endfor

endfunction

function int=MC1(g, a, b, M, n)
  x=unifrnd(a, b, 1, n);
  y=unifrnd(0, M, 1, n);
  int=(b-a)*M*mean(y<=g(x));
endfunction

function pb2b(n=1000)
  % g1 = @(x) exp( - x .^ 2), a = -2, b = 2, M = 1
  % g2 = @(x) abs(sin(exp(x))), a = -1, b = 3, M = 1
  % g3 = @(x)  x .^2 ./ (1 + x .^ 2) .* (x <= 0) + sqrt(2 * x - x .^ 2) .* (x > 0), a = -1, b = 2, M = 1
  % g3 - functie pe ramuri

  % aleg un g din g1, g2, g3
  g = @(x) exp( - x .^ 2), a = -2, b = 2, M = 1;
  MC1(g, a, b, M, n)
  MC2(g, a, b, n)
  integral(g, a, b)
endfunction

function int=MC2(g, a, b, n)
  int=(b-a)*mean(g(unifrnd(a, b, 1, n)));
endfunction

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

function pb1(N=1000, m=165, sigma=10)
  close all;
  x=normrnd(m, sigma, 1, N);
  % i
  figure
  hist(x, 10);
  % ii
  figure, hold on
  [app, centers] = hist(x, 10);
  hist(x, centers, 10/(max(x)-min(x)));
  t=linspace(min(x), max(x), N);
  plot(t, normpdf(t, m, sigma), '-r');
  % iii
  [mean(x), m]
  [std(x), sigma]
  u=mean((160<=x)&(x<=170));
  v=normcdf(170, m, sigma)-normcdf(160, m, sigma);
  [u, v]
endfunction

function pb2n(n=1000)
  % g1 = @(x) exp( - x .^ 2), a = -2, b = 2, M = 1
  % g2 = @(x) abs(sin(exp(x))), a = -1, b = 3, M = 1
  % g3 = @(x)  x .^2 ./ (1 + x .^ 2) .* (x <= 0) + sqrt(2 * x - x .^ 2) .* (x > 0), a = -1, b = 2, M = 1
  % g3 - functie pe ramuri

  % aleg un g din g1, g2, g3
  g = @(x)  x .^2 ./ (1 + x .^ 2) .* (x <= 0) + sqrt(2 * x - x .^ 2) .* (x > 0), a = -1, b = 2, M = 1
  clf; hold on;
  x=unifrnd(a, b, 1, n);
  y=unifrnd(0, M, 1, n);
  plot(x(y<=g(x)), y(y<=g(x)), 'r*')
  plot(x(y>g(x)), y(y>g(x)), 'b*')
  t=linspace(a, b, n);
  plot(t, g(t), '-k')
endfunction

pb2n()

pb2n(1000)

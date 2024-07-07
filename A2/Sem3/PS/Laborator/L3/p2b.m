#pkg load statistics
clf; close all;
# n=5 zaruri,
# p=1/3 sanse sa fie divizibile cu 3,
# m=5000 simulari
# cate= cate succese vrem
p=1/3; n=5; m=1000; cate=2;
v=binornd(n,p,1,m);
fa=sum(v==cate); # cazuri favorabile
# format long - mai multe zecimale
ProbP=fa/m # prob practica
ProbT=binopdf(cate, n, p) # prob teoretica


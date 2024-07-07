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




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
st = []; # sume teoretice, toate perechile posibile
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


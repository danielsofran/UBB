pkg load statistics; close all;

N = 1000;
bile='rrrrrrrrrrnnnnnaaaaa';
tipbile='rna';
PA=0; PB=0; PC=0; PAC=0;

for i=1:N;
  extrase=randsample(bile, 3, replacement=false);
  isAC=0;
  if all(extrase=='r') || all(extrase=='n') || all(extrase=='a')
    PA++;
    isAC+=1;
  endif
  if extrase=='rna' || extrase=='ran' || extrase=='arn' || extrase=='anr' || extrase=='nar' || extrase=='nra'
    PB++;
  endif
  if any(extrase=='n')
    PC++;
    isAC+=1;
  endif
  if isAC == 2
    PAC++;
  endif
endfor

PA/=N
PB/=N
PC/=N
PAC/=N;
PCdA=PAC/PC

# A
# 3 rosii => C(10, 3)*C(5, 0)*C(5, 0)/C(20, 3)

PtA=nchoosek(10, 3)/nchoosek(20, 3); # rosii
PtA+=nchoosek(5, 3)/nchoosek(20, 3); # negre
PtA+=nchoosek(5, 3)/nchoosek(20, 3); # albe
PtA

# B
# P(r)=C(10, 1)*C(5, 1)*C(5, 1)/C(20, 3)
PtB=nchoosek(10, 1)*nchoosek(5, 1)*nchoosek(5, 1)/nchoosek(20, 3)

# C
# P(n>=1) => 1-P(n=0): (r, a) 0 3, 1 2, 2 1, 3 0
PtC=nchoosek(10, 3)*nchoosek(5, 0)/nchoosek(20, 3);  # 3 r, 0 a
PtC+=nchoosek(10, 2)*nchoosek(5, 1)/nchoosek(20, 3); # 2 r, 1 a
PtC+=nchoosek(10, 1)*nchoosek(5, 2)/nchoosek(20, 3); # 1 r, 2 a
PtC+=nchoosek(10, 0)*nchoosek(5, 3)/nchoosek(20, 3); # 0 r, 3 a
PtC=1-PtC

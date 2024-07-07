#R21
pkg load statistics;
S=[1    2       3    4];
P=[1/10 2/10 3/10 4/10]; # vectorul de probab
#T=[exp(1), exp(1/2), exp(1/3), exp(1/4)];

# a P(t<=3), de serverul 4 => exp(4)
PA=expcdf(3, 4) # expcdf face fct de repartitie

gets=[1 2 2 3 3 3 4 4 4 4]; # ca sa det serverul cu prob data

# b
N=1000; # nr de generari
r=randi(9, 1, N)+1; # generam n alegeri aleat in 1..10
# in fct de r(i), in t insumam distribuia coresp fiec server
t=exprnd(1, 1, N).*(r<=1)+exprnd(2, 1, N).*(r>=2&r<=3)+exprnd(3, 1, N).*(r>=4&r<=6)+exprnd(4, 1, N).*(r>=7&r<=10);

TM=mean(t) # val medie a timpului de procesare

# c P(t>3) = 1 - P(t<=3), nu am fol asta, am calculat pb estimata
PC=mean(t>3)


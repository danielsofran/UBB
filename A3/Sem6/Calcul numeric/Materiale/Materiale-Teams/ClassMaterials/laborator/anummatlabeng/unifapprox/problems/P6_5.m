%P6_5
%studiul influentei nodurilor
%uniforme, distincte
close all
kn=-4:10;
t=linspace(-1,6,200);
n=0:10;

A=tbaza(kn,t,n);
plot(t,A)
s=cell(11,1);
for k=0:11
    s{k+1}=sprintf('k=%d',k);
end
legend(s{1:10},'Location','EastOutside')
title('noduri uniforme,distincte')
pause
%uniforme, multiple la capete
kn=noduri(10,3);
t=linspace(0,8,200);
A=tbaza(kn,t,n);
plot(t,A)
legend(s{1:10},'Location','EastOutside')
title('noduri uniforme, multiple la capete')
pause
%uniforme, multiple la capete si in centru
kn=[kn(1:8),4,kn(9:end)];
A=tbaza(kn,t,0:11);
plot(t,A)
legend(s,'Location','EastOutside')
title('noduri uniforme, multiple la capete si 4 multiplu')
pause
%neuniforme, distincte
kn=sort(rand(1,15));
t=linspace(kn(4),kn(11),200);
A=tbaza(kn,t,n);
plot(t,A)
legend(s{1:10},'Location','EastOutside')
title('noduri neuniforme, distinte')
pause
%neuniforme, un nod multiplu
kn=[kn(1:8),kn(8),kn(9:end)];
A=tbaza(kn,t,0:11);
plot(t,A)
legend(s,'Location','EastOutside')
title('noduri neuniforme, un nod multiplu')
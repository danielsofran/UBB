%metoda lui Newton pentru sisteme de ecuatii
%F(x,y)=[x^2+y^2-1, x^3-y] - sistemul de ecuatii ce trebuie rezolvat
%Fd(x,y)=[2*x 2*y; 3*x^2 -1] -Jacobianul lui F
%grafic, avem intersectia unui cerc cu o functie de gradul 3
%cercul
t=0:0.001:2*pi;
x=cos(t);y=sin(t);
plot(x,y,'r');
axis equal
%functia de gradul 3
x=-1:0.01:1;
y=x.^3;
hold on
plot(x,y,'k')
%se observa ca avem doua solutii.
%aplicam metoda lui Newton
F=@(x) [x(1)^2+x(2)^2-1; x(1)^3-x(2)]
Fd=@(x,y) [2*x(1) 2*x(2); 3*x(1)^2 -1]
%solutia din cadranul III
[z,ni]=Newton(F,Fd,[-0.5;-0.5],1e-6)
plot(z(1),z(2),'o')
%solutia din cadranul III
[z,ni]=Newton(F,Fd,[0.5;0.5],1e-6)
plot(z(1),z(2),'og')



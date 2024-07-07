%metoda lui Newton si a secantei pentru ecuatii
%f(x)=cos(x)-x
%grafic
x=0:0.01:1;
plot(x,x,'r',x,cos(x),'k');
hold on
f=@(x) cos(x)-x;
fd=@(x) -sin(x)-1;
%metoda lui Newton
[z,ni]=Newtons(f,fd,0.5,1e-6)
plot(z,z,'o')
%metoda secantei
[z,ni]=secant(f,0.5,1,1e-6)
plot(z,z,'xk')
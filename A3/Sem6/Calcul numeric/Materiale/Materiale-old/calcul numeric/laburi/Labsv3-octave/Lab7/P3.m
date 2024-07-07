%3. Scrie�ti o rutina care reprezinta grafic o cubica parametrica
%Hermite (o curba care trece prin doua puncte date si are
%in acele puncte tangente date).

function P3 ()
  
  x_graph=0:0.01:pi;
  y_garph=sin(x_graph);
  plot(x_graph,y_garph,'k')
  hold on

  X = [0 pi]; Y = [0 0]; Yd = [1, -1]; 
  x = X(1):0.01:X(2);
  [H,y] = Hermite_v2(x,X,Y,Yd);

  plot(x,y);

endfunction

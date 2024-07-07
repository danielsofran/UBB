  clf; axis equal;axis([0 2 0 1]);
  xticks(0:0.2:2);yticks(0:0.2:1);
  grid on;hold on; set(gca,"fontsize", 15)
  X=[]; Y=[];
  i=1
  [x,y]=ginput(1)
  while ~isempty([x,y])
    X=[X x];
    Y=[Y y];
   plot(x,y,'*k','MarkerSize',10);
   text(x+0.02,y+0.02,num2str(i),'fontsize',13);
   i=i+1
   [x,y]=ginput(1)
  endwhile
  timp=linspace(0,1,1000);
  noduri=linspace(0,1,length(X));
  sx=evalsplinecubic(noduri, X, 'deBoor', [], timp); %deBoor are nevoie de cel putin 4 puncte
  sy=evalsplinecubic(noduri, Y, 'deBoor', [], timp);
  plot(sx,sy,'-r','linewidth',2)


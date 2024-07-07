function er=err_rel(x,xp,p=2)
  er=norm(x-xp, p)/norm(x,p);

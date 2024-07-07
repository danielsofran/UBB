pkg load statistics

N=5000;
urm='rrrrrggbbb';
PA=0; PAB=0; Pcond=0;
for i=1:N
    ext = randsample(urm, 3, 0); # 0 - no replacement - nu se pune bila inapoi
    incs=0;
    if any(ext=='r') # A
      PA++;
      incs++;
    endif
    if all(ext=='r') || all(ext=='g') || all(ext=='b') # B
      if incs == 1
        PAB++;
      endif
    endif
end

ProbA = PA/N
ProbAB = PAB/N
ProbBdA = ProbAB/ProbA # se simplifica N u


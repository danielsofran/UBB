
function [] = P3_test ()
  fprintf("Aproximare Pade pentru sin(pi/2) (m=4,n=4) cu reducerea rangului: "); sin_cos_AP("sin",pi/2,4,4)
  fprintf("Aproximare Pade pentru sin(pi/2) (m=6,n=6) cu reducerea rangului: "); sin_cos_AP("sin",pi/2,6,6)
  fprintf("Aproximare Pade pentru cos(pi/2) (m=4,n=4) cu reducerea rangului: "); sin_cos_AP("cos",pi/2,4,4)
  fprintf("Aproximare Pade pentru cos(pi/2) (m=6,n=6) cu reducerea rangului: "); sin_cos_AP("cos",pi/2,6,6)
    
end

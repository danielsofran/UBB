int = integral(@sin, 0, pi)
res = adquad(@sin, 0, pi, 0.000001)

int2 = integral(@exp, 0, 10)
r2 = adquad(@exp, 0, 10, 0.001)
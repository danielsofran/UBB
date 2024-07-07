#pragma once

typedef struct
{
	double x, y;
} complex_t;

typedef complex_t* complex_p;

complex_p init_complex(double, double);

double get_complex_re(complex_p);
double get_complex_im(complex_p);

complex_p complex_add(complex_p, complex_p);
complex_p complex_sub(complex_p, complex_p);
complex_p complex_mul(complex_p, complex_p);


void destroy_complex(complex_p);

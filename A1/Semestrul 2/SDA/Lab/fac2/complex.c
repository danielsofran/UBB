#include "complex.h"

complex_p init_complex(double x, double y)
{
	complex_p z = (complex_p*)malloc(sizeof(complex_t));
	z->x = x;
	z->y = y;
	return z;
}

double get_complex_re(complex_p z)
{
	return z->x;
}

double get_complex_im(complex_p z)
{
	return z->y;
}

complex_p complex_add(complex_p z1, complex_p z2)
{
	return init_complex(z1->x + z2->x, z1->y + z2->y);
}

complex_p complex_sub(complex_p z1, complex_p z2)
{
	return init_complex(z1->x - z2->x, z1->y - z2->y);
}

complex_p complex_mul(complex_p z1, complex_p z2)
{
	return init_complex(
		z1->x * z2->x - z1->y * z2->y,
		z1->x * z2->y + z1->y * z2->x
	);
}

void destroy_complex(complex_p z)
{
	free(z);
}
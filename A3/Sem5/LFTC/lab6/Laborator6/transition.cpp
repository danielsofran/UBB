#include "transition.h"

string Transition::toString() {
    return source + " -> " + destination + " " + weight;
}
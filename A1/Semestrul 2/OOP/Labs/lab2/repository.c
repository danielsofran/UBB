//
// Created by Daniel on 05->03->2022->
//

#include "repository.h"

Repository* repository_create(int (*eq)(void*, void*)){
    Repository* repository = malloc(sizeof(Repository));
    repository->capacity = 100;
    repository->length = 0;
    repository->elements = (void**) malloc(sizeof(void*) * repository->capacity);
    for(int i=0;i<repository->capacity;++i)
        repository->elements[i] = NULL;
    repository->elem_eq = eq;
    return repository;
}

int repository_get_length(Repository* repository){
    return repository->length;
}
int repository_get_capacity(Repository* repository) {
    return repository->capacity;
}
void* repository_get_element_at(Repository* repository, int index) {
    if(index>=0 && index<repository->length)
        return repository->elements[index];
    SET_ERROR(OUT_OF_RANGE);
    return NULL;
}

void repository_set_length(Repository* repository, int length) {
    if(length>repository->capacity)
        repository_set_capacity(repository, length);
    repository->length = length;
}
void repository_set_capacity(Repository* repository, int capacity) {
    void** copy = malloc(sizeof(void*)*capacity);
    for(int i=0;i<repository->capacity;++i) {
        // copiem memoria?
        copy[i] = repository->elements[i];
    }
    free(repository->elements);
    repository->elements = copy;
    repository->capacity = capacity;
}
void repository_set_element_at(Repository* repository, int index, void* value) {
    if(index<repository->length && index >=0) {
        if(repository->elements[index] != NULL) free(repository->elements[index]);
        repository->elements[index] = value;
    }
    else SET_ERROR(OUT_OF_RANGE);
}

void repository_add(Repository* repository, void* value){
    if(repository->length > repository->capacity) repository_set_capacity(repository, repository->capacity *2);
    repository->elements[repository->length++] = value;
}

int repository_index_of(Repository* repository, void* value)
{
    int n = repository_get_length(repository);
    for(int i=0;i<n;++i)
        if(repository->elem_eq(value, repository_get_element_at(repository, i)))
            return i;
    return NOT_FOUND;
}

void repository_swap(Repository* repository, int index_i, int index_f)
{
    void* el1 = repository_get_element_at(repository, index_i);
    void* el2 = repository_get_element_at(repository, index_f);
    if(el1 == NULL || el2 == NULL) return;
    repository->elements[index_f] = el1;
    repository->elements[index_i] = el2;
}

void repository_sort(Repository* repository, int(*compare)(void*,void*,int), int reversed)
{
    int n = repository_get_length(repository);
    for(int i=0;i<n-1;++i)
        for(int j=i+1;j<n;++j)
        {
            void* eli = repository_get_element_at(repository, i);
            void* elj = repository_get_element_at(repository, j);
            if(compare(eli, elj, reversed)==GREATER) // daca nu sunt in ordine
                repository_swap(repository, i, j);
        }
}

Repository* repository_filter(Repository* repository, void* filter_data, int (*filter)(void*,void*)){
    int n = repository_get_length(repository);
    Repository* filtered = repository_create(repository->elem_eq);
    for(int i=0;i<n;++i)
    {
        void* elem = repository_get_element_at(repository, i);
        if(filter(elem, filter_data))
            repository_add(filtered, elem);
    }
    return filtered;
}


void repository_delete(Repository* repository)
{
    free(repository->elements);
    free(repository);
}
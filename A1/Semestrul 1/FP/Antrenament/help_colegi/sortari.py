from random import choice

def insertsort(v):
    for i in range(1, len(v)):
        ind = i-1
        a = v[i]
        while ind >= 0 and a < v[ind]:
            v[ind+1] = v[ind]
            ind -= 1
        v[ind+1] = a

def partitie(v, left, right):
    pivot = v[left]
    i = left
    j = right
    while i!=j:
        while v[j]>=pivot and i<j:
            j -= 1
        v[i] = v[j]
        while v[i]<=pivot and i<j:
            i += 1
        v[j] = v[i]
    v[i] = pivot
    return i

def quicksort(v, left, right):
    pos = partitie(v, left, right)
    if left<pos-1:
        quicksort(v, left, pos-1)
    if pos+1<right:
        quicksort(v, pos+1, right)

# def merge(l, start, end, m):
#     start1 = start
#     end1 = m-1
#     start2 = m
#     end2 = end
#     while start1<=end1 and start2<=end2:
#         if l[start1] <= l[start2]:
#             start1 += 1
#         else:
#             index = start2
#             val = l[start2]
#             while index!=start1:
#                 l[index] = l[index-1]
#                 index -= 1
#             l[start1] = val
#             start1+=1
#             start2+=1
#             end1+=1

def merge_in(arr, start, mid, end):
    start2 = mid + 1

    # If the direct merge is already sorted
    if (arr[mid] <= arr[start2]):
        return

    # Two pointers to maintain start
    # of both arrays to merge
    while (start <= mid and start2 <= end):

        # If element 1 is in right place
        if (arr[start] <= arr[start2]):
            start += 1
        else:
            value = arr[start2]
            index = start2

            # Shift all the elements between element 1
            # element 2, right by 1.
            while (index != start):
                arr[index] = arr[index - 1]
                index -= 1

            arr[start] = value

            # Update all the pointers
            start += 1
            mid += 1
            start2 += 1

def merge(l, start, mid, stop):
    starti = start
    stopi = stop
    rez = []
    start2 = mid
    while start < mid and start2 < stop:
        if l[start] < l[start2]:
            rez.append(l[start])
            start += 1
        else:
            rez.append(l[start2])
            start2 += 1
    while start < mid:
        rez.append(l[start])
        start += 1
    while start2 < stop:
        rez.append(l[start2])
        start2 += 1
    #assert len(rez) == len(l)
    for i in range(starti, stopi):
        l[i] = rez[i-starti]

def mergesort(l, start, end):
    if end-start <= 1:
        return
    m = (end+start)//2
    mergesort(l, start, m)
    mergesort(l, m, end)
    merge(l, start, m, end)

def mergesort_mem(l):
    if len(l)<=1: return l[:]
    # if len(l)==2:
    #     if l[0]>l[1]:
    #         return [l[1], l[0]]
    #     else: return l[:]
    m = len(l) // 2
    left = mergesort_mem(l[:m])
    right = mergesort_mem(l[m:])
    return merge(left, right)

def mt(l):
    copy = l[:]
    l.clear()
    l.extend(mergesort_mem(copy))

def test_sortat(sortare, startindex=None, endindexsupl=-1):
    lista = [choice(range(1, 10)) for i in range(4)]
    print(lista)
    if startindex == None:
        sortare(lista)
    else:
        st = sortare(lista, startindex, len(lista)+endindexsupl)
        if st!=None:
            print(st)
    print(lista)

#test_sortat(mergesort, 0, 0)


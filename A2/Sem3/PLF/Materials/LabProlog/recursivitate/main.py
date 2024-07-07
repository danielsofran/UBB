
class Nod:
    def __init__(self, val=None):
        self.val = val
        self.urm = None

class Lista:
    def __init__(self):
        self.head = Nod()

    def append(self, val):
        if self.head.val == None:
            self.head.val = val
        else:
            nod = Nod(val)
            nod.urm = self.head
            self.head = nod

    def __last(self, nod: Nod):
        if nod.urm is None: return nod
        return self.__last(nod.urm)
    def last(self) -> Nod: return self.__last(self.head)

    def toString(self, nod) -> str:
        if nod is None: return "\b"
        return f"{nod.val} {self.toString(nod.urm)}"

    def __str__(self):
        return self.toString(self.head)

    # region Ex1
    def __appendAfter(self, nod:Nod, val):
        nounod = Nod(val)
        nounod.urm = nod.urm
        nod.urm = nounod

    def __appendListAfter(self, nod:Nod, lstnode: Nod):
        self.__appendAfter(nod, lstnode.val)
        if lstnode.urm is None: return nod.urm
        return self.__appendListAfter(nod.urm, lstnode.urm)

    def __substElemWithList(self, nod: Nod, lstnode: Nod):
        if lstnode is None: return nod.urm
        nod.val = lstnode.val # substitute first value
        if lstnode.urm is not None:
            return self.__appendListAfter(nod, lstnode.urm)

    def __substElemsWithList(self, nod: Nod, elem, lst):
        if nod is None: return
        next = nod.urm
        if nod.val == elem: next = self.__substElemWithList(nod, lst.head)
        self.__substElemsWithList(next, elem, lst)

    def substElWithList(self, elem, lst):
        self.__substElemsWithList(self.head, elem, lst)

    def __getNth(self, nod: Nod, n: int):
        if n == 0: return nod
        if nod is None: return None
        return self.__getNth(nod.urm, n-1)

    def getNth(self, n: int, indexare:int = 1):
        return self.__getNth(self.head, n - indexare)
    # endregion

    #region Ex2
    def __searchForAnotherAparition(self, nod: Nod, nodval: Nod):
        if nod is None: return None
        if nod == nodval: return None
        if nod.val == nodval.val and nod.urm != nodval.urm:
            return nod
        return self.__searchForAnotherAparition(nod.urm, nodval)

    def __removeNode(self, pre:Nod, nod:Nod):
        if nod is None: pre.urm = None
        # nu exista pre None
        else:
            pre.urm = nod.urm

    def __removeDuplicates(self, pre:Nod, nod:Nod):
        if nod is None: return
        duplicate = self.__searchForAnotherAparition(self.head, nod)
        if duplicate is not None:
            self.__removeNode(pre, nod) # sterg nodul curent
            self.__removeDuplicates(pre, nod.urm)
        else: self.__removeDuplicates(pre.urm, nod.urm)

    def removeDuplicates(self):
        if self.head is None: return
        self.__removeDuplicates(self.head, self.head.urm)

    def concat(self, l):
        last = self.last()
        last.urm = l.head

    def union(self, l):
        self.concat(l)
        self.removeDuplicates()

    #endregion

def test():
    l12 = Lista()
    l12.append('-')
    l12.append('-')
    print(l12)
    assert l12.last().val == '-'

    l123 = Lista()
    l123.append(3)
    l123.append(2)
    l123.append(1)
    l123.append(2)
    l123.append(3)
    print(l123)
    l123.substElWithList(2, l12)
    print(l123)
    l123.removeDuplicates()
    print(l123)
    l123.union(l12)
    print(l123)

test()
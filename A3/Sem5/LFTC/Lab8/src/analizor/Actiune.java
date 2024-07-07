package analizor;

enum Actiune {
    ACCEPTARE, SHIFTARE, REDUCERE, EROARE
}

class ActiuneData {
    Actiune actiune;
    int index;

    public ActiuneData(Actiune actiune, int index) {
        this.actiune = actiune;
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ActiuneData actiuneData) {
            return this.actiune == actiuneData.actiune && this.index == actiuneData.index;
        }
        return false;
    }

    @Override
    public String toString() {
        return actiune == Actiune.SHIFTARE ? "s" + index : actiune == Actiune.REDUCERE ? "r" + index : actiune == Actiune.ACCEPTARE ? "acc" : "err";
    }
}

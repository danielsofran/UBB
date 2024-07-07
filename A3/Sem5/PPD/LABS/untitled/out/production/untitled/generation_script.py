import random

ids = [i for i in range(1, 501)]
listLength = 0
for i in range(1,6):
    idLength = random.randint(80, 101)
    currentIds = ids[listLength:listLength + idLength]
    listLength += idLength  
    for j in range(1,11):
        random.shuffle(currentIds)
        scores = [random.randint(-1, 100) for _ in range(idLength)]
        for k in range(idLength):
            if random.randint(-1, 4) == -1:
                scores[k] = -1
        with open('C:\\Users\\Bogdan\\Desktop\\FACULTA\\PPD\\labs\\Lab4Tema\\untitled\\input\\RezultateC' + str(i) + '_P' + str(j) + '.txt', 'w') as f:
            for k in range(idLength):
                f.write(str(currentIds[k]) + ' ' + str(scores[k]) + '\n')
    # for j in range(1,11):

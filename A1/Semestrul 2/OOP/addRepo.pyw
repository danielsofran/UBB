import pyautogui as ctr
from random import *

no_of_setting = 1 # first setting after default
center = (800, 450)

#datas
with open("names.txt") as f:
    names=f.readlines()

# focus on console
ctr.sleep(0.1)
ctr.click(center[0], center[1])
ctr.click(center[0], center[1])
ctr.sleep(0.1)

nr = 10
pause = 0.03

used = {}

for i in range(nr):
    apartament = randint(1, 20)
    if apartament in used:
        nr += 1
        continue
    used[apartament] = 1
    nume = choice(names)
    suprafata = randint(5, 50)*10
    tip = choice(["apartament", "garsoniera"])
    # execute
    ctr.press("1")
    ctr.sleep(pause)
    ctr.press("enter")
    ctr.sleep(pause)    
    ctr.typewrite(str(apartament))
    ctr.sleep(pause)
    ctr.press("enter")
    ctr.sleep(pause)    
    ctr.typewrite(nume)
    ctr.sleep(pause)
    ctr.press("enter")
    ctr.sleep(pause)    
    ctr.typewrite(str(suprafata))
    ctr.sleep(pause)
    ctr.press("enter")
    ctr.sleep(pause)    
    ctr.typewrite(tip)
    ctr.sleep(pause)
    ctr.press("enter")
    ctr.sleep(pause)    
    ctr.press("enter")
    ctr.sleep(pause)

# # open PUTTY
# ctr.press("win")
# ctr.sleep(0.1)
# ctr.typewrite("putty")
# ctr.press("enter")
# ctr.sleep(0.1)
#
# # press setver linux setting
# ctr.click(1040, 440)
# for i in range(5): ctr.press("tab")
# for i in range(1+no_of_setting): ctr.press("down")
# ctr.press("enter")
# ctr.sleep(0.7)
# # press load
# # ctr.press("tab")
# # ctr.press("enter")
#
# ctr.write("sdir3217")
# ctr.press("enter")
# # ctr.sleep(0.1)
# ctr.write("2fe39eb#86")
# ctr.press("enter")
# # ctr.sleep(0.1)


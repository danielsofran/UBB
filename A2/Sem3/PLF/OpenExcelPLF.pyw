import webbrowser
import pyautogui as c

webbrowser.open('https://docs.google.com/spreadsheets/d/e/2PACX-1vQUwJlbZElVg_1IeiqtBFz0xqiSUotJDOQX_9zkAfhht5kvTULE7W-X-aUK6Z-CeUQIH-g38h6n0w1w/pubhtml?gid=1675021479&single=true')

c.sleep(1.5)
c.click(500, 500)
c.hotkey('ctrl', 'f')
c.typewrite("4Y116537")
c.press("enter")

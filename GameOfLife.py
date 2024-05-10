import tkinter as tk
import time

root = tk.Tk()

root.geometry("800x650")

canvas = tk.Canvas(root, width = 600, height = 600)
canvas.pack()
controls = tk.Canvas(root, width = 600, height = 50)
controls.pack()

gameRunning = True



def draw_board(canvas):  
    currentId = 1
    for r in range(20):
        for c in range(20):
            coords = (c*30+4, r*30+4, c*30+30, r*30+30)
            canvas.create_rectangle(coords, fill="white",
                                    width=2, tags=("shape", currentId))
            currentId += 1
    
    start = tk.Button(controls, text = "Start", width = 50, height = 2, bg = "white", fg = "black", command = startLife)
    start.grid(row=0, column=0)
    reset = tk.Button(controls, text = "Reset", width = 50, height = 2, bg = "white", fg = "black", command = resetLife)
    reset.grid(row=0, column=1)

def startLife():
    if gameRunning:
        time.sleep(0.5)
        root.update_idletasks()
        live_cells = []
        for r in range(20):
            for c in range(20):
                x = c*30+10
                y = r*30+10
                nearby_tags = canvas.find_closest(x,y)

                

                for tag in nearby_tags:
                    count = 0
                    for i in [tag-21, tag-20, tag-19, tag-1, tag+1, tag+19, tag+20, tag+21]:
                        if not(i < 0 or i > 400):
                            if canvas.itemcget(i, "fill") == "black":
                                count += 1
                    if (canvas.itemcget(tag, "fill") == "black" and count > 1 and count < 4) or (canvas.itemcget(tag, "fill") == "white" and count == 3):
                        live_cells.append(tag)
        continueLife(live_cells)
    root.after(500, startLife)

    

def continueLife(live_cells):
    for r in range(20):
        for c in range(20):
            x = c*30+10
            y = r*30+10
            nearby_tags = canvas.find_closest(x,y)
            for tag in nearby_tags:
                canvas.itemconfigure(tag, fill="white")

    for tag in live_cells:
        canvas.itemconfigure(tag, fill="black")

def resetLife():
    gameRunning = False



def handle_click(event):
    x = event.x
    y = event.y

    nearby_tags = canvas.find_closest(x, y)

    for tag in nearby_tags:
        if canvas.itemcget(tag, "fill") == "white":
            # Change the fill color of the shape
            canvas.itemconfigure(tag, fill="black")
        else:
            canvas.itemconfigure(tag, fill="white")
        



canvas.bind("<Button-1>", handle_click)


draw_board(canvas)

root.mainloop()
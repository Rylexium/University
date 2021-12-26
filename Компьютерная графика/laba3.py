from tkinter import *
root = Tk()

root.title("laba3")
root.geometry('1024x768')
root.minsize(1024, 768)
root.maxsize(1024, 768)
x1, y1, x2, y2 = 30, 10, 120, 80 #стартовые значения прямоугольника
speed, indexOfColor = 15, 0 #смещение, текущие значение color
colors = ["red", "blue", "green", "yellow", "black", "orange", "brown"] #список цветов

def clickMe():
    global x1, y1, x2, y2
    try:
        """Получаем новые точки"""
        x1, y1, x2, y2 = int(x1_text.get()), int(y1_text.get()), int(x2_text.get()), int(y2_text.get())
        myCanvas.delete("all") #удаляем предыдущие фигуры
        myCanvas.create_rectangle(#рисуем новый
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    except:
        pass

label = Label(root, text="Введите координаты прямоугольника")
label.grid(column=0, row=0)

x1_text = StringVar()
textEntered1 = Entry(root, width=15, textvariable=x1_text)
textEntered1.grid(column=0, row=1)

y1_text = StringVar()
textEntered2 = Entry(root, width=15, textvariable=y1_text)
textEntered2.grid(column=0, row=2)

x2_text = StringVar()
textEntered3 = Entry(root, width=15, textvariable=x2_text)
textEntered3.grid(column=0, row=3)

y2_text = StringVar()
textEntered4 = Entry(root, width=15, textvariable=y2_text)
textEntered4.grid(column=0, row=4)

button = Button(root, text="Click Me", command=clickMe)
button.grid(column=0, row=5)

myCanvas = Canvas(root, width=1024, height=768, bg="white")
myCanvas.grid()

def change_rectangle(event):
    global x1, y1, x2, y2, indexOfColor
    if event.keysym == 'Up' and y1 - speed >= 0: #если нажата кнопка верх и мы не выходим за границы окна
        myCanvas.delete("all") #очищаем канву
        y1 -= speed #делаем смещение прямоугольника по х1 и х2
        y2 -= speed
        myCanvas.create_rectangle( #рисуем прямоугольник
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    elif event.keysym == 'Down' and y2 + speed <= 640:#если нажата кнопка вниз и мы не выходим за границы окна
        myCanvas.delete("all")
        y1 += speed #делаем смещение прямоугольника по х1 и х2
        y2 += speed
        myCanvas.create_rectangle(
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    elif event.keysym == 'Left' and x1 - speed >= 0:
        myCanvas.delete("all")
        x1 -= speed
        x2 -= speed
        myCanvas.create_rectangle(
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    elif event.keysym == 'Right' and x2 + speed <= 1024:
        myCanvas.delete("all")
        x1 += speed
        x2 += speed
        myCanvas.create_rectangle(
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    elif event.keysym == "Prior": #PageUp нажата
        indexOfColor += 1 #смотрим следующий цвет
        if indexOfColor > len(colors) - 1: #проверка не вышли ли за границы массива
            indexOfColor = 0 #вышли, поэтому обнуляем
        myCanvas.create_rectangle( #рисуем
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    elif event. keysym == "Next": #PageDown нажата
        indexOfColor -= 1
        if indexOfColor < 0:
            indexOfColor = len(colors) - 1
        myCanvas.create_rectangle(
            x1, y1, x2, y2,
            outline=colors[indexOfColor], fill=colors[indexOfColor]
        )
    elif event.keysym == 'Escape': #нажата клавиша Esc
        root.quit()
root.bind("<Key>", change_rectangle) #поставили нашему кнопку прослушиватель на кнопки
myCanvas.create_rectangle( #изначально что-то рисуем
            x1, y1, x2, y2,
            outline=colors[0], fill=colors[0]
        )
root.mainloop()
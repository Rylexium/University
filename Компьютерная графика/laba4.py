from tkinter import *
import math, time
root = Tk()

root.title("laba4")
myCanvas = None
x1, y1, x2, y2, x3, y3 = 70, 200, 150, 400, 350, 500 #200, 100, 500, 200, 300, 70
points = [(x1, y1), (x2, y2), (x3, y3)] #точки треугольника
center_x, center_y = (x1+x2+x3)/3, (y1+y2+y3)/3 #центр треугольника
alpha = 35 * math.pi / 180 #стартовый угол поворота
fps = 120
def clickMe():
    global x1, y1, x2, y2, x3, y3, center_x, center_y, alpha, points
    try:
        x1, y1, x2, y2, x3, y3 = int(x1_text.get()), int(y1_text.get()), int(x2_text.get()), int(y2_text.get()), int(x3_text.get()), int(y3_text.get())
        points = [(x1, y1), (x2, y2), (x3, y3)] #новый список точек
        center_x, center_y = (x1 + x2 + x3) / 3, (y1 + y2 + y3) / 3 #новый центр треугольника
        myCanvas.delete("all") #очищаем канву
        myCanvas.create_polygon(points, fill='red')#рисуем снова
        alpha = float(rotate_text.get()) * math.pi / 180 #обновляем угол поворота
    except:
        pass


picture=Frame(root)
manage=Frame(root)

picture.pack(side=LEFT)
manage.pack(side=RIGHT)
myCanvas=Canvas(picture, width=800, height=600)
myCanvas.pack(fill=BOTH, expand=1)

Label(manage, text="Введите координаты треугольника").pack()
x1_text=StringVar()
Entry(manage, width=15, textvariable=x1_text).pack()

y1_text=StringVar()
Entry(manage, width=15, textvariable=y1_text).pack()

x2_text=StringVar()
Entry(manage, width=15, textvariable=x2_text).pack()

y2_text=StringVar()
Entry(manage, width=15, textvariable=y2_text).pack()

x3_text=StringVar()
Entry(manage, width=15, textvariable=x3_text).pack()

y3_text=StringVar()
Entry(manage, width=15, textvariable=y3_text).pack()

lb_x = Label(manage, text='Угловая скорость', width=20)
lb_x.pack()
rotate_text = Entry(manage, width = 12)
rotate_text.pack()
butDraw=Button(manage, text='Рисовать', width=12, command=clickMe)
butDraw.pack()


myCanvas.create_polygon(points, fill='red')

def rotateTriangle():
    global points, center_x, center_y, alpha
    new_points = []
    angle = alpha * math.pi / 180 #из радианов в углы переводим
    for x, y in points:
        new_x = center_x + (x - center_x) * math.cos(angle) - (y - center_y) * math.sin(angle)
        new_y = center_y + (x - center_x) * math.sin(angle) + (y - center_y) * math.cos(angle)
        new_points.append((new_x, new_y))
    points = new_points
    myCanvas.create_polygon(points, fill='red')

while True: #в бесконечном цикле смотрим
    try:
        rotateTriangle() #поворачиваем треугольник и рисуем
        picture.update() #обновляем канву
        time.sleep(1 / fps) #делаем небольшую задержку
        myCanvas.delete("all") #удаляем треугольник,чтобы нарисовать такой же с другим поворотом
    except:
        exit(1)
root.mainloop()
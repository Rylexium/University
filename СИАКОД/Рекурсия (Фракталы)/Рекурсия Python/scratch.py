import kivy
from kivy.app import App
from kivy.uix.widget import Widget
from kivy.graphics import *
from kivy.uix.button import Button
from kivy.uix.textinput import TextInput
from kivy.core.window import Window
from kivy.uix.label import Label
from cmath import(sin,cos)
kivy.require('1.11.1')

class PainterWidget(Widget):
    def __init__(self,**kwargs):
        super(PainterWidget,self).__init__(**kwargs)
        Window.clearcolor=(.8,.8,.8,1)
        self.x0=Window.size[0]/2
        self.y0=Window.size[1]/2
    def LineCoord(self,angle,radius):
        coord=[]
        angle%=360
        angle*=1
        if angle>0 and angle<180:
           coord.append((self.x0+(radius*sin(3.14*angle/180))).real)
           coord.append((self.y0-(radius*cos(3.14*angle/180))).real)
        else:
            coord.append(self.x0 - (radius * -sin(3.14 * angle / 180)).real)
            coord.append(self.y0 - (radius * cos(3.14 * angle / 180)).real)
        return coord

    def paint(self,d,count,alpha):
        if count :
            point = []
            phi=0
            for i in range(4):
                point.append(self.LineCoord(alpha+phi,d))
                phi+=90
            with self.canvas:
                Color(0,0,0)
                Line(points=(point[0][0],point[0][1],point[1][0],point[1][1],point[2][0],point[2][1],point[3][0],point[3][1]),close=True,width=1)
                d += (alpha) / 10
                self.paint(d,count-1,alpha+4)


class PaintApp(App):
    def build(self):
        parent=Widget()
        self.painter=PainterWidget()
        parent.add_widget(self.painter)
        parent.add_widget(Label(text='Введите глубину рекурсии ',size=[80,30],pos=[Window.size[0]-140,Window.size[1]-30]))
        self.number = TextInput(font_size=15, multiline=False, halign="right", size=[60, 30],pos=[Window.size[0] - 63, Window.size[1] - 60])
        parent.add_widget(self.number)
        parent.add_widget(Button(text="Clear",on_press=self.clear_canvas,size=[100,50],pos=[Window.size[0]-100,Window.size[1]-110]))
        parent.add_widget((Button(text='Draw',on_press=self.Draw,size=[100,50],pos=[Window.size[0]-100,Window.size[1]-160])))
        return parent
    
    def clear_canvas(self,instance):
        self.painter.canvas.clear()


    def Draw(self,instance):
        if ''.join(self.number.text).isdigit() and self.number.text!='':
            if int(self.number.text)<100:
                self.painter.canvas.clear()
                self.painter.paint(10,int(self.number.text),10)

if __name__=="__main__":
    PaintApp().run()
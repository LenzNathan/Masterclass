from kivymd.uix.screen import MDScreen
from kivy.core.window import Window
from kivymd.app import MDApp
from kivymd.uix.label import MDLabel
from kivymd.uix.button import MDRaisedButton
from kivy.lang import Builder
from kivy.uix.floatlayout import FloatLayout
from kivymd.uix.textfield import MDTextField
from kivy.graphics import Color, Rectangle
import requests

class Workscreen(MDScreen):

    def __init__(self,jsonUser, **kwargs):
        super().__init__(**kwargs)
        root = FloatLayout()
        with root.canvas.before:
            Color(0.2, 0.6, 0.2, 0.5)
            self.rect = Rectangle(size=root.size, pos=root.pos)

        label = MDLabel(
            text="Hallo "+str(jsonUser['name']),
            pos_hint={"center_x": 0.9, "center_y": 0.5}
        )
        root.add_widget(label)

        root.bind(size=self.update_rect, pos=self.update_rect)
        self.add_widget(root)




    def update_rect(self, instance, value):
        self.rect.size = instance.size
        self.rect.pos = instance.pos


if __name__ == "__main__":
    Workscreen.run()

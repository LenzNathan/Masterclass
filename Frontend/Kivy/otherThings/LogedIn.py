from kivymd.uix.screen import MDScreen
from kivy.core.window import Window
from kivymd.app import MDApp
from kivymd.uix.label import MDLabel
from kivymd.uix.button import MDRaisedButton
from kivy.lang import Builder
from kivy.uix.floatlayout import FloatLayout
from kivymd.uix.textfield import MDTextField
from kivy.graphics import *
from kivy.uix.image import Image

class LogedIn(MDScreen):

    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        root = FloatLayout()
        with root.canvas.before:
            Color(0.2, 0.6, 0.2, 0.5)
            self.rect = Rectangle(size=root.size, pos=root.pos)

        root.bind(size=self.update_rect, pos=self.update_rect)
        self.add_widget(root)

        welcomeLabel = MDLabel(
            #text="Hello: "+user.get_username(),
            text="Welcome to Kivy!",
            text_color=(0, 0, 0.5),
            pos_hint={"x": 0.05, "center_y": 0.9}
        )
        # add to layout
        root.add_widget(welcomeLabel)

    def update_rect(self, instance, value):
        # background size to the whole root size
        self.rect.size = instance.size
        self.rect.pos = instance.pos

    def getToken(self):
        url = ""
        headers ={""}
        data ={
            
        }
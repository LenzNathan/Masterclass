from importlib.metadata import pass_none
from kivy.modules.recorder import on_recorder_key

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
from pygments.styles.dracula import background

class LogedIn(MDScreen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        root = FloatLayout()
        with root.canvas.before:
            Color(0.2, 0.6, 0.2, 0.5)
            self.rect = Rectangle(size=root.size, pos=root.pos)

        root.bind(size=self.update_rect, pos=self.update_rect)
        self.add_widget(root)

    def update_rect(self, instance, value):
        # background size to the whole root size
        self.rect.size = instance.size
        self.rect.pos = instance.pos
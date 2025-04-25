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

class Raum(MDScreen):

    #basic url
    url=""
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        root = FloatLayout()
        with root.canvas.before:
            Color(0.2, 0.6, 0.2, 0.5)
            self.rect = Rectangle(size=root.size, pos=root.pos)

        root.bind(size=self.update_rect, pos=self.update_rect)
        self.add_widget(root)

        self.buildingInput = MDTextField(
            mode="fill",
            hint_text="Gebaeudename",
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.15, "center_y": 0.9},
            multiline=False
        )
        self.buildingInput.focus = True
        root.add_widget(self.buildingInput)

        self.kuerzel = MDTextField(
            mode="fill",
            hint_text="Kuerzel",
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.15, "center_y": 0.8},
            multiline=False
        )
        self.kuerzel.focus = True
        root.add_widget(self.kuerzel)

        self.raumID = MDTextField(
            mode="fill",
            hint_text="raumID",
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.15, "center_y": 0.7},
            disabled=True,
            multiline=False
        )
        self.raumID.focus = True
        root.add_widget(self.raumID)

        self.output = MDLabel(
            text="",
            disabled=True,
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.4, "center_y": 0.6}
        )
        root.add_widget(self.output)

        self.button = MDRaisedButton(
            text="ENTER",
            pos_hint={"x": 0.15, "center_y": 0.6},
            size_hint_x=None,
            width="120dp",
            on_release=self.enter
        )
        self.button.focus = True
        root.add_widget(self.button)

    def enter(self, instance):
        gebName = self.buildingInput.text
        kuerzel = self.kuerzel.text

        if gebName != "" and kuerzel != "":

            try:
                payload = {"kuerzel": kuerzel}
                #response = requests.get(url, params=payload).json()
                response = {"name": "gebName", "payload": 213, "id": 1}
                self.output.text = ""
                self.raumID.text = str(response["id"])
            except:
                self.output.text = "An error occurred while getting data from the API"

        else:
            self.output.text = "Type data :O"
            self.output.focus = True

    def update_rect(self, instance, value):
        self.rect.size = instance.size
        self.rect.pos = instance.pos



if __name__ == "__main__":
    Raum.run()

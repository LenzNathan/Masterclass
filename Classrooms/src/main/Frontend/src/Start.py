from importlib.metadata import pass_none
from kivy.modules.recorder import on_recorder_key

from kivy.uix.screenmanager import ScreenManager, Screen
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

class Start(MDScreen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        # root layout
        root = FloatLayout()

        # background
        with root.canvas.before:
            Color(0.2, 0.6, 0.2, 0.5)
            self.rect = Rectangle(size=root.size, pos=root.pos)

        root.bind(size=self.update_rect, pos=self.update_rect)

        # label
        labelUser = MDLabel(
            text="Username: ",
            text_color=(0, 0, 0.5),
            pos_hint={"x": 0.05, "center_y": 0.9}
        )
        # add to layout
        root.add_widget(labelUser)

        labelPw = MDLabel(
            text="Password: ",
            text_color=(0, 0, 0.5),
            pos_hint={"x": 0.05, "center_y": 0.8}
        )
        root.add_widget(labelPw)

        # Button
        self.loginBt = MDRaisedButton(
            text="Safe",
            size_hint_x=None,
            width="100dp",
            pos_hint={"x": 0.05, "center_y": 0.7},
            on_release=self.clickLogin,
            md_bg_color=[0.2, 0.6, 0.4]
        )
        root.add_widget(self.loginBt)

        self.registBt = MDRaisedButton(
            text="Registered",
            size_hint_x=None,
            width="100dp",
            pos_hint={"x": 0.2, "center_y": 0.7},
            on_release=self.clickRegister,
            md_bg_color=[0.2, 0.6, 0.4]
        )
        root.add_widget(self.registBt)

        # textfield
        self.userInput = MDTextField(
            mode="fill",
            hint_text="username",
            size_hint_x=None,  # to controll parameter such as width
            width="240dp",
            pos_hint={"x": 0.2, "center_y": 0.9},
            multiline=False  # no multiple lines
        )
        self.userInput.focus = True
        root.add_widget(self.userInput)

        self.pwInput = MDTextField(
            mode="fill",
            hint_text="password",
            password=True,
            size_hint_x=None,
            width="240dp",
            pos_hint={"x": 0.2, "center_y": 0.8},
            multiline=False,  # Textfield response to ENTER key
        )
        self.pwInput.bind(on_text_validate=self.clickLogin)
        root.add_widget(self.pwInput)

        Window.bind(on_key_down=self.key_pressed)

        self.output = MDLabel(
            text="",
            size_hint_x=None, width="400dp",
            pos_hint={"x": 0.05, "center_y": 0.6}
        )
        root.add_widget(self.output)

        self.add_widget(root) #add whole layout to object
        #return root

    def update_rect(self, instance, value):
        # background size to the whole root size
        self.rect.size = instance.size
        self.rect.pos = instance.pos

    def clickLogin(self, instance):
        # button click
        username = self.userInput.text
        password = self.pwInput.text
        if username == "" or password == "":
            self.output.text = "Login Failed"
            self.userInput.text = ""  # reset
            self.pwInput.text = ""
        else:
            #self.root.clear_widgets()
            print(username)
            print(password)
            self.manager.current = "login"  # Switch to login
            #self.view2Login(username)

    '''def clickLogout(self, instance):
        self.root.clear_widgets()
        exit()'''

    def clickRegister(self, instance):
        #self.root.clear_widgets()
        self.manager.current = "register"  # Switch to register
        #self.viewRegistration()

    def key_pressed(self, window, key, scancode, codepoint, modifier):
        # key events
        if key == 9:  # Tab
            self.next_field()
            return True
        elif key == 13:  # Enter
            Window.unbind(on_key_down=self.key_pressed)
            self.clickLogin

    def next_field(self):
        #jumps to next text field
        if self.userInput.focus:  # if first is in focus set 2nd in focus
            self.pwInput.focus = True




if __name__ == "__main__":
    Start().run()

from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.gridlayout import GridLayout
from kivy.uix.button import Button
from kivy.uix.image import Image
from kivy.uix.label import Label
from kivy.uix.screenmanager import ScreenManager, Screen
import webbrowser
from kivy.core.window import Window
from kivy.graphics import Color, RoundedRectangle
from DatenView import DatenView, DataDetailView

Window.maximize()

class HomeView(Screen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        layout = BoxLayout()
        layout.add_widget(Label(text="🏠 Willkommen auf der Startseite"))
        self.add_widget(layout)

class StundenplanView(Screen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        layout = BoxLayout()
        layout.add_widget(Label(text="📅 Stundenplan-Verwaltung"))
        self.add_widget(layout)



# Eigene Button-Klasse für Navigation
class NavButton(Button):
    def __init__(self, text, target_screen, app, color, **kwargs):
        super().__init__(**kwargs)
        self.text = text
        self.target_screen = target_screen
        self.app = app
        self.font_size = 20
        self.bold = True
        self.size_hint_y = None
        self.height = 80
        self.size_hint_x = 1
        self.background_normal = ''
        self.background_color = (0, 0, 0, 0)  # transparent background
        self.color = (1,1,1,1)  # white text

        # Farbverlauf (hier nur einfarbig) als abgerundetes Rechteck
        with self.canvas.before:
            Color(*color)
            self.bg_rect = RoundedRectangle(radius=[10], size=self.size, pos=self.pos)

        self.bind(pos=self.update_bg, size=self.update_bg)
        self.bind(on_release=self.on_click)

    def update_bg(self, *args):
        self.bg_rect.pos = self.pos
        self.bg_rect.size = self.size

    def on_click(self, *args):
        if self.target_screen:
            self.app.switch_view(self.target_screen)
        else:
            print(f"Button '{self.text}' hat keine Ziel-View.")

class MainApp(App):
    def build(self):
        root_layout = BoxLayout(orientation='vertical')

        # Weißer Hintergrund für die gesamte App
        with root_layout.canvas.before:
            Color(1, 1, 1, 1)  # Weiß
            self.bg_rect = None

        def update_bg(instance, value):
            if self.bg_rect:
                self.bg_rect.pos = instance.pos
                self.bg_rect.size = instance.size

        root_layout.bind(size=update_bg, pos=update_bg)

        self.screen_manager = ScreenManager()
        self.screen_manager.add_widget(HomeView(name='home'))
        self.screen_manager.add_widget(StundenplanView(name='stundenplan'))
        self.screen_manager.add_widget(DatenView(app= self,name='daten'))

        logo_colors = [
            (0.83, 0.48, 0.64, 1),  # #d47ba2
            (0.94, 0.52, 0.39, 1),  # #f08664
            (0.67, 0.81, 0.52, 1),  # #accf84
            (0.51, 0.55, 0.77, 1),  # #818dc5
            (0.91, 0.80, 0.34, 1),  # #e9cb56
            (0.15, 0.30, 0.61, 1),  # #254c9b
        ]

        logo_height = 80

        button_bar = GridLayout(cols=6, size_hint_y=None, height=logo_height)

        logo_container = BoxLayout(size_hint_x=2, size_hint_y=None, height=logo_height, padding=5)
        logo = Image(source="images/htl-logo.png", allow_stretch=True, keep_ratio=True)
        logo_container.add_widget(logo)
        button_bar.add_widget(logo_container)

        # Erstelle NavButtons mit Ziel-Screens
        buttons = [
            ("Stundenplan verwalten", 'stundenplan', logo_colors[0]),
            ("Daten verwalten", 'daten', logo_colors[1]),
            ("Konten verwalten", None, logo_colors[2]),
            ("eigenen Account verwalten", None, logo_colors[4]),
        ]

        for text, target, color in buttons:
            if text == "eigenen Account verwalten":
                # Spezieller Button mit Web-Link
                btn = NavButton(text=text, target_screen=None, app=self, color=color)
                btn.bind(on_release=lambda x: webbrowser.open("http://localhost:8180/realms/stundenplanApp/account"))
            elif target is None:
                btn = NavButton(text=text, target_screen=None, app=self, color=color)
                btn.bind(on_release=lambda x: print("Noch nicht implementiert."))
            else:
                btn = NavButton(text=text, target_screen=target, app=self, color=color)
            button_bar.add_widget(btn)

        root_layout.add_widget(button_bar)
        root_layout.add_widget(self.screen_manager)

        self.screen_manager.current = 'home'
        return root_layout

    def switch_view(self, view_name):
        self.screen_manager.current = view_name

MainApp().run()

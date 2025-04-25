import subprocess
from flask import Flask, request, render_template_string
import requests

app = Flask(__name__)

KEYCLOAK_TOKEN_URL = "http://localhost:8180/realms/stundenplanApp/protocol/openid-connect/token"
CLIENT_ID = "kivyClient"
CLIENT_SECRET = "zQmolcGt78XHLejZjkVn3DXXUDvgkS34"
REDIRECT_URI = "http://localhost:5001/authenticationKey"

#endpoint um den authentizierungskey zu bekommen
@app.route('/authenticationKey')
def getLoginDetails():
    code = request.args.get('code')
    print(code)
    if code:
        data = {
            'grant_type': 'authorization_code',
            'code': code,
            'redirect_uri': REDIRECT_URI,
            'client_id': CLIENT_ID,
            'client_secret': CLIENT_SECRET
        }
        #API-Abfrage Keycloak
        response = requests.post(KEYCLOAK_TOKEN_URL, data=data)
        token_data = response.json()
        # Hier kannst du das Access Token speichern und es in Kivy verwenden
        access_token = token_data.get('access_token')
        if(access_token!=None):
            #wird als subprozess ausgeführt, dass flask-server weiterhin läuft
            subprocess.Popen(["python3", "Main.py", access_token])
            return render_template_string("""<html><body><p>Login erfolgreich. Du kannst das Fenster schließen.</p><script>setTimeout(() => {window.close(); }, 10000);</script></body></html>""")
        return "No authorisation code received"
    return "Error: No code from keycloak received"

if __name__ == '__main__':
    app.run(debug=True, port=5001)
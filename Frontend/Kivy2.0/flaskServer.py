from app import Flask

app = Flask(__name__)

@app.route('/')
def getLoginDetails():
    print()

if __name__ == '__main__':
    app.run(debug=True, port=5001)
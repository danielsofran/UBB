from flask import Flask
from Persistance.filerepository import FileRepository
from Domain.reading import Reading

repo = FileRepository("readings.txt", Reading.line_to_reading, Reading.reading_to_line)


app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    print(repo)
    return f"<p>{repo}</p>"

@app.route("/readings")
def readings():
    return f"{repo}"


if __name__ == '__main__':
    app.run()

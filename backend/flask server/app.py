from flask import Flask
from flask import request
from flask.json import jsonify
import google.generativeai as genai
import os
import dotenv


dotenv.load_dotenv()
genai.configure(api_key=os.getenv("GOOGLE_KEY"))

model = genai.GenerativeModel('gemini-pro')

app = Flask (__name__)


@app.route("/",methods=['GET'])
def hello_world():
    return "Hello World"

<<<<<<< HEAD
@app.route("/getjson",methods=['POST'])
=======
@app.route("/ask_ai",methods=['POST'])
>>>>>>> a25e9a5d3130c8c0a3231f9697bdad3c6e7f1f94
def generate():
    data = request.get_json()
    text = data['text']
    prompt = data['prompt'] 
    response = model.generate_content(text + prompt)
    cleaned_response = (str(response.text).replace('**',''))
    return cleaned_response




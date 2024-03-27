from flask import Flask
from flask import request
from flask.json import jsonify
import google.generativeai as genai
import os
import dotenv
import json


dotenv.load_dotenv()
genai.configure(api_key=os.getenv("GOOGLE_KEY"))

model = genai.GenerativeModel('gemini-pro')

app = Flask (__name__)


@app.route("/",methods=['GET'])
def hello_world():
    return "Hello World"

@app.route("/",methods=['POST'])
def generate():
    data = request.get_json()
    text = data['text']
    prompt = data['prompt'] 
    response = model.generate_content(text + prompt)
    return str(response.text)




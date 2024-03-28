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

@app.route("/ask_ai",methods=['POST'])
def generate():
    data = request.get_json()
    text = data['text']
    prompt = data['prompt'] 
    response = model.generate_content(text + prompt)
    cleaned_response = (str(response.text).replace('**',''))
    return cleaned_response



